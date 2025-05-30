# 🚀 Proyecto **SimuladorAscensores**

Simulación de ascensores en una universidad, gestionando la llegada de personas, colas de espera, movimientos de ascensores y visualización por consola.

---

## 📂 Estructura de Clases

- **Universidad**  
  Coordina la entrada de personas, su estancia en las plantas, y la operación de los ascensores. Gestiona el estado global.

- **Planta**  
  Representa cada nivel del edificio. Administra colas de espera y personas dentro realizando actividades.

- **Persona**  
  Usuario que conoce su planta destino desde su creación. Tiene un tiempo de estancia limitado y lógica para entrar, esperar y salir del edificio.

- **Llamada**  
  Solicitud de uso del ascensor generada por una persona. Incluye planta origen, destino y la persona que la emite.

- **Ascensor**  
  Vehículo que transporta personas entre plantas. Atiende llamadas, recoge y deja personas según capacidad y prioridad.

- **Tiempo**  
  Controlador horario del sistema. Avanza minuto a minuto y define si la universidad está abierta o cerrada.

- **ControlAscensor**  
  Asigna llamadas a los ascensores más cercanos y gestiona su movimiento. Considera ventanas de evacuación.

- **Mundo** / **Main**  
  Punto de entrada de la simulación. Inicializa los objetos principales y ejecuta el bucle temporal del sistema.

- **ConsolaVista**, **LineaVista**, **RepresentadorPlanta**  
  Clases que muestran el estado de la universidad y sus componentes a través de la consola, planta por planta.

---

## 🏗️ Modelo del Dominio

### 🗂️ Diagrama de Clases

![Diagrama de Clases](images/MdD/DdC/DdC.svg)  
[📖 Ver PlantUML](modelosUML/MdD/DdC/DdC.puml)

---

### 📦 Diagrama de Objetos

![Diagrama de Objetos](images/MdD/DiagramaDeObjetos/DiagramaObjetos.svg)  
[📖 Ver PlantUML](modelosUML/MdD/DiagramaObjetos/DiagramaObjetos.puml)

---

## 🔄 Diagramas de Estados

| Entidad      | Imagen                                                                                  | Código PlantUML                                                                                       |
|--------------|-----------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------|
| **Persona**  | ![Estados Persona](images/MdD/DiagramaEstados/DiagramaEstadosPersona.svg)              | [📖 modelo_estados_persona.puml](modelosUML/MdD/DiagramaEstados/DiagramaEstadosPersona.puml)         |
| **Llamada**  | ![Estados Llamada](images/MdD/DiagramaEstados/DiagramaDeEstadosLlamada.svg)            | [📖 modelo_estados_llamada.puml](modelosUML/MdD/DiagramaEstados/DiagramaDeEstadosLlamada.puml)       |
| **Planta**   | ![Estados Planta](images/MdD/DiagramaEstados/DiagramaEstadosPlanta.svg)                | [📖 modelo_estados_planta.puml](modelosUML/MdD/DiagramaEstados/DiagramaEstadosPlanta.puml)           |
| **Ascensor** | ![Estados Ascensor](images/MdD/DiagramaEstados/DiagramaEstadosAscensor.svg)            | [📖 modelo_estados_ascensor.puml](modelosUML/MdD/DiagramaEstados/DiagramaEstadosAscensor.puml)       |

---

## ✅ Lógica de Finalización

La simulación termina cuando:
- La hora del sistema alcanza las **21:00**, cerrando el acceso al edificio.
- **No quedan personas** dentro de ninguna planta ni esperando ni en ascensores.
- Todos los ascensores se vacían y el sistema entra en estado de reposo.

