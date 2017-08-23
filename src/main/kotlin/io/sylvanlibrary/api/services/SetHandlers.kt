package io.sylvanlibrary.api.services

import io.sylvanlibrary.api.models.QueryResult
import io.sylvanlibrary.api.models.RequestPagination
import ratpack.handling.Handler
import ratpack.registry.Registry

class SetHandlers {
  companion object Factory {
    fun view(): Handler {
      return Handler { ctx ->
        val pagination = ctx!!.get(RequestPagination::class.java)
        val results = ctx.get(SetService::class.java).view(ctx.pathTokens["abbr"]!!, pagination.limit, pagination.from)

        ctx.next(Registry.single(QueryResult(results)))
      }
    }

    fun index(): Handler {
      return Handler { ctx ->
        val pagination = ctx!!.get(RequestPagination::class.java)
        val results = ctx.get(SetService::class.java).index(ctx.request.queryParams, pagination.limit, pagination.from)

        ctx.next(Registry.single(QueryResult(results)))
      }
    }
  }
}
