package io.sylvanlibrary.api.renderers

import ratpack.handling.Context

interface Renderer {
  fun render(ctx: Context, resultsFactory: () -> Any)}
