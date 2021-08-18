FROM postgres:alpine

COPY init-db /docker-entrypoint-initdb.d

ENV POSTGRES_USER andersen
ENV POSTGRES_PASSWORD pass123
ENV POSTGRES_DB andersen

EXPOSE 5432