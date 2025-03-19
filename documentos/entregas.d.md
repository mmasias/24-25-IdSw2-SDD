[![Inicio](https://img.shields.io/badge/-Inicio-FFF?style=flat&logo=Emlakjet&logoColor=black)](/README.md) [![Diseño](https://img.shields.io/badge/-Entrega_1-FFF?style=flat&logo=openstreetmap&logoColor=black)](/documentos/entregas.d.md) [![Diseño Modular](https://img.shields.io/badge/-Entrega_2-FFF?style=flat&logo=openstreetmap&logoColor=black)](/documentos/entregas.dM.md)  [![Diseño Orientado a Objetos](https://img.shields.io/badge/-Entrega_3-FFF?style=flat&logo=openstreetmap&logoColor=black)](/documentos/entregas.dOO.md)  [![Entrega Final](https://img.shields.io/badge/-Entrega_4-FFF?style=flat&logo=openstreetmap&logoColor=black)]()

# Diseño (D)

## 1. Introducción
El diseño de este proyecto es fundamental para estructurar adecuadamente la solución, facilitando su mantenimiento y evolución.  
Al tratarse de una hoja de cálculo en consola, es esencial definir un diseño que permita:  

- Navegación fluida.  
- Almacenamiento eficiente de los datos.  
- Interfaz de usuario intuitiva dentro de una terminal.  

## 2. Objetivo del Diseño
El diseño busca garantizar que el sistema sea:  

- **Flexible**: Capaz de adaptarse a futuras mejoras.  
- **Escalable**: Manejo eficiente de filas y columnas.  
- **Intuitivo**: Navegación clara mediante comandos (`W/A/S/D` para moverse, `E` para editar, `Q` para salir).  
- **Estructurado**: Representación clara de la hoja de cálculo con encabezados de filas y columnas.  

## 3. Modelo del Dominio

### Diagrama de clases

![Diagrama de clases](/images/modelosUML/DiagramaClases1.svg)

### Diagrama de Estados

| **Matriz** | **Celda** | **Interfaz De Usuario** |
|----------|----------|----------|
| ![Diagrama de Estados Matriz](/images/modelosUML/DiagramaEstadosMatriz.svg) | ![Diagrama de Estados Celda](/images/modelosUML/DiagramaEstadosCelda.svg) |  ![Diagrama de Estados InterfazUsuario](/images/modelosUML/DiagramaEstadosInterfazUsuario.svg) |

## 4. Código

Vemos cómo funcionan las clases y cómo se relacionan entre sí, una vez ya implementadas en el [código](/src/HojaCalculo.java).

| Clase               | Descripción | Relaciones de Composición |
|---------------------|-------------|---------------------------|
| [**ComponenteHoja**](https://github.com/oscarsantasanchez/24-25-IdSw2-SDD/blob/1262ea1a57e90b63dfb4db57a52fc116fa828348/src/HojaCalculo.java#L3)  | Clase base abstracta con constantes y métodos comunes. | N/A |
| [**Matriz**](https://github.com/oscarsantasanchez/24-25-IdSw2-SDD/blob/1262ea1a57e90b63dfb4db57a52fc116fa828348/src/HojaCalculo.java#L77)          | Representa la cuadrícula de datos de la hoja de cálculo. | Contenida en HojaCalculo. Compone múltiples Celdas. |
| [**Celda**](https://github.com/oscarsantasanchez/24-25-IdSw2-SDD/blob/1262ea1a57e90b63dfb4db57a52fc116fa828348/src/HojaCalculo.java#L52)           | Almacena y formatea datos dentro de la hoja de cálculo. | Parte de Matriz. Visualizada por InterfazUsuario y modificada por Teclado. |
| [**Posicion**](https://github.com/oscarsantasanchez/24-25-IdSw2-SDD/blob/1262ea1a57e90b63dfb4db57a52fc116fa828348/src/HojaCalculo.java#L10)        | Define la ubicación dentro de la matriz y permite navegar. | Contenida en HojaCalculo. Visualizada por InterfazUsuario. |
| [**Teclado**](https://github.com/oscarsantasanchez/24-25-IdSw2-SDD/blob/1262ea1a57e90b63dfb4db57a52fc116fa828348/src/HojaCalculo.java#L110)         | Maneja la entrada de usuario y la edición de celdas. | Usada por InterfazUsuario. Modifica Celdas. |
| [**InterfazUsuario**](https://github.com/oscarsantasanchez/24-25-IdSw2-SDD/blob/1262ea1a57e90b63dfb4db57a52fc116fa828348/src/HojaCalculo.java#L137) | Muestra la hoja de cálculo y gestiona la interacción. | Usa HojaCalculo, agrega Teclado y visualiza Celdas y Posiciones. |
| [**HojaCalculo**](https://github.com/oscarsantasanchez/24-25-IdSw2-SDD/blob/1262ea1a57e90b63dfb4db57a52fc116fa828348/src/HojaCalculo.java#L198)     | Coordina la aplicación y sus componentes. | Contiene Matriz, Posicion e InterfazUsuario. |

## 5. Legibilidad

| **Aspecto**           | **Descripción** |
|----------------------|---------------|
| **Nombres claros** | Clases como `Matriz`, `Celda` e `InterfazUsuario` reflejan bien su propósito. |
| **Estructura organizada** | Diagramas UML muestran relaciones bien definidas entre componentes. |
| **Modularidad** | Cada clase tiene una función específica (`Teclado` modifica `Celda`, `HojaCalculo` coordina). |
