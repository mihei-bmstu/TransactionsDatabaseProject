package producer.functions

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import java.util.Properties

object StringProducer extends KafkaConfigService {
  val kafkaProperties = new Properties()
  kafkaProperties.put("bootstrap.servers", kafkaHost)
  kafkaProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
  kafkaProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

}
