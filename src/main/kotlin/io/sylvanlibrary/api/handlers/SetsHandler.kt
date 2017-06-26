package io.sylvanlibrary.api.handlers

import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.daos.SetDao
import io.sylvanlibrary.api.models.SetResultSetMapper
import org.skife.jdbi.v2.DBI
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.jackson.Jackson
import io.sylvanlibrary.api.models.Set

class SetsHandler : Handler {
    override fun handle(ctx: Context?) {
      val config = DbConfig.getConfig()
      val conn = HikariDataSource(config)
      val setDao = DBI(conn).open(SetDao::class.java)

      val results = setDao.all()

      setDao.close()
      conn.close()

      ctx!!.render(Jackson.json(results))
    }
}