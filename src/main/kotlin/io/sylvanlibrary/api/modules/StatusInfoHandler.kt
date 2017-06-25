package io.sylvanlibrary.api.modules

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.skife.jdbi.v2.DBI
import org.skife.jdbi.v2.Handle
import org.skife.jdbi.v2.util.IntegerMapper
import ratpack.handling.Context
import ratpack.handling.Handler

class StatusInfoHandler : Handler {
  override fun handle(ctx: Context?) {
    val config = HikariConfig()

    config.jdbcUrl = "jdbc:postgresql://localhost:5432/sylvanapi"
    config.username = "sylvanlibrary"
    config.password = "jacesucks"

    val db = DBI(HikariDataSource(config)).open()

    val result = db.createQuery("select count(*) from schema_version").map(IntegerMapper.FIRST).first()

    db.close()

    if (result == 1) {
      ctx!!.response.status(200)
      ctx.render("Status OK!")
    } else {
      ctx!!.response.status(500)
      ctx.render("No DB connection!")
    }
  }
}