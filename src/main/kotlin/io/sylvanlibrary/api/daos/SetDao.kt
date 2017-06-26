package io.sylvanlibrary.api.daos

import org.skife.jdbi.v2.sqlobject.SqlQuery
import io.sylvanlibrary.api.models.Set
import io.sylvanlibrary.api.models.SetResultSetMapper
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper

@RegisterMapper(SetResultSetMapper::class)
interface SetDao {
  @SqlQuery("select * from sets")
  fun all(): List<Set>

  fun close()
}