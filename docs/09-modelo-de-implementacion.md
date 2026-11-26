# Modelo de implementación

> **Resumen ejecutivo.** Se estructura el modelo de implementación de Mely Faciales a partir del modelo de diseño: cuatro subsistemas de implementación alineados con los paquetes de diseño, sus dependencias de import y los componentes físicos con valor arquitectónico. El plan de construcciones se documenta en `docs/10-plan-de-integracion.md`.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Modelo de diseño](03-modelo-de-diseno.md) · [Arquitectura](05-descripcion-de-la-arquitectura.md) · [Plan de integración](10-plan-de-integracion.md)

## 1. Actividades y artefactos
| Actividad | Entrada principal | Salida principal | Responsable |
|---|---|---|---|
| Estructurar el modelo de implementación | Modelo de diseño y modelo de despliegue | Modelo de implementación, subsistemas de implementación y vista de implementación de la arquitectura | Arquitecto |
| Planificar la integración | Modelo de implementación y casos de uso de la iteración | Plan de construcciones | Integrador |
| Implementar componentes | Elementos del modelo de diseño | Componentes con pruebas unitarias | Desarrollador (Sprint 1) |
| Integrar cada subsistema | Componentes implementados | Subsistema integrado y probado | Integrador |
| Integrar el sistema | Subsistemas integrados | Sistema ejecutable | Integrador |

La disciplina se planifica en Sprint 0. La escritura de código y las pruebas unitarias de componentes se dejan para Sprint 1, según la planificación registrada en `SPRINTBOARD.md` y las construcciones del documento 10.

## 2. Estructura del modelo de implementación
Un subsistema de implementación es el análogo físico de un paquete de diseño, no de un subsistema de diseño: agrupa componentes y otros subsistemas para poder integrarlos y probarlos por separado. Por eso los cinco subsistemas de diseño (`SubsistemaCatalogo`, `SubsistemaResenas`, `SubsistemaMedios`, `SubsistemaIdentidad` y `SubsistemaIntegraciones`) permanecen como agrupaciones lógicas multcapa y no se replican como subsistemas de implementación.

| Paquete de diseño | Subsistema de implementación | Componentes principales |
|---|---|---|
| `com.melyfaciales.interfaz` | Interfaz | Controladores REST, DTO y clase principal ejecutable |
| `com.melyfaciales.negocio` | Negocio | Servicios de aplicación y generador de enlaces de WhatsApp |
| `com.melyfaciales.dominio` | Dominio | Entidades, enumerados y reglas de negocio |
| `com.melyfaciales.infraestructura` | Infraestructura | Repositorios JPA, almacenamiento de medios y configuración |

![Estructura del modelo de implementación](<images/modelo-de-implementacion/estructura-modelo-implementacion.png>)

## 3. Ajuste de los subsistemas de implementación
| Escenario de ajuste | Decisión para Mely Faciales |
|---|---|
| Equipos de desarrollo | Un solo desarrollador trabaja las cuatro capas; no se crea un subsistema por especialista. |
| Declaraciones de tipos | Los enumerados de estado y tipo de publicación permanecen en Dominio, accesibles desde todas las capas. |
| Sistemas legados | WhatsApp es un servicio externo alcanzado por deep link HTTPS; no requiere subsistema de implementación propio. |
| Dependencias circulares | No existen: los imports fluyen de Interfaz a Negocio y de Negocio a Dominio e Infraestructura. |

## 4. Dependencias de import
| Subsistema origen | Subsistema destino | Motivo |
|---|---|---|
| Interfaz | Negocio | Los controladores invocan los servicios de aplicación. |
| Negocio | Dominio | Los servicios aplican reglas y manipulan entidades. |
| Negocio | Infraestructura | Los servicios usan repositorios y almacenamiento de medios. |
| Infraestructura | Dominio | Los repositorios persisten entidades del dominio. |

En Java estas dependencias se expresan con directivas `import`. Todas son unidireccionales, por lo que no hace falta extraer elementos a una capa inferior para romper ciclos.

## 5. Elementos de implementación y objetos derivados
| Estereotipo | Elementos | Tratamiento |
|---|---|---|
| `«executable»` | `MelyFacialesApplication.java` | Se muestra: es el punto de arranque de la aplicación. |
| `«file»` | Controladores, DTO, servicios, entidades, repositorios, `DiscoMediaStorage.java`, `application.properties` | Se muestran los significativos; no se dibuja un fichero por cada clase del modelo de diseño porque la correspondencia es 1:1. |
| `«table»` | `servicios`, `publicaciones`, `resenas`, `administradoras` | Se muestran porque son artefactos desplegables definidos en `docs/08-diseno-de-la-base-de-datos.md`. |
| Derivados | Ficheros `.class`, JAR de Spring Boot y `frontend/dist/` de Vite | No se muestran: se regeneran desde el código fuente y no aportan información arquitectónica. |

Los ficheros se organizan en directorios por capa bajo `backend/src/main/java/com/melyfaciales/`, con el cliente React en `frontend/src/` y los medios subidos en un volumen externo.

![Componentes de implementación](<images/modelo-de-implementacion/componentes-implementacion.png>)

## 6. Vista de implementación de la arquitectura
Esta vista complementa el modelo de despliegue documentado en `docs/05-descripcion-de-la-arquitectura.md` sin modificarlo: indica qué componentes físicos se ubican en el nodo de aplicación, en el nodo de datos y en el volumen de medios.

| Comprobación del modelo | Resultado |
|---|---|
| Cada clase del diseño tiene un fichero fuente correspondiente | Sí, relación 1:1 documentada en la sección 5. |
| Dependencias de import unidireccionales | Sí, según la sección 4. |
| Objetos derivados 1:1 excluidos del diagrama | Sí, `.class`, JAR y `dist/` no se dibujan. |
| Componentes agrupados en directorios por capa | Sí, cuatro subsistemas de implementación más el cliente. |
| Ejecutable y tablas visibles | Sí, con estereotipos `«executable»` y `«table»`. |

## 7. Conclusión
La estructura de implementación mantiene la arquitectura en capas del diseño, evita duplicar los subsistemas lógicos y deja preparado el orden de integración descrito en `docs/10-plan-de-integracion.md`.
