package io.sylvanlibrary.api

import io.sylvanlibrary.api.handlers.PaginationHandler
import io.sylvanlibrary.api.handlers.RenderHandler
import io.sylvanlibrary.api.repositories.StatusRepository
import io.sylvanlibrary.api.services.SetHandlers
import ratpack.guice.Guice
import ratpack.handling.Handlers
import ratpack.server.RatpackServer

class Application {
  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      RatpackServer.start { server ->
        server
          .registry(Guice.registry { it.module(ApplicationModule::class.java) })
          .handlers { chain ->
            chain
              .prefix("sets") { sets ->
                sets
                  .get(Handlers.chain(PaginationHandler(), SetHandlers.index(), RenderHandler()))
                  .get(":abbr", Handlers.chain(PaginationHandler(), SetHandlers.view(), RenderHandler()))
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
  }
}
