package io.sylvanlibrary.api.repositories

import com.google.inject.Inject
import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.daos.Dao
import io.sylvanlibrary.api.factories.DaoFactory

class DbConnectionImpl @Inject constructor(val daoFactory: DaoFactory, val hikariConn: HikariDataSource): DbConnection {
  override fun <T: Dao, R> open(daoClass: Class<T>, dbOperation: (dao: T) -> R): R {
    val dao = daoFactory.get(daoClass, hikariConn)
    val result = dbOperation(dao)

    dao.close()
    hikariConn.close()

    return result
  }
}
