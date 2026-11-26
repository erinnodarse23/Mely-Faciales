package com.melyfaciales.dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "publicaciones")
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servicio_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_publicaciones_servicio"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Servicio servicio;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "tipo", length = 20, nullable = false)
    private TipoPublicacion tipo;

    @Column(name = "url", columnDefinition = "TEXT", nullable = false)
    private String url;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    protected Publicacion() {
    }

    public Publicacion(Servicio servicio, TipoPublicacion tipo, String url, String descripcion) {
        this.servicio = servicio;
        this.tipo = tipo;
        this.url = url;
        this.descripcion = descripcion;
    }

    public void actualizarContenido(String url, String descripcion) {
        if (url == null || url.isBlank()) {
            throw new IllegalArgumentException("La URL de la publicación no puede estar vacía");
        }
        this.url = url;
        this.descripcion = descripcion;
    }

    public UUID getId() {
        return id;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public TipoPublicacion getTipo() {
        return tipo;
    }

    public String getUrl() {
        return url;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
