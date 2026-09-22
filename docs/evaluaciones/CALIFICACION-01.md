# Retroalimentación — Laboratorio 1: Codificación del diseño OO

**Grupo:** Grupo5 · **Proyecto:** Sistema Académico
**Fecha límite:** 2026-09-08 23:59 · **Versión revisada:** commit `c1b6b02`

¡Muy buen trabajo! El diseño está completo y el programa funciona bien.

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| El código sigue el diagrama UML | 60% | 5.0 |
| Pruebas: creación de objetos en el programa | 20% | 5.0 |
| Buenas prácticas de programación | 20% | 3.0 |
| **Nota del laboratorio** | | **4.60** |

La nota se calcula así: 60% diseño UML + 20% pruebas + 20% buenas prácticas.

## 1. El código sigue el diagrama UML (5.0)
**Lo que hicieron bien:**
- La interfaz `RolAcademico` tiene los dos métodos que pide el diagrama.
- `Persona` es abstracta, implementa `RolAcademico` y protege sus atributos con `private`. Además revisa que el correo tenga `@` y, si no lo tiene, lanza un error. Deja `identificarRol()` para que cada subclase lo resuelva a su manera.
- `Estudiante` y `Profesor` heredan de `Persona`, tienen sus propios atributos y cada uno responde `identificarRol()` de forma distinta.
- Entre `Matricula` y `Calificacion` hay una relación fuerte: una Calificación solo se puede crear desde su Matrícula, y una Matrícula tiene una o varias Calificaciones. Justo como en el diagrama.
- Las relaciones de `Calificacion` con `Materia` y de `Matricula` con `Estudiante` están bien hechas, y `Materia` tiene sus atributos protegidos con getters y setters.

**Lo que pueden mejorar:**
- El archivo `App.java` quedó con el "Hello, World!" de plantilla y no se usa. No afecta la nota, pero conviene borrarlo o darle uso.
- No siguieron la estructura de carpetas acordada en clase: el código está dentro de una carpeta extra `SISTEMA_ACADEMICO/`. El código debe quedar en `src/` directamente en la raíz del repositorio.

## 2. Pruebas: creación de objetos (5.0)
**Lo que hicieron bien:**
- `PruebaCreacionObjetos` compila y se ejecuta sin errores.
- Crean un `Estudiante` y un `Profesor`, y agregan el estudiante a una `Matricula`.
- Recorren ambos objetos como `Persona` y llaman a `identificarRol()`. En pantalla se ve que cada uno responde diferente.

## 3. Buenas prácticas (3.0)
**Lo que hicieron bien:**
- Hicieron 14 commits antes del plazo, con mensajes claros que dicen qué cambió.
- Los nombres siguen la convención de Java: clases con mayúscula inicial (`Estudiante`) y métodos y variables en camelCase (`identificarRol`).

**Lo que pueden mejorar:**
- No usaron ramas. Todo el trabajo se hizo directamente en `main`. Lo pedido era trabajar en ramas y luego unir los cambios a `main`.

## ¿El programa funciona?
Sí. Compila sin errores y al ejecutarlo muestra los datos del estudiante y del profesor, cada uno con su rol. No hubo errores.

## Para el próximo laboratorio
- Saquen el código de la carpeta `SISTEMA_ACADEMICO/` y déjenlo en `src/` en la raíz del repositorio, como se acordó en clase.
- Creen una rama por integrante o por funcionalidad y luego unan los cambios a `main`.
- Borren o usen el `App.java` de plantilla.
