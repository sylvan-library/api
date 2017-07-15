package io.sylvanlibrary.api.utils

fun String.isHeaderJson(): Boolean {
  return this.toUpperCase().split(',').any { it == "APPLICATION/JSON" }
}

fun String.isHeaderHtml(): Boolean {
  return this.toUpperCase().split(',').any { it == "TEXT/HTML" || it == "APPLICATION/XHTML+XML"}
}