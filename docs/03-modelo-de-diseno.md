# Modelo de Diseño del Sistema

> **Resumen ejecutivo.** El modelo de análisis se transforma en un modelo físico aplicable a Java 25 LTS + Spring Boot 4.1 y React + TypeScript para Mely Faciales. Se definen 4 paquetes, 5 subsistemas, 5 interfaces de aplicación, las clases del diseño y 8 realizaciones de caso de uso. El resultado son 47 diagramas de diseño distribuidos entre este documento y los documentos 04, 05, 06, 07, 08, 09, 12, 14, 15, 16, 17, 18 y 19.

| Campo | Valor |
|---|---|
| Versión | 2.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

**Índice de secciones:** 1. Contexto · 2. Análisis a diseño · 3. Jerarquía · 3.1 Arquitectura en capas · 4. Paquetes · 5. Subsistemas · 6. Interfaces · 7. Clases del diseño · 8. Trazabilidad · 9. Cobertura · 10. Conclusiones. Consulte también: [Realizaciones de CU](04-realizaciones-de-caso-de-uso.md), [Descripción de la arquitectura](05-descripcion-de-la-arquitectura.md) y [Modelado web](06-modelado-de-aplicaciones-web.md).

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

![Arquitectura en capas](<images/modelo-de-diseno/capas-diseno.png>)

## 4. Paquetes de diseño

| Paquete | Contenido | Responsabilidad |
|---|---|---|
| `com.melyfaciales.interfaz` | Controladores REST y DTO | Exponer la API al frontend React |
| `com.melyfaciales.negocio` | Interfaces de aplicación y servicios | Coordinar los casos de uso |
| `com.melyfaciales.dominio` | Entidades, enums y reglas | Representar Mely Faciales |
| `com.melyfaciales.infraestructura` | Repositorios, almacenamiento de medios y configuración | Aislar detalles técnicos |

![Paquetes del diseño](<images/modelo-de-diseno/paquetes-diseno.png>)

## 5. Subsistemas de diseño

| Subsistema | Interfaz que proporciona | Contenido principal |
|---|---|---|
| `SubsistemaCatalogo` | `GestionServicios` | ServicioController, ServicioService, repositorios de servicios y publicaciones |
| `SubsistemaResenas` | `GestionResenas` | ResenaController, ResenaService, ResenaRepository, Resena |
| `SubsistemaMedios` | `MediaStorage` | DiscoMediaStorage; sustituible por Object Storage o S3 |
| `SubsistemaIdentidad` | `GestionIdentidad` | AuthController, AuthService, Administradora |
| `SubsistemaIntegraciones` | `GeneradorEnlacesWhatsApp` | WhatsAppLinkGenerator |

**Propuesta detallada — SubsistemaResenas.** Proporciona `GestionResenas` al resto del sistema. Contiene el controlador, el servicio, el repositorio y la entidad. Depende de `MediaStorage` del `SubsistemaMedios` para guardar la foto opcional, sin que el controlador conozca detalles de almacenamiento.

![Subsistema de reseñas](<images/modelo-de-diseno/subsistema-resenas.png>)

## 6. Interfaces de aplicación

| Interfaz | Operaciones principales |
|---|---|
| `GestionServicios` | `listarActivos`, `obtenerDetalle`, `actualizar` |
| `GestionResenas` | `publicar`, `listarPublicadas`, `eliminar` |
| `MediaStorage` | `guardarFoto`, `guardarVideo`, `eliminar` |
| `GestionIdentidad` | `autenticar` |
| `GeneradorEnlacesWhatsApp` | `construir(servicio)` |

No son interfaces gráficas: son contratos de aplicación entre subsistemas. La clase que proporciona una interfaz implementa sus operaciones con métodos concretos.

![Interfaces de aplicación](<images/modelo-de-diseno/interfaces-aplicacion.png>)

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

![Modelo de diseño global](<images/modelo-de-diseno/modelo-diseno-clases.png>)

## 8. Trazabilidad análisis → diseño → implementación prevista

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

## 9. Cobertura de escenarios
- [x] CU-01: catálogo y estado vacío
- [x] CU-02: video disponible y fallback a imagen
- [x] CU-03: con foto, sin foto y validación
- [x] CU-04: muro con reseñas y estado vacío
- [x] CU-05: número único y mensaje contextual según servicio
- [x] CU-06: sesión válida, confirmación y error al borrar
- [x] CU-07: credenciales válidas e inválidas
- [x] CU-08: texto, video y error al guardar
- Los 8 diagramas de secuencia del diseño incluyen bloques `alt` para los flujos alternos.
- Los 2 diagramas de [Descripción de la arquitectura](05-descripcion-de-la-arquitectura.md) muestran los nodos y la asignación de componentes.
- El [Modelado de aplicaciones web](06-modelado-de-aplicaciones-web.md) aplica los estereotipos web a la realización de CU-07.

## 10. Conclusiones
- El modelo de análisis es conceptual; el modelo de diseño es físico y conserva su estructura como esquema de implementación.
- El diseño especifica visibilidad, tipos y operaciones usando la sintaxis del lenguaje elegido.
- El resultado principal es el modelo de diseño, integrado por clases del diseño, paquetes, subsistemas e interfaces de aplicación.
- La arquitectura en capas garantiza dependencias descendentes entre presentación, negocio, infraestructura y dominio.
- El modelo de despliegue describe los nodos, sus conexiones y la ubicación física de los componentes.
- Los mecanismos de diseño hacen homogénea y controlada la implementación de requisitos comunes.
- El modelado web concreta las páginas cliente, las páginas servidor y los formularios con relaciones `<<Build>>`, `<<submit>>` y `<<redirect>>`.
- Con los 18 diagramas de análisis, el proyecto reúne 65 diagramas UML (47 de diseño).

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
