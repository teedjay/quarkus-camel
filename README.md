# quarkus-camel
Creating testable Camel routes with Quarkus

## Pre-requisite
You need a Java 11 and Maven 3.6.2 to run this.
You need to start S3 server (Minio) and PostgreSQL database before running like this :
```
cd docker
docker-compose up -d
```

Access Minio at : http://localhost:9000/minio/teedjay-bucket/

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
