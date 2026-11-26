package com.melyfaciales.infraestructura;

import com.melyfaciales.dominio.Servicio;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicioRepository extends JpaRepository<Servicio, UUID> {

    List<Servicio> findByActivoTrue();
}
