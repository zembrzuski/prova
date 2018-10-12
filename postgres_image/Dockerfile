FROM postgres:latest

RUN mkdir -p /var/ssl

COPY ssl/server.crt /var/ssl/server.crt
COPY ssl/server.key /var/ssl/server.key
COPY ssl/server.req /var/ssl/server.req

# RUN mkdir -p /docker-entrypoint-initdb.d
COPY ssl/ssl_entrypoint.sh /docker-entrypoint-initdb.d/ssl_entrypoint.sh
