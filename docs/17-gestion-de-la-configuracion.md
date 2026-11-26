# Gestión de la configuración

> **Resumen ejecutivo.** Se describe cómo Mely Faciales identifica, conserva y controla sus elementos de configuración con CMMI CM como marco de referencia, Scrum como metodología única y UML como notación única. Git, GitHub y `CHANGELOG.md` sostienen las líneas base y el registro de cambios; el comité de control de cambios formal y las ramas distintas de `main` se documentan como prácticas previstas, no como prácticas ya ejecutadas.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Buenas prácticas de desarrollo personal](18-buenas-practicas-de-desarrollo-personal.md) · [Planificación, integración, monitoreo y control](16-planificacion-monitoreo-y-control.md) · [Modelos de mejora](15-modelos-de-mejora-de-proceso.md) · [Fundamentos de gestión](14-fundamentos-de-gestion-de-proyectos.md)

## 1. Propósito y alcance
Este documento complementa el documento 16, donde el control de configuración aparece como práctica aplicada y el comité de control de cambios (CCC) como referencia futura. CMMI CM se emplea como marco de referencia de proceso; no implica evaluación formal ni certificación. Scrum es la metodología única de trabajo y UML la notación única de los modelos, incluidos los tres flujos de actividad de este documento.

| Marco | Papel en Mely Faciales | Límite de aplicación |
|---|---|---|
| CMMI CM | Referencia para identificar elementos, establecer el sistema, crear líneas base, controlar cambios y auditar | Autocomprobación con listas de chequeo; sin evaluación formal |
| Scrum | Metodología única: sprints, backlog, ceremonias y Definition of Done | No se combina con otra metodología |
| UML | Notación única de los modelos, con diagramas de actividad exportados con Mermaid 11.4.1 | Un solo lenguaje de modelado en todos los documentos |
| Git y GitHub | Sistema de versiones y repositorio remoto | Herramienta, no metodología |

La tabla siguiente resume los tres objetivos específicos y las siete prácticas de CMMI CM con su aplicación real y su estado.

| Objetivo | Práctica | Aplicación real en Mely Faciales | Estado |
|---|---|---|---|
| SG1 Establecer líneas base | SP1.1 Identificar elementos de configuración | Documentos 01 a 17, PNG, `SPRINTBOARD.md` y `CHANGELOG.md`; el código queda previsto para Sprint 1 | Aplicado |
| SG1 Establecer líneas base | SP1.2 Establecer el sistema de gestión de la configuración | Git y GitHub con carpetas por documento y convención de nombres | Aplicado |
| SG1 Establecer líneas base | SP1.3 Crear y liberar líneas base | Cada cierre de entrega se identifica con un commit y su entrada en el changelog | Aplicado |
| SG2 Supervisar y controlar los cambios | SP2.1 Seguimiento de solicitudes de cambio | Solicitudes registradas en `SPRINTBOARD.md` y `CHANGELOG.md` | Aplicado |
| SG2 Supervisar y controlar los cambios | SP2.2 Controlar cambios en elementos bajo configuración | Análisis de impacto y acuerdo con Mely y Erin antes de cada cambio | Aplicado |
| SG3 Establecer la integridad | SP3.1 Registros de configuración | Historial de Git y entradas de `CHANGELOG.md` | Aplicado |
| SG3 Establecer la integridad | SP3.2 Auditorías de configuración | Lista de chequeo con 67 referencias de imagen vigentes y 0 rutas rotas | Aplicado |

## 2. Motivación y conceptos
La configuración del software reúne los productos de trabajo de Sprint 0 y sus descripciones, de modo que cada entrega pueda reconstruirse y explicarse. El proyecto todavía no tiene código en ejecución: hoy se controla la documentación y queda prevista la incorporación del código de Sprint 1.

| Concepto | Definición aplicada al proyecto |
|---|---|
| Configuración del software | Conjunto de documentos, imágenes, fuentes de diagramas y registros que describen el sistema en un momento dado |
| Elemento de configuración | Producto de trabajo identificado de forma única y bajo control de versiones, como un documento, un PNG o `SPRINTBOARD.md` |
| Línea base | Conjunto aprobado de elementos en un momento determinado, identificado por un commit y su registro en el changelog |

