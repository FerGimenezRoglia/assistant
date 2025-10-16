
# Flujo Funcional - Asistente de Preguntas Frecuentes (Consola + API)

## Menú Inicial (Consola)

```
¿Quién eres?
[1] Empleado
[2] Administrador
```

---

## Perfil: Empleado

1. Se solicita el **nombre del usuario** (consola).
2. Se muestra saludo personalizado.
3. Se muestra **menú de categorías**:
   ```
   [1] Calendario y vacaciones
   [2] Onboarding e integración
   [3] Tecnología de la empresa
   [4] Cultura interna
   [5] Otros
   ```
4. El usuario **escribe su pregunta** (consola).
5. Se consulta `GET /api/knowledge` filtrando `approved = true`.

### Si encuentra coincidencia:
- Muestra la respuesta (consola).
- Pregunta si fue útil:
  ```
  ¿Esta respuesta fue útil?
  [1] Sí
  [2] No
  ```

#### Si fue útil:
- Agradece y finaliza.

#### Si NO fue útil:
- Muestra opciones:
  ```
  [1] Sugerir una posible respuesta
  [2] Enviar solo la pregunta
  ```
- Se envía vía `POST /api/knowledge` con `approved = false`.

### Si NO encuentra coincidencia:
- Muestra mensaje: "No tengo esa respuesta aún."
- Muestra las mismas dos opciones:
  ```
  [1] Sugerir una posible respuesta
  [2] Enviar solo la pregunta
  ```
- Se envía vía `POST /api/knowledge` con `approved = false`.

---

## Perfil: Administrador

1. Se solicita el **nombre del administrador** (consola).
2. Se muestra saludo personalizado.
3. Se muestra menú de opciones:
   ```
   [1] Ver entradas aprobadas
   [2] Ver sugerencias no aprobadas
   [3] Revisar una sugerencia pendiente
   ```

### Ver entradas aprobadas:
- `GET /api/knowledge?approved=true`

### Ver sugerencias no aprobadas:
- `GET /api/knowledge?approved=false`

### Revisar una sugerencia pendiente:
- Se muestra pregunta y respuesta sugerida.
- Se permite **editar** texto de pregunta/respuesta.
- Se aprueba manualmente → actualiza `approved = true`.

### Eliminar una entrada o sugerencia:
- Se pregunta si está seguro de eliminar.

---

## Notas Técnicas

- Todo el flujo de usuarios y admin es por **consola**.
- Todos los datos son consultados o enviados por **API REST**.
- Persistencia usando base de datos H2.
- Endpoints:
  - `GET /api/knowledge`
  - `POST /api/knowledge`
  - `PUT /api/knowledge/{id}` (para aprobación)
  - `DELETE /api/knowledge/{ID}`

---

## Fin del Flujo

Este esquema está pensado para ser funcional, extensible y legible.
