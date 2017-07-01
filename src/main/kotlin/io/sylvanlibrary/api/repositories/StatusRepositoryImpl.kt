package io.sylvanlibrary.api.repositories

import com.google.inject.Inject
import io.sylvanlibrary.api.daos.StatusDao

class StatusRepositoryImpl @Inject constructor(val conn: DbConnection): StatusRepository {
  override fun check(): Boolean {
    val result: Int = conn.open(StatusDao::class.java) { statusDao ->
      statusDao.check()
    }

    return result > 0
  }
}
