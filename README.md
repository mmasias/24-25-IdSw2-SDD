# Sistema de Gestión de Biblioteca Universitaria

## Descripción del Proyecto

Este proyecto tiene como objetivo la digitalización del sistema de gestión de préstamos y recursos de una biblioteca universitaria. La biblioteca contiene diversos tipos de materiales y atiende a diferentes tipos de usuarios, permitiendo administrar préstamos, devoluciones, reservas y sanciones por retrasos. También se llevará un registro detallado del inventario y su disponibilidad.

Proyecto: [pyBibliotecas](https://github.com/puntoReflex/pyBiblioteca/blob/main/enunciado.md)

## Requisitos Básicos

### Gestión de Inventario
El sistema debe permitir administrar los siguientes tipos de materiales:
- **Libros**: título, autor, editorial, año, género, ISBN, número de copias.
- **Revistas**: título, editorial, número, mes/año, temática.
- **Material audiovisual**: título, formato (DVD, CD, etc.), duración, tema.
- **Recursos digitales**: título, formato, tamaño, link de acceso, tipo de licencia.

### Gestión de Usuarios
Cada usuario tendrá un tipo y diferentes privilegios:
- **Estudiantes**: máximo 5 ítems por 15 días.
- **Profesores**: máximo 10 ítems por 30 días.
- **Administrativos**: máximo 3 ítems por 10 días.
- **Visitantes**: solo consulta en sala, sin préstamos.

### Sistema de Préstamos
El sistema debe:
- Registrar qué usuario toma prestado qué material.
- Llevar control de la fecha de préstamo y devolución esperada.
- Verificar si el usuario tiene capacidad para más préstamos.
- Verificar si el material está disponible para préstamo.

### Sistema de Devoluciones
El sistema debe:
- Registrar la devolución del material.
- Calcular si hay retraso en la devolución.
- Aplicar sanciones en caso de retraso (1 día de suspensión por cada día de retraso).

## Retos Extendidos

### Sistema de Reservas
- Los usuarios pueden reservar material que esté prestado.
- Cuando el material es devuelto, se notifica al primer usuario en la lista de reservas.
- El material se mantiene reservado por 48 horas antes de pasar al siguiente usuario.

### Sistema de Búsqueda
- Buscar por título (búsqueda aproximada).
- Buscar por autor.
- Buscar por tema o género.
- Filtrar por disponibilidad.
- Ordenar resultados por relevancia o fecha de adquisición.

### Sistema de Estadísticas
- Material más solicitado por categoría.
- Usuarios con más préstamos.
- Tasa de rotación del material.
- Días promedio de préstamo por tipo de usuario.
- Material que nunca ha sido prestado.

## Escenarios Avanzados

### Sistema de Renovación de Préstamos
- Los usuarios pueden renovar un préstamo hasta 2 veces.
- No se puede renovar si el material está reservado por otro usuario.
- No se puede renovar si el usuario tiene sanciones pendientes.

### Sistema de Recomendaciones
- Basado en préstamos anteriores, sugiere material similar.
- Muestra las novedades en categorías de interés del usuario.
- Recomienda material popular en la comunidad.

### Gestión de Copias
- Algunas copias pueden ser solo de consulta (no préstamo).
- El sistema decide qué copia entregar (la más antigua, la más nueva).
- Cuando una copia se reporta como dañada, se registra y se retira de circulación.

### Control de Acceso a Recursos Digitales
- Los recursos digitales tienen diferentes tipos de licencias.
- Algunos recursos tienen un límite de accesos simultáneos.
- Ciertos recursos son accesibles solo desde la red de la universidad.

## Implementación de Interfaz
El sistema debe mostrar:
- Estado actual del inventario.
- Lista de préstamos activos.
- Usuarios con sanciones.
- Reservas pendientes.
- Estadísticas generales.
