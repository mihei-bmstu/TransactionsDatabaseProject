package services

import com.typesafe.config.ConfigFactory

trait ConfigService {
  private val config = ConfigFactory.load()
  private val databaseConfig = config.getConfig("database")
  val jdbcUrl: String = databaseConfig.getString("url")
  val dbUser: String = databaseConfig.getString("user")
  val dbPassword: String = databaseConfig.getString("password")

}
