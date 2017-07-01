package io.sylvanlibrary.api

import io.sylvanlibrary.api.renderers.Renderer
import io.sylvanlibrary.api.repositories.StatusRepository
import io.sylvanlibrary.api.services.SetService
import ratpack.guice.Guice
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
                  .get { ctx -> ctx.get(Renderer::class.java).render(ctx) { ctx.get(SetService::class.java).index(ctx.request.queryParams) } }
                  .get(":abbr") { ctx -> ctx.get(Renderer::class.java).render(ctx) { ctx.get(SetService::class.java).view(ctx.pathTokens["abbr"]!!) } }
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
