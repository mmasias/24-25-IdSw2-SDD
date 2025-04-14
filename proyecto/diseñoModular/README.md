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


