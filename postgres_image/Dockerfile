FROM postgres:latest

RUN mkdir -p /var/ssl

COPY ssl/server.crt /var/ssl/server.crt
COPY ssl/server.key /var/ssl/server.key
COPY ssl/server.req /var/ssl/server.req
COPY postgresql.conf /var/ssl/postgresql.conf
COPY pg_hba.conf /var/ssl/pg_hba.conf

COPY ssl/ssl_entrypoint.sh /docker-entrypoint-initdb.d/ssl_entrypoint.sh
RUN chown postgres /var/ssl/postgresql.conf
RUN chown postgres /var/ssl/pg_hba.conf
RUN chown postgres /var/ssl/server.key && chmod 600 /var/ssl/server.key
