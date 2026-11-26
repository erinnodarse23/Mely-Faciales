# Changelog

Todos los cambios relevantes de este proyecto se documentan aquí.
El formato sigue *Keep a Changelog* y la versión sigue *Semantic Versioning*.

## [Unreleased]

### Added
- Documento del Modelo de Diseño del Sistema con paquetes, subsistemas, interfaces y realizaciones de caso de uso.
- Estructura de carpetas de imágenes con 47 diagramas de diseño exportados.
- Actualización de README.md y SPRINTBOARD.md con la documentación cerrada en Sprint 0.
- Reorientación del proyecto a Mely Faciales con catálogo exclusivo de tratamientos faciales.
- Número de WhatsApp confirmado: `+5355795510`, con deep link `https://wa.me/5355795510?text=...`.
- Los 65 diagramas regenerados para reflejar el nuevo alcance.
- Diagramas de análisis con estereotipos Boundary/Control/Entity, paquete de análisis y colaboraciones con mensajes numerados.
- Arquitectura en capas documentada con diagrama propio; realizaciones de CU-Diseño con flujos alternos mediante bloques `alt`.
- Modelo de despliegue y mecanismos de diseño documentados con dos diagramas propios y tablas de nodos, asignación y mecanismos.
- Modelado de aplicaciones web con mapa de navegación, guía de estilos y realización web de CU-07 mediante estereotipos UML.
- Documentación de diseño separada en cuatro documentos: modelo central, realizaciones de CU, arquitectura y modelado web.
- Patrones de diseño aplicados y modelo relacional documentados con tres diagramas propios.
- Imágenes de diseño reorganizadas en una carpeta por documento; `modelo-de-diseno/` queda limitado a los 5 diagramas del documento 03.
- Modelo de implementación y plan de construcciones documentados con dos diagramas propios.
- Pruebas de caja negra y caja blanca documentadas con un grafo de flujo propio.
- Fundamentos de gestión de proyectos y modelos de mejora de proceso documentados con dos diagramas propios.
- Planificación, integración, monitoreo y control documentados con cuatro diagramas de actividad propios.
- Gestión de la configuración documentada con tres diagramas de actividad propios.
- Buenas prácticas de desarrollo personal documentadas con tres diagramas de actividad propios.
- Buenas prácticas de métodos ágiles documentadas con tres diagramas de actividad propios.
- Fase de prueba consolidada en el documento 11, con gestión de la calidad, estrategia de pruebas, caja negra y caja blanca, y tres diagramas de actividad nuevos.
- Base técnica del backend en la construcción 1: proyecto Maven con Java 25 LTS y Spring Boot 4.1, entidades y enumerados del dominio, repositorios JPA, almacenamiento de medios en disco, esquema PostgreSQL y pruebas unitarias de repositorio con JUnit 5 y Mockito disponible.

### Changed
- Se eliminaron las referencias académicas de la documentación para presentarla como documentación profesional del proyecto.
- Se unificó la metodología del proyecto en Scrum; el modelado UML se usa únicamente como notación de análisis.
- Se aplicó una plantilla visual consistente: metadatos, resumen ejecutivo, índice y pie de documento.
- Se concentró toda la documentación en Sprint 0 y se reprogramó la implementación para Sprint 1.
- Se reclasificaron los artefactos de diseño como parte del Sprint 0.
- Se eliminaron la segunda estilista, sus servicios y los filtros por estilista.
- Se simplificó la interfaz a una sola identidad visual rosa/skincare.
- Se renombró el repositorio a `Mely-Faciales` y se actualizaron los documentos 01, 02 y 03 a la versión 1.1.
- Stack actualizado a Java 25 LTS + Spring Boot 4.1; eliminados Firebase y Firestore del modelo de diseño; nombres de controladores y mensajes alineados con los diagramas.
- Precisados los estados de aplicación del documento 16 y normalizadas las tildes en sus cuatro diagramas de actividad.
- Unificada la terminología de desviación en el documento 16 y separado el conteo actual del progreso del sprintboard.
- Corregida la denominación de los objetivos CMMI CM y la clasificación de las fuentes de diagramas en el documento 17.
- Actualizados los rangos de documentos citados en los documentos 16 y 17 tras incorporar el documento 19.
- Eliminado el documento 12 y adaptados los rangos de documentos citados a 01-18.
- Revisión general de alcance y evidencia: RF-08 y RF-09 pasan a Sprint 1, RNF-05 concreta el honeypot sin CAPTCHA, se añade CP-03-10 y se argumentan la arquitectura, los patrones, la base de datos, el plan de integración y los roles; el modelo global del análisis queda incrustado en el documento 02.
- Alineada la evidencia de pruebas con las construcciones del documento 10 y con la trazabilidad de la construcción 5 del documento 11; precisada la configuración de despliegue prevista en el documento 05.
- Renumerados los documentos 13 a 19 como 12 a 18 para cerrar el hueco dejado por la integración del antiguo documento 12 en el documento 11; el contenido de ese antiguo documento 12 sigue en el 11 y las entradas históricas conservan la numeración vigente en su fecha.
- Corregidos los conteos vigentes: 68 referencias de imagen en el documento 16 y 68 diagramas UML referenciados (18 de análisis y 50 de diseño) en el documento 15.
- Reestructurado el repositorio en `backend/` (proyecto Maven) y `frontend/` (cliente React previsto), con el código Java bajo `backend/src/` y las rutas de fuentes y del build actualizadas en los documentos 09 y 10, el README y el SPRINTBOARD.
- Ajustada la construcción 1: JAR canónico `mely-faciales.jar`, credenciales de base de datos sin valores por defecto con perfil `local`, validación de medios (extensiones, tamaño y cabecera), métodos de dominio para publicar y actualizar contenido, y pruebas ampliadas a 22 casos.

## [0.1.0] - 2026-11-25

### Added
- Estructura inicial del repositorio.
- `README.md` con visión, equipo, stack previsto y enlaces.
- `SPRINTBOARD.md` con la planificación Scrum del Sprint 0.
- Documento de extracción de requisitos funcionales y no funcionales.
- Documento del Modelo de Análisis (UML) con referencias a los diagramas.
- 18 diagramas exportados: 1 global, 1 de paquetes, 8 de clases del análisis y 8 de colaboración.

---

*Mely Faciales · Documentación oficial · Sprint 0 · Actualizado: 2026-11-25*
