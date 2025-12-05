# Tickets Support Server (Spring Microservices)

Plataforma completa de gestión de tickets de soporte, diseñada para empresas que brindan soporte a usuarios finales. Incluye autenticación, chat en vivo por ticket, trazabilidad de estados y está lista para ejecutarse con Docker (gateway + microservicios + config server).

## Características clave
- Gestión integral de tickets: apertura, seguimiento, cambio de estado, cierre, trazabilidad de tiempo y responsable.
- Autenticación y autorización centralizada (JWT/OAuth2 según configuración).
- Chat en vivo por ticket para comunicación directa usuario–soporte.
- Arquitectura de microservicios con API Gateway.
- Config Server centralizado para propiedades por entorno.
- Preparado para contenedores (Docker), con orquestación vía `docker-compose`.
- Logs y trazabilidad de eventos de ticket (timestamp por cambio de estado).

## Arquitectura (alto nivel)
- **Gateway**: entrada única, routing y filtros cross-cutting.
- **Auth/User Service**: registro/login, emisión y validación de tokens.
- **Ticket Service**: CRUD de tickets, estados, SLA/tiempos.
- **Chat Service**: mensajes en tiempo (o near-real-time) asociados al ticket.
- **Config Server**: propiedades centralizadas (perfiles `dev`, `prod`, etc.).
- **Discovery/Registry**: Eureka

## Tecnologías
- Java + Spring Boot (Web, Security, Data, Cloud Config, Gateway, Eureka Server
- Base de datos: (PostgreSQL) para tickets/usuarios
- WebSocket/STOMP
- Docker/Docker Compose para despliegue local/CI

## Requisitos previos
- JDK 17+ (ajusta según tu build)
- Docker y Docker Compose
- Maven o Gradle

## Roadmap
- Métricas y trazas (Micrometer + Prometheus/Grafana).
- Rate limiting en gateway.
- SLA tracking y notificaciones (email/webhook).
- Historial de chat y anexos en ticket.
- Hardening de seguridad (CORS, headers, rotación de claves).
