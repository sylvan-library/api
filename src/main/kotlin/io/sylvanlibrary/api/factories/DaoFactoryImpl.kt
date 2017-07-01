package io.sylvanlibrary.api.factories

import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.daos.Dao
import org.skife.jdbi.v2.DBI

class DaoFactoryImpl: DaoFactory {
  override fun <T : Dao> get(daoClass: Class<T>, conn: HikariDataSource): T = DBI(conn).open(daoClass)
}
