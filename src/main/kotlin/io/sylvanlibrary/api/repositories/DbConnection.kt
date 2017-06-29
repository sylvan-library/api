package io.sylvanlibrary.api.repositories

import com.google.inject.Inject
import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.daos.Dao
import org.skife.jdbi.v2.DBI

class DbConnection @Inject constructor(val hikariConn: HikariDataSource) {
  fun <T: Dao, R> open(daoClass: Class<T>, dbOperation: (dao: T) -> R): R {
    val dao = DBI(hikariConn).open(daoClass)

    val result = dbOperation(dao)

    dao.close()
    hikariConn.close()

    return result
  }
}
