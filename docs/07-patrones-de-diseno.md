# Patrones de diseño aplicados

> **Resumen ejecutivo.** Se documentan los patrones de diseño que resuelven problemas reales de Mely Faciales. No se aplica ningún patrón por obligación: solo cuando el problema lo justifica.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

Navegación: [Modelo de diseño](03-modelo-de-diseno.md) · [Realizaciones](04-realizaciones-de-caso-de-uso.md) · [Base de datos](08-diseno-de-la-base-de-datos.md)

## 1. Criterio de aplicación
Un patrón es una solución probada y reutilizable a un problema frecuente. Su uso es aconsejable, no obligatorio; forzarlo añade complejidad sin beneficio. Por eso se descartan fábricas adicionales, observadores y decoradores que el dominio no necesita.

## 2. Patrones seleccionados
| Patrón | Categoría | Problema resuelto | Elementos en Mely Faciales | Trazabilidad |
|---|---|---|---|---|
| Strategy | Comportamiento | Alternar la forma de almacenar fotos y videos sin tocar los servicios | `MediaStorage`, `DiscoMediaStorage` | RNF-09, CU-03, CU-08 |
| Facade | Estructural | Ofrecer a la interfaz una API simple sobre repositorios, storage y reglas | `ServicioService`, `ResenaService` | CU-01 a CU-08 |
| Builder | Creación | Construir el deep link con número único y mensaje según el servicio | `WhatsAppLinkGenerator.construir(servicio)` | RF-05, CU-05 |
| Singleton | Creación | Mantener una sola instancia de servicios y repositorios | Beans de Spring con alcance singleton | RNF-06 |
| Repository | Arquitectónico | Desacoplar el acceso a datos de JPA | `ServicioRepository`, `ResenaRepository`, `PublicacionRepository` | RNF-06 |

## 3. Diagrama de clases con los patrones aplicados
![Patrones aplicados](<images/modelo-de-diseno/patrones/patrones-aplicados.png>)

## 4. Conclusión
Los patrones refuerzan la arquitectura en capas y el mecanismo de almacenamiento de medios sin añadir abstracciones innecesarias.
