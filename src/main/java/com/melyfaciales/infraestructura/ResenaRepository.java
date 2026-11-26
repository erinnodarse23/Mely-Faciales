package com.melyfaciales.infraestructura;

import com.melyfaciales.dominio.EstadoResena;
import com.melyfaciales.dominio.Resena;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResenaRepository extends JpaRepository<Resena, UUID> {

    List<Resena> findByEstadoOrderByIdDesc(EstadoResena estado);
}
