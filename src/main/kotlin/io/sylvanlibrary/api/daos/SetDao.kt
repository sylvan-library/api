package io.sylvanlibrary.api.daos

import org.skife.jdbi.v2.sqlobject.SqlQuery
import io.sylvanlibrary.api.models.Set
import io.sylvanlibrary.api.models.SetResultSetMapper
import org.skife.jdbi.v2.sqlobject.Bind
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(SetResultSetMapper::class)
interface SetDao: Dao {

  @SqlQuery("select * from sets limit :limit offset :from")
  fun all(@Bind("limit") limit: Int, @Bind("from") from: Int): List<Set>

  @SqlQuery("select * from sets where UPPER(name) like UPPER(:name) limit :limit offset :from")
  fun byName(@Bind("name") name: String, @Bind("limit") limit: Int, @Bind("from") from: Int): List<Set>

  @SqlQuery("select * from sets where UPPER(abbr) = UPPER(:abbr) limit :limit offset :from")
  fun byAbbr(@Bind("abbr") abbr: String, @Bind("limit") limit: Int, @Bind("from") from: Int): Set
}
