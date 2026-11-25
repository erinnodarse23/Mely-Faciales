# Modelado de aplicaciones web

> **Resumen ejecutivo.** Estereotipos web y realización web de CU-07 de Mely Faciales.

| Campo | Valor |
|---|---|
| Versión | 1.0 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

← [Modelo de diseño](03-modelo-de-diseno.md) · [Realizaciones](04-realizaciones-de-caso-de-uso.md) · [Arquitectura](05-descripcion-de-la-arquitectura.md)

## 1. Modelado de aplicaciones web
La web de Mely Faciales se modela con la extensión UML para aplicaciones web. Las clases interfaz, control y entidad del análisis se refinan como páginas cliente, páginas servidor y entidades proveedoras de información.

### 1.1 Mapa de navegación
![Mapa de navegación](<images/modelo-de-diseno/web/mapa-navegacion-web.png>)

El mapa muestra cuatro niveles de profundidad: Inicio; Catálogo, Muro y Login; Detalle, Formulario de reseña y Panel; y Editor de servicio. El detalle de servicio enlaza con WhatsApp mediante un deep link HTTPS.

### 1.2 Guía de estilos
| Elemento | Definición |
|---|---|
| Identidad visual | Rosa/skincare cálido: primario `#F8BBD0`, superficie `#FCE4EC`, texto `#424242` |
| Tipografía | `Inter`, con fallback `system-ui`; jerarquía clara de títulos y cuerpo |
| Componentes | Botones redondeados, tarjetas con sombra suave y micro-videos en bucle y silenciados |
| Diseño | Mobile-first desde 320 px, navegación por teclado y contraste AA |
| Contenido | Español neutro; textos alternativos en todas las imágenes |

### 1.3 Estereotipos y relaciones web
| Elemento | Estereotipo | Uso en Mely Faciales | Prefijo |
|---|---|---|---|
| Página cliente | `<<ClientPage>>` | Pantallas React renderizadas en el navegador | `cl_` |
| Página servidor | `<<ServerPage>>` | Recursos REST que construyen datos y redirigen | `sp_` |
| Formulario | `<<Form>>` | Formulario de login y formulario de reseña | `Form_` |
| Construcción | `<<Build>>` | De página servidora a página cliente | — |
| Hipervínculo | hipervínculo | De página cliente a otra página cliente | — |
| Envío | `<<submit>>` | De formulario a página servidora | — |
| Redirección | `<<redirect>>` | De página servidora a página servidora | — |
| Inclusión | `<<include>>` | Reutilización de comportamiento servidor | — |

Las marcas «Build», «submit» y «redirect» en los diagramas son la forma textual de los estereotipos `<<Build>>`, `<<submit>>` y `<<redirect>>`.

### 1.4 Realización web de CU-07 — Iniciar sesión
Esta vista web complementa, sin sustituir, la realización tecnológica de CU-07 en `docs/04-realizaciones-de-caso-de-uso.md`.

**Clases del diseño web:** ![Clases web CU-07](<images/modelo-de-diseno/web/CU-07-clases-web.png>)

**Secuencia web:** ![Secuencia web CU-07](<images/modelo-de-diseno/web/CU-07-colaboracion-web.png>)

- `cl_Inicio` enlaza por hipervínculo con `cl_Login`; `sp_Login` construye la página cliente con `<<Build>>`; `Form_Login` envía sus campos a `sp_Auth` con `<<submit>>`; `sp_Auth` delega en `AuthService` y redirige a `sp_PanelAdmin` con `<<redirect>>`, que construye `cl_PanelAdmin`.
- Flujos alternos: credenciales inválidas devuelven 401 y sesión ya activa muestra el mensaje correspondiente.

