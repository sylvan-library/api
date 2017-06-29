package io.sylvanlibrary.api.repositories

import com.google.inject.Inject
import io.sylvanlibrary.api.daos.SetDao
import io.sylvanlibrary.api.models.Set
import org.skife.jdbi.v2.DBI
import java.util.*

class SetRepositoryImpl @Inject constructor(val conn: DBI): SetRepository {
  override fun all(): List<Set> {
    val setDao = conn.open(SetDao::class.java)
    val result = setDao.all()

    setDao.close()

    return result
  }

  override fun byName(name: String): List<Set> {
    val setDao = conn.open(SetDao::class.java)
    val results = setDao.byName(name)

    setDao.close()

    return results
  }

  override fun byAbbr(abbr: String): Optional<Set> {
    val setDao = conn.open(SetDao::class.java)
    val result = Optional.ofNullable(setDao.byAbbr(abbr))

    setDao.close()

    return result
  }
}
