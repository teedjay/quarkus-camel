# quarkus-camel
Creating and testing Camel routes with Quarkus.

Some components are from Quarkus Camel :
- [x] file
- [x] aws-s3
- [x] http-platform (http://localhost:8080/camel/input)
- [x] timer
- [x] direct
- [x] seda
- [x] log
- [x] controlbus
- [x] bean
- [x] jdbc

Additional components from regular Camel 3 :
- [x] file-watch
- [x] mock

Other stuff that are tested :
- [x] custom @QuarkusTestResource
- [ ] inject TestContainers using @QuarkusTestResource


## Pre-requisite 1 (S3 server and Postgres)
You need a Java 11 and Maven 3.6.2 to run this.
You need to start S3 server (Minio) and PostgreSQL database before running like this :
```
cd docker
docker-compose up -d
```
Access Minio at : http://localhost:9000/minio/teedjay-bucket/

## Pre-requisite 2 (Postgres with Debezium)
You need to manually start and configure a separate Postgres 12 database
for Debezium usage.  This database should have a test table with some
data in it.

Follow the commands below to start and cofigure.
```
docker run --name postgres-debezium -p 5433:5432 -e POSTGRES_PASSWORD=postgres -d postgres:12

# https://github.com/debezium/debezium-examples/tree/master/auditlog
# docker exec -it postgres-debezium /bin/bash

(1) connect to postgres
docker exec -it postgres-debezium psql -U postgres

create table users
(
id serial not null constraint users_pk primary key,
username text,
config jsonb
);

insert into users(username, config) values ('ola', '{}');
insert into users(username, config) values ('bola', '{}');
insert into users(username, config) values ('jola', '{}');

alter table users owner to postgres;

ALTER SYSTEM SET wal_level = logical;
SHOW wal_level;

(2)
For wal level change to work, exit the psql and restart docker
docker restart postgres-debezium

(3) connect back to postgres and assert that wal_level is correct
docker exec -it postgres-debezium psql -U postgres
SHOW wal_level;

(3)
Also show old value
ALTER TABLE users REPLICA IDENTITY FULL;

There are 4 possible values for REPLICA IDENTITY:
DEFAULT - UPDATE and DELETE events will only contain the previous values for the primary key columns of a table
NOTHING - UPDATE and DELETE events will not contain any information about the previous value on any of the table columns
FULL - UPDATE and DELETE events will contain the previous values of all the table’s columns
INDEX index name - UPDATE and DELETE events will contains the previous values of the columns contained in the index definition named index name
```

## Start in Quarkus dev mode
```
mvn clean quarkus:dev
```

## Metrics
You get application metrics out of the box
```
http://localhost:8080/metrics/application
```

## Camel Health
You get Readyness and Liveness probes out of the box from `http://localhost:8080/health`.
```
{
    "status": "UP",
    "checks": [
        {
            "name": "camel",
            "status": "UP",
            "data": {
                "contextStatus": "Started",
                "name": "camel-1"
            }
        },
        {
            "name": "camel-liveness-checks",
            "status": "UP",
            "data": {
                "route:route3": "UP",
                "route:route2": "UP",
                "route:route1": "UP"
            }
        },
        {
            "name": "camel",
            "status": "UP",
            "data": {
                "contextStatus": "Started",
                "name": "camel-1"
            }
        },
        {
            "name": "camel-readiness-checks",
            "status": "UP",
            "data": {
                "route:route3": "UP",
                "route:route2": "UP",
                "route:route1": "UP"
            }
        }
    ]
}
```
