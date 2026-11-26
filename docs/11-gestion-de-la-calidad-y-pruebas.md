# Gestión de la calidad y pruebas

> **Resumen ejecutivo.** Se consolida en un solo documento la gestión de la calidad y la fase de prueba de Mely Faciales. Scrum es la metodología única; PMBOK y CMMI se usan solo como marcos de referencia y UML como notación única. Se cubren los atributos de calidad mapeados a RNF-01..10, el costo de la calidad, PPQA, la verificación y la validación, el ciclo de pruebas, los niveles y tipos, el diseño de casos desde CU-03 y la prueba del camino básico de `ResenaService.publicar`. Los catorce casos diseñados están listos para Sprint 1; hoy no hay código ni ejecución, y los estados se declaran sin anticipar resultados.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Modelo de implementación](09-modelo-de-implementacion.md) · [Plan de integración](10-plan-de-integracion.md) · [Organización y roles](13-organizacion-y-roles-del-proyecto.md)

## 1. Propósito, alcance y marcos
Este documento reúne la disciplina de calidad y la estrategia de pruebas del proyecto. La calidad se planifica, se gestiona y se controla desde la documentación; las pruebas se diseñan en Sprint 0 y se ejecutarán cuando exista código. No se presenta ninguna prueba como ejecutada ni ningún defecto como cerrado.

| Marco | Papel en Mely Faciales | Límite de aplicación |
|---|---|---|
| Scrum | Metodología única: roles, artefactos, ceremonias y sprints | No se combina con otra metodología |
| PMBOK | Referencia de Planificar, Gestionar y Controlar la calidad | Sin declaración de certificación ni sustitución de Scrum |
| CMMI | Referencia de PPQA, verificación y validación | Solo como prácticas aplicables al proyecto |
| UML | Notación única de los modelos y de los cuatro flujos de actividad | Un solo lenguaje de modelado |

El alcance cubre los requisitos funcionales RF-03 y RF-04, los no funcionales RNF-01 a RNF-10, el caso de uso CU-03 y la construcción 5 del documento 10. Los estados son honestos: lo documentado está **Aplicado**, lo que requiere código está **Preparado para Sprint 1** o **Referencia para Sprint 1**, y lo que no corresponde al producto se marca **No aplicable**.

## 2. Calidad de software
La calidad de software es el grado en que un producto, componente o proceso cumple las necesidades y expectativas de las interesadas. Se mide sobre los requisitos explícitos y sobre las características implícitas esperadas en un producto profesional: menos defectos, mejor situación económica y clientas satisfechas.

| Atributo | RNF relacionado | Criterio observable en Mely Faciales | Estado |
|---|---|---|---|
| Eficiencia | RNF-01 | Carga inicial inferior a 2 s en móvil con conexión normal | Preparado para Sprint 1 |
| Usabilidad | RNF-02 | Diseño mobile-first usable a 320 px de ancho | Preparado para Sprint 1 |
| Usabilidad | RNF-03 | Contraste AA, textos alternativos y navegación por teclado | Preparado para Sprint 1 |
| Funcionalidad | RNF-04 | Títulos, meta descripción, Open Graph y palabras clave por servicio | Preparado para Sprint 1 |
| Seguridad | RNF-05 | HTTPS, validación de entradas, anti-spam y endpoints de administración protegidos | Preparado para Sprint 1 |
| Mantenibilidad | RNF-06 | El contenido vive en base de datos; cambiar texto o video no exige redespliegue | Preparado para Sprint 1 |
| Confiabilidad | RNF-07 | Sin cold starts visibles; decisión de despliegue pendiente en el ADR | Referencia para Sprint 1 |
| Mantenibilidad | RNF-08 | Arquitectura en capas y documentación oficial actualizada | Aplicado en la documentación |
| Portabilidad | RNF-09 | Interfaz abstracta de almacenamiento de medios, sustituible sin tocar el resto | Preparado para Sprint 1 |
| Usabilidad | RNF-10 | Español neutro en interfaz y documentación | Aplicado en la documentación |

El costo de la calidad reúne las actividades realizadas para lograrla y los costos posteriores de su falta. Prevenir suele costar menos que corregir después de la inspección.

| Categoría | Actividad en el proyecto | Estado |
|---|---|---|
| Prevención | Planear la calidad, definir estándares, listas de chequeo y diseño de casos antes de codificar | Aplicado en la documentación |
| Evaluación | Revisiones técnicas, recopilación de medidas y ejecución prevista de las pruebas | Preparado para Sprint 1 |
| Falla interna | Defectos detectados antes del despliegue, corregidos dentro del sprint | Referencia para Sprint 1 |
| Falla externa | Defectos detectados después del despliegue, con impacto en la imagen del producto | Referencia para Sprint 1 |

