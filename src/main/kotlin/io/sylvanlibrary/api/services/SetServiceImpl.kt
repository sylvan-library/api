package io.sylvanlibrary.api.services

import com.google.inject.Inject
import io.sylvanlibrary.api.models.Set
import io.sylvanlibrary.api.repositories.SetRepository
import java.util.*

class SetServiceImpl @Inject constructor(val repo: SetRepository): SetService {
  override fun index(queryParams: Map<String, String>, limit: Int, from: Int): List<Set> {
    val name = Optional.ofNullable(queryParams["name"])

    return if (name.isPresent) {
      repo.byName(name.get(), limit, from)
    } else {
      repo.all(limit, from)
    }
  }

  override fun view(abbr: String, limit: Int, from: Int): Optional<Set> {
    return repo.byAbbr(abbr, limit, from)
  }
}
