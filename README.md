# Assistant - Internal FAQ Console App

Aplicación de consola desarrollada como práctica para una hackathon, cuyo objetivo es simular un **asistente interno** de preguntas frecuentes dentro de una empresa.

Permite a los empleados realizar consultas categorizadas sobre temas internos y a los administradores gestionar y aprobar sugerencias de nuevos conocimientos.

---

##  Funcionalidad

- Menú interactivo de consola para:
    - Empleados: consultar categorías y hacer preguntas.
    - Administradores: revisar y aprobar sugerencias.
- Persistencia en base de datos H2 en memoria.
- Carga y gestión de preguntas/respuestas.
- Sugerencia de nuevas FAQs por parte de usuarios.
- Consola diferenciada según rol (empleado / administrador).

---

##  Tecnologías usadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

---

##  Modo de uso

### 1. Clonar el repositorio
git clone https://github.com/tu-usuario/assistant.git
cd assistant

### 2. Ejecutar el proyecto
./mvnw spring-boot:run

### 3. Seguir las instrucciones en consola.
Al correr el proyecto, se lanzará automáticamente la consola y se activará el flujo desde ConsoleRunner.


---

##  Notas

Este proyecto fue diseñado con enfoque en buenas prácticas:
- Arquitectura limpia y modular.
- Nomenclatura estándar.
- Git fluido con convenciones de commits.
- `.gitignore` personalizado y optimizado.

---

## 📬 Contacto

Este repositorio es parte de una práctica individual para hackathon. Para dudas o sugerencias, no dudes en abrir una issue o forkear el proyecto.