## 3. Gestión y control de la calidad
![Flujo de gestión de la calidad](<images/gestion-de-la-calidad-y-pruebas/flujo-gestion-de-la-calidad.png>)

| Proceso | Qué hace en Mely Faciales | Evidencia |
|---|---|---|
| Planificar la calidad | Identifica requisitos y estándares y documenta cómo se demostrará el cumplimiento | Este documento y los RNF del documento 01 |
| Gestionar la calidad | Convierte el plan en actividades ejecutables: revisiones, listas de chequeo y diseño de pruebas | Documentos 10 y 11 y `SPRINTBOARD.md` |
| Controlar la calidad | Monitorea y registra resultados para evaluar el desempeño y la completitud de las salidas | Git, `CHANGELOG.md` y resultados previstos de Sprint 1 |

La prevención va antes que la inspección: la calidad se planifica, se diseña y se integra. La mejora continua sigue el ciclo planificar-hacer-revisar-actuar y se alimenta de cada cierre de entrega.

PPQA aporta una visión objetiva de los procesos y de los productos de trabajo. Evalúa la adherencia a las descripciones de proceso, los estándares y los procedimientos; comunica las **no conformidades** y las monitorea hasta garantizar su resolución. Con una sola desarrolladora, la objetividad se refuerza con listas de chequeo y con el registro de decisiones en Git.

| Práctica | Pregunta que responde | Frase de referencia |
|---|---|---|
| Verificación | ¿Los productos de trabajo reflejan los requisitos especificados? | «Lo construyó correctamente» |
| Validación | ¿El producto cumple su uso previsto en el entorno previsto? | «Construyó lo correcto» |

## 4. Estrategia de pruebas
![Flujo de estrategia y niveles de prueba](<images/gestion-de-la-calidad-y-pruebas/flujo-estrategia-y-niveles-de-prueba.png>)

La estrategia integra los métodos de diseño de casos en pasos planificados y describe cómo se planean, cuándo se ejecutan y qué esfuerzo consumen. El ciclo básico es **planificar → diseñar → implementar → ejecutar → evaluar**.

| Técnica | Cómo detecta defectos | Aplicación prevista |
|---|---|---|
| Estática | Revisión de artefactos sin ejecutar el software | Revisiones de documentos, diseño y código con listas de chequeo |
| Dinámica | Ejecución del software con entradas válidas e inválidas | Casos CP-03 y CP-B en Sprint 1 |

Los principios de la prueba exigen definir resultados esperados, incluir entradas inválidas e inesperadas, no planear suponiendo que no hay defectos y aceptar que la prueba es una tarea creativa. El programador debe evitar probar su propio desarrollo; con una sola desarrolladora, ese principio se atiende con revisiones documentales y con la separación entre diseño y ejecución prevista.

Una buena prueba tiene alta probabilidad de encontrar un defecto, no es redundante, es la mejor de su clase para descubrir una familia de defectos y no resulta demasiado simple ni demasiado compleja. Una prueba exitosa es la que detecta defectos; las pruebas muestran la presencia de errores, nunca su ausencia.

## 5. Niveles y tipos
| Nivel | Objetivo | Momento | Estado |
|---|---|---|---|
| Unidad | Verificar una clase o un método de forma independiente | Sprint 1 | Preparado para Sprint 1 |
| Integración | Verificar la comunicación entre componentes y subsistemas | Sprint 1 | Preparado para Sprint 1 |
| Regresión | Repetir un subconjunto de pruebas ante cada cambio | Sprint 1 | Preparado para Sprint 1 |
| Humo | Revisar rápido el flujo crítico de extremo a extremo | Sprint 1 | Referencia para Sprint 1 |
| Sistema | Ejercitar el sistema ensamblado por completo | Sprint 2 | Referencia para Sprint 1 |
| Validación | Comprobar acciones visibles y salidas reconocibles por la usuaria | Sprint 2 | Referencia para Sprint 1 |
| Aceptación | Confirmar los requisitos con Mely como dueña del negocio | Sprint 2 | Referencia para Sprint 1 |
| Alfa | Usuarios representativos en el sitio del desarrollador | No planificado | No aplicable: producto a medida con una sola dueña del negocio |
| Beta | Clientes seleccionados en entornos no controlados | No planificado | No aplicable: producto a medida con una sola dueña del negocio |

Los tipos funcionales ejercitan los requisitos funcionales ya diseñados, como RF-03 y RF-04. Los tipos no funcionales juzgan la operación del sistema: compatibilidad, seguridad, rendimiento, carga, estrés, estabilidad, volumen, usabilidad, recuperación, mantenibilidad, instalabilidad y portabilidad. Todos quedan previstos; ninguno se declara ejecutado.

