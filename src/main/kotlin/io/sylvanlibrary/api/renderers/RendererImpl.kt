package io.sylvanlibrary.api.renderers

import ratpack.handling.Context
import ratpack.jackson.Jackson
import java.util.Optional
import io.sylvanlibrary.api.utils.*

class RendererImpl : Renderer {
  override fun render(ctx: Context, resultsFactory: () -> Any) {
    var results = resultsFactory()

    if (results is Optional<*>) {
      if (results.isPresent)
        results = results.get()
      else {
        ctx.response.status(404)
        ctx.response.send()
        return
      }
    }

    val acceptHeader = ctx.request.headers["accept"]

    // For ease of browser testing, allow requests to return json.
    // This will probably change later on
    if (acceptHeader.isHeaderJson() || acceptHeader.isHeaderHtml())
      ctx.render(Jackson.json(results))
    else {
      ctx.response.status(406)
      ctx.response.send()
    }
  }
}