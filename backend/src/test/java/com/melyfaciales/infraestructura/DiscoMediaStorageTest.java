package com.melyfaciales.infraestructura;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiscoMediaStorageTest {

    private static final byte[] JPEG = {
            (byte) 0xFF, (byte) 0xD8, (byte) 0xFF, 0x00, 0x01
    };

    private static final byte[] PNG = {
            (byte) 0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A, 0x00, 0x01
    };

    @TempDir
    Path temporal;

    private DiscoMediaStorage storage;

    @BeforeEach
    void setUp() {
        storage = new DiscoMediaStorage(temporal.toString());
    }

    @Test
    void guardarFotoCreatesRelativeUrlAndFile() throws IOException {
        String url = storage.guardarFoto(JPEG, "foto.jpg");
        Path archivo = temporal.resolve(url);

        assertTrue(url.startsWith("fotos/"));
        assertFalse(Path.of(url).isAbsolute());
        assertFalse(url.contains(".."));
        assertTrue(Files.exists(archivo));
        assertArrayEquals(JPEG, Files.readAllBytes(archivo));
    }

    @Test
    void guardarVideoCreatesRelativeUrlAndFile() throws IOException {
        String url = storage.guardarVideo(PNG, "clip.mp4");
        Path archivo = temporal.resolve(url);

        assertTrue(url.startsWith("videos/"));
        assertFalse(Path.of(url).isAbsolute());
        assertFalse(url.contains(".."));
        assertTrue(Files.exists(archivo));
        assertArrayEquals(PNG, Files.readAllBytes(archivo));
    }

    @Test
    void eliminarRemovesStoredFile() {
        String url = storage.guardarFoto(PNG, "borrar.png");
        Path archivo = temporal.resolve(url);
        assertTrue(Files.exists(archivo));

        storage.eliminar(url);

        assertFalse(Files.exists(archivo));
    }

    @Test
    void guardarFotoSanitizesTraversalName() {
        String url = storage.guardarFoto(PNG, "../malicioso.png");
        String nombre = Path.of(url).getFileName().toString();
        Path archivo = temporal.resolve(url).normalize();

        assertFalse(url.contains(".."));
        assertTrue(nombre.matches("[A-Za-z0-9_-]+"));
        assertTrue(archivo.startsWith(temporal.toAbsolutePath().normalize()));
        assertTrue(Files.exists(archivo));
    }

    @Test
    void guardarFotoRechazaExtensionNoPermitida() {
        assertThrows(MediaInvalidoException.class,
                () -> storage.guardarFoto(JPEG, "foto.gif"));
    }

    @Test
    void guardarFotoRechazaCabeceraInvalida() {
        assertThrows(MediaInvalidoException.class,
                () -> storage.guardarFoto(new byte[]{1, 2, 3, 4}, "foto.jpg"));
    }

    @Test
    void guardarFotoRechazaTamanoMayorACincoMB() {
        byte[] contenido = Arrays.copyOf(JPEG, 5 * 1024 * 1024 + 1);

        assertThrows(MediaInvalidoException.class,
                () -> storage.guardarFoto(contenido, "foto.jpg"));
    }

    @Test
    void guardarVideoRechazaExtensionNoPermitida() {
        assertThrows(MediaInvalidoException.class,
                () -> storage.guardarVideo(PNG, "clip.avi"));
    }
}
