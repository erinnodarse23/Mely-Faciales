# Modelo de Diseño del Sistema

> **Resumen ejecutivo.** El modelo de análisis se transforma en un modelo físico aplicable a Java 25 LTS + Spring Boot 4.1 y React + TypeScript para Mely Faciales. Se definen 4 paquetes, 5 subsistemas, 5 interfaces de aplicación, las clases del diseño y 8 realizaciones de caso de uso. El resultado son 21 diagramas referenciados desde `images/modelo-de-diseno/`.

| Campo | Valor |
|---|---|
| Versión | 1.3 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

**Índice de secciones:** 1. Contexto · 2. Análisis a diseño · 3. Jerarquía · 3.1 Arquitectura en capas · 4. Paquetes · 5. Subsistemas · 6. Interfaces · 7. Clases del diseño · 8. Realizaciones · 9. Trazabilidad · 10. Cobertura · 11. Conclusiones.

## 1. Contexto y objetivos
El diseño modela el sistema y define su arquitectura. La entrada esencial es el Modelo de Análisis ya documentado. Se impone una estructura que debe conservarse durante la implementación.

Objetivos del diseño en este proyecto:
- Profundizar en restricciones no funcionales y tecnológicas: lenguaje Java, Spring Boot, PostgreSQL, React, almacenamiento de medios y concurrencia.
- Crear un punto de partida para la implementación: capturar clases, interfaces y subsistemas individuales.
- Descomponer el trabajo en partes manejables, asignables a módulos independientes del backend y del frontend.

## 2. Del modelo de análisis al modelo de diseño

| Modelo de análisis | Modelo de diseño |
|---|---|
| Conceptual: abstracción del sistema | Físico: plano de implementación |
| Genérico, aplicable a varios diseños | Específico para Java/Spring Boot/React |
| Tres estereotipos: Interfaz, Control, Entidad | Estereotipos técnicos: Controller, Service, Repository, Entity |
| Menos formal | Más formal, con visibilidad y tipos |
| Menos capas | Más capas |
| No necesariamente mantenido durante todo el ciclo | Debe mantenerse durante todo el ciclo |
| Entrada esencial para el diseño | Esquema para la implementación |

## 3. Jerarquía del modelo de diseño
El sistema de diseño se organiza en subsistemas; cada subsistema reúne paquetes, clases, interfaces y realizaciones de caso de uso. Esta jerarquía permite administrar la complejidad sin modificar lo ya desplegado.

### 3.1 Arquitectura en capas
La arquitectura se organiza en cuatro capas. Una capa solo puede emplear funcionalidades de la misma capa o de capas inferiores; están prohibidas las dependencias ascendentes.

| Capa | Paquete / tecnología | Contenido |
|---|---|---|
| 1. Presentación | React + `com.melyfaciales.interfaz` | Componentes React, `@RestController`, DTO |
| 2. Lógica de negocio | `com.melyfaciales.negocio` | `@Service`, interfaces de aplicación |
| 3. Acceso a datos e infraestructura | `com.melyfaciales.infraestructura` | Repositorios JPA, `MediaStorage`, configuración |
| 4. Dominio | `com.melyfaciales.dominio` | Entidades, enums y reglas |

![Arquitectura en capas](<images/modelo-de-diseno/capas/capas-diseno.png>)

## 4. Paquetes de diseño

| Paquete | Contenido | Responsabilidad |
|---|---|---|
| `com.melyfaciales.interfaz` | Controladores REST y DTO | Exponer la API al frontend React |
| `com.melyfaciales.negocio` | Interfaces de aplicación y servicios | Coordinar los casos de uso |
| `com.melyfaciales.dominio` | Entidades, enums y reglas | Representar Mely Faciales |
| `com.melyfaciales.infraestructura` | Repositorios, almacenamiento de medios y configuración | Aislar detalles técnicos |

![Paquetes del diseño](<images/modelo-de-diseno/paquetes/paquetes-diseno.png>)

## 5. Subsistemas de diseño

| Subsistema | Interfaz que proporciona | Contenido principal |
|---|---|---|
| `SubsistemaCatalogo` | `GestionServicios` | ServicioController, ServicioService, repositorios de servicios y publicaciones |
| `SubsistemaResenas` | `GestionResenas` | ResenaController, ResenaService, ResenaRepository, Resena |
| `SubsistemaMedios` | `MediaStorage` | DiscoMediaStorage; sustituible por Object Storage o S3 |
| `SubsistemaIdentidad` | `GestionIdentidad` | AuthController, AuthService, Administradora |
| `SubsistemaIntegraciones` | `GeneradorEnlacesWhatsApp` | WhatsAppLinkGenerator |

**Propuesta detallada — SubsistemaResenas.** Proporciona `GestionResenas` al resto del sistema. Contiene el controlador, el servicio, el repositorio y la entidad. Depende de `MediaStorage` del `SubsistemaMedios` para guardar la foto opcional, sin que el controlador conozca detalles de almacenamiento.

![Subsistema de reseñas](<images/modelo-de-diseno/subsistemas/subsistema-resenas.png>)

## 6. Interfaces de aplicación

| Interfaz | Operaciones principales |
|---|---|
| `GestionServicios` | `listarActivos`, `obtenerDetalle`, `actualizar` |
| `GestionResenas` | `publicar`, `listarPublicadas`, `eliminar` |
| `MediaStorage` | `guardarFoto`, `guardarVideo`, `eliminar` |
| `GestionIdentidad` | `autenticar` |
| `GeneradorEnlacesWhatsApp` | `construir(servicio)` |

