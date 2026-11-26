CREATE TABLE IF NOT EXISTS servicios (
    id UUID NOT NULL PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    descripcion TEXT,
    imagen_url TEXT,
    activo BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS publicaciones (
    id UUID NOT NULL PRIMARY KEY,
    servicio_id UUID NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    url TEXT NOT NULL,
    descripcion TEXT,
    CONSTRAINT fk_publicaciones_servicio FOREIGN KEY (servicio_id)
        REFERENCES servicios (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS resenas (
    id UUID NOT NULL PRIMARY KEY,
    servicio_id UUID NOT NULL,
    nombre_clienta VARCHAR(80) NOT NULL,
    valoracion INTEGER NOT NULL,
    comentario TEXT NOT NULL,
    foto_url TEXT,
    estado VARCHAR(20) NOT NULL,
    CONSTRAINT fk_resenas_servicio FOREIGN KEY (servicio_id)
        REFERENCES servicios (id) ON DELETE RESTRICT,
    CONSTRAINT chk_resenas_valoracion CHECK (valoracion BETWEEN 1 AND 5),
    CONSTRAINT chk_resenas_estado CHECK (estado = 'PUBLICADA')
);

CREATE TABLE IF NOT EXISTS administradoras (
    id UUID NOT NULL PRIMARY KEY,
    usuario VARCHAR(60) NOT NULL,
    nombre VARCHAR(80) NOT NULL,
    rol VARCHAR(20) NOT NULL,
    CONSTRAINT uq_administradoras_usuario UNIQUE (usuario)
);
