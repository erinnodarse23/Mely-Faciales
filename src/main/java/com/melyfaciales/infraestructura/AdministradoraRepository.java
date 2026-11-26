package com.melyfaciales.infraestructura;

import com.melyfaciales.dominio.Administradora;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradoraRepository extends JpaRepository<Administradora, UUID> {

    Optional<Administradora> findByUsuario(String usuario);
}
