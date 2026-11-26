# Planificación, integración, monitoreo y control

> **Resumen ejecutivo.** Se adaptan los marcos de referencia CMMI, PMBOK e ISO 31000 a la planificación, la integración, el monitoreo y el control de Mely Faciales, con Scrum como metodología única y UML como notación única. Cuatro flujos de actividad muestran cómo se estima el trabajo, se controlan los cambios, se gestionan los riesgos y se corrigen las desviaciones.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Gestión de la configuración](17-gestion-de-la-configuracion.md) · [Modelos de mejora](15-modelos-de-mejora-de-proceso.md) · [Fundamentos de gestión](14-fundamentos-de-gestion-de-proyectos.md) · [Organización y roles](13-organizacion-y-roles-del-proyecto.md)

## 1. Propósito y alcance
Este documento completa la disciplina de gestión ya registrada en los documentos 13 a 15. No sustituye a Scrum: le añade disciplina de estimación, línea base, seguimiento y control de cambios. Los marcos se emplean solo como referencia de prácticas aplicables al proyecto.

| Marco | Papel en Mely Faciales | Límite de aplicación |
|---|---|---|
| CMMI | Referencia de proceso para planificación (PP), monitoreo y control (PMC) y gestión de configuración | Autocomprobación con listas de chequeo; sin evaluación formal |
| PMBOK | Lista de control de cobertura: integración, alcance, cronograma, costos, calidad, recursos, comunicaciones, riesgos, adquisiciones e interesados | Guía de buenas prácticas, no metodología |
| ISO 31000 | Estructura para identificar, analizar, evaluar, tratar y monitorear riesgos | Aplica al registro cualitativo de la sección 7 |
| Scrum | Metodología única de trabajo: sprints, backlog, ceremonias y Definition of Done | No se combina con otra metodología |
| UML | Notación única de los modelos y de los cuatro flujos de actividad | Un solo lenguaje de modelado en todos los documentos |

El alcance queda delimitado por RF-01 a RF-09, CU-01 a CU-08 y por la decisión de no incluir agenda ni citas online. No se incorporan fuentes externas: solo prácticas verificables en el repositorio.

## 2. La planeación y el WBS
La EDT se deriva de la arquitectura en capas de `docs/03-modelo-de-diseno.md` y del orden de construcciones de `docs/10-plan-de-integracion.md`. Cada paquete de trabajo tiene una salida reconocible en el repositorio.

| Paquete de la EDT | Contenido real | Salida verificable |
|---|---|---|
| Documentación | Requisitos, análisis, diseño, arquitectura, implementación, pruebas y gestión | `docs/01` a `docs/16`, con 38 diagramas de diseño y gestión referenciados |
| Dominio e infraestructura | Entidades, repositorios, `DiscoMediaStorage` y esquema de PostgreSQL | Construcción 1 del documento 10 |
| Negocio | `ServicioService`, `ResenaService` y `WhatsAppLinkGenerator` | Construcción 2 del documento 10 |
| Interfaz y cliente | Controladores REST, DTO y cliente React | Construcciones 4 y 6 del documento 10 |
| Datos y medios | PostgreSQL y volumen externo de fotos y micro-videos | Documentos 05 y 08 |
| Cierre y operación | Despliegue y decisión de hosting pendiente | ADR previsto para Sprint 2 |

La estimación se alimenta de entradas ya presentes en el proyecto. CMMI PP organiza esas entradas en SG1, SG2 y SG3.

