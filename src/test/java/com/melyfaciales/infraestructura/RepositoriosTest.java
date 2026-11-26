package com.melyfaciales.infraestructura;

import com.melyfaciales.dominio.Administradora;
import com.melyfaciales.dominio.EstadoResena;
import com.melyfaciales.dominio.Publicacion;
import com.melyfaciales.dominio.Resena;
import com.melyfaciales.dominio.Rol;
import com.melyfaciales.dominio.Servicio;
import com.melyfaciales.dominio.TipoPublicacion;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriosTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ServicioRepository servicios;

    @Autowired
    private PublicacionRepository publicaciones;

    @Autowired
    private ResenaRepository resenas;

    @Autowired
    private AdministradoraRepository administradoras;

    private Servicio servicio(String nombre, boolean activo) {
        return em.persistAndFlush(new Servicio(nombre, "Descripcion", null, activo));
    }

    @Test
    void saveAndFindServicioGeneratesUuid() {
        Servicio guardado = servicio("Limpieza facial", true);
        em.clear();

        Optional<Servicio> encontrado = servicios.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertNotNull(encontrado.orElseThrow().getId());
        assertEquals("Limpieza facial", encontrado.orElseThrow().getNombre());
        assertTrue(encontrado.orElseThrow().isActivo());
    }

    @Test
    void findByActivoTrueReturnsOnlyActiveServicios() {
        servicio("Activo uno", true);
        servicio("Activo dos", true);
        servicio("Inactivo", false);
        em.flush();
        em.clear();

        List<Servicio> activos = servicios.findByActivoTrue();

        assertEquals(2, activos.size());
        assertTrue(activos.stream().allMatch(Servicio::isActivo));
    }

    @Test
    void deletingServicioCascadesPublicaciones() {
        Servicio servicio = servicio("Facial", true);
        Publicacion publicacion = publicaciones.saveAndFlush(
                new Publicacion(servicio, TipoPublicacion.VIDEO, "videos/clip", "Detalle"));
        UUID idPublicacion = publicacion.getId();
        em.clear();

        servicios.deleteById(servicio.getId());
        servicios.flush();
        em.clear();

        assertTrue(publicaciones.findById(idPublicacion).isEmpty());
    }

    @Test
    void valoracionOneAndFiveAreAccepted() {
        Servicio servicio = servicio("Facial", true);

        Resena unaEstrella = resenas.saveAndFlush(
                new Resena(servicio, "Clienta A", 1, "Comentario", null, EstadoResena.PUBLICADA));
        Resena cincoEstrellas = resenas.saveAndFlush(
                new Resena(servicio, "Clienta B", 5, "Comentario", null, EstadoResena.PUBLICADA));

        assertEquals(1, unaEstrella.getValoracion());
        assertEquals(5, cincoEstrellas.getValoracion());
    }

    @Test
    void valoracionZeroIsRejected() {
        Servicio servicio = servicio("Facial", true);
        Resena resena = new Resena(servicio, "Clienta", 0, "Comentario", null, EstadoResena.PUBLICADA);

        assertThrows(DataIntegrityViolationException.class, () -> resenas.saveAndFlush(resena));
    }

    @Test
    void valoracionSixIsRejected() {
        Servicio servicio = servicio("Facial", true);
        Resena resena = new Resena(servicio, "Clienta", 6, "Comentario", null, EstadoResena.PUBLICADA);

        assertThrows(DataIntegrityViolationException.class, () -> resenas.saveAndFlush(resena));
    }

    @Test
    void estadoDifferentFromPublicadaIsRejected() {
        Servicio servicio = servicio("Facial", true);

        assertThrows(DataIntegrityViolationException.class, () -> jdbcTemplate.update(
                "insert into resenas (id, servicio_id, nombre_clienta, valoracion, comentario, estado) "
                        + "values (?, ?, 'Clienta', 4, 'Comentario', 'PENDIENTE')",
                UUID.randomUUID(), servicio.getId()));
    }

    @Test
    void resenaWithMissingServicioIsRejected() {
        assertThrows(DataIntegrityViolationException.class, () -> jdbcTemplate.update(
                "insert into resenas (id, servicio_id, nombre_clienta, valoracion, comentario, estado) "
                        + "values (?, ?, 'Clienta', 4, 'Comentario', 'PUBLICADA')",
                UUID.randomUUID(), UUID.randomUUID()));
    }

    @Test
    void duplicateUsuarioIsRejected() {
        administradoras.saveAndFlush(new Administradora("mely", "Mely", Rol.ADMIN));
        Administradora repetida = new Administradora("mely", "Otra", Rol.ADMIN);

        assertThrows(DataIntegrityViolationException.class, () -> administradoras.saveAndFlush(repetida));
    }

    @Test
    void findByUsuarioAndFindByEstadoOrderByIdDescWork() {
        Servicio servicio = servicio("Facial", true);
        resenas.saveAndFlush(new Resena(servicio, "A", 3, "c", null, EstadoResena.PUBLICADA));
        resenas.saveAndFlush(new Resena(servicio, "B", 4, "c", null, EstadoResena.PUBLICADA));
        resenas.saveAndFlush(new Resena(servicio, "C", 5, "c", null, EstadoResena.PUBLICADA));
        administradoras.saveAndFlush(new Administradora("mely", "Mely", Rol.ADMIN));
        em.flush();
        em.clear();

        assertTrue(administradoras.findByUsuario("mely").isPresent());
        assertEquals("Mely", administradoras.findByUsuario("mely").orElseThrow().getNombre());
        assertTrue(administradoras.findByUsuario("inexistente").isEmpty());

        List<UUID> esperados = jdbcTemplate.queryForList(
                "select cast(id as varchar) from resenas where estado = 'PUBLICADA' order by id desc",
                String.class).stream().map(UUID::fromString).toList();
        List<UUID> actuales = resenas.findByEstadoOrderByIdDesc(EstadoResena.PUBLICADA).stream()
                .map(Resena::getId)
                .toList();

        assertEquals(3, actuales.size());
        assertEquals(esperados, actuales);
    }
}
