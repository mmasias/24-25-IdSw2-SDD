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

Vemos cómo funcionan las clases y cómo se relacionan entre sí, una vez ya implementadas en el código.

| Clase               | Descripción | Relaciones de Composición |
|---------------------|-------------|---------------------------|
| [**ComponenteHoja**](/src/ComponenteHoja.java)  | Clase base abstracta con constantes y métodos comunes para todas las demás clases. | N/A |
| [**Matriz**](/src/Matriz.java)          | Representa la cuadrícula de datos de la hoja de cálculo. Almacena y gestiona una colección de celdas. | Contenida en HojaCalculo. Compone múltiples Celdas. |
| [**Celda**](/src/Celda.java)           | Almacena y formatea datos dentro de la hoja de cálculo. Mantiene tanto el contenido completo como su versión formateada para visualización. | Parte de Matriz. Visualizada por InterfazUsuario y modificada por Teclado. |
| [**Posicion**](/src/Posicion.java)        | Define la ubicación actual dentro de la matriz y permite la navegación a través de la hoja de cálculo. | Contenida en HojaCalculo. Visualizada por InterfazUsuario. |
| [**Teclado**](/src/Teclado.java)         | Maneja la entrada de usuario y permite la edición directa de celdas. | Usada por InterfazUsuario. Modifica Celdas. |
| [**InterfazUsuario**](/src/InterfazUsuario.java) | Muestra la hoja de cálculo y gestiona la interacción con el usuario. Visualiza tanto la posición actual como el contenido de las celdas. | Utilizada por HojaCalculo. Contiene Teclado. Visualiza Celdas y la Posición. |
| [**HojaCalculo**](/src/HojaCalculo.java)     | Clase principal que coordina la aplicación y sus componentes. Gestiona el flujo de ejecución del programa. | Contiene Matriz, Posicion e InterfazUsuario. |

## 5. Legibilidad

| **Aspecto**           | **Descripción** |
|----------------------|---------------|
| **Nombres claros** | Clases como `Matriz`, `Celda` e `InterfazUsuario` reflejan bien su propósito. |
| **Estructura organizada** | Diagramas UML muestran relaciones bien definidas entre componentes. |
| **Modularidad** | Cada clase tiene una función específica (`Teclado` modifica `Celda`, `HojaCalculo` coordina). |
