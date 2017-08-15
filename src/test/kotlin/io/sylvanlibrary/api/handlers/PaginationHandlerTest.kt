package io.sylvanlibrary.api.handlers

import io.sylvanlibrary.api.models.RequestPagination
import org.junit.Before
import org.junit.Test
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.runner.Request

import ratpack.test.handling.RequestFixture

class PaginationHandlerTest {
  lateinit var classUnderTest: PaginationHandler
  lateinit var fixture: RequestFixture

  companion object {
    val TEST_VALUE = 10
  }

  @Before
  fun before() {
    classUnderTest = PaginationHandler()
    fixture = RequestFixture.requestFixture()
  }

  @Test
  fun itParsesPaginationValuesFromQueryString() {
    val result = fixture
        .uri("http://example.com/example?limit=$TEST_VALUE&from=$TEST_VALUE")
        .handle(classUnderTest)

    val pagination = result.registry.get(RequestPagination::class.java)

    assertThat(pagination.limit, equalTo(TEST_VALUE))
    assertThat(pagination.from, equalTo(TEST_VALUE))
  }

  @Test
  fun itDefaultsValuesWhenTheyAreNotSpecified() {
    val result = fixture
        .uri("http://example.com/example")
        .handle(classUnderTest)

    val pagination = result.registry.get(RequestPagination::class.java)

    assertThat(pagination.limit, equalTo(RequestPagination.DEFAULT_LIMIT))
    assertThat(pagination.from, equalTo(RequestPagination.DEFAULT_FROM))
  }

  @Test
  fun itDoesNotAllowNegativeValues() {
    val result = fixture
        .uri("http://example.com/example?limit=-1&from=-1")
        .handle(classUnderTest)

    val pagination = result.registry.get(RequestPagination::class.java)

    assertThat(pagination.limit, equalTo(RequestPagination.DEFAULT_LIMIT))
    assertThat(pagination.from, equalTo(RequestPagination.DEFAULT_FROM))
  }
}
