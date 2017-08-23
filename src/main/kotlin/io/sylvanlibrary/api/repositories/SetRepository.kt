package io.sylvanlibrary.api.repositories

import io.sylvanlibrary.api.models.Set
import java.util.*

interface SetRepository {
  fun all(limit: Int, from: Int): List<Set>
  fun byName(name: String, limit: Int, from: Int): List<Set>
  fun byAbbr(abbr: String, limit: Int, from: Int): Optional<Set>
}
