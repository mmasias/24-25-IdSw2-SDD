# Diseño SOLID - Juego de Vampiros v3.0

## 1. **Descripción del Proyecto**

El Juego de Vampiros v3.0 representa una evolución significativa desde la versión modular v2.0, transformando el diseño hacia una **arquitectura completamente basada en principios SOLID**. El sistema implementa un combate por turnos entre un Héroe y un Vampiro, donde el diseño orientado a objetos no solo separa responsabilidades, sino que aplica de manera ejemplar todos los principios SOLID para crear un código mantenible, extensible y testeable.

### Arquitectura SOLID

La versión 3.0 introduce una **arquitectura basada en contratos (interfaces)** que implementa los cinco principios SOLID:

- **📐 S - Single Responsibility**: Cada clase tiene una única responsabilidad bien definida
- **🔓 O - Open/Closed**: Extensible sin modificación mediante herencia e interfaces
- **🔄 L - Liskov Substitution**: Implementaciones intercambiables que respetan contratos
- **🎯 I - Interface Segregation**: Interfaces específicas y cohesivas
- **🔀 D - Dependency Inversion**: Dependencias mediante abstracciones, no implementaciones

### Estructura de Paquetes Evolutiva

**📂 auth - Gestión de Usuarios**
| Clase/Interfaz | Descripción | Principio SOLID |
|-------|-------------|----------------|
| [IGestorUsuarios](/src/JuegoVampiro3/core/interfaces/IGestorUsuarios.java) | **Contrato** para gestión de usuarios | **DIP** - Abstracción estable |
| [GestorUsuarios](/src/JuegoVampiro3/auth/GestorUsuarios.java) | **Implementación** de autenticación | **SRP** - Responsabilidad única |

**📂 core/interfaces - Contratos del Sistema**
| Interfaz | Descripción | Principio SOLID |
|----------|-------------|----------------|
| [IPersonaje](/src/JuegoVampiro3/core/interfaces/IPersonaje.java) | Contrato básico de personaje de combate | **ISP** - Interfaz específica |
| [ILuchador](/src/JuegoVampiro3/core/interfaces/ILuchador.java) | Extensión para personajes controlables | **ISP** - Segregación de interfaces |
| [IVistaJuego](/src/JuegoVampiro3/core/interfaces/IVistaJuego.java) | Contrato para interfaces de usuario | **DIP** - Inversión de dependencias |
| [IControladorJuego](/src/JuegoVampiro3/core/interfaces/IControladorJuego.java) | Contrato para controladores de juego | **OCP** - Abierto para extensión |

**📂 core - Lógica del Juego**
| Clase | Descripción | Principio SOLID |
|-------|-------------|----------------|
| [Personaje](/src/JuegoVampiro3/core/Personaje.java) | Clase abstracta base con **método plantilla** | **OCP** + **SRP** |
| [Ataque](/src/JuegoVampiro3/core/Ataque.java) | Jerarquía extensible de ataques | **OCP** - Extensión sin modificación |
| [Arma](/src/JuegoVampiro3/core/Arma.java) | Implementación específica de ataques del Héroe | **LSP** - Sustitución coherente |
| [Mordida](/src/JuegoVampiro3/core/Mordida.java) | Implementación específica de ataques del Vampiro | **LSP** - Sustitución coherente |
| [Pocion](/src/JuegoVampiro3/core/Pocion.java) | Gestión encapsulada de pociones | **SRP** - Responsabilidad única |
| [Guerrero](/src/JuegoVampiro3/core/Guerrero.java) | Implementación del personaje jugador | **LSP** + **SRP** |
| [Vampiro](/src/JuegoVampiro3/core/Vampiro.java) | Implementación del enemigo IA | **LSP** + **SRP** |
| [Batalla](/src/JuegoVampiro3/core/Batalla.java) | Controlador de combate **desacoplado** | **DIP** - Depende de abstracciones |
| [JuegoVampiros](/src/JuegoVampiro3/core/JuegoVampiros.java) | **Orquestador principal** con inyección de dependencias | **DIP** + **SRP** |

**📂 ui - Interfaz de Usuario**
| Clase | Descripción | Principio SOLID |
|-------|-------------|----------------|
| [VistaConsola](/src/JuegoVampiro3/ui/VistaConsola.java) | **Implementación intercambiable** de interfaz | **DIP** - Implementa contrato |
| [CredencialesUsuario](/src/JuegoVampiro3/ui/CredencialesUsuario.java) | Encapsulación de datos de usuario | **SRP** - Responsabilidad única |

### 📊 **Comparación de Versiones**

| Aspecto | JuegoVampiro2 | JuegoVampiro3 | Mejora |
|---------|---------------|---------------|---------|
| **Interfaces** | 0 | 5 | ✅ **Contratos estables** |
| **Inyección de Dependencias** | No | Sí | ✅ **Testabilidad** |
| **Acoplamiento** | Alto | Bajo | ✅ **Flexibilidad** |
| **Extensibilidad** | Limitada | Alta | ✅ **OCP aplicado** |
| **Violaciones LSP** | 1 | 0 | ✅ **Polimorfismo limpio** |
| **Testabilidad** | Difícil | Excelente | ✅ **Mocks inyectables** |

## Cambios post revisión 29/05

- Cambiados los nombres de las clases interfaces
- Arreglados todos los errores de compilación
- Movido JuegoVampiro.java un directorio arriba