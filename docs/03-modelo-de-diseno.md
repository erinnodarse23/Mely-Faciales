# Modelo de Diseño del Sistema

> **Resumen ejecutivo.** El modelo de análisis se transforma en un modelo físico aplicable a Java 25 LTS + Spring Boot 4.1 y React + TypeScript para Mely Faciales. Se definen 4 paquetes, 5 subsistemas, 5 interfaces de aplicación, las clases del diseño y 8 realizaciones de caso de uso. El resultado son 26 diagramas referenciados desde `images/modelo-de-diseno/`.

| Campo | Valor |
|---|---|
| Versión | 1.5 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

**Índice de secciones:** 1. Contexto · 2. Análisis a diseño · 3. Jerarquía · 3.1 Arquitectura en capas · 4. Paquetes · 5. Subsistemas · 6. Interfaces · 7. Clases del diseño · 8. Realizaciones · 9. Trazabilidad · 10. Cobertura · 11. Descripción de la arquitectura · 12. Modelado de aplicaciones web · 13. Conclusiones.

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
- Los 2 diagramas de la sección 11 muestran los nodos y la asignación de componentes de la arquitectura.
- La sección 12 aplica los estereotipos web a la realización de CU-07 con sus flujos alternos.

## 11. Descripción de la arquitectura
La vista de arquitectura describe el modelo de despliegue, la asignación de los componentes a los nodos y los mecanismos de diseño que homogeneízan la implementación.

### 11.1 Requisitos de distribución y configuración de red
- La aplicación es web pública: cliente móvil o escritorio con navegador, sin instalación.
- El frontend se sirve como sitio estático por HTTPS; el backend se ejecuta en una VM siempre activa para evitar cold starts (RNF-07). La decisión definitiva de alojamiento se difiere a un ADR del Sprint 2.
- PostgreSQL se despliega en el entorno del servidor de datos y solo es accesible por red interna; el puerto 5432 no se expone a Internet.
- Las fotos y los videos se guardan en un volumen del servidor de aplicaciones mediante `DiscoMediaStorage`, detrás de la interfaz `MediaStorage` (RNF-09).
- WhatsApp es un servicio externo: se emplea un deep link HTTPS, sin API de negocio ni credenciales.
- No se justifican enlaces redundantes en la configuración actual; su incorporación queda registrada para el ADR de despliegue.

### 11.2 Modelo de despliegue
![Modelo de despliegue](<images/modelo-de-diseno/despliegue/modelo-despliegue-diseno.png>)

| Nodo | Propósito | Software | Hardware orientativo |
|---|---|---|---|
| Cliente | Navegar el catálogo, enviar reseñas y abrir WhatsApp | Navegador moderno en Android, iOS, Windows o Linux | 2 GB RAM y conexión a Internet |
| Servidor Web | Publicar el build estático del frontend | Servidor de archivos estáticos con HTTPS | Servicio gestionado, sin administración de sistema operativo |
| Servidor de Aplicaciones | Ejecutar la API REST, la lógica de negocio y el almacenamiento de medios | Linux, Java 25 LTS, Spring Boot 4.1, `DiscoMediaStorage` | 2 vCPU, 2 GB RAM, 20 GB SSD |
| Servidor de Datos | Almacenar servicios, reseñas, publicaciones y usuarios | Linux, PostgreSQL | 2 vCPU, 4 GB RAM, 20 GB SSD |
| WhatsApp | Servicio externo de contacto | Aplicación WhatsApp en el dispositivo del cliente | Sin requisitos locales en el sistema |

Los protocolos entre nodos son HTTPS para cliente-servidor y para el deep link a WhatsApp, y JDBC sobre TCP 5432 entre la API y PostgreSQL.

### 11.3 Asignación de componentes a nodos
![Asignación de componentes](<images/modelo-de-diseno/despliegue/asignacion-componentes-diseno.png>)

| Nodo | Elementos lógicos asignados | Artefacto físico |
|---|---|---|
| Cliente | Componentes React y DTO de interfaz | Build del frontend en el navegador |
| Servidor Web | Interfaz de usuario estática | HTML, CSS y JavaScript generados por Vite |
| Servidor de Aplicaciones | `ServicioController`, `ResenaController`, `AuthController`, servicios, repositorios, `MediaStorage` | `mely-faciales.jar` más volumen de medios |
| Servidor de Datos | Entidades `Servicio`, `Publicacion`, `Resena`, `Administradora` | Esquema `mely_faciales` en PostgreSQL |

