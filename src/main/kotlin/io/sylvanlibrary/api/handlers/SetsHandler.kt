package io.sylvanlibrary.api.handlers

import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.daos.SetDao
import io.sylvanlibrary.api.models.SetResultSetMapper
import org.skife.jdbi.v2.DBI
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.jackson.Jackson
import io.sylvanlibrary.api.models.Set

class SetsHandler {

  fun getSets(ctx: Context) {
    val config = DbConfig.getConfig()
    val conn = HikariDataSource(config)
    val setDao = DBI(conn).open(SetDao::class.java)

    val results = if (ctx!!.request.queryParams.containsKey("name")) {
      setDao.getByName("%${ctx.request.queryParams["name"]!!}%")
    } else{
      setDao.all()
    }


    setDao.close()
    conn.close()

    ctx!!.render(Jackson.json(results))
  }

  fun getSetByAbbr(ctx: Context) {
    val config = DbConfig.getConfig()
    val conn = HikariDataSource(config)
    val setDao = DBI(conn).open(SetDao::class.java)

    val result = setDao.getByAbbr(ctx.pathTokens["abbr"]!!)

    setDao.close()
    conn.close()

    ctx.render(Jackson.json(result))
  }
}