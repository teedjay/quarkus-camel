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


## Pre-requisite
You need a Java 11 and Maven 3.6.2 to run this.
You need to start S3 server (Minio) and PostgreSQL database before running like this :
```
cd docker
docker-compose up -d
```

Access Minio at : http://localhost:9000/minio/teedjay-bucket/

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
