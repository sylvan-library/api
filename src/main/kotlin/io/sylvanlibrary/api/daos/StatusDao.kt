package io.sylvanlibrary.api.daos

import org.skife.jdbi.v2.sqlobject.SqlQuery

interface StatusDao {
  @SqlQuery("select count(*) from schema_version")
  fun check(): Int

  fun close()
}
