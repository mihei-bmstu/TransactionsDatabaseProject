FROM postgres:15.0

ENV POSTGRES_PASSWORD transactions
ENV POSTGRES_USER transactions

ADD create_db.sql /docker-entrypoint-initdb.d/
