package com.nilenso.uncle.webserver.testsuites

import com.nilenso.uncle.webserver.components.DaggerTestComponent
import org.junit.platform.suite.api.*

@Suite
@SuiteDisplayName("Repository tests")
@SelectPackages("com.nilenso.uncle.webserver.repositories")
@IncludeClassNamePatterns(".*Test")
class RepositoryTestSuite {
    companion object {
        val tc = DaggerTestComponent.create()
    }
}
