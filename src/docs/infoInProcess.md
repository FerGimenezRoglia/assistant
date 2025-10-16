# 🧠 Parte 2 – Resumen técnico profesional
_(para retomar después)_

## 💡 Proyecto
**Internal FAQ Assistant (Asistente interno de preguntas frecuentes)**  
Tecnologías: **Spring Boot + H2 + Consola interactiva**

---

## ✅ Estado actual del proyecto

**Arquitectura implementada:**
- `model`: `KnowledgeEntry (id, question, answer, approved)`
- `repository`: interfaz JPA con queries por `approved`
- `service`: CRUD completo con validaciones y excepciones
- `exception`: manejo global centralizado con `@ControllerAdvice`
- `controller`: `KnowledgeController` base creado
- `console`: flujo funcional inicial (`ConsoleRunner`, `ConsoleUI`, `UserFlowHandler`, `AdminFlowHandler`)

---

## ⚙️ Funcionalidades implementadas

**Empleado (consola):**
- Selección de rol en menú inicial
- Menú de categorías (sin uso aún)
- Ingreso de nombre
- Escritura de pregunta
- Llamada al endpoint `GET /api/knowledge?approved=true`
- Match básico (`contains()`)
- Si encuentra coincidencia → muestra respuesta → pide feedback
- Si no hay coincidencia → informa y ofrece sugerir nueva entrada
- Envío de sugerencias (`POST /api/knowledge`, `approved=false`)

---

## 🖥️ Backend

- Persistencia con **H2 in-memory** (los datos se borran al reiniciar)
- `POST` fuerza `approved = false` para nuevas entradas
- `PUT /api/knowledge/{id}` permite aprobar manualmente entradas
- `RestTemplate` configurado en `AssistantApplication` para llamadas HTTP internas

---

## ⚠️ Limitaciones detectadas

| Área | Detalle |
|------|----------|
| Persistencia | H2 borra datos al reiniciar. Evaluar uso de MySQL o H2 file. |
| Búsqueda | Comparación textual literal; se podría mejorar con normalización. |
| Categorías | Selección visible en consola, pero sin atributo en el modelo. |
| Navegación | Falta opción para volver al menú principal sin reiniciar. |
| AdminFlow | Estructura creada pero sin lógica aún. |

---

## 🚀 Próximos pasos propuestos

1. **Agregar campo `category` a `KnowledgeEntry`:**
   ```java
   @Column(nullable = false)
   private String category;
