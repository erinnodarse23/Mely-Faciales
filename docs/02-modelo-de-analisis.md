# Modelo de Análisis del Sistema

> **Resumen ejecutivo.** 8 casos de uso, 17 clases del análisis y 18 diagramas (1 global, 1 de paquetes, 8 de clases y 8 de colaboración). Los diagramas se referencian desde `images/diagramas de clases del analisis/`, salvo el modelo global, que se incrusta en la sección 3.

| Campo | Valor |
|---|---|
| Versión | 1.1 |
| Estado | Aprobado para Sprint 0 |
| Actualizado | 2026-11-25 |
| Autor | Erin Nodarse |
| Gestión | Scrum |
| Modelado | UML |
| Sprint | 0 |

**Índice de secciones:** 1. Contexto · 2. Modelo de Casos de Uso · 3. Paquetes y clases · 4. Realizaciones · 5. Trazabilidad · 6. Cobertura · 7. Conclusiones.

## 1. Contexto
Este documento recoge el análisis del sistema de Mely Faciales mediante modelado UML: Modelo de Casos de Uso del Sistema, clases del análisis (interfaz, control y entidad) y realizaciones de caso de uso con sus diagramas de clases y de colaboración. Estos artefactos se producen dentro del flujo de análisis planificado en SPRINTBOARD.md, bajo el marco de trabajo Scrum.

**Importante:** este archivo no contiene los diagramas. Todos los diagramas están exportados como imágenes en:
`images/diagramas de clases del analisis/clases/` (diagramas de clases del análisis)
`images/diagramas de clases del analisis/colaboracion/` (diagramas de colaboración)

## 2. Modelo de Casos de Uso del Sistema
Actores: Cliente (primario), Administradora = Mely (secundario), WhatsApp (sistema externo).

| ID | Caso de Uso | Actor | Prioridad |
|---|---|---|---|
| CU-01 | Consultar catálogo de servicios | Cliente | Alta |
| CU-02 | Ver detalle de servicio (micro-video + descripción) | Cliente | Alta |
| CU-03 | Publicar reseña (foto, valoración, servicio, comentario) | Cliente | Alta |
| CU-04 | Consultar muro de reseñas | Cliente | Alta |
| CU-05 | Contactar por WhatsApp según servicio | Cliente | Alta |
| CU-06 | Eliminar reseña | Administradora | Alta |
| CU-07 | Iniciar sesión | Administradora | Alta |
| CU-08 | Gestionar contenido del catálogo sin volver a desplegar | Administradora | Media |

## 3. Paquetes y clases del análisis
Paquetes: `Paquete_Interfaz` (Boundary), `Paquete_Negocio` (Control), `Paquete_Dominio` (Entity).

![Paquetes del análisis](<images/diagramas de clases del analisis/paquetes/paquetes-analisis.png>)

| Estereotipo | Significado |
|---|---|
| Interfaz (Boundary) | Frontera con el usuario o con un sistema externo |
| Control | Coordina un caso de uso y a las entidades |
| Entidad | Información del dominio del negocio |

| Estereotipo | Clase | Paquete | Responsabilidad |
|---|---|---|---|
| Interfaz | CI_Portada | Interfaz | Portada y navegación |
| Interfaz | CI_CatalogoServicios | Interfaz | Lista de servicios activos |
| Interfaz | CI_DetalleServicio | Interfaz | Micro-video, descripción y CTA |
| Interfaz | CI_MuroReseñas | Interfaz | Muro de reseñas |
| Interfaz | CI_FormularioReseña | Interfaz | Captura datos, foto y valoración |
| Interfaz | CI_PanelAdmin | Interfaz | Listado y borrado de reseñas |
| Interfaz | CI_Login | Interfaz | Credenciales de administradora |
| Interfaz | CI_WhatsApp | Interfaz | Frontera con WhatsApp (sistema externo) |
| Control | CC_GestorServicios | Negocio | Consulta y gestión del catálogo |
| Control | CC_GestorReseñas | Negocio | Validar, crear, listar y eliminar |
| Control | CC_GestorMedios | Negocio | Guardar fotos/videos vía MediaStorage |
| Control | CC_GestorAutenticación | Negocio | Sesión y rol ADMIN |
| Control | CC_GestorWhatsApp | Negocio | Construir wa.me con mensaje contextual |
| Entidad | CE_Servicio | Dominio | id, nombre, descripción, videoUrl, imagenUrl, activo |
| Entidad | CE_Reseña | Dominio | id, nombreClienta, servicio, valoración, comentario, fotoUrl, estado, fecha |
| Entidad | CE_Publicacion | Dominio | Galería: tipo FOTO/VIDEO, url, descripción |
| Entidad | CE_Administradora | Dominio | usuario, nombre, rol |

