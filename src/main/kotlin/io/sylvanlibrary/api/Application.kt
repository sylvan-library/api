package io.sylvanlibrary.api

import ratpack.guice.Guice
import io.sylvanlibrary.api.handlers.StatusInfoHandler
import io.sylvanlibrary.api.handlers.SetsHandler
import ratpack.server.RatpackServer

fun main(args: Array<String>) {
  RatpackServer.start { server ->
    server
      .registry(Guice.registry {
        it.bind(StatusInfoHandler::class.java)
      })
      .handlers { chain ->
        chain
          .path("status/info", StatusInfoHandler::class.java)
          .path("sets", SetsHandler::class.java)
      }
  }
}
