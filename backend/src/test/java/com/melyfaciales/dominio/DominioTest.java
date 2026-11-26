package com.melyfaciales.dominio;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DominioTest {

    private Servicio servicio() {
        return new Servicio("Limpieza facial", "Descripción anterior", "fotos/antigua.jpg", true);
    }

    @Test
    void publicarAsignaFotoYEstado() {
        Resena resena = new Resena(servicio(), "Clienta", 5, "Excelente", null, null);

        resena.publicar("fotos/resena.jpg");

        assertEquals("fotos/resena.jpg", resena.getFotoUrl());
        assertEquals(EstadoResena.PUBLICADA, resena.getEstado());
    }

    @Test
    void publicarSinFotoDejaFotoNulaYPublica() {
        Resena resena = new Resena(servicio(), "Clienta", 4, "Muy bien", "fotos/vieja.jpg", null);

        resena.publicar(null);

        assertNull(resena.getFotoUrl());
        assertEquals(EstadoResena.PUBLICADA, resena.getEstado());
    }

    @Test
    void actualizarContenidoServicioReemplazaValores() {
        Servicio servicio = servicio();

        servicio.actualizarContenido("Nueva descripción", "fotos/nueva.jpg", false);

        assertEquals("Nueva descripción", servicio.getDescripcion());
        assertEquals("fotos/nueva.jpg", servicio.getImagenUrl());
        assertFalse(servicio.isActivo());
    }

    @Test
    void actualizarContenidoPublicacionValidaUrl() {
        Publicacion publicacion = new Publicacion(servicio(), TipoPublicacion.VIDEO,
                "videos/antiguo.mp4", "Descripción antigua");

        publicacion.actualizarContenido("videos/nuevo.mp4", "Descripción nueva");

        assertEquals("videos/nuevo.mp4", publicacion.getUrl());
        assertEquals("Descripción nueva", publicacion.getDescripcion());
        assertThrows(IllegalArgumentException.class,
                () -> publicacion.actualizarContenido("  ", "Sin URL"));
    }
}
