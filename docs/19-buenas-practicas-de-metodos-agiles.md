# Buenas prácticas de métodos ágiles

> **Resumen ejecutivo.** Se toman los valores del manifiesto ágil, los roles, artefactos y ceremonias de Scrum y varias prácticas de XP como referencia de buenas prácticas para Mely Faciales. Scrum sigue siendo la metodología única: no se combina con XP ni se persigue certificación. Tres flujos de actividad muestran el ciclo de sprint, la conversión de historias en entregas y la mejora continua.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Buenas prácticas de desarrollo personal](18-buenas-practicas-de-desarrollo-personal.md) · [Gestión de la configuración](17-gestion-de-la-configuracion.md) · [Planificación, integración, monitoreo y control](16-planificacion-monitoreo-y-control.md)

## 1. Propósito y alcance
Este documento recoge prácticas ágiles ya visibles en los documentos 15 a 18 y las organiza para su aplicación en los sprints con código. El documento 15 sitúa a Scrum en el contexto de los modelos de mejora, el 16 fija la planificación y el monitoreo, el 17 conserva la evidencia en Git y el cambio, y el 18 describe la disciplina individual del PSP.

| Marco | Papel en Mely Faciales | Límite de aplicación |
|---|---|---|
| Scrum | Metodología única: roles, artefactos, ceremonias y sprints | No se combina con otra metodología |
| Manifiesto ágil | Referencia de valores y principios para priorizar el trabajo | Sin certificación ni declaración de adhesión formal |
| XP | Referencia de prácticas de ingeniería y de relación con la clienta | No se adopta como método paralelo |
| UML | Notación única de los modelos y de los tres flujos de actividad | Un solo lenguaje de modelado |

El alcance es la documentación de Sprint 0. No se presentan prácticas de ejecución de código como realizadas: las que requieren horas reales, pruebas automatizadas o integración continua quedan marcadas como previstas.

## 2. Contexto ágil y manifiesto
Los cuatro valores se interpretan en la realidad de un proyecto con una sola desarrolladora y una dueña del negocio que valida el contenido.

| Valor | Interpretación en Mely Faciales | Estado |
|---|---|---|
| Personas e interacción | Mely participa en la definición y revisión del incremento | Aplicado de forma simplificada |
| Software que funciona | El incremento con código se prevé para Sprint 1 | Preparado para Sprint 1 |
| Colaboración con la clienta | Revisiones de sprint y decisiones registradas por Mely y Erin | Aplicado de forma simplificada |
| Respuesta al cambio | Cambios evaluados por impacto y registrados en Git y en el changelog | Aplicado de forma simplificada |

Los principios aplicables son la entrega temprana y continua, la aceptación de requisitos cambiantes, el trabajo conjunto durante el proyecto y la reflexión periódica para ajustar el proceso. La entrega continua de código y la reflexión con datos reales quedan como Referencia para Sprint 1; la reflexión documental ya se realiza al cerrar cada entrega.

## 3. Roles y ceremonias de Scrum
Los tres roles de Scrum se concentran en dos personas. Mely ejerce la voz del negocio y Erin reúne la traducción de requisitos, la facilitación y el desarrollo.

| Rol de Scrum | Adaptación real en Mely Faciales | Estado |
|---|---|---|
| Product Owner | Mely prioriza el catálogo, las reseñas y el canal de WhatsApp como voz del negocio | Aplicado de forma simplificada |
| Scrum Master | Erin facilita el flujo de trabajo y mantiene el sprintboard y el changelog | Aplicado de forma simplificada |
| Equipo de desarrollo | Erin ejecuta la documentación y ejecutará las construcciones de código | Preparado para Sprint 1 |

Las duraciones de referencia del material son cuatro a ocho horas para la planeación, quince minutos para el diario, cuatro horas para la revisión y tres horas para la retrospectiva. Con una sola persona esos tiempos no se aplican literalmente: el diario es asíncrono y se registra en `SPRINTBOARD.md`, y las ceremonias se adaptan al cierre de cada entrega.

| Ceremonia | Duración de referencia | Aplicación real | Estado |
|---|---|---|---|
| Planeación del sprint | 4 a 8 h | Backlog y estimaciones del sprintboard | Aplicado de forma simplificada |
| Diario | 15 min | Registro asíncrono, sin reunión diaria presencial | Aplicado de forma simplificada |
| Revisión del incremento | 4 h | Revisión documental con Mely al cerrar la entrega | Aplicado de forma simplificada |
| Retrospectiva | 3 h | Ajuste de estimaciones y Definition of Ready en el sprintboard | Aplicado de forma simplificada |

