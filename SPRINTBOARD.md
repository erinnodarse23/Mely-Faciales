# Sprintboard — Mely Faciales

## Metodología
Se usa **Scrum** para gestionar el trabajo (sprints, backlog, ceremonias, Definition of Done).
Los artefactos de **modelado UML** (casos de uso, clases del análisis, colaboración) se documentan en `docs/02-modelo-de-analisis.md` y constituyen la salida del flujo de análisis del proyecto.
Duración del sprint: **1 semana**.

## Visión del producto
Dar visibilidad a Mely Faciales mediante una web rápida, móvil y visual, donde las clientas puedan ver servicios de faciales con micro-videos, dejar reseñas con foto y valoración, y contactar por WhatsApp sin pasar por un sistema de citas.

## Roles
- Product Owner: Mely (voz del negocio), Erin Nodarse (traducción a requisitos).
- Scrum Master: Erin Nodarse.
- Development Team: Erin Nodarse (backend Java + frontend React).

## Definition of Ready (DoR)
- La historia tiene actor, criterios de aceptación y prioridad.
- Está vinculada a un requisito RF o a un caso de uso CU.
- No depende de datos pendientes (logo, colores, lista de precios) salvo que esté marcada como bloqueada.

## Definition of Done (DoD)
- El artefacto o código está creado y en el repositorio.
- La documentación correspondiente está actualizada.
- Si es un documento, ha sido revisado contra los lineamientos de análisis del proyecto.
- Si es código, compila y pasa las pruebas básicas (aplica en sprints futuros).

## Backlog del producto (priorizado)
| ID | Historia / tarea | Prioridad | Estimación | Sprint |
|---|---|---|---|---|
| DOC-01 | Documentar requisitos funcionales y no funcionales | Alta | 3 | 0 |
| DOC-02 | Documentar modelo de análisis y generar diagramas | Alta | 5 | 0 |
| DOC-03 | Crear README, CHANGELOG y Sprintboard | Media | 2 | 0 |
| DOC-04 | Documentar modelo de diseño y generar diagramas | Alta | 8 | 0 |
| DOC-05 | Reorientar la documentación a Mely Faciales y regenerar diagramas | Alta | 8 | 0 |
| DOC-06 | Documentar arquitectura en capas y flujos alternos | Alta | 5 | 0 |
| DOC-07 | Documentar despliegue y mecanismos de diseño | Alta | 5 | 0 |
| DOC-08 | Documentar modelado web con estereotipos UML | Alta | 5 | 0 |
| DOC-09 | Separar la documentación de diseño en cuatro documentos | Media | 3 | 0 |
| DOC-10 | Documentar patrones de diseño aplicados | Media | 3 | 0 |
| DOC-11 | Documentar diseño de la base de datos | Alta | 5 | 0 |
| DOC-12 | Reorganizar imágenes por documento | Media | 2 | 0 |
| DOC-13 | Documentar el modelo de implementación | Alta | 5 | 0 |
| DOC-14 | Planificar la integración del sistema | Alta | 3 | 0 |
| DOC-15 | Documentar pruebas de caja negra | Alta | 5 | 0 |
| DOC-16 | Documentar pruebas de caja blanca | Media | 5 | 0 |
| RF-01 a RF-09 | Funcionalidades del catálogo, reseñas, WhatsApp y admin | Alta | Por estimar | 1 |
| RNF-01 a RNF-10 | Rendimiento, responsive, seguridad, SEO, escalabilidad | Alta | Por estimar | 1 |
| CU-01 a CU-08 | Realizaciones documentadas para Mely Faciales (análisis y diseño) | Alta | 8 | 0 |

## Sprint 0 — Definición y documentación (2026-11-25 → 2026-12-08)
**Objetivo:** definir por completo el sistema (requisitos, análisis y diseño) antes de escribir código.

Progreso del Sprint 0: `██████████ 100%` (24/24 tareas Done).

| Tarea | Estado |
|---|---|
| Crear repositorio y estructura de carpetas | Done |
| README.md | Done |
| CHANGELOG.md | Done |
| SPRINTBOARD.md | Done |
| Extracción de requisitos funcionales y no funcionales | Done |
| Modelo de análisis con referencias a diagramas | Done |
| Exportar 18 diagramas de análisis | Done |
| Documento del Modelo de Diseño | Done |
| 32 diagramas exportados a PNG | Done |
| Actualización de README y CHANGELOG | Done |
| Actualización del Sprintboard | Done |
| Reorientación a Mely Faciales y regeneración de 50 diagramas | Done |
| Diagrama de paquetes y colaboraciones numeradas | Done |
| Arquitectura en capas y flujos alternos del diseño | Done |
| Modelo de despliegue y mecanismos de diseño | Done |
| Modelado web y realización web de CU-07 | Done |
| Separación de la documentación de diseño | Done |
| Patrones de diseño aplicados | Done |
| Diseño de la base de datos | Done |
| Carpetas de imágenes organizadas por documento | Done |
| Modelo de implementación estructurado | Done |
| Plan de construcciones definido | Done |
| Casos de caja negra del CU-03 | Done |
| Camino básico y complejidad ciclomática | Done |

## Sprints futuros (por definir)
- Sprint 1: implementación del backend Spring Boot y del frontend React.
- Sprint 2: despliegue y ADRs operativos.

## Ceremonias
- **Daily:** asíncrono, registro breve de avances en este archivo.
- **Sprint Review:** al cierre del sprint, revisar DoD y actualizar el CHANGELOG.
- **Retrospectiva:** ajustar estimaciones y DoR para el sprint siguiente.

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
