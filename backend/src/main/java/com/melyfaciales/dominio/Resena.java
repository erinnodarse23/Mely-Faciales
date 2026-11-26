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
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "resenas")
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "servicio_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_resenas_servicio"))
    private Servicio servicio;

    @Column(name = "nombre_clienta", length = 80, nullable = false)
    private String nombreClienta;

    @Column(name = "valoracion", nullable = false,
            columnDefinition = "INTEGER CHECK (valoracion BETWEEN 1 AND 5)")
    private int valoracion;

    @Column(name = "comentario", columnDefinition = "TEXT", nullable = false)
    private String comentario;

    @Column(name = "foto_url", columnDefinition = "TEXT")
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "estado", length = 20, nullable = false,
            columnDefinition = "VARCHAR(20) CHECK (estado = 'PUBLICADA')")
    private EstadoResena estado;

    protected Resena() {
    }

    public Resena(Servicio servicio, String nombreClienta, int valoracion, String comentario,
                  String fotoUrl, EstadoResena estado) {
        this.servicio = servicio;
        this.nombreClienta = nombreClienta;
        this.valoracion = valoracion;
        this.comentario = comentario;
        this.fotoUrl = fotoUrl;
        this.estado = estado;
    }

    public void publicar(String fotoUrl) {
        this.fotoUrl = fotoUrl;
        this.estado = EstadoResena.PUBLICADA;
    }

    public UUID getId() {
        return id;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public String getNombreClienta() {
        return nombreClienta;
    }

    public int getValoracion() {
        return valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public EstadoResena getEstado() {
        return estado;
    }
}
