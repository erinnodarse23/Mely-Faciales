# Buenas prácticas de desarrollo personal

> **Resumen ejecutivo.** Se adapta el PSP como marco de referencia individual para el trabajo de Erin en Mely Faciales, con Scrum como metodología única y UML como notación única. Los formularios de tiempo, defectos y tamaño, la estimación con PROBE, el valor ganado formal y las vistas de estado y secuencia se documentan como prácticas previstas: hoy solo existen la documentación y el seguimiento por tareas del sprintboard.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Buenas prácticas de métodos ágiles](19-buenas-practicas-de-metodos-agiles.md) · [Gestión de la configuración](17-gestion-de-la-configuracion.md) · [Planificación, integración, monitoreo y control](16-planificacion-monitoreo-y-control.md) · [Modelos de mejora](15-modelos-de-mejora-de-proceso.md)

## 1. Propósito y alcance
Este documento desarrolla las prácticas individuales que el documento 15 presenta de forma resumida y que el documento 16 deja fuera del monitoreo formal por falta de ejecución de código. El PSP se usa como marco de referencia individual: aporta disciplina personal sin sustituir a Scrum, que sigue siendo la metodología única del proyecto, y sin perseguir certificación ni evaluación formal. UML permanece como notación única, también para los tres flujos de actividad de este documento.

| Marco | Papel en Mely Faciales | Límite de aplicación |
|---|---|---|
| PSP | Referencia de disciplina individual: registro, estimación, revisión y mejora | Sin certificación; ninguna práctica se presenta como ejecutada sin evidencia |
| Scrum | Metodología única: sprints, backlog, ceremonias y Definition of Done | No se combina con otra metodología |
| UML | Notación única de modelos y flujos, exportados con Mermaid 11.4.1 | Un solo lenguaje de modelado |
| CMMI y TSP | Contexto de proceso por niveles, descrito en la sección 2 | Referencia de modelo, no evaluación |

El alcance se limita a la documentación de Sprint 0. No hay tiempos reales, conteos de líneas ni registros de defectos de código: esos datos aparecerán cuando comience Sprint 1.

## 2. Disciplina personal y motivación
La disciplina personal consiste en planificar con datos reales, medir la calidad del trabajo propio y revisar cada entrega antes de cerrarla. Como todavía no hay ejecución de código, el proyecto aplica hoy una versión simplificada de esa disciplina sobre los entregables documentales.

| Nivel | Práctica asociada | Situación en Mely Faciales |
|---|---|---|
| Individuo | PSP: plan personal, registro y revisión propia | Aplicado de forma simplificada en la documentación |
| Equipo | TSP: coordinación y responsabilidad compartida | Preparado para Sprint 1; hay una sola desarrolladora y Mely como voz del negocio |
| Organización | CMMI: proceso repetible y mejorable | Referencia para Sprint 1; sin evaluación formal |

La motivación es evitar estimaciones arbitrarias y detectar problemas en la fase en que aparecen, no acumular formularios vacíos. Para lograrlo, los datos que se recojan en Sprint 1 alimentarán las estimaciones y las revisiones posteriores.

## 3. Estructura incremental del PSP
Los niveles se introducen de forma gradual; cada uno añade una práctica sobre el anterior y no se declara alcanzado sin la evidencia correspondiente.

| Nivel | Práctica que introduce | Estado en Mely Faciales |
|---|---|---|
| PSP0 | Registrar tiempo, registrar defectos y definir estándar de defectos | Referencia para Sprint 1 |
| PSP0.1 | Estándar de codificación, métricas de tamaño y formulario PIP | Referencia para Sprint 1 |
| PSP1 | Estimación de tamaño con PROBE y reporte de pruebas | Referencia para Sprint 1 |
| PSP1.1 | Planificación de tareas y cronograma semanal | Aplicado de forma simplificada en la documentación |
| PSP2 | Revisiones de diseño y de código con listas de chequeo | Aplicado de forma simplificada en la documentación |
| PSP2.1 | Plantillas de diseño | Aplicado en la documentación |
| PSP3 | Desarrollo iterativo e incremental con reintegración | Preparado para Sprint 1 |

## 4. Registro de tiempo, defectos y tamaño
El registro se organiza por fase de trabajo y usa categorías de defectos estándar para que los hallazgos sean comparables entre entregas. El tamaño se expresa en LOC con sus distintas mediciones, sin inventar valores para el código que aún no existe.

