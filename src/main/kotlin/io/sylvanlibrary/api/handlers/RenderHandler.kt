package io.sylvanlibrary.api.handlers

import io.sylvanlibrary.api.models.QueryResult
import io.sylvanlibrary.api.utils.isHeaderHtml
import io.sylvanlibrary.api.utils.isHeaderJson
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.jackson.Jackson
import java.util.*

class RenderHandler: Handler {
  override fun handle(ctx: Context) {
    var queryResult = ctx.maybeGet(QueryResult::class.java)

    var result = if (queryResult.isPresent) {
      queryResult.get().result
    } else {
      ctx.response.status(404)
      null
    }

    if (result is Optional<*>) {
      result = if (result.isPresent) result.get() else null
    }

    val acceptHeader = ctx.request.headers["accept"]

    // For ease of browser testing, allow requests to return json. This will probably change later on
    // Null is treated as though the user wants our default behaviour, which is json
    if (acceptHeader == null || acceptHeader.isHeaderJson() || acceptHeader.isHeaderHtml())
      ctx.render(Jackson.json(result))
    else
      ctx.response.status(406)

    ctx.response.send()
  }
}