| Entrada de estimación y plan | Evidencia real | Tratamiento |
|---|---|---|
| Alcance | RF-01 a RF-09 y CU-01 a CU-08 | SG1: estimar el trabajo y los atributos de los productos |
| Esfuerzo | Estimaciones 3, 5 y 8 en el backlog de `SPRINTBOARD.md` | SG1 y SG2: esfuerzo y calendario por tarea |
| Costo | Sin presupuesto formal; decisión de infraestructura diferida | Se registrará en el ADR operativo |
| Calendario | Sprint 0 del 2026-11-25 al 2026-12-08; sprints 1 y 2 previstos | SG2: presupuesto y cronograma |
| Riesgos | Matriz de la sección 7 | SG2: identificación y análisis |
| Datos | Esquema relacional y medios fuera de la base de datos | SG2: plan de gestión de datos |
| Recursos | Un desarrollador con apoyo de la dueña del negocio | SG2: plan de recursos |
| Conocimiento | Java 25 LTS, Spring Boot 4.1, React y TypeScript ya definidos | SG2: conocimiento y habilidades necesarias |
| Involucrados | Mely, Erin, clientas y proveedor de hosting | SG2 y SG3: participación y revisión del plan |
| Compromiso | Definition of Ready y Definition of Done del sprintboard | SG3: revisión y compromisos |
| Herramientas | Git, `CHANGELOG.md`, `SPRINTBOARD.md` y Mermaid CLI 11.4.1 | Infraestructura y herramientas del plan |

![Flujo de planificación](<images/planificacion-y-control/flujo-planificacion.png>)

## 3. Integración y control de cambios
La integración coordina documentación, backend, frontend y despliegue. El control de cambios evita que una solicitud modifique la línea base sin evaluación de impacto.

| Práctica | Tratamiento en Mely Faciales | Estado |
|---|---|---|
| Acta de constitución | Visión y autorización en `README.md` y en la visión de `SPRINTBOARD.md`, con Mely como patrocinadora | Aplicado de forma simplificada |
| Plan para la dirección | Sprintboard, plan de construcciones y documentos 01 a 18 | Aplicado |
| Dirigir y gestionar el trabajo | Erin ejecuta la documentación en el orden previsto; las construcciones de código están previstas para Sprint 1 | Aplicado en la documentación |
| Gestionar el conocimiento | Cambios y decisiones registrados en `CHANGELOG.md` | Aplicado |
| Monitorear el trabajo | Progreso de tareas en `SPRINTBOARD.md` | Aplicado |
| Control integrado de cambios | Cada cambio se documenta con mensaje de commit y entrada de changelog | Aplicado |
| CCC | Comité formal aún no constituido; las decisiones las toman Erin y Mely | Referencia para Sprint 1 |
| Control de configuración | Git, nombres sin acentos, carpetas por documento y líneas base; detalle en el documento 17 | Aplicado |
| Cierre de proyecto o fase | Fin de sprint con revisión del incremento; ADR operativo previsto para Sprint 2 | Preparado para el cierre de Sprint 0 |

| Control de configuración | Control de cambios |
|---|---|
| Conserva versiones, nombres y líneas base en Git | Evalúa el impacto antes de autorizar una modificación |
| Responde a la pregunta qué versión está vigente | Responde a la pregunta si el cambio se acepta |
| `CHANGELOG.md` registra cada versión | El CCC formal queda como referencia futura |

![Flujo de control de cambios](<images/planificacion-y-control/flujo-control-de-cambios.png>)

## 4. Planificación por áreas
| Área | Práctica prevista | Evidencia real |
|---|---|---|
| Cronograma | Gantt y ruta crítica como técnicas de referencia; estimación y control por sprint | Sprint 0 fechado y construcciones 1 a 6 del documento 10 |
| Alcance | EDT y línea base de RF y CU | RF-01 a RF-09, CU-01 a CU-08 y documento 03 |
| Interesados | Identificación, expectativas y atención por rol | Tabla de interesados del documento 14 |
| Riesgos | Matriz probabilidad-impacto y estrategias evitar, transferir, mitigar y aceptar | Sección 7 de este documento |
| Comunicaciones | Documentación en el repositorio, changelog y revisiones de sprint | `README.md`, `CHANGELOG.md` y `SPRINTBOARD.md` |
| Costos | Registro de decisiones con impacto de infraestructura | Sin presupuesto formal; ADR posterior |
| Adquisiciones | Servicios externos previstos | Dominio, hosting y WhatsApp como canal |

