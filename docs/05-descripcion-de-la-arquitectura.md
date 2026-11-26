# Descripción de la arquitectura

> **Resumen ejecutivo.** Modelo de despliegue y mecanismos de diseño de Mely Faciales.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

← [Modelo de diseño](03-modelo-de-diseno.md) · [Realizaciones](04-realizaciones-de-caso-de-uso.md) · [Modelado web](06-modelado-de-aplicaciones-web.md)

## 1. Descripción de la arquitectura
La vista de arquitectura describe el modelo de despliegue, la asignación de los componentes a los nodos y los mecanismos de diseño que homogeneízan la implementación.

### 1.1 Requisitos de distribución y configuración de red
- La aplicación es web pública: cliente móvil o escritorio con navegador, sin instalación.
- El frontend se sirve como sitio estático por HTTPS; el backend se ejecuta en una VM siempre activa para evitar cold starts (RNF-07). La decisión definitiva de alojamiento se difiere a un ADR del Sprint 2.
- PostgreSQL se despliega en el entorno del servidor de datos y solo es accesible por red interna; el puerto 5432 no se expone a Internet.
- Las fotos y los videos se guardan en un volumen del servidor de aplicaciones mediante `DiscoMediaStorage`, detrás de la interfaz `MediaStorage` (RNF-09).
- WhatsApp es un servicio externo: se emplea un deep link HTTPS, sin API de negocio ni credenciales.
- No se justifican enlaces redundantes en la configuración actual; su incorporación queda registrada para el ADR de despliegue.

La decisión de despliegue es mantener la VM siempre activa frente a una opción serverless: así se evitan los cold starts de RNF-07 en un catálogo con reseñas que debe responder de inmediato. El coste es un cargo fijo mensual predecible, superior al de una ejecución bajo demanda, que se acepta a cambio de disponibilidad. El ADR operativo con dimensionamiento y coste queda pendiente para Sprint 2; hasta entonces esta configuración es una referencia, no un despliegue ejecutado.

### 1.2 Modelo de despliegue
![Modelo de despliegue](<images/descripcion-de-la-arquitectura/modelo-despliegue-diseno.png>)

| Nodo | Propósito | Software | Hardware orientativo |
|---|---|---|---|
| Cliente | Navegar el catálogo, enviar reseñas y abrir WhatsApp | Navegador moderno en Android, iOS, Windows o Linux | 2 GB RAM y conexión a Internet |
| Servidor Web | Publicar el build estático del frontend | Servidor de archivos estáticos con HTTPS | Servicio gestionado, sin administración de sistema operativo |
| Servidor de Aplicaciones | Ejecutar la API REST, la lógica de negocio y el almacenamiento de medios | Linux, Java 25 LTS, Spring Boot 4.1, `DiscoMediaStorage` | 2 vCPU, 2 GB RAM, 20 GB SSD |
| Servidor de Datos | Almacenar servicios, reseñas, publicaciones y usuarios | Linux, PostgreSQL | 2 vCPU, 4 GB RAM, 20 GB SSD |
| WhatsApp | Servicio externo de contacto | Aplicación WhatsApp en el dispositivo del cliente | Sin requisitos locales en el sistema |

Los protocolos entre nodos son HTTPS para cliente-servidor y para el deep link a WhatsApp, y JDBC sobre TCP 5432 entre la API y PostgreSQL.

### 1.3 Asignación de componentes a nodos
![Asignación de componentes](<images/descripcion-de-la-arquitectura/asignacion-componentes-diseno.png>)

| Nodo | Elementos lógicos asignados | Artefacto físico |
|---|---|---|
| Cliente | Componentes React y DTO de interfaz | Build del frontend en el navegador |
| Servidor Web | Interfaz de usuario estática | HTML, CSS y JavaScript generados por Vite |
| Servidor de Aplicaciones | `ServicioController`, `ResenaController`, `AuthController`, servicios, repositorios, `MediaStorage` | `mely-faciales.jar` más volumen de medios |
| Servidor de Datos | Entidades `Servicio`, `Publicacion`, `Resena`, `Administradora` | Esquema `mely_faciales` en PostgreSQL |

### 1.4 Mecanismos de diseño
| Mecanismo | Elementos del proyecto | Requisito atendido |
|---|---|---|
| Persistencia | Spring Data JPA, `ServicioRepository`, `ResenaRepository`, `PublicacionRepository` | RF-01 a RF-08, RNF-06 |
| Autorización | Spring Security, `AuthController`, `GestionIdentidad`, rol ADMIN | RF-06, RF-07, RNF-05 |
| Transacciones | `@Transactional` en `ResenaService` y `ServicioService` | Consistencia entre reseña o servicio y sus medios |
| Registro de trazas | Logging por solicitud con identificador de correlación | RNF-05, RNF-08 |
| Almacenamiento de medios | `MediaStorage` y `DiscoMediaStorage` | RF-03, RF-08, RNF-09 |
| Generación de enlaces | `GeneradorEnlacesWhatsApp`, `WhatsAppLinkGenerator` | RF-05 |

Estos mecanismos refinan los mecanismos de análisis y se aplican de forma homogénea en todos los casos de uso.

