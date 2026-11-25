# Pruebas de caja blanca

> **Resumen ejecutivo.** Se aplica la prueba del camino básico al método `ResenaService.publicar`. Se construye su grafo de flujo, se calcula la complejidad ciclomática y se derivan los cinco caminos independientes que garantizan ejecutar cada sentencia y cada condición en sus dos variantes.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Modelo de implementación](09-modelo-de-implementacion.md) · [Pruebas de caja negra](11-pruebas-de-caja-negra.md) · [Plan de integración](10-plan-de-integracion.md)

## 1. Método seleccionado
Se analiza `ResenaService.publicar`, que valida los datos, guarda la foto opcional mediante `MediaStorage` y persiste la reseña con estado `PUBLICADA`.

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

El método tiene cuatro nodos predicado: las líneas 2, 3, 4 y 5. La condición compuesta de rango está encapsulada en `valoracionFueraDeRango`, por lo que dentro de este método cuenta como un solo predicado.

## 2. Grafo de flujo
![Grafo de flujo de publicar](<images/pruebas-de-caja-blanca/grafo-flujo-publicar.png>)

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

El grafo tiene 11 nodos y 14 aristas.

## 3. Complejidad ciclomática
- `V(G) = P + 1 = 4 + 1 = 5`, con cuatro nodos predicado.
- `V(G) = A - N + 2 = 14 - 11 + 2 = 5`, con 14 aristas y 11 nodos.
- El grafo tiene 5 regiones, incluida la región exterior.

Las tres formas coinciden: hacen falta cinco caminos independientes para cubrir el método.

## 4. Caminos independientes
| Camino | Secuencia de nodos | Condición que introduce | Resultado esperado |
|---|---|---|---|
| C1 | 1-2-3-11 | Nombre vacío, verdadero | Excepción de validación |
| C2 | 1-2-4-5-11 | Valoración fuera de rango, verdadero | Excepción de validación |
| C3 | 1-2-4-6-7-11 | Comentario vacío, verdadero | Excepción de validación |
| C4 | 1-2-4-6-8-9-10-11 | Foto presente, verdadero | Guarda foto y reseña; responde `201` |
| C5 | 1-2-4-6-8-10-11 | Foto presente, falso | Guarda reseña sin `fotoUrl`; responde `201` |

Cada camino recorre al menos una arista no usada por los anteriores, por lo que el conjunto es básico.

## 5. Casos de prueba derivados
| Id | Camino | Datos de entrada | Resultado esperado |
|---|---|---|---|
| CP-B-01 | C1 | Nombre vacío, valoración 4, comentario válido | `ValidacionException` antes de tocar almacenamiento |
| CP-B-02 | C2 | Nombre válido, valoración 0, comentario válido | `ValidacionException` antes de tocar almacenamiento |
| CP-B-03 | C3 | Nombre válido, valoración 4, comentario vacío | `ValidacionException` antes de tocar almacenamiento |
| CP-B-04 | C4 | Datos válidos y foto JPG | Se invoca `guardarFoto` y luego `save` |
| CP-B-05 | C5 | Datos válidos sin foto | No se invoca `guardarFoto`; `fotoUrl` queda null y se ejecuta `save` |

## 6. Trazabilidad
Los casos cubren RF-03, CU-03, RNF-05 y la construcción 5 del plan de integración documentado en `docs/10-plan-de-integracion.md`.

## 7. Conclusión
La caja blanca complementa los casos de caja negra del documento 11 al verificar las decisiones internas del servicio y justificar la cantidad mínima de pruebas unitarias con la complejidad ciclomática.
