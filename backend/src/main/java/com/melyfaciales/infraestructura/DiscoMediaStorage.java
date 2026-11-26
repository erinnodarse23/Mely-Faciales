package com.melyfaciales.infraestructura;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscoMediaStorage implements MediaStorage {

    public static final int MAX_FOTO = 5 * 1024 * 1024;
    public static final int MAX_VIDEO = 20 * 1024 * 1024;

    private static final String FOTOS = "fotos";
    private static final String VIDEOS = "videos";
    private static final Set<String> EXTENSIONES_FOTO = Set.of(".jpg", ".jpeg", ".png");
    private static final Set<String> EXTENSIONES_VIDEO = Set.of(".mp4", ".webm");
    private static final byte[] CABECERA_JPEG = {(byte) 0xFF, (byte) 0xD8, (byte) 0xFF};
    private static final byte[] CABECERA_PNG = {
            (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A
    };

    private final Path directorio;

    public DiscoMediaStorage(@Value("${mely.media.dir}") String directorio) {
        this.directorio = Path.of(directorio).toAbsolutePath().normalize();
    }

    @Override
    public String guardarFoto(byte[] contenido, String nombreArchivo) {
        validar(contenido, nombreArchivo, true);
        return guardar(contenido, nombreArchivo, FOTOS);
    }

    @Override
    public String guardarVideo(byte[] contenido, String nombreArchivo) {
        validar(contenido, nombreArchivo, false);
        return guardar(contenido, nombreArchivo, VIDEOS);
    }

    private void validar(byte[] contenido, String nombreArchivo, boolean foto) {
        if (contenido == null || contenido.length == 0) {
            throw new MediaInvalidoException("El contenido del medio no puede estar vacío");
        }
        String nombre = nombreArchivo == null ? "" : nombreArchivo;
        int punto = nombre.lastIndexOf('.');
        String extension = punto < 0 ? "" : nombre.substring(punto);
        if (!extension.equals(extension.toLowerCase(Locale.ROOT))) {
            throw new MediaInvalidoException("La extensión del medio debe estar en minúsculas");
        }
        Set<String> permitidas = foto ? EXTENSIONES_FOTO : EXTENSIONES_VIDEO;
        if (!permitidas.contains(extension)) {
            throw new MediaInvalidoException(foto
                    ? "La extensión de la foto no es permitida"
                    : "La extensión del video no es permitida");
        }
        int maximo = foto ? MAX_FOTO : MAX_VIDEO;
        if (contenido.length > maximo) {
            throw new MediaInvalidoException(foto
                    ? "La foto supera el tamaño máximo de 5 MB"
                    : "El video supera el tamaño máximo de 20 MB");
        }
        if (foto && !tieneCabecera(contenido, extension)) {
            throw new MediaInvalidoException("La cabecera de la foto no es válida");
        }
    }

    private boolean tieneCabecera(byte[] contenido, String extension) {
        byte[] esperada = extension.equals(".png") ? CABECERA_PNG : CABECERA_JPEG;
        if (contenido.length < esperada.length) {
            return false;
        }
        for (int i = 0; i < esperada.length; i++) {
            if (contenido[i] != esperada[i]) {
                return false;
            }
        }
        return true;
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
