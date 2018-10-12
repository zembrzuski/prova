#/bin/sh

cp /var/ssl/server.crt /var/lib/postgresql/data
cp /var/ssl/server.key /var/lib/postgresql/data
cp /var/ssl/server.req /var/lib/postgresql/data
cp /var/ssl/postgresql.conf /var/lib/postgresql/data
cp /var/ssl/pg_hba.conf /var/lib/postgresql/data
