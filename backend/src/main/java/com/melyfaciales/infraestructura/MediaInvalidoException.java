package com.melyfaciales.infraestructura;

public class MediaInvalidoException extends IllegalArgumentException {

    public MediaInvalidoException(String mensaje) {
        super(mensaje);
    }
}
