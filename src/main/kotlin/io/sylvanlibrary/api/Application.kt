package io.sylvanlibrary.api

import ratpack.server.RatpackServer

fun main(args: Array<String>) {
  RatpackServer.start { server ->
    server
      .handlers { chain ->
        chain
          .get { ctx -> ctx.render("Hello World!") }
      }
  }
}
