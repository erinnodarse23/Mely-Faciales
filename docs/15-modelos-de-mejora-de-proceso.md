# Modelos de mejora de proceso

> **Resumen ejecutivo.** Se revisan CMMI, PSP, TSP y MCDAI como marcos de referencia para mejorar la calidad del proceso, y se registra qué prácticas ya están presentes en Mely Faciales. No se persigue certificación: Scrum sigue siendo la metodología de trabajo.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Fundamentos de gestión](14-fundamentos-de-gestion-de-proyectos.md) · [Organización y roles](13-organizacion-y-roles-del-proyecto.md) · [Pruebas](11-gestion-de-la-calidad-y-pruebas.md)

## 1. Niveles de la mejora
| Nivel de aplicación | Modelo | Enfoque |
|---|---|---|
| Organización | CMMI | Capacidades de dirección y madurez institucional. |
| Equipo | TSP | Ejecución disciplinada del equipo. |
| Individuo | PSP | Conocimientos, disciplina personal y registro de defectos. |

La calidad del producto depende en gran medida de la calidad del proceso empleado para desarrollarlo y mantenerlo.

## 2. CMMI
CMMI es un modelo de referencia con 22 áreas de proceso agrupadas en cuatro categorías —ingeniería, gestión de proyectos, gestión de procesos y soporte—. Define cinco niveles de madurez y dos representaciones.

| Representación | Característica | Uso recomendado |
|---|---|---|
| Escalonada | Secuencia de implantación por niveles; permite comparar organizaciones | Organizaciones con poca experiencia en mejora de procesos |
| Continua | Selección flexible de áreas alineadas con objetivos de negocio | Organizaciones con experiencia previa en mejora |

| Nivel | Característica | Situación de Mely Faciales |
|---|---|---|
| 1. Inicial | Resultados dependientes del esfuerzo individual | Superado en la documentación: los entregables están definidos |
| 2. Administrado | Gestión básica de proyectos, requisitos y configuración | Práctica actual en sprintboard, Git y trazabilidad |
| 3. Definido | Proceso caracterizado y proactivo | Objetivo de este Sprint 0 documental |
| 4. Cuantitativamente administrado | Control cuantitativo del proceso | No aplicado todavía |
| 5. Optimizado | Mejora continua basada en datos | No aplicado todavía |

La evaluación SCAMPI comprueba si se cumplen las metas y si las prácticas están implantadas; en este proyecto no hay evaluación formal, solo autocomprobación con listas de chequeo.

## 3. PSP y TSP
PSP aporta disciplina personal: estimación, registro de defectos y revisión del trabajo propio. TSP traslada esa disciplina al equipo. En Mely Faciales se aplican de forma ligera mediante estimaciones en el sprintboard, registro de cambios en el changelog y revisión de entregables con lista de chequeo antes de marcarlos `Done`. El detalle de estas prácticas individuales se documenta en el [documento 18](18-buenas-practicas-de-desarrollo-personal.md).

## 4. MCDAI
MCDAI es un modelo cubano de calidad para el desarrollo de aplicaciones informáticas, construido a partir de buenas prácticas internacionales y regulaciones nacionales. Organiza 12 procesos base en cuatro categorías y define tres niveles de madurez.

| Categoría | Procesos base |
|---|---|
| Gestión de la organización | Gestión de procesos de la organización, de portafolio, del conocimiento y de adquisiciones |
| Gestión de proyecto | Planificación, monitoreo y control de proyecto, y gestión de riesgos |
| Ingeniería | Ingeniería de requisitos, desarrollo de la solución técnica y pruebas de software |
| Soporte | Medición y análisis, aseguramiento de la calidad y gestión de la configuración |

| Nivel MCDAI | Característica |
|---|---|
| 1. Procesos definidos | Planificados, con roles y responsabilidades, ejecutados y monitoreados |
| 2. Procesos institucionalizados | Analizados con la dirección, medidos y con tratamiento de riesgos |
| 3. Optimización de procesos | Mejoras basadas en causas de problemas, conocimiento e innovación |

La evaluación valora el porcentaje de actividades realizadas como no implementado, parcialmente implementado, altamente implementado o completamente implementado.

![Niveles de madurez](<images/modelos-de-mejora-de-proceso/niveles-madurez.png>)

## 5. Aplicación a Mely Faciales
| Proceso base | Evidencia actual | Valoración |
|---|---|---|
| Ingeniería de requisitos | RF-01 a RF-09 y trazabilidad con los 8 casos de uso | Altamente implementado |
| Desarrollo de la solución técnica | Modelos de análisis, diseño, arquitectura e implementación | Altamente implementado |
| Pruebas de software | Casos de caja negra y caja blanca diseñados | Parcialmente implementado: ejecución en Sprint 1 |
| Gestión de la configuración | Git, changelog, nombres normalizados y alineados con el modelo | Altamente implementado |
| Planificación, monitoreo y control | Sprintboard y progreso de tareas | Altamente implementado |
| Medición y análisis | Conteos de diagramas, criterios de éxito y lista de chequeo | Parcialmente implementado |
| Aseguramiento de la calidad | Revisiones antes de marcar `Done` | Parcialmente implementado |
| Gestión de riesgos | Backlog priorizado y ADR operativo pendiente | Parcialmente implementado |

## 6. Diferencias entre modelos
| Aspecto | CMMI | MCDAI |
|---|---|---|
| Vocabulario | Áreas de proceso, metas y prácticas | Procesos base, requisitos y actividades |
| Evaluación | Según representación escalonada o continua | Según porcentaje de actividades de los procesos base |
| Niveles | Cinco de madurez | Tres de madurez |

## 7. Conclusión
Los modelos de mejora sirven para identificar qué prácticas conviene reforzar —especialmente métricas, ejecución de pruebas y gestión de riesgos— sin convertir un proyecto pequeño en un proceso certificable. La mejora se apoya en lo ya documentado y en las entregas iterativas de Scrum.