No son interfaces gráficas: son contratos de aplicación entre subsistemas. La clase que proporciona una interfaz implementa sus operaciones con métodos concretos.

![Interfaces de aplicación](<images/modelo-de-diseno/interfaces/interfaces-aplicacion.png>)

## 7. Clases del diseño

| Clase de análisis | Clase de diseño | Implementación prevista |
|---|---|---|
| `CI_CatalogoServicios` | `ServicioController` | `@RestController` en `com.melyfaciales.interfaz` |
| `CC_GestorServicios` | `ServicioService` | `@Service` que implementa `GestionServicios` |
| `CE_Servicio` | `Servicio` | `@Entity` JPA con tipos Java |
| `CI_FormularioReseña` | `ResenaController` | `@RestController` |
| `CC_GestorReseñas` | `ResenaService` | `@Service` |
| `CE_Reseña` | `Resena` | `@Entity` JPA |
| `CC_GestorMedios` | `DiscoMediaStorage` | Implementación de `MediaStorage` |
| `CI_WhatsApp` | `WhatsAppLinkGenerator` | Implementación de `GeneradorEnlacesWhatsApp` |
| `CE_Administradora` | `Administradora` | `@Entity` JPA |

Las clases del diseño especifican visibilidad, atributos, tipos y operaciones con sintaxis del lenguaje elegido.

![Modelo de diseño global](<images/modelo-de-diseno/clases-de-diseno/modelo-diseno-clases.png>)

## 8. Realizaciones de caso de uso — Diseño

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
- Mensajes: 1. POST /api/resenas · 2. publicar(datos) · alt datos válidos: validar, guardarFoto(opcional) o sin foto, guardar(Resena), DTO, 201 Created · else datos inválidos: 400 Bad Request.

### CU-04 — Consultar muro de reseñas
Filtra por `EstadoResena.PUBLICADA`.

**Subsistema:** `SubsistemaResenas` · **Interfaz:** `GestionResenas`
- Clases: ![Clases CU-04](<images/modelo-de-diseno/clases-de-diseno/CU-04-clases-diseno.png>)
- Colaboración: ![Colaboración CU-04](<images/modelo-de-diseno/colaboracion-diseno/CU-04-colaboracion-diseno.png>)
- Mensajes: 1. GET /api/resenas · 2. listarPublicadas() · 3. findByEstado(PUBLICADA) · alt hay reseñas: entidades, DTO, 200 OK · else muro vacío: Sé la primera en opinar.

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
- Mensajes: 1. credenciales · alt credenciales válidas: Sesion ADMIN, seleccionar, confirmar, DELETE, eliminar, deleteById, 204 No Content y muro actualizado; else cancelación: operación cancelada · else credenciales inválidas: 401 Unauthorized.

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
- Mensajes: 1. Editar servicio · 2. PUT /api/servicios/{id} · 3. actualizar · alt servicio válido: guardarVideo(opcional) o solo texto, guardar, DTO, 200 OK y catálogo actualizado · else error al guardar: 500.

## 9. Trazabilidad análisis → diseño → implementación prevista

| CU | Clases del análisis | Clases del diseño |
|---|---|---|
| CU-01 | CI_Portada, CI_CatalogoServicios, CC_GestorServicios, CE_Servicio | ServicioController, GestionServicios, ServicioService, ServicioRepository, Servicio |
| CU-02 | CI_DetalleServicio, CC_GestorServicios, CE_Servicio, CE_Publicacion | ServicioController, ServicioService, PublicacionRepository, Servicio, Publicacion |
| CU-03 | CI_FormularioReseña, CC_GestorReseñas, CC_GestorMedios, CE_Reseña | ResenaController, GestionResenas, ResenaService, MediaStorage, ResenaRepository, Resena |
| CU-04 | CI_MuroReseñas, CC_GestorReseñas, CE_Reseña | ResenaController, ResenaService, ResenaRepository, Resena |
| CU-05 | CI_DetalleServicio, CC_GestorWhatsApp, CI_WhatsApp | GeneradorEnlacesWhatsApp, WhatsAppLinkGenerator |
| CU-06 | CI_PanelAdmin, CC_GestorAutenticación, CC_GestorReseñas, CE_Reseña | AuthController, GestionIdentidad, ResenaController, ResenaService, ResenaRepository |
| CU-07 | CI_Login, CC_GestorAutenticación, CE_Administradora | AuthController, GestionIdentidad, AuthService, Administradora |
| CU-08 | CI_PanelAdmin, CC_GestorServicios, CC_GestorMedios, CE_Servicio | ServicioController, ServicioService, MediaStorage, ServicioRepository, Servicio |

## 10. Cobertura de escenarios
- [x] CU-01: catálogo y estado vacío
- [x] CU-02: video disponible y fallback a imagen
- [x] CU-03: con foto, sin foto y validación
- [x] CU-04: muro con reseñas y estado vacío
- [x] CU-05: número único y mensaje contextual según servicio
- [x] CU-06: sesión válida, confirmación y error al borrar
- [x] CU-07: credenciales válidas e inválidas
- [x] CU-08: texto, video y error al guardar
- Los 8 diagramas de secuencia del diseño incluyen bloques `alt` para los flujos alternos.

## 11. Conclusiones
- El modelo de análisis es conceptual; el modelo de diseño es físico y conserva su estructura como esquema de implementación.
- El diseño especifica visibilidad, tipos y operaciones usando la sintaxis del lenguaje elegido.
- El resultado principal es el modelo de diseño, integrado por clases del diseño, paquetes, subsistemas e interfaces de aplicación.
- La arquitectura en capas garantiza dependencias descendentes entre presentación, negocio, infraestructura y dominio.

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
