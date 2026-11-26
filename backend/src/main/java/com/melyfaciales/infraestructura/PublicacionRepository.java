package com.melyfaciales.infraestructura;

import com.melyfaciales.dominio.Publicacion;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, UUID> {

    List<Publicacion> findByServicioId(UUID servicioId);
}
