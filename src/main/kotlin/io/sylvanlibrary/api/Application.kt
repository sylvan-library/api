package io.sylvanlibrary.api

import ratpack.guice.Guice
import io.sylvanlibrary.api.modules.StatusInfoHandler
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
      }
  }
}
