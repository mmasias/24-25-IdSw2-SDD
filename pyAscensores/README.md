# 🚀 Proyecto **pyAscensores**

Simulación de ascensores en una universidad, gestionando la llegada de personas, colas de espera, movimientos de ascensores y visualización por consola.

---

## 📂 Estructura de Clases

- **Universidad**  
  Coordina generación de llegadas, gestión de estancias y control de ascensores.

- **Planta**  
  Representa cada planta del edificio, con colas de personas en espera (`esperando`) y personas dentro (`enPlanta`).

- **Persona**  
  Modelo de usuario con destino, tiempo de estancia y llamadas internas.

- **Llamada**  
  Petición de uso de ascensor: origen de la solicitud y referencia a la persona.

- **Ascensor**  
  Simula un ascensor con capacidad limitada. Gestiona llamadas, pasajeros y movimiento.

- **Tiempo**  
  Reloj de simulación (día, hora, minuto). Avanza el tiempo y valida ventanas de entrada.

- **ControlAscensor**  
  Asigna llamadas a ascensores y dispara su movimiento.

- **Mundo** / **Main**  
  Bucle principal que avanza el reloj y empieza la simulación.

- **ConsolaVista**, **LineaVista**, **RepresentadorPlanta**  
  Clases para representar el estado actual en consola.

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

| Entidad  | Imagen                                                                                  | Código PlantUML                                                                                   |
|----------|-----------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Persona**  | ![Estados Persona](images/MdD/DiagramaEstados/DiagramaEstadosPersona.svg)         | [📖 modelo_estados_persona.puml](modelosUML/MdD/DiagramaEstados/DiagramaEstadosPersona.puml)      |
| **Llamada**  | ![Estados Llamada](images/MdD/DiagramaEstados/DiagramaDeEstadosLlamada.svg)         | [📖 modelo_estados_llamada.puml](modelosUML/MdD/DiagramaEstados/DiagramaDeEstadosLlamada.puml)      |
| **Planta**   | ![Estados Planta](images/MdD/DiagramaEstados/DiagramaEstadosPlanta.svg)           | [📖 modelo_estados_planta.puml](modelosUML/MdD/DiagramaEstados/DiagramaEstadosPlanta.puml)        |
| **Ascensor** | ![Estados Ascensor](images/MdD/DiagramaEstados/DiagramaEstadosAscensor.svg)       | [📖 modelo_estados_ascensor.puml](modelosUML/MdD/DiagramaEstados/DiagramaEstadosAscensor.puml)    |


