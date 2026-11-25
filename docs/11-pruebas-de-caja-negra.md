# Pruebas de caja negra

> **Resumen ejecutivo.** Se aplica la técnica de caja negra al CU-03 Publicar reseña. Los casos se derivan de RF-03 y de los mensajes documentados en `docs/04-realizaciones-de-caso-de-uso.md`, sin inspeccionar el código interno.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Modelo de diseño](03-modelo-de-diseno.md) · [Realizaciones](04-realizaciones-de-caso-de-uso.md) · [Pruebas de caja blanca](12-pruebas-de-caja-blanca.md)

## 1. Niveles de prueba previstos
| Nivel | Objetivo | Momento | Responsable | Artefacto |
|---|---|---|---|---|
| Desarrollador | Comprobar la unidad de código mientras se implementa | Sprint 1 | Desarrollador | Prueba unitaria |
| Unidad | Verificar una clase o un método | Sprint 1 | Desarrollador | Casos de caja blanca del documento 12 |
| Integración | Verificar subsistemas y API entre capas | Sprint 1 | Integrador | Construcciones del documento 10 |
| Sistema | Verificar el sistema desplegado | Sprint 2 | Integrador | Prueba de humo de los ocho casos de uso |
| Aceptación | Confirmar los requisitos con la dueña del negocio | Sprint 2 | Product Owner | Casos de este documento |
| Independiente | Validar el resultado desde fuera del equipo | Sprint 2 | Erin Nodarse | Informe de defectos |

## 2. Objetivo y alcance
El caso de uso seleccionado es CU-03 Publicar reseña, asociado a RF-03. Se ejerce el endpoint `POST /api/resenas` con datos válidos e inválidos. Se observa solo la interfaz: código de respuesta, mensaje de error, estado `PUBLICADA` y presencia o ausencia de `fotoUrl`.

## 3. Plantilla de casos de prueba
| Campo | Contenido |
|---|---|
| Identificador | Código único `CP-03-NN`. |
| Objetivo | Comportamiento que se quiere demostrar. |
| Precondición | Estado previo necesario. |
| Entrada | Valores de nombre, servicio, valoración, comentario y foto. |
| Pasos | Acciones sobre el endpoint. |
| Resultado esperado | Respuesta HTTP y estado de la reseña. |
| Trazabilidad | Requisito y caso de uso cubiertos. |

## 4. Casos de prueba
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

## 5. Criterios de cierre
- Los nueve casos se ejecutan y se registra el resultado real de cada uno.
- No quedan defectos abiertos de severidad alta en CU-03.
- Una reseña aceptada aparece en el muro con estado `PUBLICADA` y la foto opcional solo cuando se envió.

## 6. Conclusión
La caja negra valida RF-03 desde la perspectiva de la clienta, complementando la cobertura interna de caja blanca documentada en `docs/12-pruebas-de-caja-blanca.md`.