| Elemento | Contenido previsto | Estado |
|---|---|---|
| Registro de tiempo | Tiempo invertido por fase: planificación, diseño, codificación, revisión y prueba | Referencia para Sprint 1 |
| Formulario LOGD | Registro cronológico de defectos con fase de origen y fase de corrección | Referencia para Sprint 1 |
| Estándar de defectos | Categorías comunes para clasificar cada hallazgo | Preparado para Sprint 1 |
| Estándar de codificación | Reglas de estilo y nombres aplicables al código de Spring Boot y React | Preparado para Sprint 1 |
| Formulario PIP | Plan de mejora personal con la práctica a incorporar en la siguiente iteración | Referencia para Sprint 1 |
| Retrospectiva del sprintboard | Ajuste de estimaciones y Definition of Ready al cerrar el sprint | Aplicado de forma simplificada |

| Medición LOC | Significado | Estado |
|---|---|---|
| Base | Líneas del programa al iniciar la tarea | Referencia para Sprint 1 |
| Eliminadas | Líneas retiradas durante el trabajo | Referencia para Sprint 1 |
| Modificadas | Líneas existentes cambiadas | Referencia para Sprint 1 |
| Adicionadas | Líneas añadidas sobre la base | Referencia para Sprint 1 |
| Reutilizadas | Líneas tomadas de código ya existente | Referencia para Sprint 1 |
| Nuevas | Líneas escritas desde cero | Referencia para Sprint 1 |
| Nuevas y modificadas | Suma de líneas nuevas y modificadas, usada como tamaño del cambio | Referencia para Sprint 1 |

El conteo real de LOC y el formulario LOGD relleno quedan como Referencia para Sprint 1. La única evidencia de mejora disponible hoy es la retrospectiva indicada en `SPRINTBOARD.md`.

## 5. Estimación con PROBE
PROBE organiza la estimación en objetos identificables, los clasifica en categorías lógica, E-S, cálculo, texto, datos y configuración, y asigna tamaños relativos antes de aplicar regresión lineal. La correlación indica cuánto se ajusta la relación entre tamaño y esfuerzo; el intervalo de predicción expresa la incertidumbre y la productividad convierte el tamaño estimado en esfuerzo.

| Elemento de PROBE | Uso previsto | Estado |
|---|---|---|
| Identificación de objetos | Dividir el trabajo en unidades estimables | Referencia para Sprint 1 |
| Categorías lógica, E-S, cálculo, texto, datos y configuración | Clasificar cada objeto antes de asignar tamaño | Referencia para Sprint 1 |
| Tamaños relativos | Comparar objetos entre sí sin depender de unidades absolutas | Referencia para Sprint 1 |
| Regresión lineal y correlación | Ajustar la relación entre tamaño y esfuerzo con datos propios | Referencia para Sprint 1 |
| Intervalo de predicción | Comunicar el rango probable de esfuerzo | Referencia para Sprint 1 |
| Productividad | Convertir tamaño en horas estimadas | Referencia para Sprint 1 |

No hay datos históricos de ejecución, por lo que todo el método queda como Referencia para Sprint 1. La evidencia actual se reduce a las estimaciones 3, 5 y 8 registradas en `SPRINTBOARD.md`, que son juicios iniciales y no resultados de regresión.

![Flujo de registro y estimación](<images/buenas-practicas-de-desarrollo-personal/flujo-registro-y-estimacion.png>)

## 6. Planificación de tareas y valor ganado
La planificación personal asigna valor planeado a cada tarea, ordena el cronograma semanal y compara el valor ganado con lo previsto. En el sprintboard hay 31/31 tareas Done, pero no se registran horas reales ni umbrales numéricos aprobados; por eso el seguimiento por tareas está aplicado de forma simplificada y el valor ganado formal queda como Referencia para Sprint 1.

| Elemento | Situación real | Estado |
|---|---|---|
| Tareas con valor planeado | Estimaciones 3, 5 y 8 en `SPRINTBOARD.md` | Aplicado de forma simplificada |
| Cronograma semanal | Fechas del Sprint 0 y construcciones previstas | Aplicado en la documentación |
| Seguimiento por tareas | Progreso 31/31 en el sprintboard | Aplicado de forma simplificada |
| Reglas 50-50, 0-100 y 20-70-100 | Descritas en la sección 5 del documento 16, sin umbrales aprobados | Referencia para Sprint 1 |
| Valor ganado formal | Sin horas reales ni línea base cuantitativa | Referencia para Sprint 1 |

La sección 5 del documento 16 ya indica que hoy no hay umbrales numéricos aprobados. Este documento mantiene esa misma posición y no introduce porcentajes de desviación que no han sido acordados.