La criticidad se obtiene al combinar probabilidad e impacto en una escala cualitativa de tres niveles.

| Impacto \ Probabilidad | Baja | Media | Alta |
|---|---|---|---|
| Alta | Media | Alta | Alta |
| Media | Baja | Media | Alta |
| Baja | Baja | Baja | Media |

![Flujo de gestión de riesgos](<images/planificacion-y-control/flujo-gestion-de-riesgos.png>)

## 5. Monitoreo y control
CMMI PMC define dos metas específicas. SG1 monitorea el trabajo frente al plan mediante siete prácticas: parámetros de planificación, compromisos, riesgos, gestión de datos, participación de interesados, revisiones de progreso y revisiones de hitos. SG2 gestiona las acciones correctivas hasta el cierre mediante tres prácticas: analizar problemas, emprender acciones correctivas y gestionar esas acciones.

| Punto de monitoreo | Parámetro observado | Evidencia |
|---|---|---|
| Fin de cada construcción | Componentes entregados y prueba asociada | Documento 10 |
| Fin de sprint | Tareas Done y Definition of Done | `SPRINTBOARD.md` |
| Registro de cambios | Mensaje de commit y entrada de changelog | Git y `CHANGELOG.md` |
| Revisión de riesgos | Criticidad y respuesta de la sección 7 | Este documento |
| Validación con la dueña | Alcance y contenido del incremento | Revisión de sprint con Mely |

Una desviación significativa aparece cuando el avance real se aparta de la línea base aprobada más allá de la tolerancia definida. Hoy no hay umbrales numéricos aprobados: su definición queda como referencia para Sprint 1.

| Regla de progreso | Cómo asigna el valor | Uso previsto |
|---|---|---|
| 50-50 | 50 % al iniciar la tarea y 50 % al terminarla | Referencia para sprints futuros |
| 0-100 | 0 % hasta la finalización y 100 % al cerrarla | Referencia para sprints futuros |
| 20-70-100 | 20 % al inicio, 70 % en un hito intermedio y 100 % al cierre | Referencia para sprints futuros |

El valor ganado (EVM) usaría estas reglas para comparar valor planificado, valor ganado y costo real; se mantiene como referencia para Sprint 1 porque todavía no hay ejecución de código. Las acciones correctivas previstas son actualizar el plan, traslapar actividades (*fast tracking*) y añadir recursos (*crashing*), esta última de aplicabilidad limitada con un solo desarrollador.

![Flujo de monitoreo y acciones](<images/planificacion-y-control/flujo-monitoreo-y-acciones.png>)

## 6. Aplicación en Mely Faciales
| Práctica | Evidencia real en el proyecto | Estado |
|---|---|---|
| Planificación CMMI PP | `SPRINTBOARD.md` con 30 tareas Done previas y 31/31 tras incorporar DOC-23 | Aplicado |
| EDT y línea base | Construcciones del documento 10 y documentos 01 a 18 | Aplicado |
| Estimación | Estimaciones 3, 5 y 8 en el backlog | Aplicado |
| Calendario | Sprint 0 del 2026-11-25 al 2026-12-08 y sprints futuros previstos | Aplicado |
| Registro de riesgos | Matriz cualitativa de la sección 7 y ADR operativo pendiente | Aplicado |
| Análisis cuantitativo de riesgos | Solo matriz cualitativa; sin simulación numérica | Referencia para Sprint 1 |
| Monitoreo | Progreso del sprintboard y registro histórico del changelog | Aplicado |
| Integración y cambios | Commits de Git y entradas de `CHANGELOG.md` | Aplicado |
| CCC formal | Decisiones registradas por Erin y Mely, sin comité constituido | Referencia para Sprint 1 |
| Control de configuración | Git, nombres normalizados y carpetas por documento | Aplicado |
| Valor ganado (EVM) | Reglas 50-50, 0-100 y 20-70-100 descritas, sin ejecución real | Referencia para Sprint 1 |
| Ejecución de pruebas | Casos diseñados en el documento 11 | Referencia para Sprint 1 |
| Canal de WhatsApp | Número `+5355795510` y deep link en RF-05 | Aplicado |
| Stack definido | Java 25 LTS con Spring Boot 4.1 y React con TypeScript en los documentos 03 y 09 | Aplicado |

