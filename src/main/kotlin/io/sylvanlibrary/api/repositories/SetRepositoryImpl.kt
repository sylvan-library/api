package io.sylvanlibrary.api.repositories

import com.google.inject.Inject
import io.sylvanlibrary.api.daos.SetDao
import io.sylvanlibrary.api.models.Set
import java.util.*

class SetRepositoryImpl @Inject constructor(val conn: DbConnection): SetRepository {
  override fun all(): List<Set> {
    return conn.open(SetDao::class.java) { setDao ->
      setDao.all()
    }
  }

  override fun byName(name: String): List<Set> {
    return conn.open(SetDao::class.java) { setDao ->
      setDao.byName("%$name%")
    }
  }

  override fun byAbbr(abbr: String): Optional<Set> {
    return conn.open(SetDao::class.java) { setDao ->
      Optional.ofNullable(setDao.byAbbr(abbr))
    }
  }
}
