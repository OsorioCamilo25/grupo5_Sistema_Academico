# Retroalimentación — Laboratorio Lista Simple (Momento 2)

**Grupo:** Grupo5 · **Proyecto:** Sistema Académico

## Nota

| Criterio | Peso | Nota (0-5) |
|---|---|---|
| Identificación de relaciones uno-a-muchos | 20% | 5.0 |
| `ListaSimple<T>` integrada al `Service` | 30% | 3.5 |
| Menú en consola funcional | 15% | 3.5 |
| Reemplazo del arreglo previo, sin código muerto | 20% | 3.5 |
| Buenas prácticas (commits y nombres) | 15% | 3.0 |
| **Nota del laboratorio** | | **3.73** |

La nota se calcula así: 20% relaciones + 30% integración al `Service` + 15% menú + 20% reemplazo sin código muerto + 15% buenas prácticas.

## 1. Relaciones uno-a-muchos (5.0)
**Lo que hicieron bien:**
- Eligieron `Estudiante` con sus `Matricula` y `Matricula` con sus `Calificacion`. Las dos son relaciones reales del sistema académico.
- Cada entidad del lado "uno" guarda a las del lado "muchos" en un atributo `ListaSimple`.

## 2. `ListaSimple<T>` integrada al `Service` (3.0)
**Lo que hicieron bien:**
- `Estudiante` y `Matricula` usan `ListaSimple` en lugar de `ArrayList`.
- Los servicios usan `insertarFinal`, `buscarPorValor` y `eliminarPorValor` para agregar, buscar y quitar.

**Lo que pueden mejorar:**
- `MenuView` usa `ListaSimple` y `Nodo` directamente para listar. La vista solo debe hablar con el `Service`, que debería devolverle los datos ya listos.
- Los servicios devuelven la lista completa a la vista, lo que deja la estructura interna expuesta.

## 3. Menú en consola (3.5)
**Lo que hicieron bien:**
- El menú permite agregar, listar y eliminar matrículas y calificaciones, y funciona sin errores.

**Lo que pueden mejorar:**
- Al eliminar una matrícula se crea una nueva y se borra "la que se parezca". Como todas las matrículas de un estudiante se consideran iguales, siempre se borra la primera.
- Las calificaciones siempre se guardan con una materia genérica ("Generica") y solo sobre la primera matrícula del estudiante.

## 4. Reemplazo del arreglo previo (3.5)
**Lo que hicieron bien:**
- Ya no queda ningún `ArrayList` en el proyecto y `calcularPromedioGeneral` se ajustó a la lista.

**Lo que pueden mejorar:**
- `ListaSimple` tiene métodos repetidos que hacen lo mismo: `eliminarInicio` y `eliminarAlInicio`, `eliminarFinal` y `eliminarAlFinal`. Dejen solo uno de cada par.
- `Matricula` tiene un constructor sin parámetros que nadie usa.

## 5. Buenas prácticas (3.0)
**Lo que hicieron bien:**
- Hay commits frecuentes con mensajes claros, de varios integrantes. Los nombres de clases y métodos siguen las convenciones de Java.

**Lo que pueden mejorar:**
- No siguieron la estructura de carpetas acordada en clase: los servicios están en `model/service`, y deberían estar en `service/` junto a `view/` y `utils/`.- Casi todo el trabajo se hizo directamente en `main`, sin ramas de trabajo ni merges. Algunos mensajes tienen errores de escritura.
- Conviene tener un orden más uniforme (sangría) en el código.

## ¿El programa funciona?
Sí, compila y corre bien. Probamos agregar una matrícula y una calificación, listarlas y eliminarlas, y todo respondió como se esperaba.

## Para el próximo laboratorio
- Quiten `Nodo` y `ListaSimple` de `MenuView`: que el `Service` le entregue los datos ya listos.
- Muevan `service` a la carpeta correcta y revisen que la estructura coincida con la acordada.
- Borren los métodos repetidos y el constructor que no se usa.
- Permitan elegir la matrícula y la materia reales en el menú, y diferencien una matrícula de otra.
- Trabajen en ramas y únanlas a `main` con merge.
