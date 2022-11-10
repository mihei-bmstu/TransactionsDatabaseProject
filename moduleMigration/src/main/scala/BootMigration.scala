import services._

object BootMigration extends App with ConfigService {

  val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)
  flywayService.migrateDatabase

}