**Diagrama global:** ![Modelo global de clases del análisis](<images/diagramas de clases del analisis/clases/modelo-analisis-clases.png>)

## 4. Realizaciones de los casos de uso
Para cada caso de uso se entrega un diagrama de clases del análisis y un diagrama de colaboración con mensajes numerados sobre enlaces; los escenarios alternos van en trazo discontinuo.

### CU-01 — Consultar catálogo de servicios
Actor: Cliente. Precondición: web accesible. Postcondición: catálogo visible.
Flujo principal: entra → ve el catálogo. Alterno: sin servicios activos muestra "Próximamente".
- Diagrama de clases: ![Diagrama de clases CU-01](<images/diagramas de clases del analisis/clases/CU-01-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-01](<images/diagramas de clases del analisis/colaboracion/CU-01-colaboracion.png>)
- Mensajes principales: 1: Entra · 2: mostrarCatalogo() · 3: listarServicios() · 4: buscarActivos() · 5: servicios · 6: lista · 7: Mostrar catálogo / 7a: Sin servicios activos: Próximamente.

### CU-02 — Ver detalle de servicio
Actor: Cliente. Postcondición: detalle con video/descripción visible. Alternos: sin video → imagen de portada.
- Diagrama de clases: ![Diagrama de clases CU-02](<images/diagramas de clases del analisis/clases/CU-02-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-02](<images/diagramas de clases del analisis/colaboracion/CU-02-colaboracion.png>)
- Mensajes principales: 1: Selecciona tarjeta · 2: mostrarDetalle() · 3: obtenerDetalle() · 4: buscar() · 5: buscarPorServicio() · 6: datos · 7: videoUrl · 8: Mostrar video / 8a: Sin video: mostrar imagen de portada.

### CU-03 — Publicar reseña
Actor: Cliente. Postcondición: reseña visible con estado PUBLICADA. Alternos: sin foto, datos inválidos, foto inválida.
- Diagrama de clases: ![Diagrama de clases CU-03](<images/diagramas de clases del analisis/clases/CU-03-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-03](<images/diagramas de clases del analisis/colaboracion/CU-03-colaboracion.png>)
- Mensajes principales: 1: Dejar reseña · 2: listarServicios() · 3: buscarPorServicio() · 4: servicios · 5: Mostrar formulario · 6: Ingresar datos · 7: validar() · 8a: Error: datos inválidos · 9a: Mostrar error de datos · 8b: guardarFoto() · 9b: fotoUrl · 8c: Error: foto inválida · 9c: Mostrar error de foto · 8d: Sin foto: omitir foto · 10: crear() · 11: crearReseña(PUBLICADA) · 12: reseña creada · 13: publicar · 14: Confirmación.

### CU-04 — Consultar muro de reseñas
Actor: Cliente. Postcondición: muro visible. Alterno: muro vacío → "Sé la primera en opinar".
- Diagrama de clases: ![Diagrama de clases CU-04](<images/diagramas de clases del analisis/clases/CU-04-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-04](<images/diagramas de clases del analisis/colaboracion/CU-04-colaboracion.png>)
- Mensajes principales: 1: Ver reseñas · 2: listarPublicadas() · 3: buscarPublicadas() · 4: reseñas · 5: lista · 6: Mostrar muro / 6a: Muro vacío: Sé la primera en opinar.

### CU-05 — Contactar por WhatsApp
Actor: Cliente. Postcondición: chat abierto con mensaje contextual. Alterno: sin WhatsApp → número para copiar.
- Diagrama de clases: ![Diagrama de clases CU-05](<images/diagramas de clases del analisis/clases/CU-05-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-05](<images/diagramas de clases del analisis/colaboracion/CU-05-colaboracion.png>)
- Mensajes principales: 1: Elige servicio · 2: Mostrar detalle · 3: Pulsa CTA · 4: construirLink() · 5: mensaje contextual con +5355795510 · 6: wa.me · 7: Abrir chat / 7a: Sin WhatsApp: copiar +5355795510.

