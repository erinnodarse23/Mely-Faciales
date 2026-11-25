# Modelo de Diseño del Sistema

> **Resumen ejecutivo.** El modelo de análisis se transforma en un modelo físico aplicable a Java 17/21 + Spring Boot 3 y React + TypeScript. Se definen 4 paquetes, 5 subsistemas, 5 interfaces de aplicación, las clases del diseño y 8 realizaciones de caso de uso. El resultado son 20 diagramas referenciados desde `images/modelo-de-diseno/`.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 1 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 1 |

**Índice de secciones:** 1. Contexto · 2. Análisis a diseño · 3. Jerarquía · 4. Paquetes · 5. Subsistemas · 6. Interfaces · 7. Clases del diseño · 8. Realizaciones · 9. Trazabilidad · 10. Cobertura · 11. Conclusiones.

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

## 4. Paquetes de diseño

| Paquete | Contenido | Responsabilidad |
|---|---|---|
| `com.myd.interfaz` | Controladores REST y DTO | Exponer la API al frontend React |
| `com.myd.negocio` | Interfaces de aplicación y servicios | Coordinar los casos de uso |
| `com.myd.dominio` | Entidades, enums y reglas | Representar el negocio de M&D |
| `com.myd.infraestructura` | Repositorios, almacenamiento de medios y configuración | Aislar detalles técnicos |

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
| `GeneradorEnlacesWhatsApp` | `construir` |

No son interfaces gráficas: son contratos de aplicación entre subsistemas. La clase que proporciona una interfaz implementa sus operaciones con métodos concretos.

![Interfaces de aplicación](<images/modelo-de-diseno/interfaces/interfaces-aplicacion.png>)

## 7. Clases del diseño

| Clase de análisis | Clase de diseño | Implementación prevista |
|---|---|---|
| `CI_CatalogoServicios` | `ServicioController` | `@RestController` en `com.myd.interfaz` |
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

- Clases: ![Clases CU-01](<images/modelo-de-diseno/clases-de-diseno/CU-01-clases-diseno.png>)
- Colaboración: ![Colaboración CU-01](<images/modelo-de-diseno/colaboracion-diseno/CU-01-colaboracion-diseno.png>)
- Mensajes: 1. GET · 2. listarActivos · 3. delegación al servicio · 4. findByEstilistaAndActivoTrue · 5-7. entidades · 8-9. DTO · 10. 200 OK / 10a. Próximamente.

### CU-02 — Ver detalle de servicio
Incluye `Publicacion` como fuente del micro-video o la imagen de portada; si no hay video, se devuelve `imagenUrl`.

- Clases: ![Clases CU-02](<images/modelo-de-diseno/clases-de-diseno/CU-02-clases-diseno.png>)
- Colaboración: ![Colaboración CU-02](<images/modelo-de-diseno/colaboracion-diseno/CU-02-colaboracion-diseno.png>)
- Mensajes: 1. GET detalle · 2-3. obtenerDetalle · 4-5. repositorios · 6-7. entidades · 8. DTO con videoUrl / 8a. con imagenUrl · 9. 200 OK.

### CU-03 — Publicar reseña
`ResenaService` usa `MediaStorage` antes de persistir; la reseña nace con `estado=PUBLICADA`.

- Clases: ![Clases CU-03](<images/modelo-de-diseno/clases-de-diseno/CU-03-clases-diseno.png>)
- Colaboración: ![Colaboración CU-03](<images/modelo-de-diseno/colaboracion-diseno/CU-03-colaboracion-diseno.png>)
- Mensajes: 1. POST · 2-3. publicar · 4-5. guardarFoto (opcional) · 6. nueva Resena · 7-8. save · 9-10. DTO · 11. 201 Created.

### CU-04 — Consultar muro de reseñas
Filtra por `EstadoResena.PUBLICADA` y, si se indica, por estilista.

