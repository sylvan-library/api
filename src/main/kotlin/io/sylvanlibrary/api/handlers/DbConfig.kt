package io.sylvanlibrary.api.handlers

import com.zaxxer.hikari.HikariConfig

object DbConfig {
    fun getConfig() : HikariConfig {
        val config = HikariConfig()

        config.jdbcUrl = "jdbc:postgresql://localhost:5432/sylvanapi"
        config.username = "sylvanlibrary"
        config.password = "jacesucks"

        return config
    }
}