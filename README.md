## Docker for Mac >= 1.12, Linux, Docker for Windows 10

```
docker run --rm -it \
           -p 2181:2181 -p 3030:3030 -p 8081:8081 \
           -p 8082:8082 -p 8083:8083 -p 9092:9092 \
           -e ADV_HOST=127.0.0.1 \
           landoop/fast-data-dev
```

## Docker toolbox
```
docker run --rm -it \
          -p 2181:2181 -p 3030:3030 -p 8081:8081 \
          -p 8082:8082 -p 8083:8083 -p 9092:9092 \
          -e ADV_HOST=192.168.99.100 \
          landoop/fast-data-dev
```

## Kafka command lines tools
```
docker run --rm -it --net=host landoop/fast-data-dev bash
```

## Basic Examples
```
Examples:  https://github.com/apache/kafka/tree/trunk/examples/src/main/java/kafka/examples
Video: https://www.youtube.com/watch?v=fXnNEq1v3VA
Spark Kafka
- https://github.com/BenFradet/spark-kafka-writer
- https://spark.apache.org/docs/2.1.0/streaming-kafka-integration.html

```