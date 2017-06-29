package io.sylvanlibrary.api

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.repositories.*
import io.sylvanlibrary.api.services.*
import org.skife.jdbi.v2.DBI

class ApplicationModule : AbstractModule() {
  override fun configure() {
    bind(DbConfig::class.java).`in`(Singleton::class.java)
    bind(SetRepository::class.java).to(SetRepositoryImpl::class.java)
    bind(SetService::class.java).to(SetServiceImpl::class.java)
    bind(StatusRepository::class.java).to(StatusRepositoryImpl::class.java)
  }

  @Provides
  @Singleton
  fun provideHikariConfig(dbConfig: DbConfig): HikariConfig {
    val hikariConfig = HikariConfig()

    hikariConfig.jdbcUrl = dbConfig.dbUrl
    hikariConfig.username = dbConfig.username
    hikariConfig.password = dbConfig.password

    return hikariConfig
  }

  @Provides
  fun provideDbConnection(hikariConfig: HikariConfig): DBI {
    return DBI(HikariDataSource(hikariConfig))
  }
}
