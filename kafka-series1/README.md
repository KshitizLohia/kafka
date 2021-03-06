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
```

## Spark Kafka
```
- https://github.com/BenFradet/spark-kafka-writer
- https://spark.apache.org/docs/2.1.0/streaming-kafka-integration.html

```


## Akka Streams
```
https://doc.akka.io/docs/akka/current/stream/stream-introduction.html?language=java
https://doc.akka.io/docs/akka-stream-kafka/current/home.html
https://github.com/makersu/reactive-kafka-scala-example
```

## Scala
```
https://github.com/cakesolutions/scala-kafka-client
```


## Nifi
```$xslt
# Short instructions for Mac / Linux
# download NiFi at: https://nifi.apache.org/download.html
# Terminal 1:
bin/nifi.sh run
# install docker for mac / docker for linux
# Terminal 2:
docker run -it --rm -p 2181:2181 -p 3030:3030 -p 8081:8081 -p 8082:8082 -p 8083:8083 -p 9092:9092 -e ADV_HOST=127.0.0.1 -e RUNTESTS=0 landoop/fast-data-dev
# Terminal 3:
docker run -it --net=host landoop/fast-data-dev bash
kafka-topics --create --topic nifi-topic --zookeeper 127.0.0.1:2181 --partitions 3 --replication-factor 1
kafka-console-consumer --topic nifi-topic --bootstrap-server 127.0.0.1:9092

```



## Spring
```
https://projects.spring.io/spring-kafka/ 
https://spring.io/blog/2015/04/15/using-apache-kafka-for-integration-and-data-processing-pipelines-with-spring
https://github.com/spring-projects/spring-kafka
https://github.com/spring-projects/spring-integration-kafka


```

## Log Compaction


### Start cluster
```
docker run --rm -it \
           -p 2181:2181 -p 3030:3030 -p 8081:8081 \
           -p 8082:8082 -p 8083:8083 -p 9092:9092 \
           -e ADV_HOST=127.0.0.1 \
           landoop/fast-data-dev
```


### New tab: follow the broker log
```
docker ps
docker exec -it <ID> tail -f /var/log/broker.log
```




### Kafka command lines tools
```
docker run --rm -it --net=host landoop/fast-data-dev bash
```

### New tab: create our topic with appropriate configs
```
kafka-topics --zookeeper 127.0.0.1:2181 --create \
             --topic employee-salary-compact \
             --partitions 1 --replication-factor 1 \
             --config cleanup.policy=compact \
             --config min.cleanable.dirty.ratio=0.005 \
             --config segment.ms=10000
```

### in a new tab, we start a consumer
```
kafka-console-consumer --bootstrap-server 127.0.0.1:9092 \
                       --topic employee-salary-compact \
                       --from-beginning \
                       --property print.key=true \
                       --property key.separator=,
```

### we start pushing data to the topic
```
kafka-console-producer --broker-list 127.0.0.1:9092 \
                          --topic employee-salary-compact \
                          --property parse.key=true \
                          --property key.separator=,
```

### copy these messages:
```
123,{"John":"80000"}
456,{"Mark":"90000"}
789,{"Lisa":"95000"}
```

### we observe that the messages have been pushed and read by the consumer

### we know push the following new messages
```
789,{"Lisa":"110000"}
123,{"John":"100000"}
```

