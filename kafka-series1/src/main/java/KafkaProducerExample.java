
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerExample {
    public static void main(String[] args) {
        Properties properties = new Properties();
        // Kafka producer server
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "3");
        properties.setProperty("linger.ms", "1");


        Producer<String, String> producer = new KafkaProducer<String, String>(properties);
        //ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("second_topic","3", "another message");
        //producer.send(producerRecord);
        // producer.flush();

        for (int key = 0; key < 10; key++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(
                    "second_topic", Integer.toString(key), "another message " + Integer.toString(key));
            producer.send(producerRecord);

        }
        producer.close();


    }
}
