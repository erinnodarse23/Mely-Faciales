package com.melyfaciales.infraestructura;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscoMediaStorage implements MediaStorage {

    private static final String FOTOS = "fotos";
    private static final String VIDEOS = "videos";

    private final Path directorio;

    public DiscoMediaStorage(@Value("${mely.media.dir}") String directorio) {
        this.directorio = Path.of(directorio).toAbsolutePath().normalize();
    }

    @Override
    public String guardarFoto(byte[] contenido, String nombreArchivo) {
        return guardar(contenido, nombreArchivo, FOTOS);
    }

    @Override
    public String guardarVideo(byte[] contenido, String nombreArchivo) {
        return guardar(contenido, nombreArchivo, VIDEOS);
    }

    private String guardar(byte[] contenido, String nombreArchivo, String subcarpeta) {
        try {
            Path destinoCarpeta = directorio.resolve(subcarpeta);
            Files.createDirectories(destinoCarpeta);
            String nombre = UUID.randomUUID() + "_" + sanitar(nombreArchivo);
            Files.write(destinoCarpeta.resolve(nombre), contenido);
            return subcarpeta + "/" + nombre;
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo guardar el medio", e);
        }
    }

    private String sanitar(String nombreArchivo) {
        String base = nombreArchivo == null ? "" : nombreArchivo;
        String nombre = base.replaceAll("[^A-Za-z0-9_-]", "_");
        return nombre.isEmpty() ? "archivo" : nombre;
    }

    @Override
    public void eliminar(String url) {
        if (url == null || url.isBlank()) {
            return;
        }
        Path objetivo = directorio.resolve(url.replace('\\', '/')).normalize();
        if (!objetivo.startsWith(directorio)) {
            return;
        }
        try {
            Files.deleteIfExists(objetivo);
        } catch (IOException e) {
            throw new UncheckedIOException("No se pudo eliminar el medio", e);
        }
    }
}