### CU-06 — Eliminar reseña
Actor: Administradora. Postcondición: reseña no visible. Alternos: credenciales inválidas, cancelación, error al borrar.
- Diagrama de clases: ![Diagrama de clases CU-06](<images/diagramas de clases del analisis/clases/CU-06-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-06](<images/diagramas de clases del analisis/colaboracion/CU-06-colaboracion.png>)
- Mensajes principales: 1: Credenciales · 2: autenticar() · 3: buscarPorUsuario() · 4: administradora · 5: sesión ADMIN / 5a: Credenciales inválidas / 5b: Error de autenticación · 6: Panel · 7: listarReseñas() · 8: buscarTodas() · 9: buscarPorId() · 10: reseñas · 11: lista de reseñas · 12: Seleccionar · 13: confirmarEliminación() · 14: Aceptar / 14a: Cancelación · 15: borrar() · 16: deleteById() · 17: confirmación / 17a: Error al borrar · 18: refrescar · 19: Reseña no visible.

### CU-07 — Iniciar sesión
Actor: Administradora. Postcondición: sesión con rol ADMIN. Alternos: inválida, sesión ya activa.
- Diagrama de clases: ![Diagrama de clases CU-07](<images/diagramas de clases del analisis/clases/CU-07-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-07](<images/diagramas de clases del analisis/colaboracion/CU-07-colaboracion.png>)
- Mensajes principales: 1: Entrar a /admin · 2: Mostrar formulario · 3: Credenciales · 4: autenticar() · 5: buscarPorUsuario() · 6: resultado · 7: sesión ADMIN / 7a: Credenciales inválidas / 7b: Mensaje de error / 7c: Sesión ya activa · 8: Panel.

### CU-08 — Gestionar contenido del catálogo sin volver a desplegar
Actor: Administradora. Postcondición: catálogo actualizado sin volver a desplegar. Alternos: solo texto, nuevo video, video inválido.
- Diagrama de clases: ![Diagrama de clases CU-08](<images/diagramas de clases del analisis/clases/CU-08-clases.png>)
- Diagrama de colaboración: ![Colaboración CU-08](<images/diagramas de clases del analisis/colaboracion/CU-08-colaboracion.png>)
- Mensajes principales: 1: Editar servicio · 2: obtener() · 3: buscar() · 4: servicio · 5: Mostrar formulario · 6: Formulario visible · 7: Modificar texto · 8: Solo texto: sin video · 9: guardarVideo() · 10: videoUrl / 9a: Video inválido / 10a: Error de video · 11: actualizar() · 12: guardado · 13: actualizado · 14: Confirmación / 14a: Error al guardar · 15: Catálogo actualizado.

## 5. Trazabilidad Modelo de CU → Modelo de Análisis
| CU | Clases que lo realizan |
|---|---|
| CU-01 | CI_Portada, CI_CatalogoServicios, CC_GestorServicios, CE_Servicio |
| CU-02 | CI_CatalogoServicios, CI_DetalleServicio, CC_GestorServicios, CE_Servicio, CE_Publicacion |
| CU-03 | CI_FormularioReseña, CC_GestorReseñas, CC_GestorMedios, CE_Servicio, CE_Reseña, CI_MuroReseñas |
| CU-04 | CI_MuroReseñas, CC_GestorReseñas, CE_Reseña |
| CU-05 | CI_DetalleServicio, CC_GestorWhatsApp, CI_WhatsApp |
| CU-06 | CI_Login, CI_PanelAdmin, CC_GestorAutenticación, CC_GestorReseñas, CE_Administradora, CE_Reseña, CI_MuroReseñas |
| CU-07 | CI_Login, CC_GestorAutenticación, CE_Administradora |
| CU-08 | CI_PanelAdmin, CC_GestorServicios, CC_GestorMedios, CE_Servicio |

## 6. Cobertura de escenarios
- [x] CU-01: catálogo y estado vacío
- [x] CU-02: con video / sin video
- [x] CU-03: con foto / sin foto / inválidos
- [x] CU-04: muro con reseñas / muro vacío
- [x] CU-05: número único y mensaje según servicio / sin WhatsApp
- [x] CU-06: login válido e inválido / cancelación / error
- [x] CU-07: válida / inválida / ya activa
- [x] CU-08: texto / video / video inválido / error

## 7. Conclusiones
- El Modelo de Análisis es un modelo de objetos conceptual que analiza los requisitos mediante su refinamiento y estructuración.
- Las clases del análisis son una abstracción de una o varias clases y/o subsistemas del diseño del sistema y se identifican como clase interfaz, control o entidad.
- Cada realización de caso de uso refleja cómo las clases de análisis colaboran entre sí para dar cobertura a TODOS los escenarios del caso de uso.

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
