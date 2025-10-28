# Microservicios con Eureka

Este proyecto es un ejemplo de microservicios usando Spring Boot y Eureka para service discovery.

## Arquitectura

- **Eureka Server**: Servicio de registro y descubrimiento en el puerto 8761.
- **User Management Service**: Servicio de gestión de usuarios en el puerto 8080.
- **MongoDB**: Base de datos para persistencia.
- **RabbitMQ**: Broker de mensajes.

## Requisitos

- Java 17
- Maven
- Docker y Docker Compose

## Ejecución

1. **Iniciar infraestructura**:

   ```bash
   docker-compose up -d
   ```

   Esto iniciará MongoDB en el puerto 27017 y RabbitMQ con Management UI en los puertos 5672 y 15672.

2. **Iniciar Eureka Server**:

   ```bash
   cd eurekaserver
   mvn spring-boot:run
   ```

   Accede a <http://localhost:8761> para ver el dashboard de Eureka.

3. **Iniciar User Management Service**:

   ```bash
   cd usermgmt
   mvn clean spring-boot:run
   ```

   El servicio se registrará automáticamente en Eureka.

## URLs de acceso

- Eureka Server: <http://localhost:8761>
- User Management Service: <http://localhost:8080>
- RabbitMQ Management: <http://localhost:15672> (usuario: guest, contraseña: guest)
- MongoDB: localhost:27017

## Endpoints del servicio de usuarios

- GET /users: Mensaje de bienvenida
- GET /users/listAll: Lista todos los usuarios
- GET /users/{userId}: Obtiene un usuario por ID
- POST /users/create: Crea un nuevo usuario
- DELETE /users/{userId}: Elimina un usuario