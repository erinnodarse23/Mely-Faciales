package com.melyfaciales.infraestructura;

public interface MediaStorage {

    String guardarFoto(byte[] contenido, String nombreArchivo);

    String guardarVideo(byte[] contenido, String nombreArchivo);

    void eliminar(String url);
}
