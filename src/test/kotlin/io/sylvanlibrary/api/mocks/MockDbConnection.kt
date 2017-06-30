package io.sylvanlibrary.api.mocks

import io.sylvanlibrary.api.daos.Dao
import io.sylvanlibrary.api.repositories.DbConnection

@Suppress("UNCHECKED_CAST")
class MockDbConnection(val mockDao: Dao): DbConnection {
  override fun <T : Dao, R> open(daoClass: Class<T>, dbOperation: (dao: T) -> R): R {
    return dbOperation(mockDao as T)
  }
}
