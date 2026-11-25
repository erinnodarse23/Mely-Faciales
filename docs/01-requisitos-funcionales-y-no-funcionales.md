# Extracción de requisitos funcionales y no funcionales

> **Resumen ejecutivo.** Se identifican 9 requisitos funcionales y 10 no funcionales para la web publicitaria de Mely Faciales. Son la entrada al modelado UML del proyecto y se planifican en `SPRINTBOARD.md` bajo Scrum.

| Campo | Valor |
|---|---|
| Versión | 1.1 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

## 1. Propósito
Documentar qué debe hacer la web de Mely Faciales (requisitos funcionales) y cómo debe comportarse (requisitos no funcionales), como entrada al Modelo de Casos de Uso del Sistema y al Modelo de Análisis del proyecto.

## 2. Técnicas de extracción utilizadas
- **Entrevistas** con Mely (voz del negocio): servicios que ofrece, estilo visual, forma de contacto.
- **Observación** del flujo actual: las citas se gestionan manualmente por WhatsApp.
- **Revisión documental:** prácticas de modelado UML y decisiones del Product Owner.
- **Prototipado conceptual:** una sola identidad visual, rosa/skincare, alineada con los tratamientos faciales.

## 3. Interesados y actores
| Rol | Interés |
|---|---|
| Cliente | Ver servicios, opinar, contactar rápido |
| Mely | Publicidad de limpiezas faciales y tratamientos; borrar reseñas |
| Erin Nodarse | Entregar, mantener y mostrar el proyecto en su portafolio |

Actores del sistema: **Cliente** (primario), **Administradora** = Mely (secundario), **WhatsApp** (sistema externo).

## 4. Supuestos y datos pendientes
- WhatsApp: número confirmado de Mely, `+5355795510`; deep link `https://wa.me/5355795510?text=...`.
- Logo y paleta exacta pendientes: rosa/skincare como línea base.
- Lista definitiva de servicios y precios pendiente de confirmación.
- Idioma: español neutro. Sin moneda definida todavía.

## 5. Requisitos funcionales (RF)
Prioridad: MoSCoW (Must / Should / Could).

| Prioridad | Significado |
|---|---|
| Must | Imprescindible para el MVP |
| Should | Importante; puede planificarse en el Sprint 2 |
| Could | Deseable si hay capacidad |

| ID | Requisito | Actor | Prioridad | Criterios de aceptación |
|---|---|---|---|---|
| RF-01 | Mostrar el catálogo de servicios de faciales | Cliente | Must | Se listan los servicios activos; los inactivos no se muestran |
| RF-02 | Mostrar detalle de servicio con micro-video de 5-10 s y descripción completa | Cliente | Must | Video en bucle y silenciado; si no hay video, se muestra imagen de portada |
| RF-03 | Permitir publicar reseña con nombre, servicio, valoración 1-5, comentario y foto opcional | Cliente | Must | La reseña se publica de inmediato con estado PUBLICADA; datos inválidos muestran error |
| RF-04 | Mostrar el muro de reseñas | Cliente | Must | Se ven foto, valoración, comentario y servicio; si está vacío, mensaje "Sé la primera en opinar" |
| RF-05 | Generar enlace de WhatsApp con mensaje contextual según servicio | Cliente | Must | `https://wa.me/5355795510?text=...` con el nombre del servicio |
| RF-06 | Permitir a la administradora eliminar reseñas | Administradora | Must | Requiere sesión; la reseña desaparece del muro tras borrarla |
| RF-07 | Autenticar a la administradora | Administradora | Must | Usuario y clave válidos → rol ADMIN; inválidos → mensaje de error |
| RF-08 | Permitir editar descripción y video de los servicios sin volver a desplegar | Administradora | Should | El cambio se refleja en el catálogo sin volver a desplegar |
| RF-09 | Mostrar galería de publicaciones (fotos y videos cortos con descripción) | Cliente | Should | Grid/mosaico de publicaciones |

## 6. Requisitos no funcionales (RNF)
| ID | Requisito | Medida / criterio |
|---|---|---|
| RNF-01 | Rendimiento | Carga inicial < 2 s en móvil con conexión normal |
| RNF-02 | Responsive | Diseño mobile-first, usable en 320 px de ancho |
| RNF-03 | Accesibilidad | Contraste AA, textos alternativos en imágenes, navegación por teclado |
| RNF-04 | SEO local | Títulos, meta descripción y Open Graph; palabras clave por servicio |
| RNF-05 | Seguridad | HTTPS, validación de entradas, anti-spam básico, endpoints de admin protegidos |
| RNF-06 | Escalabilidad y cambio | El contenido vive en base de datos; cambiar texto/video no requiere volver a desplegar |
| RNF-07 | Disponibilidad | Sin cold starts visibles en la web (decisión de despliegue en ADR posterior) |
| RNF-08 | Mantenibilidad | Arquitectura en capas y documentación oficial actualizada |
| RNF-09 | Almacenamiento de medios | Interfaz abstracta de almacenamiento para fotos/videos, sustituible sin tocar el resto |
| RNF-10 | Idioma | Español neutro en toda la interfaz y documentación |

## 7. Trazabilidad RF → CU → Sprint
| RF | Caso de uso | Sprint |
|---|---|---|
| RF-01 | CU-01 | 1 |
| RF-02 | CU-02 | 1 |
| RF-03 | CU-03 | 1 |
| RF-04 | CU-04 | 1 |
| RF-05 | CU-05 | 1 |
| RF-06 | CU-06 | 1 |
| RF-07 | CU-07 | 1 |
| RF-08 | CU-08 | 2 |
| RF-09 | CU-01 / CU-02 | 2 |

## 8. Requisitos excluidos explícitamente
- Sistema de agenda o reserva de citas (fuera de alcance en esta fase).
- Pasarela de pagos.
- Notificaciones por correo.
- Aprobación previa de reseñas (se publican directo; solo borrado posterior).
- Perfiles, servicios o identidades visuales de una segunda profesional (fuera de alcance).

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
