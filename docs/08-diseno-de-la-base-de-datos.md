# Diseño de la base de datos

> **Resumen ejecutivo.** Se derivan las clases persistentes del modelo de diseño, se mapean a tablas de PostgreSQL y se definen las restricciones de integridad.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Modelo de diseño](03-modelo-de-diseno.md) · [Patrones](07-patrones-de-diseno.md) · [Arquitectura](05-descripcion-de-la-arquitectura.md)

## 1. Clases persistentes y temporales
![Clases persistentes](<images/base-de-datos/clases-persistentes.png>)

| Persistentes | Temporales |
|---|---|
| `Servicio`, `Publicacion`, `Resena`, `Administradora` | `Sesion`, `ServicioDTO`, `ResenaDTO`, `WhatsAppLinkGenerator`, controladores y servicios |

Una clase compuesta por otras persistentes también lo es; por eso `Publicacion` se persiste como parte de `Servicio`. Las instancias calculadas y los DTO no se almacenan.

## 2. Mapeo objeto-relacional
| Clase | Tabla | Primary key | Columnas y conversiones |
|---|---|---|---|
| `Servicio` | `servicios` | `id UUID` | `nombre VARCHAR(120)`, `descripcion TEXT`, `imagen_url TEXT`, `activo BOOLEAN` |
| `Publicacion` | `publicaciones` | `id UUID` | `servicio_id UUID FK`, `tipo VARCHAR(20)`, `url TEXT`, `descripcion TEXT` |
| `Resena` | `resenas` | `id UUID` | `servicio_id UUID FK`, `nombre_clienta VARCHAR(80)`, `valoracion INTEGER`, `comentario TEXT`, `foto_url TEXT`, `estado VARCHAR(20)` |
| `Administradora` | `administradoras` | `id UUID` | `usuario VARCHAR(60) UNIQUE`, `nombre VARCHAR(80)`, `rol VARCHAR(20)` |

`String` se convierte a `VARCHAR` o `TEXT` según longitud, `boolean` a `BOOLEAN`, `int` a `INTEGER` y los enumerados a `VARCHAR` con restricción `CHECK`.

## 3. Modelo relacional
![Modelo relacional](<images/base-de-datos/modelo-relacional.png>)

## 4. Restricciones de integridad
- `publicaciones.servicio_id` es `NOT NULL` con `ON DELETE CASCADE`: una publicación no existe sin su servicio.
- `resenas.servicio_id` es `NOT NULL` con `ON DELETE RESTRICT`; los servicios se dan de baja con `activo = false` para no perder reseñas.
- `resenas.valoracion` tiene `CHECK (valoracion BETWEEN 1 AND 5)` y `estado` solo admite `PUBLICADA`.
- No hay jerarquías de herencia entre entidades persistentes; los estados se modelan como `VARCHAR` con `CHECK`, equivalente a una sola tabla con discriminador.
- Se usa PostgreSQL relacional en lugar de una BD orientada a objetos: cubre el dominio sin dependencias propietarias ni riesgo de vendor lock-in.

## 5. Trazabilidad
El modelo relacional da soporte a RF-01, RF-02, RF-03, RF-04, RF-06, RF-07 y RF-08, y a los mecanismos de persistencia y autorización documentados en `docs/05-descripcion-de-la-arquitectura.md`.
