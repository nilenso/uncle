package com.nilenso.uncle.webserver.testsuites

import org.junit.platform.suite.api.SelectPackages
import org.junit.platform.suite.api.Suite
import org.junit.platform.suite.api.SuiteDisplayName

@Suite
@SuiteDisplayName("Uncle Test suite")
@SelectPackages("com.nilenso.uncle.webserver.services")
class UnitTestSuite {
}