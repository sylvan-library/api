package io.sylvanlibrary.api.services

import io.sylvanlibrary.api.models.QueryResult
import ratpack.handling.Handler
import ratpack.registry.Registry

class SetHandlers {
  companion object Factory {
    fun view(): Handler {
      return Handler { ctx ->
        val results = ctx!!.get(SetService::class.java).view(ctx.pathTokens["abbr"]!!)

        ctx.next(Registry.single(QueryResult(results)))
      }
    }

    fun index(): Handler {
      return Handler { ctx ->
        val results = ctx!!.get(SetService::class.java).index(ctx.request.queryParams)

        ctx.next(Registry.single(QueryResult(results)))
      }
    }
  }
}