Los estados indicados en esta tabla distinguen lo que ya está documentado de lo que solo se prevé ejecutar en Sprint 1.

## 7. Matriz de riesgos del proyecto
| Riesgo | Probabilidad | Impacto | Criticidad | Respuesta | Responsable |
|---|---|---|---|---|---|
| Retraso en los micro-videos del catálogo (RF-02 y RF-09) | Media | Alta | Alta | Mitigar: estimar la entrega por servicio y registrar bloqueos en el sprintboard | Erin Nodarse |
| Mely no puede validar reseñas y contenido a tiempo | Media | Alta | Alta | Mitigar: revisiones al cierre de cada sprint y priorización como dueña del negocio | Mely, con Erin como traductora de requisitos |
| Despliegue pendiente y *cold starts* visibles (RNF-07) | Alta | Media | Alta | Transferir: decidir proveedor de hosting y condiciones en el ADR operativo | Proveedor de hosting, con Erin como responsable de la decisión |
| Corrupción de alcance por nuevas peticiones al catálogo | Media | Alta | Alta | Evitar: evaluar impacto antes de aceptar y mantener fuera de alcance las citas online | Erin Nodarse y Mely |
| Casos de prueba diseñados pero no ejecutados en Sprint 1 | Alta | Media | Alta | Mitigar: ejecutar los casos del documento 11 en cada construcción | Erin Nodarse |
| Fallo del canal externo de WhatsApp `+5355795510` | Baja | Alta | Media | Aceptar: conservar el número documentado y prever contacto manual | Erin Nodarse |

## 8. Trazabilidad
La trazabilidad se apoya en `SPRINTBOARD.md`, `CHANGELOG.md` y en los documentos 01 a 18, sin introducir fuentes externas.

| Sección de este documento | Evidencia de origen |
|---|---|
| 1. Propósito y alcance | Documentos 13, 14 y 15; RF-01 a RF-09 y CU-01 a CU-08 en el documento 01 |
| 2. La planeación y el WBS | `SPRINTBOARD.md` y construcciones del documento 10 |
| 3. Integración y control de cambios | `CHANGELOG.md`, Git y documentos 03 y 14 |
| 4. Planificación por áreas | Documentos 01, 02, 05 y 14 |
| 5. Monitoreo y control | `SPRINTBOARD.md` y documento 11 |
| 6. Aplicación en Mely Faciales | Documentos 01 a 15, `CHANGELOG.md` y Git |
| 7. Matriz de riesgos | Documentos 05, 10, 11 y 14 |
| 8. Trazabilidad | Este propio mapa |
| 9. Conclusiones y compromisos | `SPRINTBOARD.md` y documento 10 |

## 9. Conclusiones y compromisos
La planificación ya existe en `SPRINTBOARD.md` y en los documentos 01 a 18; la integración y el control se apoyan en Git y en `CHANGELOG.md`. El valor añadido de este documento es hacer explícitos los puntos de monitoreo, la evaluación de impacto y las respuestas ante riesgos concretos.

Los compromisos para las entregas siguientes son ejecutar los casos del documento 11 en Sprint 1, formalizar el CCC y el valor ganado cuando exista ejecución real, cerrar el ADR de hosting en Sprint 2, mantener la línea base sin citas online y conservar el número de WhatsApp `+5355795510`. El avance registrado es 31/31 tareas Done tras incorporar DOC-23.
