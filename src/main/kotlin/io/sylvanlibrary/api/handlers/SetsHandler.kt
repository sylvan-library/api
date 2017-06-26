package io.sylvanlibrary.api.handlers

import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.models.SetResultSetMapper
import org.skife.jdbi.v2.DBI
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.jackson.Jackson

class SetsHandler : Handler {
    override fun handle(ctx: Context?) {
        var config = DbConfig.getConfig()

        val db = DBI(HikariDataSource(config)).open()

        val result = db.createQuery("select * from sets").map(SetResultSetMapper::class.java)

        db.close()

        ctx!!.render(Jackson.json(result))
    }
}