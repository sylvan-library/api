package io.sylvanlibrary.api.factories

import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.daos.Dao

interface DaoFactory {
  fun <T: Dao> get(daoClass: Class<T>, conn: HikariDataSource): T
}
