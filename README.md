# Auth Service

Microservicio de autenticación y gestión de usuarios construido con Spring Boot 3.3.2 y Java 17.

## Requisitos
- Java 17
- Maven 3.x
- Base de datos PostgreSQL o MySQL

## Configuración
Las propiedades comunes se encuentran en `src/main/resources/application.yml`.
Para cada base de datos existen perfiles:
- `postgres`: usa `application-postgres.yml`
- `mysql`: usa `application-mysql.yml`

Ajusta las credenciales de los archivos según tu entorno.

## Ejecución
```bash
# Compilar y ejecutar pruebas
mvn clean package

# Ejecutar con PostgreSQL
mvn spring-boot:run -Dspring-boot.run.profiles=postgres

# Ejecutar con MySQL
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
La API estará disponible en `http://localhost:8080`.

### Swagger
La documentación OpenAPI se expone en:
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- Especificación: `http://localhost:8080/v3/api-docs`

## Endpoints principales
- `POST /api/auth/register` – registro de usuarios
- `POST /api/auth/login` – autenticación y obtención de JWT
- `GET /api/users/me` – información del usuario autenticado

## Pruebas
Se incluyen pruebas unitarias básicas para comandos, repositorios y controladores. Ejecuta `mvn test`.