![Flujo de sprint y ceremonias](<images/buenas-practicas-de-metodos-agiles/flujo-sprint-y-ceremonias.png>)

## 4. Artefactos y seguimiento visual
Los artefactos de Scrum ya tienen una correspondencia concreta en el repositorio. El seguimiento se hace por tareas y no por horas, porque todavía no hay ejecución de código.

| Artefacto | Correspondencia real | Estado |
|---|---|---|
| Product backlog | `SPRINTBOARD.md` con RF-01 a RF-09 y CU-01 a CU-08 | Aplicado |
| Sprint backlog | Tareas DOC-01 a DOC-23 del sprintboard | Aplicado |
| Incremento | Documentos 01 a 18 y su registro en `CHANGELOG.md` | Aplicado en la documentación |
| Lista de impedimentos | Decisiones pendientes de hosting y ejecución de pruebas en el sprintboard | Aplicado de forma simplificada |
| Seguimiento por tareas | Progreso 31/31 en el sprintboard | Aplicado de forma simplificada |
| Gráfica burndown | Sin horas reales registradas | Referencia para Sprint 1 |
| Gráfica burn-up | Sin alcance ejecutado medido | Referencia para Sprint 1 |

Las gráficas burndown y burn-up se dibujarán cuando existan datos de ejecución. Hoy sustituyen esas curvas el conteo de tareas Done y el registro histórico de entregas.

## 5. Historias de usuario
El formato de referencia es «Como (rol) quiero (algo) para poder (beneficio)». Una historia útil es independiente, negociable, valiosa, estimable, pequeña y verificable.

| Característica | Cómo se reconoce en el proyecto | Estado |
|---|---|---|
| Independiente | Cada RF se puede priorizar por separado | Aplicado en la documentación |
| Negociable | El alcance se acuerda con Mely antes de cerrarlo | Aplicado de forma simplificada |
| Valiosa | Los RF se vinculan al catálogo, las reseñas o el contacto | Aplicado en la documentación |
| Estimable | Estimaciones 3, 5 y 8 en el sprintboard | Aplicado de forma simplificada |
| Pequeña | Las tareas DOC se dividen por entrega documental | Aplicado en la documentación |
| Verificable | Criterios de aceptación en el documento 01 | Aplicado en la documentación |

La evidencia real son RF-01 a RF-09, con actores y criterios de aceptación en el documento 01, y su seguimiento en `SPRINTBOARD.md`. El formato literal que empieza con «Como...» no aparece redactado todavía: queda como Referencia para Sprint 1.

![Flujo de historias y entregas](<images/buenas-practicas-de-metodos-agiles/flujo-historias-y-entregas.png>)

## 6. Prácticas XP aplicables
XP se usa solo como lista de prácticas útiles. Cada una se evalúa con el estado que corresponde a la situación real del proyecto.

| Práctica de XP | Aplicación en Mely Faciales | Estado |
|---|---|---|
| Diseño simple | Arquitectura en capas y alcance sin referencias externas ni servicios añadidos | Aplicado de forma simplificada |
| Metáforas | Lenguaje común previsto para nombrar servicios y reseñas | Preparado para Sprint 1 |
| Estándares de codificación | Convención de nombres definida, pendiente de aplicar al código | Preparado para Sprint 1 |
| Versiones pequeñas | Construcciones del documento 10 como entregas reducidas | Preparado para Sprint 1 |
| Refactorización | Sin código ejecutado todavía | Referencia para Sprint 1 |
| Desarrollo guiado por pruebas | Casos de los documento 11, no ejecutados | Referencia para Sprint 1 |
| Programación en pareja | Una sola desarrolladora en el proyecto | No aplicable con una sola desarrolladora |
| Código de todos | Sin código compartido aún | Referencia para Sprint 1 |
| Integración continua | Sin canal de integración con código | Referencia para Sprint 1 |
| Ritmo sostenible | Sprint 0 documental con entregas acotadas | Aplicado de forma simplificada |
| Cliente in situ | Mely valida el contenido en las revisiones | Aplicado de forma simplificada |
| Prueba del cliente | Aceptación formal prevista para Sprint 1 | Referencia para Sprint 1 |

La programación en pareja no se simula: no hay segunda desarrolladora y no se inventa una. Las prácticas que exigen código quedan previstas para la fase de implementación.

