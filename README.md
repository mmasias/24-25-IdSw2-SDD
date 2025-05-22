# Simulación de Gestión de Colas y Cajas

Este repositorio alberga un sistema de simulación para la gestión de colas y cajas en un entorno comercial. El objetivo es evaluar métricas clave del flujo de clientes y artículos vendidos, partiendo de una implementación lineal y evolucionando hacia un diseño basado en principios de programación orientada a objetos (POO), modularidad y patrones de diseño.

---

## 📋 Tabla de Contenidos

1. [Enunciado del Proyecto](#enunciado-del-proyecto)
2. [Diseño General](#diseño-general)
3. [Versiones del Desarrollo](#versiones-del-desarrollo)
   - [Versión 1: Implementación Lineal](#versión-1-implementación-lineal)
   - [Versión 2: Enfoque Orientado a Objetos](#versión-2-enfoque-orientado-a-objetos)
   - [Versión 3: Arquitectura MVC Modular](#versión-3-arquitectura-mvc-modular)
   - [Versión 4: Principios SOLID](#versión-4-principios-solid)
4. [Modelo del Dominio](#modelo-del-dominio)

---

## Enunciado del Proyecto

El documento de enunciado define los requisitos y objetivos del sistema de simulación. Incluye:

- **Objetivo:** Analizar el comportamiento de un sistema de cajas y colas.
- **Condiciones iniciales:** Parámetros de llegada de clientes y distribución de artículos.
- **Reglas de atención:** Lógica de asignación de clientes a cajas.
- **Métricas a registrar:** Tiempo de espera, longitud de cola, uso de cajas, artículos procesados.

> 📄 [Acceder al enunciado completo](documentos/enunciado.md)

---

## Diseño General

Descripción de la arquitectura y componentes del sistema:

1. **Diagrama de clases:** Estructura de entidades y relaciones.
2. **Clases principales:** Responsabilidades y métodos clave.
3. **Interacción entre objetos:** Flujo de datos y eventos.
4. **Justificación de diseño:** Motivación de las decisiones para garantizar flexibilidad y escalabilidad.

> 📄 [Ver detalles del diseño](documentos/diseño.md)

---

## Versiones del Desarrollo

### Versión 1: Implementación Lineal

- Enfoque inicial con programación lineal.
- Código monolítico en Java.
- **Repositorio:** [RetoOriginal](https://github.com/0xJVR/24-25-IdSw2-SDD/tree/main/v001-Inicial)
- Limitaciones: difícil mantenimiento y extensibilidad.

### Versión 2: Enfoque Orientado a Objetos

- Refactorización a POO: clases bien definidas y encapsulación.
- Mejora de modularidad y legibilidad.
- **Repositorio:** [RetoMejora](https://github.com/0xJVR/24-25-IdSw2-SDD/tree/main/v002-Mejorado)

### Versión 3: Arquitectura MVC Modular

- Aplicación del patrón Modelo-Vista-Controlador.
- Separación clara de responsabilidades.
- **Repositorio:** [RetoModular](https://github.com/0xJVR/24-25-IdSw2-SDD/tree/main/v003-Modular)

### Versión 4: Principios SOLID

- Integración de los cinco principios SOLID.
- Código altamente desacoplado y orientado a la extensión.
- **Repositorio:** [SOLID](https://github.com/0xJVR/24-25-IdSw2-SDD/tree/main/v004-SOLID)

---

## Modelo del Dominio

El modelo del dominio define las entidades y sus relaciones fundamentales:

- **Entidades principales:** Cliente, Caja, Artículo, Cola.
- **Atributos:** Identificador, tiempos de llegada/servicio, número de artículos, etc.
- **Relaciones:** Asociación entre clientes y cajas, colas de espera.

> 📄 [Explorar el modelo completo](documentos/modeloDominio.md)