| Término | Significado en Mely Faciales |
|---|---|
| VCS | Sistema de control de versiones; Git es el sistema utilizado |
| SCM | Gestión de la configuración del software; CMMI CM describe las prácticas de referencia |
| CMS | Sistema de gestión de la configuración; GitHub con sus carpetas y convenciones cumple este papel |

## 3. Elementos documentados y su control
| Elemento | Ubicación | Situación |
|---|---|---|
| Documentos 01 a 18 | `docs/*.md` | Aplicado: 18 documentos versionados |
| Diagramas exportados | `docs/images/`, una carpeta por documento | Aplicado: 68 PNG, sin regeneraciones fuera de su entrega |
| Fuentes de diagramas | Directorio temporal `mely-mmd`, fuera del repositorio | Referencia externa: 68 fuentes `.mmd` en el directorio temporal, UTF-8 sin BOM, fuera del repositorio |
| Seguimiento del sprint | `SPRINTBOARD.md` | Aplicado: backlog, progreso 31/31 y conteo actual |
| Historial de cambios | `CHANGELOG.md` | Aplicado: entradas de Added y Changed |
| Código de la aplicación | Previsto en el documento 10 con Java 25 LTS, Spring Boot 4.1, React y TypeScript | Referencia para Sprint 1 |
| Fotos y micro-videos del catálogo | Almacenamiento externo previsto, fuera del repositorio | Se controla la referencia documental, no el binario |

Las fotos y los micro-videos no se versionan en Git: el repositorio conserva solo la referencia a su ubicación futura y la decisión de mantenerlos fuera del modelo de datos relacional.

## 4. Identificación y convención de nombres
Los nombres de archivos y carpetas no llevan acentos y cada documento tiene una carpeta propia de imágenes. La identificación usa los nombres ya existentes: no se renombra ningún archivo actual. La regla simple es documento `NN-tema-sin-acentos.md`, imagen `flujo-nombre.png` con su fuente `flujo-nombre.mmd`, y requisitos o casos de uso con prefijo y número consecutivo.

| Elemento | Regla | Ejemplo real |
|---|---|---|
| Documentos | Dos dígitos, guion y tema sin acentos | `docs/17-gestion-de-la-configuracion.md` |
| Imágenes | Nombre del flujo en minúsculas con guiones y extensión `.png` | `docs/images/gestion-de-la-configuracion/flujo-lineas-base.png` |
| Fuentes de diagramas | Mismo nombre que la imagen con extensión `.mmd`, en el directorio temporal | `flujo-auditoria-configuracion.mmd` |
| Requisitos funcionales | Prefijo `RF-` y número de dos dígitos | RF-01 a RF-09 |
| Casos de uso | Prefijo `CU-` y número de dos dígitos | CU-01 a CU-08 |

## 5. Líneas base y herramienta
Git y GitHub conservan el historial; `CHANGELOG.md` registra cada cierre en lenguaje legible. La línea base vigente se identifica por el commit de cierre de este trabajo y su registro queda en `CHANGELOG.md` con la entrada correspondiente a esta entrega. No se crean etiquetas Git ni se declara un CCC constituido.

| Herramienta | Uso real | Estado |
|---|---|---|
| Git | Historial de versiones y diferencias entre entregas | Aplicado |
| GitHub | Repositorio remoto `Mely-Faciales`, rama `main` | Aplicado |
| `CHANGELOG.md` | Registro de Added y Changed por entrega | Aplicado |
| Etiquetas Git | Identificación alternativa de líneas base | Referencia para Sprint 1 |

![Flujo de líneas base](<images/gestion-de-la-configuracion/flujo-lineas-base.png>)

## 6. Control de cambios (SG2)
Todo cambio sigue la secuencia registro, análisis de impacto, acuerdo con Mely y Erin, autorización y seguimiento hasta el cierre. Esta secuencia continúa el control integrado de cambios descrito en la sección 3 del documento 16, donde el CCC formal figura como «Referencia para Sprint 1».

