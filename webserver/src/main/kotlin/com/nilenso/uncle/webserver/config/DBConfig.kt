package com.nilenso.uncle.webserver.config

data class DBConfig(val driverClassName: String, val jdbcURL: String, val username: String, val password: String, val maxPoolSize: Int)