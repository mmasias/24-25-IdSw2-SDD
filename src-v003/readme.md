# 🧠 Src-V003 - Diseño Orientado a Objetos

## ✅ Objetivos del Refactor

- Cumplir con **los principios SOLID**.
- Aplicar **buenas prácticas de encapsulamiento, abstracción y modularidad**.
- Separar claramente responsabilidades entre **modelo, controlador, vista y utilidades**.
- Mejorar la mantenibilidad, extensibilidad y legibilidad del sistema.
- Corregir errores de entrada/salida en consola relacionados con `Scanner`.

---

## 🗂️ Estructura del Proyecto

- `modelo/`: Clases del dominio como `Mesa`, `Pedido`, `Reserva`, `Plato`, etc.
- `controlador/`: Lógica de control del sistema (`Main`, `Restaurante`).
- `vista/`: Implementación de la interfaz de usuario en consola (`VistaConsola`, `IVista`).
- `util/`: Clases auxiliares (`Constantes`, `Utilidades`).

---

## ✨ Cambios Realizados

### Diseño OO Aplicado

| Principio | Aplicación |
|----------|------------|
| **SRP** - Responsabilidad Única | Cada clase cumple una sola función: lógica, dominio, vista o utilidad. |
| **OCP** - Abierto/Cerrado | Uso de `Enum EstadoMesa`, interfaces como `IVista`, y clases fácilmente extensibles. |
| **LSP** - Sustitución de Liskov | Se puede sustituir `VistaConsola` por otra implementación sin afectar `Main`. |
| **ISP** - Segregación de Interfaces | `IVista` evita que otras clases dependan de métodos que no usan. |
| **DIP** - Inversión de Dependencias | `Main` depende de `IVista`, no directamente de `VistaConsola`. |

---

### Cambios por archivo

#### 🔹 `modelo/`

- **Mesa.java**: Se reemplazó el uso de Strings para el estado por un `Enum EstadoMesa`.
- **Pedido.java / Reserva.java**: Se encapsularon datos y se eliminaron salidas por consola.
- **Plato.java / Personal.java**: Validaciones agregadas, getters claros.
- **EstadoMesa.java**: Nuevo enum para representar el estado de una mesa de forma segura y extensible.

#### 🔹 `controlador/`

- **Restaurante.java**: Se eliminó toda salida de consola. Solo se encarga de lógica de negocio.
- **Main.java**: Reescrito para actuar como coordinador entre vista y lógica. Entrada/salida delegada a la vista.

#### 🔹 `vista/`

- **VistaConsola.java**: Toda la interacción con el usuario. Validaciones robustas de entrada. Usa `Scanner` correctamente.
- **IVista.java**: Nueva interfaz para desacoplar la vista de `Main`.

#### 🔹 `util/`

- **Constantes.java**: Documentado y estructurado.
- **Utilidades.java**: Hecha no instanciable. Método `esNumero` mejorado.

---

## 🚀 Resultado

- El programa funciona con mejor estructura interna.
- Puede extenderse fácilmente a nuevas vistas (por ejemplo, GUI o REST) sin tocar la lógica de negocio.
- Cumple completamente con los principios enseñados en la asignatura de IdSw2.