- Clases: ![Clases CU-04](<images/modelo-de-diseno/clases-de-diseno/CU-04-clases-diseno.png>)
- Colaboración: ![Colaboración CU-04](<images/modelo-de-diseno/colaboracion-diseno/CU-04-colaboracion-diseno.png>)
- Mensajes: 1. GET filtro · 2-3. listarPublicadas · 4. findByEstadoAndEstilista · 5-7. entidades y DTO · 8. 200 OK / 8a. estado vacío.

### CU-05 — Contactar por WhatsApp
`WhatsAppLinkGenerator` construye el deep link con número según estilista y mensaje contextual.

- Clases: ![Clases CU-05](<images/modelo-de-diseno/clases-de-diseno/CU-05-clases-diseno.png>)
- Colaboración: ![Colaboración CU-05](<images/modelo-de-diseno/colaboracion-diseno/CU-05-colaboracion-diseno.png>)
- Mensajes: 1. GET link · 2-3. construir · 4. mensaje con `[TEL_MELISSA]` o `[TEL_DARY]` · 5. URL · 6. 200 OK · 7. apertura del chat.

### CU-06 — Eliminar reseña
Requiere sesión ADMIN; el borrado se confirma antes de llamar al repositorio.

- Clases: ![Clases CU-06](<images/modelo-de-diseno/clases-de-diseno/CU-06-clases-diseno.png>)
- Colaboración: ![Colaboración CU-06](<images/modelo-de-diseno/colaboracion-diseno/CU-06-colaboracion-diseno.png>)
- Mensajes: 1. credenciales · 2-3. autenticar · 4. DELETE · 5-6. eliminar · 7. deleteById · 8. confirmación · 9. 204 No Content.

### CU-07 — Iniciar sesión
`AuthController` delega en `GestionIdentidad`; `AuthService` valida contra `Administradora`.

- Clases: ![Clases CU-07](<images/modelo-de-diseno/clases-de-diseno/CU-07-clases-diseno.png>)
- Colaboración: ![Colaboración CU-07](<images/modelo-de-diseno/colaboracion-diseno/CU-07-colaboracion-diseno.png>)
- Mensajes: 1. POST /api/auth/login · 2-3. autenticar · 4. búsqueda · 5. entidad · 6. SesionDTO / 6a. 401 · 7. 200 OK.

### CU-08 — Gestionar contenido del catálogo
Permite cambiar texto y/o video sin volver a desplegar; el video se sube vía `MediaStorage`.

- Clases: ![Clases CU-08](<images/modelo-de-diseno/clases-de-diseno/CU-08-clases-diseno.png>)
- Colaboración: ![Colaboración CU-08](<images/modelo-de-diseno/colaboracion-diseno/CU-08-colaboracion-diseno.png>)
- Mensajes: 1. PUT servicio · 2-3. actualizar · 4-5. guardarVideo (opcional) · 6-7. guardar · 8. ServicioDTO · 9. 200 OK.

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
- [x] CU-01: catálogo filtrado y estado vacío
- [x] CU-02: video disponible y fallback a imagen
- [x] CU-03: con foto, sin foto, estilista AMBAS y validación
- [x] CU-04: filtro por estilista y muro vacío
- [x] CU-05: teléfono según estilista y mensaje contextual
- [x] CU-06: sesión válida, confirmación y error al borrar
- [x] CU-07: credenciales válidas e inválidas
- [x] CU-08: texto, video y error al guardar

## 11. Conclusiones
- El modelo de análisis es conceptual; el modelo de diseño es físico y conserva su estructura como esquema de implementación.
- El diseño especifica visibilidad, tipos y operaciones usando la sintaxis del lenguaje elegido.
- El resultado principal es el modelo de diseño, integrado por clases del diseño, paquetes, subsistemas e interfaces de aplicación.

---

*Estética M&D · Documentación oficial · Sprint 1 · Actualizado: 2026-11-25*
