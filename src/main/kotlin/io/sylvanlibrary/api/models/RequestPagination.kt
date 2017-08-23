package io.sylvanlibrary.api.models

import java.util.*

class RequestPagination(limit: Optional<Int>, from: Optional<Int>) {
  companion object {
    val DEFAULT_LIMIT = 200
    val DEFAULT_FROM = 0

    private fun constrainInputs(input: Optional<Int>, default: Int): Int {
      if (!input.isPresent) {
        return default
      }

      return input.map { if (it < 0) default else it }.get()
    }
  }

  val limit: Int
  val from: Int

  init {
    this.limit = constrainInputs(limit, DEFAULT_LIMIT)
    this.from = constrainInputs(from, DEFAULT_FROM)
  }

}
