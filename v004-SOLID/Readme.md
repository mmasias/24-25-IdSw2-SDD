# ✅ Aplicación de los principios SOLID

## 1. S - Single Responsibility Principle (SRP)
Separar responsabilidades de cada clase para que tenga un solo motivo para cambiar.

### 🔧 Cambios realizados:
- **Caja**: mantiene solo la lógica de atención.
- **Estadisticas**: solo mide, no interactúa con otras clases.
- **GestorCajas**: no imprime (delegamos eso al `VisualizadorSimulacion`).
- **ControladorSimulacion**: no mezcla visualización y lógica. Creamos interfaces y las inyectamos.

---

## 2. O - Open/Closed Principle (OCP)
Clases abiertas a extensión pero cerradas a modificación.

### 🔧 Cambios realizados:
- Se introducen interfaces como `IVisualizador`, `IGeneradorClientes` e `IProcesadorCajas` para permitir intercambiabilidad.

---

## 3. L - Liskov Substitution Principle (LSP)
Una clase hija debe poder sustituir a su padre sin romper el sistema.

### 🔧 Cambios realizados:
- `GeneradorClientes` implementa una interfaz para facilitar sustitución por otros generadores (por ejemplo, para testing o reglas distintas).

---

## 4. I - Interface Segregation Principle (ISP)
Interfaces específicas en lugar de generales.

### 🔧 Cambios realizados:
- Interfaces separadas: `IVisualizador`, `IGeneradorClientes`, etc.

---

## 5. D - Dependency Inversion Principle (DIP)
Dependencias hacia abstracciones, no hacia clases concretas.

### 🔧 Cambios realizados:
- `ControladorSimulacion` ya no conoce `VisualizadorSimulacion` ni `GeneradorClientes` directamente, sino que recibe interfaces.
---

## 🔧 Nuevas interfaces
Se han creado interfaces específicas para cumplir con los principios SOLID y facilitar la extensibilidad y el desacoplamiento.

## 🔁 Implementación concreta
Cada interfaz tiene una implementación concreta intercambiable, lo que permite modificar el comportamiento sin alterar el resto del sistema.

## 🎮 ControladorSimulacion con Inversión de Dependencias
El `ControladorSimulacion` no depende de implementaciones concretas, sino de abstracciones que se le inyectan.

## 🧨 App.java ahora inyecta las dependencias correctamente
La clase principal (`App.java`) es responsable de crear las instancias concretas y pasarlas al controlador, siguiendo el principio de inversión de dependencias.

---

## ✅ Resultado final
Tu aplicación ahora:

- Está organizada por responsabilidades únicas.
- Usa interfaces para invertir dependencias.
- Es fácilmente extensible (por ejemplo, podrías crear otro `VisualizadorSimulacionGrafico`, otro `GeneradorClientesTest`, etc.).
