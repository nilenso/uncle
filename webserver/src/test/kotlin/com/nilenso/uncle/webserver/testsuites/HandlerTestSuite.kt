package com.nilenso.uncle.webserver.testsuites

import org.junit.platform.suite.api.BeforeSuite
import org.junit.platform.suite.api.IncludeClassNamePatterns
import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite

@Suite
@SelectPackages("com.nilenso.uncle.webserver.handlers")
@IncludeClassNamePatterns(".*Test")
class HandlerTestSuite {
}