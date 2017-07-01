package io.sylvanlibrary.api.repositories

import io.sylvanlibrary.api.daos.Dao

interface DbConnection {
  fun <T: Dao, R> open(daoClass: Class<T>, dbOperation: (dao: T) -> R): R
}