## 6. Caja negra y diseño de casos desde el caso de uso
![Flujo de diseño de casos desde caso de uso](<images/gestion-de-la-calidad-y-pruebas/flujo-diseno-de-casos-desde-caso-de-uso.png>)

La caja negra ejerce la interfaz sin inspeccionar la lógica interna. Parte del caso de uso CU-03 Publicar reseña, que dice a la clienta qué esperar, al programador qué código escribir, al autor técnico qué documentar y al probador qué verificar.

La generación sigue tres pasos: identificar escenarios, derivar al menos un caso de prueba por escenario y asignar valores a los datos.

| Escenario de CU-03 | Condición que lo activa | Caso derivado |
|---|---|---|
| Publicación con foto | Datos válidos y foto JPG | CP-03-05 |
| Publicación sin foto | Datos válidos sin foto | CP-03-04 |
| Nombre ausente | Nombre vacío o solo espacios | CP-03-01 y CP-03-08 |
| Valoración inválida | Valoración 0 o 6 | CP-03-02 y CP-03-03 |
| Comentario ausente | Comentario vacío | CP-03-06 |
| Servicio no reconocido | Servicio inexistente | CP-03-07 |
| Texto especial | Acentos y caracteres especiales | CP-03-09 |

La matriz de casos recoge objetivo, precondición, entrada, pasos, resultado esperado y trazabilidad. Los valores se eligen dentro del rango, por debajo y por encima del intervalo, y según las reglas de negocio: la valoración va de 1 a 5, el nombre y el comentario no pueden estar vacíos y el servicio debe existir. Estos últimos son los valores límite y de regla de negocio que alimentan la matriz de la sección 8.

## 7. Caja blanca
Se analiza `ResenaService.publicar`, que valida los datos, guarda la foto opcional mediante `MediaStorage` y persiste la reseña con estado `PUBLICADA`. El pseudocódigo conservado es:

```text
1  publicar(resena):
2    si nombreClienta.esVacio(): lanzar ValidacionException
3    si valoracionFueraDeRango(resena.valoracion): lanzar ValidacionException
4    si comentario.esVacio(): lanzar ValidacionException
5    si foto != null: fotoUrl = mediaStorage.guardarFoto(foto)
6    si foto == null: fotoUrl = null
7    resena.fotoUrl = fotoUrl
8    resena.estado = PUBLICADA
9    resenaRepository.save(resena)
10   retornar DTO
```

![Grafo de flujo de publicar](<images/gestion-de-la-calidad-y-pruebas/grafo-flujo-publicar.png>)

| Nodo | Significado |
|---|---|
| 1 | Entrada a `publicar`. |
| 2 | Predicado: nombre de la clienta vacío. |
| 3 | Lanzar excepción por nombre inválido. |
| 4 | Predicado: valoración fuera de 1 a 5. |
| 5 | Lanzar excepción por valoración inválida. |
| 6 | Predicado: comentario vacío. |
| 7 | Lanzar excepción por comentario inválido. |
| 8 | Predicado: foto no nula. |
| 9 | Guardar foto y obtener `fotoUrl`. |
| 10 | Asignar estado `PUBLICADA` y guardar la reseña. |
| 11 | Retornar el DTO con respuesta `201 Created`. |

El grafo tiene 11 nodos y 14 aristas, con cuatro nodos predicado en las líneas 2, 3, 4 y 5. La complejidad ciclomática coincide por tres vías: `V(G)=P+1=A-N+2=5`, con cinco regiones incluida la exterior. Por tanto hacen falta cinco caminos independientes.

| Camino | Secuencia de nodos | Condición que introduce | Resultado esperado |
|---|---|---|---|
| C1 | 1-2-3-11 | Nombre vacío, verdadero | Excepción de validación |
| C2 | 1-2-4-5-11 | Valoración fuera de rango, verdadero | Excepción de validación |
| C3 | 1-2-4-6-7-11 | Comentario vacío, verdadero | Excepción de validación |
| C4 | 1-2-4-6-8-9-10-11 | Foto presente, verdadero | Guarda foto y reseña; responde `201` |
| C5 | 1-2-4-6-8-10-11 | Foto presente, falso | Guarda reseña sin `fotoUrl`; responde `201` |

| Id | Camino | Datos de entrada | Resultado esperado |
|---|---|---|---|
| CP-B-01 | C1 | Nombre vacío, valoración 4, comentario válido | `ValidacionException` antes de tocar almacenamiento |
| CP-B-02 | C2 | Nombre válido, valoración 0, comentario válido | `ValidacionException` antes de tocar almacenamiento |
| CP-B-03 | C3 | Nombre válido, valoración 4, comentario vacío | `ValidacionException` antes de tocar almacenamiento |
| CP-B-04 | C4 | Datos válidos y foto JPG | Se invoca `guardarFoto` y luego `save` |
| CP-B-05 | C5 | Datos válidos sin foto | No se invoca `guardarFoto`; `fotoUrl` queda null y se ejecuta `save` |

