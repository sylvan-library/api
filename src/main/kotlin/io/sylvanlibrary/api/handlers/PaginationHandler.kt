package io.sylvanlibrary.api.handlers

import io.sylvanlibrary.api.models.RequestPagination
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.registry.Registry
import java.util.*

class PaginationHandler: Handler {
  override fun handle(ctx: Context) {
      val limit = Optional.ofNullable(ctx.request.queryParams["limit"]).map { it.toInt() }
      val from = Optional.ofNullable(ctx.request.queryParams["from"]).map { it.toInt() }

      ctx.next(Registry.single(RequestPagination(limit, from)))
  }
}
