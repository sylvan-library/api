package io.sylvanlibrary.api.models

import org.skife.jdbi.v2.StatementContext
import org.skife.jdbi.v2.tweak.ResultSetMapper
import java.sql.ResultSet
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

data class Set(
  val Id: Int,
  val Name: String,
  val Abbr: String,
  val ReleaseDate: LocalDate,
  val IconUrl: String?,
  val PromoArtUrl: String?
)

class SetResultSetMapper : ResultSetMapper<Set> {
  override fun map(index: Int, r: ResultSet?, ctx: StatementContext?): Set {
    val set = Set(
      r!!.getInt("Id"),
      r.getString("Name"),
      r.getString("Abbr"),
      LocalDate.parse(r.getString("ReleaseDate"), DateTimeFormatter.ISO_DATE),
      r.getString("IconUrl"),
      r.getString("PromoArtUrl"))

    return set
  }
}
