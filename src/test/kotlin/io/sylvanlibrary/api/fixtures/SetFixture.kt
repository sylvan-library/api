package io.sylvanlibrary.api.fixtures

import io.sylvanlibrary.api.models.Set
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SetFixture {
  companion object {
    const val ID = 1
    const val NAME = "Test Set"
    const val ABBR = "TST"
    val RELEASE_DATE: LocalDate = LocalDate.parse("2017-01-01", DateTimeFormatter.ISO_DATE)
    const val ICON_URL = ""
    const val PROMO_IMAGE_URL = ""

    val SET = Set(
      ID,
      NAME,
      ABBR,
      RELEASE_DATE,
      ICON_URL,
      PROMO_IMAGE_URL
    )
  }
}
