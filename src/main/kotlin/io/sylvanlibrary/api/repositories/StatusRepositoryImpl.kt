package io.sylvanlibrary.api.repositories

import com.google.inject.Inject
import io.sylvanlibrary.api.daos.StatusDao
import org.skife.jdbi.v2.DBI

class StatusRepositoryImpl @Inject constructor(val conn: DBI): StatusRepository {
  override fun check(): Boolean {
    val statusDao = conn.open(StatusDao::class.java)
    val result = statusDao.check()

    statusDao.close()

    return result > 0
  }
}
