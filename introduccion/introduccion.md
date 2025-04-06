# pyBiblioteca


Una biblioteca universitaria busca digitalizar su sistema de gestión de préstamos y recursos. La biblioteca contiene diferentes tipos de materiales (libros, revistas, material audiovisual, recursos digitales) y atiende a diferentes tipos de usuarios (estudiantes, profesores, personal administrativo, visitantes).

El sistema debe permitir gestionar préstamos, devoluciones, reservas y sanciones por retrasos, así como llevar un registro detallado del inventario y su disponibilidad.

## Requisitos básicos

1. Desarrolle un código que permita administrar el inventario de la biblioteca:
   - Libros: título, autor, editorial, año, género, ISBN, número de copias.
   - Revistas: título, editorial, número, mes/año, temática.
   - Material audiovisual: título, formato (DVD, CD, etc.), duración, tema.
   - Recursos digitales: título, formato, tamaño, link de acceso, tipo de licencia.
2. Implemente un sistema para gestionar los usuarios:
   - Cada usuario tendrá un tipo (estudiante, profesor, administrativo, visitante).
   - Cada tipo de usuario tiene diferentes privilegios:
     - Estudiantes: máximo 5 items por 15 días.
     - Profesores: máximo 10 items por 30 días.
     - Administrativos: máximo 3 items por 10 días.
     - Visitantes: solo consulta en sala, sin préstamos.
3. Implemente un sistema de préstamos que:
   - Registre qué usuario toma prestado qué material.
   - Lleve control de la fecha de préstamo y devolución esperada.
   - Verifique si el usuario tiene capacidad para más préstamos.
   - Verifique si el material está disponible para préstamo.
4. Implemente un sistema de devoluciones que:
   - Registre la devolución del material.
   - Calcule si hay retraso en la devolución.
   - Aplique sanciones en caso de retraso (1 día de suspensión por cada día de retraso).

## Retos extendidos

1. Agregue un sistema de reservas:
   - Los usuarios pueden reservar material que esté prestado.
   - Cuando el material es devuelto, se notifica al primer usuario en la lista de reservas.
   - El material se mantiene reservado por 48 horas; pasado ese tiempo, pasa al siguiente en la lista.
2. Implemente un sistema de búsqueda que permita:
   - Buscar por título (búsqueda aproximada).
   - Buscar por autor.
   - Buscar por tema o género.
   - Filtrar por disponibilidad.
   - Ordenar resultados por relevancia o fecha de adquisición.
3. Desarrolle un sistema de estadísticas que muestre:
   - Material más solicitado por categoría.
   - Usuarios con más préstamos.
   - Tasa de rotación del material.
   - Días promedio de préstamo por tipo de usuario.
   - Material que nunca ha sido prestado.

## Escenarios avanzados

1. Implemente un sistema de renovación de préstamos:
   - Los usuarios pueden renovar un préstamo hasta 2 veces.
   - No se puede renovar si el material está reservado por otro usuario.
   - No se puede renovar si el usuario tiene sanciones pendientes.
2. Agregue un sistema de recomendaciones que:
   - Basado en préstamos anteriores, sugiera material similar.
   - Muestre las novedades en categorías de interés del usuario.
   - Recomiende material popular en la comunidad.
3. Implemente un sistema de gestión de copias:
   - Algunas copias pueden ser solo de consulta (no préstamo).
   - El sistema debe decidir qué copia entregar (la más antigua, la más nueva).
   - Cuando una copia se reporta como dañada, se registra y se retira de circulación.
4. Añada un sistema de control de acceso a recursos digitales:
   - Los recursos digitales tienen diferentes tipos de licencias.
   - Algunos recursos tienen un límite de accesos simultáneos.
   - Ciertos recursos son accesibles solo desde la red de la universidad.

## Implementación de interfaz

El sistema debe permitir mostrar:

- Estado actual del inventario.
- Lista de préstamos activos.
- Usuarios con sanciones.
- Reservas pendientes.
- Estadísticas generales.

## Ejemplo de visualización del sistema

```
SISTEMA BIBLIOTECA UNIVERSITARIA
----------------------------------------
FECHA ACTUAL: 15/03/2025

BÚSQUEDA: "Programación Python"
Resultados (3 items):
1. [LIBRO] "Python para todos" - Autor: Raúl Gómez - Disponible: 2/3 copias
2. [REVISTA] "Código Python #45" - Editorial: Dev Press - Disponible: 0/1 copias
   RESERVADO: Juan Pérez (hasta 17/03)
3. [DIGITAL] "Curso avanzado de Python" - Formato: PDF - Accesos disponibles: 5/5

USUARIO ACTIVO: María López (Estudiante)
- Préstamos actuales: 3/5
- Sanciones: Ninguna
- Últimos préstamos: "Estructuras de datos", "Algoritmos avanzados"
- Recomendaciones: "Patrones de diseño en Python", "Programación funcional"

PRÉSTAMO SOLICITADO: "Python para todos"
CONFIRMACIÓN:
- Fecha préstamo: 15/03/2025
- Fecha devolución: 30/03/2025
- Código ejemplar: LIB-PY-003-2
----------------------------------------
```

## Desafío final

Extienda el sistema para soportar una red de bibliotecas:

- Múltiples sedes con diferentes inventarios.
- Posibilidad de préstamos inter-bibliotecarios.
- Sistema de traslado de material entre sedes.
- Usuarios pueden devolver en cualquier sede.
- Estadísticas comparativas entre sedes.
