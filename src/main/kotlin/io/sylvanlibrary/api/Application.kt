package io.sylvanlibrary.api

import io.sylvanlibrary.api.repositories.StatusRepository
import io.sylvanlibrary.api.services.SetService
import ratpack.guice.Guice
import ratpack.handling.Context
import ratpack.jackson.Jackson
import ratpack.server.RatpackServer
import java.util.*

class Application {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      RatpackServer.start { server -> server
        .registry(Guice.registry { it.module(ApplicationModule::class.java) })

        .handlers { chain -> chain
          .prefix("sets") { sets -> sets
            .get { ctx -> render(ctx) { ctx.get(SetService::class.java).index(ctx.request.queryParams) } }
            .get(":abbr") { ctx -> renderOptional(ctx) { ctx.get(SetService::class.java).view(ctx.pathTokens["abbr"]!!) } }
          }

          .path("status/info") { ctx ->
            val goodConnection = ctx.get(StatusRepository::class.java).check()

            ctx.response.status(if (goodConnection) {
              200
            } else {
              500
            })
            ctx.response.send()
          }
        }
      }
    }

    private fun renderOptional(ctx: Context, renderCb: () -> Optional<*>) {
      val renderResult = renderCb()

      if (renderResult.isPresent) {
        ctx.render(Jackson.json(renderResult.get()))
      } else {
        ctx.response.status(404)
        ctx.response.send()
      }
    }

    private fun render(ctx: Context, renderCb: () -> Any) {
      ctx.render(Jackson.json(renderCb()))
    }
  }
}
