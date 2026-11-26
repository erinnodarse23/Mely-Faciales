# Organización y roles del proyecto

> **Resumen ejecutivo.** Se adaptan los seis roles de gestión del proyecto a la realidad de Mely Faciales: una sola persona, Erin Nodarse, asume todas las responsabilidades a lo largo del ciclo, con Mely como voz del negocio. El temario, el calendario y la bibliografía de la asignatura no forman parte de la documentación del producto.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Fundamentos de gestión](14-fundamentos-de-gestion-de-proyectos.md) · [Modelos de mejora](15-modelos-de-mejora-de-proceso.md) · [Plan de integración](10-plan-de-integracion.md)

## 1. Criterio de aplicación
Los roles describen responsabilidades, no puestos adicionales. En Mely Faciales se mantienen los roles de Scrum —Product Owner, Scrum Master y equipo de desarrollo— y se les asocian las tareas de los seis roles de gestión. Mely participa como Product Owner en las decisiones de negocio; Erin ejecuta el resto de los roles. Cada entregable se revisa con su lista de chequeo antes de marcar la tarea como `Done` en `SPRINTBOARD.md`.

## 2. Mapa de roles
| Rol | Responsabilidad en Mely Faciales | Evidencia en el repositorio |
|---|---|---|
| Jefe de proyecto | Mantener el alcance, los objetivos y los riesgos bajo control | `SPRINTBOARD.md`, `CHANGELOG.md` y ADRs operativos previstos |
| Planificador | Priorizar el backlog, estimar y ordenar la integración | `SPRINTBOARD.md` y `docs/10-plan-de-integracion.md` |
| Analista-diseñador | Obtener requisitos, modelar el análisis y el diseño | `docs/01-requisitos-funcionales-y-no-funcionales.md` a `docs/09-modelo-de-implementacion.md` |
| Gestor de configuración | Versionar, nombrar, referenciar y conservar las líneas base | Git, nombres sin acentos, carpetas de imágenes por documento y `CHANGELOG.md` |
| Programador | Implementar backend Java 25 LTS y frontend React | `docs/09-modelo-de-implementacion.md` y construcciones del documento 10 |
| Probador | Diseñar y ejecutar casos de caja negra y caja blanca | `docs/11-gestion-de-la-calidad-y-pruebas.md` |

La matriz RACI simplificada reparte la decisión, la ejecución y la consulta sin crear puestos nuevos:

| Rol | Decide | Ejecuta | Consulta |
|---|---|---|---|
| Jefe de proyecto | Erin, con Mely en alcance y objetivos | Erin | Mely |
| Planificador | Erin, con prioridades validadas por Mely | Erin | Mely |
| Analista-diseñador | Mely en requisitos; Erin en el modelado | Erin | Mely |
| Gestor de configuración | Erin | Erin | Mely cuando cambia el contenido visible |
| Programador | Erin | Erin | Mely en dudas del catálogo |
| Probador | Erin | Erin | Mely para la aceptación |

## 3. Listas de chequeo por entregable
| Entregable | Comprobaciones antes de aceptarlo | Evidencia concreta |
|---|---|---|
| Alcance y plan | Casos de uso vinculados a requisitos, prioridad asignada y construcciones ordenadas. | `SPRINTBOARD.md` con los RF y CU, y `docs/10-plan-de-integracion.md` con las seis construcciones. |
| Requisitos y modelo | Trazabilidad RF a CU, UML coherente y 0 referencias de imagen rotas. | `docs/01-requisitos-funcionales-y-no-funcionales.md`, modelos de `docs/02` a `docs/09` y 68 PNG referenciados sin roturas. |
| Configuración | Nombres de archivo sin acentos, commit con mensaje claro y push verificado. | `git status -sb` limpio, HEAD local igual a `origin/main` y entradas de `CHANGELOG.md`. |
| Código | Compilación correcta y pruebas unitarias asociadas a la construcción. | Resultado de la compilación y de las pruebas de la construcción, con el commit que las incorpora. |
| Pruebas | Casos ejecutados, resultados registrados y defectos de severidad alta resueltos. | Los quince casos del documento 11 y su registro real de resultados en Git desde Sprint 1. |

La evidencia se conserva en el repositorio: una tarea no se marca `Done` sin el artefacto que la demuestra, y lo previsto para Sprint 1 o Sprint 2 se declara como referencia, sin presentarlo como ejecutado.

## 4. Conclusión
La concentración de roles en una sola persona reduce la coordinación, pero hace más importante la disciplina: documentar, versionar y revisar con lista de chequeo cada entregable.