| Paso | Tratamiento real |
|---|---|
| Registro | Entrada en `SPRINTBOARD.md` y, al cerrarse, en `CHANGELOG.md` |
| Análisis de impacto | Revisión del efecto en alcance, esfuerzo, calendario y riesgo |
| Acuerdo | Mely y Erin validan el cambio y su prioridad |
| Autorización | Decisión registrada por Mely y Erin; el CCC formal queda como Referencia para Sprint 1 |
| Seguimiento | Verificación del cambio hasta su cierre en el sprint correspondiente |

![Flujo de solicitudes de cambio](<images/gestion-de-la-configuracion/flujo-solicitudes-de-cambio.png>)

## 7. Registros y auditorías (SG3)
Los registros permiten recuperar cualquier versión desde Git y explicar su contenido mediante el changelog. La lista de chequeo de integridad se aplica a cada cierre de entrega y deja constancia del estado de los elementos.

| Comprobación | Criterio | Evidencia |
|---|---|---|
| Recuperación de versiones | Cada entrega tiene un commit identificable | Historial de Git |
| Estado de cada elemento | Documento, PNG o registro vigente en su carpeta correspondiente | `README.md` y `SPRINTBOARD.md` |
| Diferencias entre líneas base | Comparación del conjunto anterior con el actual | `git diff` y `CHANGELOG.md` |
| Referencias de imagen | 67 referencias únicas, 0 rotas y 0 duplicadas | Enlaces de imagen de los documentos 02 a 19 |
| Convención de nombres | Ningún nombre de archivo con acentos | Árbol de `docs/` |
| Control de términos | 0 apariciones de términos vetados en los documentos nuevos | Comprobación con grep |

![Flujo de auditoría de configuración](<images/gestion-de-la-configuracion/flujo-auditoria-configuracion.png>)

## 8. Política de ramas
`main` es la única rama vigente y concentra toda la documentación aceptada. El esquema maestro/desarrollo de la referencia se adapta a un proyecto con una sola desarrolladora: no se abre una rama de integración paralela mientras no exista código, y `main` cumple ese papel. Las demás ramas se documentan como política prevista, sin afirmar que ya se utilizan.

| Rama | Origen | Destino | Naming | Estado |
|---|---|---|---|---|
| `main` | Creación del repositorio | Publicación en GitHub | Nombre fijo `main` | Aplicado |
| `feature-*` | `main` | `main` | `feature-<tarea-o-RF>` en minúsculas con guiones | Referencia para Sprint 1 |
| `release-*` | `main` | `main` | `release-<versión>` para el cierre de una entrega | Referencia para Sprint 2 |
| `hotfix-*` | `main` | `main` | `hotfix-<problema>` para correcciones urgentes | Referencia para Sprint 2 |

## 9. Aplicación, trazabilidad y conclusiones
| Práctica | Evidencia real | Estado |
|---|---|---|
| Identificación de elementos | 18 documentos, 68 PNG y 68 fuentes `.mmd` en directorio temporal | Referencia externa, no versionadas |
| Sistema de gestión de la configuración | Git, GitHub y una carpeta de imágenes por documento | Aplicado |
| Líneas base | Commits de cierre y entradas de `CHANGELOG.md` | Aplicado |
| Control de cambios | Acuerdo de Mely y Erin registrado en el sprintboard y el changelog | Aplicado |
| CCC formal | Sin comité constituido | Referencia para Sprint 1 |
| Registros y auditorías | Historial de Git y lista de chequeo de integridad | Aplicado |
| Ramas `feature-*`, `release-*` y `hotfix-*` | Política documentada, sin uso real | Referencia para Sprint 1 o 2 |
| Ejecución de código y pruebas | Construcciones previstas en el documento 10 | Referencia para Sprint 1 |

La trazabilidad se apoya en los documentos 13 a 18, en `SPRINTBOARD.md` y en `CHANGELOG.md`: los roles y la mejora de proceso explican quién participa, la planificación define cómo se decide, y Git conserva la evidencia de cada cierre.

Con esta entrega, la configuración de Sprint 0 queda identificada, registrada y verificable sin anticipar prácticas que aún no se ejecutan. Los compromisos siguientes son constituir el CCC, abrir las ramas previstas cuando exista código y mantener la lista de chequeo de integridad en cada cierre.
