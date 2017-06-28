package io.sylvanlibrary.api.repositories

import java.util.*

class DbConfig {
  companion object {
    const val DEFAULT_DB_URL = "jdbc:postgresql://localhost:5432/sylvanapi"
    const val DEFAULT_USERNAME = "sylvanlibrary"
    const val DEFAULT_PASSWORD = "jacesucks"
  }

  val dbUrl: String
    get() = Optional.ofNullable(System.getenv("DB_URL")).orElse(DEFAULT_DB_URL)

  val username: String
    get() = Optional.ofNullable(System.getenv("DB_USERNAME")).orElse(DEFAULT_USERNAME)

  val password: String
    get() = Optional.ofNullable(System.getenv("DB_PASSWORD")).orElse(DEFAULT_PASSWORD)
}
