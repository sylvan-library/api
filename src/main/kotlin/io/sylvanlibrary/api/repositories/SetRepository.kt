package io.sylvanlibrary.api.repositories

import io.sylvanlibrary.api.models.Set
import java.util.*

interface SetRepository {
  fun all(): List<Set>
  fun byName(name: String): List<Set>
  fun byAbbr(abbr: String): Optional<Set>
}
