package io.sylvanlibrary.api.services

import com.google.inject.Inject
import io.sylvanlibrary.api.models.Set
import io.sylvanlibrary.api.repositories.SetRepository
import java.util.*

class SetServiceImpl @Inject constructor(val repo: SetRepository): SetService {
  override fun index(queryParams: Map<String, String>): List<Set> {
    val name = Optional.ofNullable(queryParams["name"])

    return if (name.isPresent) {
      repo.byName(name.get())
    } else {
      repo.all()
    }
  }

  override fun view(abbr: String): Optional<Set> {
    return repo.byAbbr(abbr)
  }
}