### 11.4 Mecanismos de diseño
| Mecanismo | Elementos del proyecto | Requisito atendido |
|---|---|---|
| Persistencia | Spring Data JPA, `ServicioRepository`, `ResenaRepository`, `PublicacionRepository` | RF-01 a RF-08, RNF-06 |
| Autorización | Spring Security, `AuthController`, `GestionIdentidad`, rol ADMIN | RF-06, RF-07, RNF-05 |
| Transacciones | `@Transactional` en `ResenaService` y `ServicioService` | Consistencia entre reseña o servicio y sus medios |
| Registro de trazas | Logging por solicitud con identificador de correlación | RNF-05, RNF-08 |
| Almacenamiento de medios | `MediaStorage` y `DiscoMediaStorage` | RF-03, RF-08, RNF-09 |
| Generación de enlaces | `GeneradorEnlacesWhatsApp`, `WhatsAppLinkGenerator` | RF-05 |

Estos mecanismos refinan los mecanismos de análisis y se aplican de forma homogénea en todos los casos de uso.

## 12. Modelado de aplicaciones web
La web de Mely Faciales se modela con la extensión UML para aplicaciones web. Las clases interfaz, control y entidad del análisis se refinan como páginas cliente, páginas servidor y entidades proveedoras de información.

### 12.1 Mapa de navegación
![Mapa de navegación](<images/modelo-de-diseno/web/mapa-navegacion-web.png>)

El mapa muestra cuatro niveles de profundidad: Inicio; Catálogo, Muro y Login; Detalle, Formulario de reseña y Panel; y Editor de servicio. El detalle de servicio enlaza con WhatsApp mediante un deep link HTTPS.

### 12.2 Guía de estilos
| Elemento | Definición |
|---|---|
| Identidad visual | Rosa/skincare cálido: primario `#F8BBD0`, superficie `#FCE4EC`, texto `#424242` |
| Tipografía | `Inter`, con fallback `system-ui`; jerarquía clara de títulos y cuerpo |
| Componentes | Botones redondeados, tarjetas con sombra suave y micro-videos en bucle y silenciados |
| Diseño | Mobile-first desde 320 px, navegación por teclado y contraste AA |
| Contenido | Español neutro; textos alternativos en todas las imágenes |

### 12.3 Estereotipos y relaciones web
| Elemento | Estereotipo | Uso en Mely Faciales | Prefijo |
|---|---|---|---|
| Página cliente | `<<ClientPage>>` | Pantallas React renderizadas en el navegador | `cl_` |
| Página servidor | `<<ServerPage>>` | Recursos REST que construyen datos y redirigen | `sp_` |
| Formulario | `<<Form>>` | Formulario de login y formulario de reseña | `Form_` |
| Construcción | `<<Build>>` | De página servidora a página cliente | — |
| Hipervínculo | hipervínculo | De página cliente a otra página cliente | — |
| Envío | `<<submit>>` | De formulario a página servidora | — |
| Redirección | `<<redirect>>` | De página servidora a página servidora | — |
| Inclusión | `<<include>>` | Reutilización de comportamiento servidor | — |

Las marcas «Build», «submit» y «redirect» en los diagramas son la forma textual de los estereotipos `<<Build>>`, `<<submit>>` y `<<redirect>>`.

### 12.4 Realización web de CU-07 — Iniciar sesión
Esta vista web complementa, sin sustituir, la realización tecnológica de CU-07 en la sección 8.

**Clases del diseño web:** ![Clases web CU-07](<images/modelo-de-diseno/web/CU-07-clases-web.png>)

**Secuencia web:** ![Secuencia web CU-07](<images/modelo-de-diseno/web/CU-07-colaboracion-web.png>)

- `cl_Inicio` enlaza por hipervínculo con `cl_Login`; `sp_Login` construye la página cliente con `<<Build>>`; `Form_Login` envía sus campos a `sp_Auth` con `<<submit>>`; `sp_Auth` delega en `AuthService` y redirige a `sp_PanelAdmin` con `<<redirect>>`, que construye `cl_PanelAdmin`.
- Flujos alternos: credenciales inválidas devuelven 401 y sesión ya activa muestra el mensaje correspondiente.

## 13. Conclusiones
- El modelo de análisis es conceptual; el modelo de diseño es físico y conserva su estructura como esquema de implementación.
- El diseño especifica visibilidad, tipos y operaciones usando la sintaxis del lenguaje elegido.
- El resultado principal es el modelo de diseño, integrado por clases del diseño, paquetes, subsistemas e interfaces de aplicación.
- La arquitectura en capas garantiza dependencias descendentes entre presentación, negocio, infraestructura y dominio.
- El modelo de despliegue describe los nodos, sus conexiones y la ubicación física de los componentes.
- Los mecanismos de diseño hacen homogénea y controlada la implementación de requisitos comunes.
- El modelado web concreta las páginas cliente, las páginas servidor y los formularios con relaciones `<<Build>>`, `<<submit>>` y `<<redirect>>`.
- Con los 18 diagramas de análisis, el proyecto reúne 44 diagramas UML (26 de diseño).

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
