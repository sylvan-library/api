package io.sylvanlibrary.api.repositories

import com.google.inject.Inject
import io.sylvanlibrary.api.daos.SetDao
import io.sylvanlibrary.api.models.Set
import java.util.*

class SetRepositoryImpl @Inject constructor(val conn: DbConnection): SetRepository {
  override fun all(limit: Int, from: Int): List<Set> {
    return conn.open(SetDao::class.java) { setDao ->
      setDao.all(limit, from)
    }
  }

  override fun byName(name: String, limit: Int, from: Int): List<Set> {
    return conn.open(SetDao::class.java) { setDao ->
      setDao.byName("%$name%", limit, from)
    }
  }

  override fun byAbbr(abbr: String, limit: Int, from: Int): Optional<Set> {
    return conn.open(SetDao::class.java) { setDao ->
      Optional.ofNullable(setDao.byAbbr(abbr, limit, from))
    }
  }
}
