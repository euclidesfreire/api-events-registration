# NLW CONNECT | Rocketseat

🔥 Java: API REST de inscrição em eventos com Spring Boot, MySQL e validação de regras de negócio.


## Estudos

- Failed to start App, java.net.BindException: Endereço já em uso

    - Verificar a porta host que está ocupada
        > lsof -i :8080

    - Liberar porta
        > kill -9 PID
