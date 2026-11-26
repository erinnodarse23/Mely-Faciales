# Plan de integración

> **Resumen ejecutivo.** Plan de construcciones para integrar incrementalmente los ocho casos de uso de Mely Faciales en Sprint 1, partiendo de los subsistemas de implementación de `docs/09-modelo-de-implementacion.md`.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Modelo de implementación](09-modelo-de-implementacion.md) · [Realizaciones](04-realizaciones-de-caso-de-uso.md) · [Modelo de diseño](03-modelo-de-diseno.md)

## 1. Principio de integración
Integrar es sumar partes manejables en un orden explícito. Cada construcción deja el sistema en estado ejecutable y adiciona solo los componentes necesarios para observar el caso de uso correspondiente. El orden respeta las dependencias de import: primero las capas inferiores y después las que las consumen.

## 2. Casos de uso de la iteración
| Caso de uso | Nombre | Subsistema de diseño |
|---|---|---|
| CU-01 | Consultar catálogo de servicios | `SubsistemaCatalogo` |
| CU-02 | Ver detalle de servicio | `SubsistemaCatalogo` |
| CU-03 | Publicar reseña | `SubsistemaResenas` + `SubsistemaMedios` |
| CU-04 | Consultar muro de reseñas | `SubsistemaResenas` |
| CU-05 | Contactar por WhatsApp | `SubsistemaIntegraciones` |
| CU-06 | Eliminar reseña | `SubsistemaIdentidad` + `SubsistemaResenas` |
| CU-07 | Iniciar sesión | `SubsistemaIdentidad` |
| CU-08 | Gestionar contenido del catálogo | `SubsistemaCatalogo` + `SubsistemaMedios` |

## 3. Orden de los subsistemas de implementación
| Orden | Subsistema | Motivo |
|---|---|---|
| 1 | Dominio | No depende de ningún otro subsistema de aplicación. |
| 2 | Infraestructura | Necesita las entidades para mapearlas a tablas. |
| 3 | Negocio | Consume dominio e infraestructura. |
| 4 | Interfaz | Consume negocio y expone la API al cliente. |

El cliente React se integra al final como componente desplegable del nodo web; no forma parte de los subsistemas de implementación del backend.

## 4. Plan de construcciones
| Construcción | Casos de uso | Componentes que se suman | Verificación | Caso de prueba / evidencia |
|---|---|---|---|---|
| 1 | Ninguno (base técnica) | Entidades, enumerados, repositorios, `DiscoMediaStorage`, esquema de PostgreSQL | Pruebas unitarias de repositorios contra base de prueba | Sin CP numerado; compilación y prueba unitaria de repositorios con JUnit 5 |
| 2 | CU-05 | `ServicioService`, `ResenaService`, `WhatsAppLinkGenerator` y `application.properties` | Prueba unitaria del deep link con el número `+5355795510` y mensaje por servicio | Sin CP numerado; prueba unitaria del generador con `+5355795510` y mensaje por servicio con JUnit 5 |
| 3 | CU-07 | `AuthService`, `Administradora` y regla de rol `ADMIN` | Inicio de sesión válido e inválido con respuesta esperada | Sin CP numerado; prueba unitaria de inicio de sesión válido e inválido con JUnit 5 |
| 4 | CU-01, CU-02, CU-04 | Controladores REST, DTO y `MelyFacialesApplication.java` | La API responde catálogo, detalle y muro de reseñas | CP-03-04 y CP-03-09 cubren el muro tras publicar; catálogo y detalle sin CP numerado, con JUnit 5 sobre la API REST y Vitest en el cliente |
| 5 | CU-03, CU-06, CU-08 | Foto opcional, borrado correctivo y edición de servicios | Flujo publicar → listar → eliminar y edición sin redesepliegue | CP-03-01 a CP-03-10 y CP-B-01 a CP-B-05 con JUnit 5 + Mockito; borrado y edición sin CP numerado, verificados en el flujo de esta construcción |
| 6 | Los ocho casos de uso | Cliente React, `npm run build` y despliegue del `dist/` | Smoke test del flujo crítico catálogo → reseña → WhatsApp contra la API desplegada | Humo E2E con Vitest en el cliente; los 15 CP se ejecutan con JUnit 5 en las construcciones 4 y 5; evidencia en Git |

Cada construcción adiciona sus componentes a la anterior; ninguna incorpora un componente sin su prueba unitaria correspondiente.

## 5. Criterios de aceptación de la integración
- El resultado de cada construcción es ejecutable y se puede demostrar al final de la iteración.
- La secuencia de construcciones respeta las dependencias `import` del documento 09.
- El enlace de WhatsApp usa el número real y un mensaje contextual por servicio.
- No se incorpora agenda ni citas online: la cita se gestiona manualmente por WhatsApp.
- Los medios se almacenan fuera de la base de datos, en el volumen previsto en el modelo de despliegue.

## 6. Trazabilidad
El plan cubre RF-01 a RF-09 y los casos de uso CU-01 a CU-08, y se apoya en los subsistemas de implementación, el modelo relacional y los patrones documentados en los documentos 07, 08 y 09.
