package io.sylvanlibrary.api.daos

import org.skife.jdbi.v2.sqlobject.SqlQuery
import io.sylvanlibrary.api.models.Set
import io.sylvanlibrary.api.models.SetResultSetMapper
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(SetResultSetMapper::class)
interface SetDao: Dao {

  @SqlQuery("select * from sets")
  fun all(): List<Set>

  @SqlQuery("select * from sets where UPPER(name) like UPPER(:name)")
  fun byName(@Bind("name") name: String): List<Set>

  @SqlQuery("select * from sets where UPPER(abbr) = UPPER(:abbr)")
  fun byAbbr(@Bind("abbr") abbr: String): Set
}
