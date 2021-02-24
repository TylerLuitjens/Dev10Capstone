FROM mysql:latest

COPY *.sql /docker-entrypoint-initdb.d/

ENV MYSQL_ROOT_PASSWORD=trivia_night_passkey
ENV MYSQL_DATABASE=trivia_night