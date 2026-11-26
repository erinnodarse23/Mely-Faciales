package com.melyfaciales.infraestructura;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiscoMediaStorageTest {

    @TempDir
    Path temporal;

    private DiscoMediaStorage storage;

    @BeforeEach
    void setUp() {
        storage = new DiscoMediaStorage(temporal.toString());
    }

    @Test
    void guardarFotoCreatesRelativeUrlAndFile() throws IOException {
        byte[] contenido = "foto".getBytes(StandardCharsets.UTF_8);

        String url = storage.guardarFoto(contenido, "foto.jpg");
        Path archivo = temporal.resolve(url);

        assertTrue(url.startsWith("fotos/"));
        assertFalse(Path.of(url).isAbsolute());
        assertFalse(url.contains(".."));
        assertTrue(Files.exists(archivo));
        assertArrayEquals(contenido, Files.readAllBytes(archivo));
    }

    @Test
    void guardarVideoCreatesRelativeUrlAndFile() throws IOException {
        byte[] contenido = new byte[]{1, 2, 3, 4};

        String url = storage.guardarVideo(contenido, "clip.mp4");
        Path archivo = temporal.resolve(url);

        assertTrue(url.startsWith("videos/"));
        assertFalse(Path.of(url).isAbsolute());
        assertFalse(url.contains(".."));
        assertTrue(Files.exists(archivo));
        assertArrayEquals(contenido, Files.readAllBytes(archivo));
    }

    @Test
    void eliminarRemovesStoredFile() {
        String url = storage.guardarFoto(new byte[]{1, 2, 3}, "borrar.png");
        Path archivo = temporal.resolve(url);
        assertTrue(Files.exists(archivo));

        storage.eliminar(url);

        assertFalse(Files.exists(archivo));
    }

    @Test
    void guardarFotoSanitizesTraversalName() {
        String url = storage.guardarFoto(new byte[]{1}, "../malicioso.png");
        String nombre = Path.of(url).getFileName().toString();
        Path archivo = temporal.resolve(url).normalize();

        assertFalse(url.contains(".."));
        assertTrue(nombre.matches("[A-Za-z0-9_-]+"));
        assertTrue(archivo.startsWith(temporal.toAbsolutePath().normalize()));
        assertTrue(Files.exists(archivo));
    }
}
