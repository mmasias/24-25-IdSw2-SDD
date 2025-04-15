## 🧱 Arquitectura del Proyecto – Diseño Modular (MVC)

El proyecto ha sido refactorizado siguiendo el patrón **Modelo-Vista-Controlador (MVC)** para mejorar la organización del código, facilitar la escalabilidad y promover una clara separación de responsabilidades.

---
## 📦 Diagrama de clases

![](/images/modelosUML/diagramaClasesModular.svg)

---

## 🧠 Componentes Principales

| Capa         | Clases                                                                  | Rol                                                                                      |
|--------------|-------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| **Modelo**   | `Cliente`, `Caja`, `Cola`, `GestorCajas`, `Estadisticas`                | Gestionan la lógica de negocio: atención al cliente, control de cajas, métricas de uso   |
| **Vista**    | `VisualizadorSimulacion`                                                | Presenta el estado de la simulación al usuario (por consola u otra salida visual)        |
| **Controlador** | `ControladorSimulacion`                                             | Ejecuta y coordina la lógica del sistema minuto a minuto, gestionando la simulación      |
| **Utilidades** | `GeneradorClientes`, `Constantes`                                    | Proveen herramientas de soporte como generación aleatoria de datos y configuración global |

---

Cada capa interactúa con las demás de forma controlada para asegurar un bajo acoplamiento y una alta cohesión entre los componentes. Esto facilita el mantenimiento del sistema y permite incorporar nuevas funcionalidades (más cajas, nuevos tipos de clientes, etc.) sin romper la estructura general del código.

# Comparación entre Diseño Modular (Avance 2) y Proyecto Mejorado (Avance 1)

Este documento detalla las diferencias clave entre un **diseño modular** (organizado en carpetas por responsabilidades) y un **proyecto mejorado con solo clases** (sin estructura de carpetas).

---

## 📂 Estructura del Proyecto

| **Aspecto**               | **Diseño Modular**                                      | **Proyecto Mejorado (Solo Clases)**                     |
|---------------------------|---------------------------------------------------------|---------------------------------------------------------|
| **Organización**          | Carpetas separadas:                                     | Todas las clases en un único paquete.                   |
|                           | - `Modelo`, `Vista`, `Controlador`, `Utilidades`.       | Ej: `proyecto.proyectoMejorado`.                        |
| **Ejemplo de Paquete**    | `proyecto.diseñoModular.proyectoMejorado.Modelo`        | `proyecto.proyectoMejorado`                             |
| **Ventaja**               | Claridad para ubicar componentes y responsabilidades.   | Simplicidad inicial, sin navegación entre carpetas.     |

---

## 🧩 Principios de Diseño

| **Principio**             | **Diseño Modular**                                      | **Proyecto Mejorado (Solo Clases)**                     |
|---------------------------|---------------------------------------------------------|---------------------------------------------------------|
| **Bajo Acoplamiento**     | Módulos independientes. | Dependencias directas (ej: `Jornada` accede a `Cola` internamente). |
| **Alta Cohesión**         | Clases con responsabilidades únicas. | Clases multifuncionales (ej: `Jornada` maneja simulación, estadísticas y UI). |
| **Encapsulación**         | Atributos privados con métodos públicos (ej: `tiempoRestante` en `Caja`). | Exposición innecesaria de atributos/métodos.            |

---

## 🛠️ Mantenibilidad y Escalabilidad

| **Aspecto**               | **Diseño Modular**                                      | **Proyecto Mejorado (Solo Clases)**                     |
|---------------------------|---------------------------------------------------------|---------------------------------------------------------|
| **Implementar Cambios**   | Fácil: Modificar un módulo no afecta otros. | Riesgoso: Cambios en una clase pueden romper funcionalidades. |
| **Agregar Features**      | Simple: Añadir clases en módulos existentes (ej: nueva `VistaHTML`). | Complejo: Requiere modificar clases monolíticas.        |
| **Ejemplo Práctico**      | Actualizar `Estadisticas` sin tocar `GestorCajas`.      | Modificar `Jornada` afecta toda la simulación.          |

---

## 🔄 Reusabilidad y Pruebas

| **Aspecto**               | **Diseño Modular**                                      | **Proyecto Mejorado (Solo Clases)**                     |
|---------------------------|---------------------------------------------------------|---------------------------------------------------------|
| **Reusabilidad**          | Componentes como `Cola` o `GeneradorClientes` son reutilizables en otros proyectos. | Clases demasiado acopladas para reutilización.          |
| **Pruebas Unitarias**     | Fáciles: Probar `Caja` de forma aislada con Mockito.    | Difíciles: Dependencia de estados globales o otras clases. |
| **Ejemplo**               | Prueba `Estadisticas.clienteAtendido()` sin `Cola`.     | Pruebas requieren ejecutar toda la simulación en `Jornada`. |

---


