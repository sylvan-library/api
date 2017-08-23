package io.sylvanlibrary.api.services

import io.sylvanlibrary.api.models.Set
import java.util.*

interface SetService {
  fun index(queryParams: Map<String, String>, limit: Int, from: Int): List<Set>
  fun view(abbr: String, limit: Int, from: Int): Optional<Set>
}
