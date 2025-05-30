# 🧠 V004 - Mejora del Diseño SOLID con Fábricas y Modularidad Avanzada

## ✅ Objetivos del Refactor

- **Mejorar la versión V003**, que ya aplicaba principios SOLID, con una estructura más escalable y profesional.
- Introducir el **patrón Factory** para desacoplar la creación de objetos.
- Dividir responsabilidades aún más mediante **controladores y vistas especializadas por entidad**.
- Facilitar la extensión a futuras interfaces (GUI, REST, etc.) sin tocar la lógica interna.
- Hacer el sistema más **modular, mantenible y testeable**.

---

## 🗂️ Estructura del Proyecto

- `factory/`: Clases para la creación centralizada de objetos (vista, controlador, productos, pedidos).
- `implementacion/controlador/`: Controladores separados por entidad (`MesaController`, `PedidoController`, etc.).
- `implementacion/modelo/`: Clases del dominio (`Mesa`, `Pedido`, `Plato`, etc.) con validaciones y encapsulación.
- `implementacion/vista/`: Vistas especializadas para cada entidad y una consola coordinadora (`VistaConsola`).
- `Main.java`: Punto de entrada, ahora completamente desacoplado de implementaciones concretas.

---

## ✨ Mejoras Respecto a V003

| Aspecto | Versión V003 | Versión V004 |
|--------|---------------|--------------|
| Principios SOLID | Aplicación básica y efectiva | Refuerzo con uso de fábricas y separación por entidad |
| Controladores | Unificados en `Restaurante.java` | Descompuestos por entidad |
| Vistas | Centralizada en `VistaConsola` | Descompuestas y reutilizables (`MesaVista`, etc.) |
| Creación de objetos | Manual y directa | Delegada a clases Factory |
| Escalabilidad | Buena, pero limitada por acoplamiento | Alta, con bajo acoplamiento y alta cohesión |
| Mantenibilidad | Clara pero monolítica | Totalmente modular |

---

## 📁 Detalles por Carpeta

#### 🏭 `factory/`

- **VistaFactory / PedidoFactory / ProductoFactory**: Aplicación del patrón Factory para reducir el acoplamiento y facilitar pruebas y mantenimiento.

#### 🎮 `implementacion/controlador/`

- Controladores específicos como `MesaController`, `PedidoController`, etc., para separar lógica de negocio y facilitar testing.

#### 🧠 `implementacion/modelo/`

- Se mantiene el uso de `EstadoMesa` como Enum.
- Clases más limpias, solo representan lógica de dominio.
- Validaciones internas y encapsulamiento robusto.

#### 👁️ `implementacion/vista/`

- Vistas especializadas por entidad: `MesaVista`, `PedidoVista`, `ProductoVista`.
- `VistaConsola` orquesta la interacción, pero delega a vistas especializadas.

---

## 🚀 Resultado Final

- Se mejora notablemente la estructura interna con respecto a V003.
- El sistema ahora es mucho más fácil de extender, probar y mantener.
- Cumple completamente con principios SOLID.
- Preparado para integrarse con nuevas interfaces sin reescribir la lógica central.