## 7. Calidad, entrega continua y definición de terminado
La calidad se sostiene con pruebas automáticas, integración continua y mini-versiones. La Definition of Done del sprintboard es hoy el filtro vigente antes de marcar una tarea como terminada.

| Elemento | Evidencia real | Estado |
|---|---|---|
| Pruebas automáticas | Casos de caja negra y caja blanca en los documento 11 | Referencia para Sprint 1 |
| Integración continua | Sin ejecución de código ni canal automatizado | Referencia para Sprint 1 |
| Mini-versiones | Construcciones previstas en el documento 10 | Preparado para Sprint 1 |
| Definition of Done | Lista de chequeo del sprintboard | Aplicado de forma simplificada |
| Registro de cada entrega | Git y `CHANGELOG.md`, según el documento 17 | Aplicado |

Cuando comience Sprint 1, las pruebas de los documento 11 se ejecutarán antes de cerrar cada construcción y su resultado quedará en Git y en `CHANGELOG.md`. La entrega continua y la integración automática no se presentan como prácticas ya activas.

![Flujo de calidad y mejora continua](<images/buenas-practicas-de-metodos-agiles/flujo-calidad-y-mejora-continua.png>)

## 8. Adaptación a un equipo de una sola persona
Los roles que XP reparte entre varias personas se concentran en Erin y Mely. No se introduce una segunda desarrolladora: las responsabilidades técnicas recaen en Erin y la voz del negocio en Mely.

| Rol de XP | Responsable real | Estado |
|---|---|---|
| Programador | Erin, autora de la documentación y futura implementación | Preparado para Sprint 1 |
| Cliente | Mely, dueña del negocio y priorizadora del alcance | Aplicado de forma simplificada |
| Pruebas | Erin, con los casos de los documento 11 | Preparado para Sprint 1 |
| Seguimiento | Erin, con progreso y estimaciones en el sprintboard | Aplicado de forma simplificada |
| Entrenador | Sin figura asignada; acompañamiento previsto para Sprint 1 | Referencia para Sprint 1 |
| Consultor | Mely como fuente de decisiones de producto | Aplicado de forma simplificada |
| Gestor | Erin, con planificación y registro en el repositorio | Aplicado de forma simplificada |

La concentración de roles reduce la coordinación, pero también concentra el riesgo. Por eso las responsabilidades que requieren ejecución de código, acompañamiento y pruebas automatizadas quedan como referencia futura y no se declaran cubiertas.

## 9. Aplicación, trazabilidad y conclusiones
| Práctica | Evidencia real | Estado |
|---|---|---|
| Metodología de trabajo | Scrum declarado en `README.md` y en `SPRINTBOARD.md` | Aplicado |
| Valores y principios ágiles | Priorización y revisiones con Mely | Aplicado de forma simplificada |
| Roles y ceremonias | Adaptación a dos personas, con diario asíncrono | Aplicado de forma simplificada |
| Artefactos de Scrum | Backlog, tareas e incremento en el repositorio | Aplicado |
| Seguimiento visual | 31/31 tareas Done, sin horas reales | Aplicado de forma simplificada |
| Historias de usuario | RF-01 a RF-09 con actores y criterios en el documento 01 | Aplicado en la documentación |
| Formato literal de historia | Sin redacción «Como...» registrada | Referencia para Sprint 1 |
| Prácticas de ingeniería de XP | Diseño simple, estándares y ritmo sostenible | Preparado para Sprint 1 |
| Programación en pareja | Una sola desarrolladora | No aplicable con una sola desarrolladora |
| Pruebas automatizadas e integración continua | Sin ejecución de código | Referencia para Sprint 1 |
| Definition of Done | Lista de chequeo del sprintboard y registro en Git | Aplicado de forma simplificada |

La trazabilidad se apoya en los documentos 13 a 18, en `SPRINTBOARD.md` y en `CHANGELOG.md`: los roles y el monitoreo explican quién decide y cómo se mide, la gestión de la configuración conserva la evidencia, y el PSP aporta la disciplina individual que complementa a Scrum sin sustituirlo.

Con esta entrega, las prácticas ágiles quedan descritas con la misma honestidad que el resto de la documentación: lo que ya se aplica sobre entregables, lo que está preparado para Sprint 1 y lo que no tiene sentido con una sola desarrolladora. El compromiso es mantener Scrum como metodología única y usar el manifiesto y XP solo como referencia de prácticas.
