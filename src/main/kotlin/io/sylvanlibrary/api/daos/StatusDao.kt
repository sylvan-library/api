package io.sylvanlibrary.api.daos

import org.skife.jdbi.v2.sqlobject.SqlQuery

interface StatusDao: Dao {
  @SqlQuery("select count(*) from schema_version")
  fun check(): Int
}
