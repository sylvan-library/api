package io.sylvanlibrary.api

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class PlaceholderTest {
  lateinit var classUnderTest: String

  @Before
  fun setUp() {
    classUnderTest = "A String!"
  }

  @Test
  fun itIsAString() {
    assertThat("A String!", equalTo(classUnderTest))
  }
}
