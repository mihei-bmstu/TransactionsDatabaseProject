import services._
import slick.basic._
import slick.jdbc._

import scala.concurrent.ExecutionContext

object Boot extends App with ConfigService {

  val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)
  flywayService.migrateDatabase



}