## 8. Casos de prueba y ejecución prevista
Los nueve casos de caja negra conservan su resultado esperado y su trazabilidad. Todos están diseñados; ninguno tiene resultado real registrado.

| Id | Entrada | Pasos | Resultado esperado | Trazabilidad |
|---|---|---|---|---|
| CP-03-01 | Nombre vacío; resto válido | Enviar `POST /api/resenas` | `400 Bad Request`; no se guarda la reseña | RF-03, CU-03 |
| CP-03-02 | Valoración 0; resto válido | Enviar `POST /api/resenas` | `400 Bad Request`; mensaje de rango inválido | RF-03, CU-03 |
| CP-03-03 | Valoración 6; resto válido | Enviar `POST /api/resenas` | `400 Bad Request`; mensaje de rango inválido | RF-03, CU-03 |
| CP-03-04 | Valoración 1; sin foto | Enviar `POST /api/resenas` y consultar el muro | `201 Created`; reseña `PUBLICADA` sin `fotoUrl` | RF-03, RF-04, CU-03 |
| CP-03-05 | Valoración 5; foto JPG válida | Enviar `POST /api/resenas` con la foto | `201 Created`; reseña `PUBLICADA` con `fotoUrl` no vacía | RF-03, RNF-09, CU-03 |
| CP-03-06 | Comentario vacío; resto válido | Enviar `POST /api/resenas` | `400 Bad Request`; no se guarda la reseña | RF-03, CU-03 |
| CP-03-07 | Servicio inexistente; resto válido | Enviar `POST /api/resenas` | `400 Bad Request`; servicio no reconocido | RF-03, CU-03 |
| CP-03-08 | Nombre con solo espacios en blanco | Enviar `POST /api/resenas` | `400 Bad Request`; validación de entradas | RNF-05, CU-03 |
| CP-03-09 | Comentario con acentos y caracteres especiales | Enviar `POST /api/resenas` y consultar el muro | `201 Created`; el texto se muestra sin alterar | RNF-10, RF-04, CU-03 |

| Capa | Herramienta | Qué cubre | Estado |
|---|---|---|---|
| Backend · unidad | JUnit 5 + Mockito | `ResenaService.publicar` con `MediaStorage` y `ResenaRepository` aislados; caminos CP-B | Preparado para Sprint 1 |
| Backend · integración | JUnit 5 sobre la API REST | `POST /api/resenas` con los 9 casos CP-03 | Preparado para Sprint 1 |
| Backend · humo E2E | Prueba de humo de extremo a extremo | Flujo crítico catálogo → reseña → WhatsApp `+5355795510` | Referencia para Sprint 1 |
| Frontend · React | Vitest | Lógica y componentes del catálogo, galería, muro de reseñas y enlaces de WhatsApp | Preparado para Sprint 1 |

Hoy no hay código ni pruebas ejecutadas; las herramientas están previstas para Sprint 1 y la regresión se ejecutará ante cada cambio, sin integración continua activa.

## 9. Criterios de cierre y trazabilidad
Los catorce casos están diseñados pero **NO ejecutados**. El cierre de Sprint 1 exigirá ejecutarlos, registrar el resultado real de cada uno, conservar la evidencia en Git y no dejar defectos abiertos de severidad alta en CU-03.

| Evidencia de origen | Cubierta por | Estado |
|---|---|---|
| RF-03 Publicar reseña | CP-03-01 a CP-03-09 y CP-B-01 a CP-B-05 | Diseñado, no ejecutado |
| RF-04 Mostrar reseñas | CP-03-04 y CP-03-09 | Diseñado, no ejecutado |
| RNF-05 Seguridad | CP-03-08 | Diseñado, no ejecutado |
| RNF-09 Almacenamiento de medios | CP-03-05 y CP-B-04 | Diseñado, no ejecutado |
| RNF-10 Idioma | CP-03-09 | Diseñado, no ejecutado |
| CU-03 Publicar reseña | Los catorce casos | Diseñado, no ejecutado |
| Construcción 5 del documento 10 | CP-B-01 a CP-B-05 | Diseñado, no ejecutado |

La trazabilidad se apoya en el documento 01 para los requisitos, en el documento 04 para la realización de CU-03, en el documento 10 para la construcción 5, en `SPRINTBOARD.md` para el progreso y en `CHANGELOG.md` para cada cierre. Las revisiones de calidad y las no conformidades se registran con el mismo criterio que el resto de la documentación, sin introducir fuentes externas.

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
