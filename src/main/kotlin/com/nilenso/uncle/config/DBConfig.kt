package com.nilenso.uncle.config

data class DBConfig(val driverClassName: String, val jdbcURL: String, val username: String, val password: String, val maxPoolSize: Int)