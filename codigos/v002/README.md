[![Inicio](https://img.shields.io/badge/-Inicio-FFF?style=flat&logo=Emlakjet&logoColor=black)](https://github.com/lydiaa-gr/24-25-IdSw2-SDD)
[![Diseño](https://img.shields.io/badge/-Diseño-FFF?style=flat&logo=openstreetmap&logoColor=black)](https://github.com/lydiaa-gr/24-25-IdSw2-SDD/blob/main/modelosUML/modelos.md)
[![Diseño Modular](https://img.shields.io/badge/-Diseño_modular-FFF?style=flat&logo=openstreetmap&logoColor=black)](https://github.com/lydiaa-gr/24-25-IdSw2-SDD/blob/main/casosDeUso/readme.md)
[![Diseño POO](https://img.shields.io/badge/-Diseño_POO-FFF?style=flat&logo=openstreetmap&logoColor=black)](https://github.com/lydiaa-gr/24-25-IdSw2-SDD/tree/main/codigos)
          
# Diseño Modular (DM)

Este documento resume los cambios clave realizados respecto a la versión anterior (codigos/v001), presentando una arquitectura escalable y mantenible basada en el patrón MVC (Modelo-Vista-Controlador).

## 0. Diferencias con la versión anterior (codigos/v001)

### Cambios arquitectónicos

- **Aplicación del patrón MVC**:
  - **Modelo**: Zona, Habitacion, Aspiradora, Bateria, Gato, Mueble
  - **Vista**: VistaConsola
  - **Controlador**: Mundo

- **Organización por paquetes**:
  - Separación clara de responsabilidades
  - Mejor estructura de código

- **Eliminación de dependencias innecesarias**:
  - Uso de interfaces (UnidadConMovimiento)
  - Mejor encapsulamiento de funcionalidades

- **Módulos más pequeños y especializados**:
  - Cada clase tiene una única responsabilidad
  - Código más limpio, mantenible y preparado para ampliaciones

## 1. Diseño Modular y Estructura MVC

### Objetivo
Adoptar una arquitectura que favorezca la extensibilidad, mantenibilidad y claridad funcional.

| Capa | Clases Principales | Descripción |
|------|-------------------|-------------|
| Modelo | Zona, Habitacion, Aspiradora, Bateria, Gato, Mueble | Representan los datos y su lógica interna |
| Vista | VistaConsola | Encargada de mostrar el mundo y gestionar la interacción visual |
| Controlador | Mundo | Controla el flujo y la lógica del programa |

## 2. Jerarquía de Clases

### Nivel Básico

| Clase | Usa / Depende de | Descripción |
|-------|-----------------|-------------|
| Zona | Mueble | Unidad mínima del espacio en la habitación |
| ZonaDeRecarga | Zona, VistaConsola | Zona especial para recargar la aspiradora |
| Mueble | - | Representa obstáculos en la habitación |

### Nivel Medio

| Clase | Usa | Descripción |
|-------|-----|-------------|
| Habitacion | Zona, ZonaDeRecarga | Estructura bidimensional de zonas |
| Bateria | VistaConsola | Gestiona la energía de la aspiradora |
| UnidadConMovimiento | - | Interfaz para elementos que pueden moverse |

### Nivel Alto

| Clase | Usa | Descripción |
|-------|-----|-------------|
| Aspiradora | Bateria, UnidadConMovimiento, VistaConsola | Unidad principal que limpia la habitación |
| Gato | Habitacion, Aspiradora, UnidadConMovimiento, VistaConsola | Entidad que se mueve y ensucia la habitación |
| Mundo | Aspiradora, Bateria, Habitacion, Gato, VistaConsola | Orquesta el ciclo de vida de la aplicación |

## 3. Diagrama de Clases

El diagrama de clases muestra las relaciones entre los componentes del sistema, destacando:
- Herencia (ZonaDeRecarga extiende Zona)
- Implementación de interfaces (Aspiradora y Gato implementan UnidadConMovimiento)
- Composición (Mundo compone Habitacion, Aspiradora contiene Bateria)
- Asociaciones (Gato interactúa con Habitacion y Aspiradora)

## 4. Cumplimiento de Principios Fundamentales

| Principio | Cumplimiento | Observaciones |
|-----------|--------------|---------------|
| Alta Cohesión | Excelente | Responsabilidades claramente separadas por clases |
| Bajo Acoplamiento | Excelente | Dependencias minimizadas gracias a interfaces y encapsulamiento |
| Tamaño Adecuado | Excelente | Clases enfocadas en una única responsabilidad |

## 5. Análisis del Acoplamiento

- **Acoplamiento por datos**: Comunicación entre clases a través de estructuras bien definidas.
- **Acoplamiento por interfaz**: Interacción mediante métodos públicos, sin dependencia en implementaciones internas.
- **Separación de responsabilidades**: Cada clase pertenece a una capa específica (modelo, vista o controlador).
- **Interacción controlada**: Las clases interactúan a través de interfaces bien definidas.

## 6. Análisis Detallado

| Componente | Cohesión | Acoplamiento | Tamaño | Fortalezas |
|------------|---------|--------------|--------|------------|
| Modelo | Funcional | Bajo | Adecuado | Datos encapsulados, sin lógica de presentación ni control |
| Vista | Funcional | Bajo | Adecuado | Solo presentación e interacción, sin lógica de negocio |
| Controlador | Funcional | Bajo | Adecuado | Orquesta el flujo de la app, mantiene modelo y vista desacoplados |

## 7. Comparativa de Versiones

| Aspecto | Versión 1 (codigos/v001) | Versión 2 (codigos/v002) |
|---------|-------------------------|-------------------------|
| Arquitectura | Monolítica | Patrón MVC |
| Cohesión | Aceptable | Excelente |
| Acoplamiento | Moderado/Alto | Bajo |
| Mantenibilidad | Media | Alta |
| Extensibilidad | Limitada | Mejorada |
| Reutilización | Baja | Alta |

## 8. Mejoras codigos/v002

- Separación clara de responsabilidades mediante Modelo, Vista y Controlador.
- Mejor encapsulamiento de la lógica de negocio.
- Menor acoplamiento entre componentes gracias al uso de interfaces.
- Clases especializadas en tareas concretas y bien acotadas.
- Mayor cohesión: cada clase cumple un único propósito definido.
- Implementación de control manual del gato por parte del usuario.
- Mejor manejo de la interacción entre componentes del sistema.

## 9. Cambios Implementados

- Se ha añadido la funcionalidad para que el usuario pueda controlar manualmente el movimiento del gato.
- Se ha implementado la lógica para evitar que el gato atraviese muebles.
- Se ha mejorado la interacción usuario-sistema mediante controles intuitivos (WASD).
- Se ha mantenido la coherencia del sistema respetando las reglas existentes (pasos del gato, ensuciamiento).

        