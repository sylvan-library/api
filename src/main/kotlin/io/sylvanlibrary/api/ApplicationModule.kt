package io.sylvanlibrary.api

import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.factories.DaoFactory
import io.sylvanlibrary.api.factories.DaoFactoryImpl
import io.sylvanlibrary.api.renderers.*
import io.sylvanlibrary.api.repositories.*
import io.sylvanlibrary.api.services.SetService
import io.sylvanlibrary.api.services.SetServiceImpl

class ApplicationModule : AbstractModule() {
  override fun configure() {
    bind(DbConfig::class.java).`in`(Singleton::class.java)
    bind(SetRepository::class.java).to(SetRepositoryImpl::class.java)
    bind(SetService::class.java).to(SetServiceImpl::class.java)
    bind(StatusRepository::class.java).to(StatusRepositoryImpl::class.java)
    bind(DbConnection::class.java).to(DbConnectionImpl::class.java)
    bind(DaoFactory::class.java).to(DaoFactoryImpl::class.java).`in`(Singleton::class.java)
    bind(Renderer::class.java).to(RendererImpl::class.java).`in`(Singleton::class.java)
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
  fun provideHikariConnection(hikariConfig: HikariConfig): HikariDataSource {
    return HikariDataSource(hikariConfig)
  }
}
