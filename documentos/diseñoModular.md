# Diseño modular - Versión 2 (src-v002)

## 1. *Jerarquización del Proyecto*

<div align=center>

| Diagrama de Clases |
|--------------------|
|![Diagrama](/ModeloDeDominio/imagenes/DiagramaDeClasesV002.svg)|

</div>

## 📦 Estructura

| Paquete        | Propósito                                                              |
|----------------|------------------------------------------------------------------------|
| `modelo`       | Contiene las clases que representan los datos de la aplicación         |
| `vista`        | Contiene la clase encargada de mostrar información al usuario          |
| `controlador`  | Contiene las clases que gestionan la lógica y flujo del restaurante    |
| `util`         | Agrupa utilidades y constantes compartidas                             |

---

## 🧱 Clases por Módulo

### Modelo

| Clase                                      | Depende de / Usa | Descripción                                                                 |
|-------------------------------------------|------------------|-----------------------------------------------------------------------------|
| [`Mesa`](src-v002/modelo/Mesa.java)       | -                | Representa una mesa del restaurante con número, ocupación, etc.             |
| [`Reserva`](src-v002/modelo/Reserva.java) | `Mesa`           | Encapsula los datos de una reserva y asocia una mesa                       |
| [`Pedido`](src-v002/modelo/Pedido.java)   | `Mesa`, `Plato`  | Representa un pedido asociado a una mesa, incluye platos                    |
| [`Plato`](src-v002/modelo/Plato.java)     | -                | Define los datos de un plato: nombre, precio, categoría, etc.              |
| [`Personal`](src-v002/modelo/Personal.java)| -               | Representa al personal del restaurante, como camareros o cocineros         |

---

### Vista

| Clase                                                       | Depende de / Usa      | Descripción                                                             |
|--------------------------------------------------------------|------------------------|-------------------------------------------------------------------------|
| [`VistaConsola`](src-v002/vista/VistaConsola.java)           | `Constantes`          | Muestra menús y opciones por consola, interactúa con el usuario         |

---

### Controlador

| Clase                                                     | Depende de / Usa                              | Descripción                                                                   |
|------------------------------------------------------------|-----------------------------------------------|-------------------------------------------------------------------------------|
| [`Main`](src-v002/controlador/Main.java)                   | `VistaConsola`, `Restaurante_`               | Clase principal, inicia la aplicación e invoca el controlador general        |
| [`Restaurante_`](src-v002/controlador/Restaurante_.java)   | `Mesa`, `Reserva`, `Pedido`, `Personal`, `Utilidades` | Gestiona la lógica del sistema y coordina los datos                        |

---

### Util

| Clase                                            | Depende de / Usa | Descripción                                                                   |
|--------------------------------------------------|------------------|-------------------------------------------------------------------------------|
| [`Constantes`](src-v002/util/Constantes.java)    | -                | Almacena textos y valores constantes del sistema                              |
| [`Utilidades`](src-v002/util/Utilidades.java)    | -                | Métodos reutilizables como entrada segura, impresión de listas, etc.          |

---

### 🧠 Clase Principal

| Clase                                   | Depende de / Usa                          | Descripción                                                                 |
|----------------------------------------|-------------------------------------------|-----------------------------------------------------------------------------|
| [`Main`](src-v002/controlador/Main.java) | `VistaConsola`, `Restaurante_`           | Ejecuta el programa y sirve como punto de entrada al flujo de control      |

---

## ✅ Cumplimiento de Principios Fundamentales

| Principio         | Cumplimiento   | Observaciones                                                              |
|------------------|----------------|-----------------------------------------------------------------------------|
| Alta Cohesión     | ✅ Excelente    | Cada clase tiene una única responsabilidad clara                           |
| Bajo Acoplamiento | ✅ Excelente    | Las dependencias están bien organizadas entre módulos                      |
| Tamaño Adecuado   | ✅ Excelente    | Clases concisas, enfocadas en una sola tarea                               |

---

## 🔍 Análisis del Acoplamiento

- **Acoplamiento por datos**: Las clases del modelo se comunican mediante atributos claramente definidos (e.g. Pedido contiene Platos).
- **Acoplamiento por interfaz**: La vista y el controlador solo acceden a lo necesario mediante métodos públicos.
- **Separación de responsabilidades**: Clara distinción entre modelo, vista y lógica de control.
- **Utilidades externas**: Centralización en `Constantes` y `Utilidades` para evitar duplicación.

---

## 🧪 Análisis Detallado

| Componente     | Cohesión       | Acoplamiento | Tamaño  | Fortalezas                                                                 |
|----------------|----------------|--------------|---------|----------------------------------------------------------------------------|
| **Modelo**     | ✅ Funcional    | ✅ Bajo       | ✅ Adecuado | Datos bien encapsulados y separados de la lógica de presentación          |
| **Vista**      | ✅ Funcional    | ✅ Bajo       | ✅ Adecuado | Separada de la lógica, fácil de modificar                                 |
| **Controlador**| ✅ Funcional    | ✅ Bajo       | ✅ Adecuado | Encargado exclusivamente de coordinar el sistema y mantener integridad    |
| **Util**       | ✅ Funcional    | ✅ Bajo       | ✅ Adecuado | Reutilización y centralización de funciones comunes                       |

---

## ⚖ Comparativa de Versiones

| Aspecto              | Versión 1 (src-v001)     | Versión 2 (src-v002)         |
|----------------------|--------------------------|-------------------------------|
| Arquitectura         | Estructura sin capas claras | Patrón MVC bien definido       |
| Cohesión             | Media                    | Alta                          |
| Acoplamiento         | Alto                     | Bajo                          |
| Mantenibilidad       | Baja                     | Alta                          |
| Extensibilidad       | Limitada                 | Alta                          |
| Reutilización        | Escasa                   | Centralizada y efectiva       |

---

## Mejoras `src-v002`

1. **Separación clara de responsabilidades**: MVC bien aplicado.
2. **Modularización en paquetes**: mejora la organización general.
3. **Reducción del acoplamiento**: dependencias mejor estructuradas.
4. **Centralización de utilidades**: evita duplicación de código.
5. **Especialización de clases**: responsabilidades bien delimitadas.
6. **Mayor cohesión y limpieza**: facilita mantenimiento y futuras ampliaciones.

