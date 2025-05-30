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
