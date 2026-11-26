# Fundamentos de la gestión de proyectos

> **Resumen ejecutivo.** Se aplican a Mely Faciales los conceptos básicos de dirección de proyectos: la diferencia entre proyecto y operación, los interesados, los criterios de éxito medibles, los grupos de proceso y las áreas de conocimiento. La guía PMBOK se usa como marco de referencia; Scrum continúa siendo la metodología de trabajo.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Organización y roles](13-organizacion-y-roles-del-proyecto.md) · [Modelos de mejora](15-modelos-de-mejora-de-proceso.md) · [Modelo de diseño](03-modelo-de-diseno.md)

## 1. Proyecto y operación
| Trabajo | Naturaleza | Tratamiento en Mely Faciales |
|---|---|---|
| Crear la web publicitaria | Temporal y único: tiene inicio, fin y un entregable definido | Se gestiona como proyecto en los sprints 0, 1 y 2. |
| Responder mensajes de WhatsApp | Continuo y repetitivo | Es operación del negocio; queda fuera del alcance del proyecto. |
| Publicar una reseña | Operación repetitiva del sistema en explotación | Se implementa como funcionalidad, pero su ejecución diaria no es un proyecto. |

La planificación es progresiva e iterativa: el detalle aumenta a medida que se dispone de mejor información, sin intentar cerrar desde el inicio decisiones que dependen del despliegue.

## 2. Guía PMBOK y enfoque adoptado
PMBOK es una guía de buenas prácticas, no una metodología ni un estándar prescriptivo: cada organización adapta sus procesos. En Mely Faciales se conserva Scrum como método de trabajo y se usa la guía como lista de control de cobertura. Los procesos del ciclo de vida se organizan en tres visiones: principales —desarrollo—, soporte —configuración, calidad, verificación, validación y documentación— y organización —gestión, infraestructura, mejora y formación—.

## 3. Interesados
| Interesado | Necesidad | Influencia | Forma de atención |
|---|---|---|---|
| Mely, dueña del negocio | Visibilidad del negocio y control del contenido | Alta: decide alcance y prioridades | Product Owner; revisión de cada incremento |
| Erin Nodarse | Entregar el proyecto y usarlo en el portafolio | Alta: ejecuta y decide técnicamente | Scrum Master y equipo de desarrollo |
| Clientas potenciales | Ver faciales, reseñas y contactar por WhatsApp | Media: su uso mide el valor | Diseño mobile-first y pruebas de aceptación |
| WhatsApp | Canal externo de contacto | No decide sobre el proyecto | Deep link con número real y mensaje contextual |
| Proveedor de hosting | Soporte de despliegue | Media en Sprint 2 | Decisión diferida a un ADR operativo |

Se detectan en etapas tempranas, se mantienen informados y se gestionan sus expectativas en cada revisión de sprint.

## 4. Criterios de éxito medibles
| Objetivo | Medida | Estado |
|---|---|---|
| Web pública con catálogo y reseñas | Los 8 casos de uso implementados y verificados | Planificado para Sprint 1 |
| Contenido editable sin redespliegue | Cambio de texto o video reflejado desde PostgreSQL | RF-08, planificado |
| Contacto directo | Deep link `https://wa.me/5355795510?text=...` con mensaje por servicio | RF-05, planificado |
| Experiencia móvil | Carga inicial menor a 2 s y usable a 320 px | RNF-01 y RNF-02 |
| Calidad documental | 52 diagramas UML referenciados y 0 imágenes rotas | Cumplido en Sprint 0 |
| Sin citas online | Ningún componente de agenda en el alcance | Cumplido por diseño |

Los criterios se revisan con la dueña del negocio, porque el cumplimiento de alcance, plazo y costo no garantiza por sí solo el éxito del negocio.

## 5. Ciclo de vida y grupos de proceso
![Grupos de proceso aplicados](<images/fundamentos-de-gestion-de-proyectos/grupos-proceso-gestion.png>)

| Grupo de proceso | Aplicación en Mely Faciales | Artefacto principal |
|---|---|---|
| Inicio | Autorización formal y visión del producto | `README.md` y visión en `SPRINTBOARD.md` |
| Planificación | Backlog, estimaciones y orden de construcciones | `SPRINTBOARD.md` y `docs/10-plan-de-integracion.md` |
| Ejecución | Implementación de componentes y frontend | Construcciones del documento 10 |
| Monitoreo y control | Seguimiento del progreso y decisiones de cambio | `SPRINTBOARD.md` y `CHANGELOG.md` |
| Cierre | Despliegue, documentación operativa y cierre del sprint | ADR previsto para Sprint 2 |

Los grupos de proceso no son fases: se repiten en cada sprint y se diferencian de las fases del producto. Cada fin de sprint funciona como punto de revisión con decisión de continuar, ajustar o cerrar.

## 6. Áreas de conocimiento
| Área | Aplicación en el proyecto |
|---|---|
| Integración | Coordinación entre documentación, backend, frontend y despliegue. |
| Alcance | RF-01 a RF-09 y CU-01 a CU-08; se controla la corrupción de alcance. |
| Cronograma | Sprints de una semana y construcciones ordenadas del documento 10. |
| Costos | Sin presupuesto formal; se registran decisiones que impactan infraestructura en un ADR posterior. |
| Calidad | Modelo UML, listas de chequeo y casos de prueba del documento 11. |
| Recursos | Un desarrollador con apoyo de la dueña del negocio para decisiones de contenido. |
| Comunicaciones | Documentación en el repositorio, changelog y revisiones de sprint. |
| Riesgos | Backlog priorizado, ADR operativo pendiente y continuidad del servidor para evitar cold starts. |
| Adquisiciones | Solo servicios externos previstos: dominio, hosting y WhatsApp como canal. |
| Interesados | Identificados y atendidos según la tabla de la sección 3. |

## 7. Acta de constitución y plan para la dirección
En un proyecto pequeño el acta se simplifica: la visión y la autorización quedan en `README.md` y en la sección de visión del `SPRINTBOARD.md`, con Mely como patrocinadora. El plan para la dirección lo forman el propio sprintboard, el plan de construcciones y la documentación técnica de los documentos 01 a 12.

## 8. Conclusión
La dirección de proyectos aporta predictibilidad y control sin sustituir a Scrum: delimita el alcance, hace visibles los interesados y convierte el éxito en criterios medibles.
