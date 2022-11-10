package producer.functions

import com.typesafe.config.ConfigFactory

trait KafkaConfigService {
  private val config = ConfigFactory.load()
  private val kafkaConfig = config.getConfig("producer")
  val kafkaHost: String = kafkaConfig.getString("host")

}
