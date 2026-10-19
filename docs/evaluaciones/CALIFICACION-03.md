# Retroalimentación — Pilas y Colas en el proyecto de aula

**Grupo:** Grupo5 · **Proyecto:** Sistema Académico

Muy buen trabajo, sobre todo con la cola, que quedó casi perfecta.

## Nota

| Pilas | Puntos | | Colas | Puntos |
|---|---|---|---|---|
| `Pila<T>` correcta | 30/30 | | `Cola<T>` correcta | 30/30 |
| Casos límite | 10/10 | | Casos límite | 10/10 |
| Integración en entidad y `Service` | 22/30 | | UML y entidad dueña | 15/15 |
| Menú en `view/` | 10/15 | | Integración en el `Service` | 23/25 |
| Buenas prácticas | 11/15 | | Menú en `view/` | 10/10 |
| | | | Buenas prácticas | 9/10 |
| **Total Pilas** | **83/100** | | **Total Colas** | **97/100** |

**Nota del laboratorio: 4.50**

Se calcula así: (83 + 97) ÷ 2 = 90 sobre 100, y luego ÷ 20 para pasarlo a escala de 0 a 5.

## 1. Pilas (83/100)
**Lo que hicieron bien:**
- `Pila<T>` funciona muy bien: apila, desapila y consulta en orden, y avisa con un mensaje claro cuando está vacía o cuando se intenta apilar `null`.
- La pila vive en `Materia`, como pide el enunciado, es privada y se crea en el constructor.
- Listar el historial con una pila auxiliar deja todo en su lugar: probamos listar dos veces y el orden y la cantidad no cambiaron.
- El menú usa `ConsoleUtils`, no usa `try/catch` y revisa si hay notas antes de deshacer, así que no se cierra con la pila vacía.

**Lo que pueden mejorar:**
- Al deshacer una nota, solo se saca de la pila; no se vuelve la nota de la materia a su versión anterior.
- El `Service` recibe la `Materia` completa y no busca la materia con un buscador propio, como se pedía.
- En la opción 1 del menú, la vista apila directamente en la pila de la materia en vez de llamar a un método del `Service`. La vista solo debe pedir datos y llamar al `Service`.
- La materia del menú es una materia de prueba fija, no una del sistema.
- Buenas prácticas: la rama `feature/pila-en-el-proyecto` quedó fusionada a `main`, pero hay pocos commits y no uno por cada Parte.

## 2. Colas (97/100)
**Lo que hicieron bien:**
- `Cola<T>` está muy bien hecha: al vaciarla quedan limpios el frente y el fin, y probamos encolar de nuevo después de vaciarla y funcionó.
- `ProcesoMatricula` coincide con el diagrama: tiene `semestre`, la fila privada creada en el constructor, sin `setFila`.
- El `Service` registra buscando al estudiante (con su excepción si no existe), atiende sin sacar al estudiante de la lista y lista la fila rotando sin alterarla.
- El menú es limpio: una llamada al `Service` por opción y revisa si hay gente antes de atender.
- Rama `feature/cola-en-el-proyecto` fusionada y un commit por cada Parte.

**Lo que pueden mejorar:**
- Al listar la fila se ve algo como `Estudiante@84e` en lugar de datos legibles: a `Estudiante` le falta un `toString()`.
- Los commits de la cola se hicieron casi todos la noche anterior a la entrega; repártanlos mejor en el tiempo.
