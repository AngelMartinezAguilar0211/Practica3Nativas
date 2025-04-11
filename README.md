# Práctica 3.- "Aplicación de Cámara y Micrófono para Android"


## Descripción General

Esta aplicación Android fue desarrollada en Kotlin utilizando Android Studio. Su objetivo es permitir la captura de fotografías y grabación de audio con funcionalidades adicionales como filtros en vivo, almacenamiento organizado y una galería integrada para visualización y reproducción de archivos. Se implementó una interfaz moderna con soporte para cambio de tema (guinda y azul) y adaptación automática a modo claro/oscuro.

## Arquitectura del Proyecto

Componentes Principales

- CameraRenderer.kt / FilterRenderer.kt: Renderizado en tiempo real con OpenGL ES 2.0. Captura de imagen desde GLSurfaceView utilizando SurfaceTexture y GL_TEXTURE_EXTERNAL_OES.
- FullScreenQuad.kt: Control de shaders para mostrar la vista previa con filtros.
- AudioRecorder.kt: Manejador de grabación de audio con MediaRecorder.
- GalleryFragment.kt: Galería que muestra imágenes y audios almacenados localmente.
- MainActivity.kt: Activity principal con configuración de temas y navegación entre fragments.

## Funcionalidades

### Captura de Fotografías

- Vista previa mediante GLSurfaceView y CameraX.
- Renderizado de imagen en tiempo real con filtros (grayscale, sepia, invertir).
- Captura del frame desde framebuffer (sin uso de glReadPixels directo sobre OES).
- Almacenamiento en carpeta interna y acceso desde la galería.

### Grabación de Audio

- Grabación de voz en formato .m4a.
- Temporizador y selector de sensibilidad de grabación.
- Visualización en la galería y reproducción directa.

### Galería Integrada

- Visualización de archivos capturados.
- Soporte para organización por categorías.
- Compatibilidad con distintos formatos.

### Interfaz de Usuario

- Navegación por Fragments.
- Selector de tema visual.

### Flujo de Navegación

MainActivity → CameraFragment | AudioFragment | GalleryFragment