## 7. Revisiones y detección temprana
Las listas de chequeo de diseño y de código se aplican antes de cerrar cada entrega. Cuanto más tarde se encuentra un defecto, mayor es el costo de corregirlo: por eso la revisión temprana reduce los defectos que llegan a compilación y prueba.

| Práctica | Evidencia real | Estado |
|---|---|---|
| Lista de chequeo de diseño | Revisión de documentos antes de marcarlos `Done` | Aplicado de forma simplificada |
| Lista de chequeo de código | Definición de lista para el código de Sprint 1 | Preparado para Sprint 1 |
| Corrección en fase temprana | Cambios registrados en Git y en `CHANGELOG.md` | Aplicado en la documentación |
| Ejecución de pruebas | Casos del documento 11, todavía no ejecutados | Referencia para Sprint 1 |
| Análisis posterior y formulario PIP | Mejora registrada en la retrospectiva del sprintboard | Aplicado de forma simplificada |

La Definition of Done del sprintboard funciona hoy como lista de chequeo vigente. Las pruebas automatizadas y su ejecución forman parte de Sprint 1.

![Flujo de revisión y calidad](<images/buenas-practicas-de-desarrollo-personal/flujo-revision-y-calidad.png>)

## 8. Completitud del diseño y desarrollo iterativo
La completitud del diseño se revisa en cuatro dimensiones: estática, dinámica, interna y externa. Cuatro plantillas de especificación sostienen esas vistas; la escalabilidad se logra descomponiendo el sistema en componentes que se desarrollan, integran y verifican por partes.

| Dimensión o plantilla | Cobertura actual | Estado |
|---|---|---|
| Vista estática e interna | Clases y paquetes en los documentos 02 a 09 | Aplicado en la documentación |
| Vista dinámica y externa | Colaboraciones, interfaces y despliegue en los documentos 02 a 09 | Aplicado en la documentación |
| Plantillas de especificación | Modelos usados en los documentos de análisis y diseño | Aplicado en la documentación |
| Vistas de estado y secuencia | No incluidas en el alcance actual | Referencia para Sprint 1 |
| Descomposición en componentes | Subsistemas y construcciones del documento 10 | Preparado para Sprint 1 |
| Desarrollo y reintegración | Previsto para los sprints 1 y 2 | Preparado para Sprint 1 |

El desarrollo iterativo e incremental se alinea con Sprint 1, dedicado al backend y al frontend, y con Sprint 2, dedicado al despliegue y a las decisiones operativas.

![Flujo de desarrollo incremental](<images/buenas-practicas-de-desarrollo-personal/flujo-desarrollo-incremental.png>)

## 9. Aplicación, trazabilidad y conclusiones
| Práctica | Evidencia real | Estado |
|---|---|---|
| Disciplina personal sobre entregables | Documentos revisados antes de cerrarse | Aplicado de forma simplificada |
| Registro de tiempo y defectos | Sin datos de ejecución de código | Referencia para Sprint 1 |
| Formularios LOGD y PIP | Diseñados como formato, sin datos reales | Referencia para Sprint 1 |
| Estimación con PROBE | Solo estimaciones 3, 5 y 8 del sprintboard | Referencia para Sprint 1 |
| Seguimiento por tareas | 31/31 tareas Done, sin horas reales | Aplicado de forma simplificada |
| Valor ganado formal | Sin umbrales numéricos aprobados | Referencia para Sprint 1 |
| Revisiones con lista de chequeo | Definition of Done del sprintboard | Aplicado de forma simplificada |
| Completitud de diseño | Clases, colaboraciones y construcciones en los documentos 02 a 10 | Aplicado en la documentación |
| Desarrollo iterativo e incremental | Sprints 1 y 2 previstos | Preparado para Sprint 1 |
| Ejecución de pruebas | Casos diseñados, no ejecutados | Referencia para Sprint 1 |

La trazabilidad se apoya en el documento 15, que introduce el PSP de forma resumida; en el documento 16, que fija el monitoreo y la ausencia de umbrales; en el documento 17, que conserva la evidencia en Git y en el registro de cambios; y en `SPRINTBOARD.md` y `CHANGELOG.md`, que muestran el avance real.

Con esta entrega queda claro qué disciplina personal ya se aplica sobre la documentación y qué partes exigen datos de Sprint 1. El compromiso es registrar tiempo, defectos y tamaño desde la primera construcción, alimentar PROBE con esos datos y mantener las revisiones antes de cada cierre, sin presentar como ejecutadas prácticas que hoy solo están previstas.
