# Realizaciones de caso de uso — Diseño

> **Resumen ejecutivo.** 8 realizaciones de caso de uso del diseño de Mely Faciales y 16 diagramas referenciados desde `images/modelo-de-diseno/`.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

← [Modelo de diseño](03-modelo-de-diseno.md) · [Arquitectura](05-descripcion-de-la-arquitectura.md) · [Modelado web](06-modelado-de-aplicaciones-web.md)

## 1. Realizaciones de caso de uso — Diseño

### CU-01 — Consultar catálogo de servicios
`ServicioController` expone `GET /api/servicios`; `ServicioService` delega en `ServicioRepository` y convierte entidades en DTO.

**Subsistema:** `SubsistemaCatalogo` · **Interfaz:** `GestionServicios`
- Clases: ![Clases CU-01](<images/modelo-de-diseno/clases-de-diseno/CU-01-clases-diseno.png>)
- Colaboración: ![Colaboración CU-01](<images/modelo-de-diseno/colaboracion-diseno/CU-01-colaboracion-diseno.png>)
- Mensajes: 1. GET /api/servicios · 2. listarActivos() · 3. findByActivoTrue() · 4. consulta JPA (PostgreSQL) · alt hay servicios activos: entidades, lista, DTO, 200 OK · else sin servicios activos: Próximamente.

### CU-02 — Ver detalle de servicio
Incluye `Publicacion` como fuente del micro-video o la imagen de portada; si no hay video, se devuelve `imagenUrl`.

**Subsistema:** `SubsistemaCatalogo` · **Interfaz:** `GestionServicios`
- Clases: ![Clases CU-02](<images/modelo-de-diseno/clases-de-diseno/CU-02-clases-diseno.png>)
- Colaboración: ![Colaboración CU-02](<images/modelo-de-diseno/colaboracion-diseno/CU-02-colaboracion-diseno.png>)
- Mensajes: 1. GET /api/servicios/{id} · 2. obtenerDetalle(id) · 3. findById(id) · 4. findByServicio(id) · alt hay video: DTO con videoUrl y 200 OK · else sin video: DTO con imagenUrl.

### CU-03 — Publicar reseña
`ResenaService` usa `MediaStorage` antes de persistir; la reseña nace con `estado=PUBLICADA`.

**Subsistema:** `SubsistemaResenas` + `SubsistemaMedios` · **Interfaz:** `GestionResenas` + `MediaStorage`
- Clases: ![Clases CU-03](<images/modelo-de-diseno/clases-de-diseno/CU-03-clases-diseno.png>)
- Colaboración: ![Colaboración CU-03](<images/modelo-de-diseno/colaboracion-diseno/CU-03-colaboracion-diseno.png>)
- Mensajes: 1. POST /api/resenas · 2. publicar(datos) · alt datos válidos: validar; alt con foto: guardarFoto(opcional) y fotoUrl, else sin foto: omitirFoto(); guardar(Resena), DTO, 201 Created · else datos inválidos: 400 Bad Request.

### CU-04 — Consultar muro de reseñas
Filtra por `EstadoResena.PUBLICADA`.

**Subsistema:** `SubsistemaResenas` · **Interfaz:** `GestionResenas`
- Clases: ![Clases CU-04](<images/modelo-de-diseno/clases-de-diseno/CU-04-clases-diseno.png>)
- Colaboración: ![Colaboración CU-04](<images/modelo-de-diseno/colaboracion-diseno/CU-04-colaboracion-diseno.png>)
- Mensajes: 1. GET /api/resenas · 2. listarPublicadas() · 3. findByEstado(PUBLICADA) · alt hay reseñas publicadas: entidades, DTO, 200 OK · else muro vacío: Sé la primera en opinar.

### CU-05 — Contactar por WhatsApp
`WhatsAppLinkGenerator` construye el deep link con el número único de Mely y mensaje contextual según el servicio.

**Subsistema:** `SubsistemaIntegraciones` · **Interfaz:** `GeneradorEnlacesWhatsApp`
- Clases: ![Clases CU-05](<images/modelo-de-diseno/clases-de-diseno/CU-05-clases-diseno.png>)
- Colaboración: ![Colaboración CU-05](<images/modelo-de-diseno/colaboracion-diseno/CU-05-colaboracion-diseno.png>)
- Mensajes: 1. Pulsar CTA · 2. construir(servicio) · alt WhatsApp disponible: URL y abrir conversación · else sin WhatsApp: copiar +5355795510.

### CU-06 — Eliminar reseña
Requiere sesión ADMIN; el borrado se confirma antes de llamar al repositorio.

**Subsistema:** `SubsistemaIdentidad` + `SubsistemaResenas` · **Interfaz:** `GestionIdentidad` + `GestionResenas`
- Clases: ![Clases CU-06](<images/modelo-de-diseno/clases-de-diseno/CU-06-clases-diseno.png>)
- Colaboración: ![Colaboración CU-06](<images/modelo-de-diseno/colaboracion-diseno/CU-06-colaboracion-diseno.png>)
- Mensajes: 1. credenciales · alt credenciales válidas: Sesion ADMIN, seleccionar reseña, alt confirmación aceptada: confirmar, DELETE, eliminar, deleteById, 204 No Content y muro actualizado; else cancelación: operación cancelada · else credenciales inválidas: 401 Unauthorized.

### CU-07 — Iniciar sesión
`AuthController` delega en `GestionIdentidad`; `AuthService` valida contra `Administradora`.

**Subsistema:** `SubsistemaIdentidad` · **Interfaz:** `GestionIdentidad`
- Clases: ![Clases CU-07](<images/modelo-de-diseno/clases-de-diseno/CU-07-clases-diseno.png>)
- Colaboración: ![Colaboración CU-07](<images/modelo-de-diseno/colaboracion-diseno/CU-07-colaboracion-diseno.png>)
- Mensajes: 1. /admin · 2. Mostrar formulario · 3. usuario y clave · 4. POST /api/auth/login · 5. autenticar · 6. buscarPorUsuario · 7. resultado · alt credenciales válidas y sesión inactiva: Sesion ADMIN, 200 OK y Panel · else credenciales inválidas: 401 · else sesión ya activa: mensaje.

### CU-08 — Gestionar contenido del catálogo
Permite cambiar texto y/o video sin volver a desplegar; el video se sube vía `MediaStorage`.

**Subsistema:** `SubsistemaCatalogo` + `SubsistemaMedios` · **Interfaz:** `GestionServicios` + `MediaStorage`
- Clases: ![Clases CU-08](<images/modelo-de-diseno/clases-de-diseno/CU-08-clases-diseno.png>)
- Colaboración: ![Colaboración CU-08](<images/modelo-de-diseno/colaboracion-diseno/CU-08-colaboracion-diseno.png>)
- Mensajes: 1. Editar servicio · 2. PUT /api/servicios/{id} · 3. actualizar · alt servicio válido: alt con video: guardarVideo(opcional) y videoUrl, else solo texto: mantener video anterior; guardar(servicio), ServicioDTO, 200 OK y catálogo actualizado · else error al guardar: 500.

