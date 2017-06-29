package io.sylvanlibrary.api.services

import io.sylvanlibrary.api.fixtures.SetFixture
import io.sylvanlibrary.api.repositories.SetRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.*
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat
import java.util.*

class SetServiceImplTest {
  @Mock lateinit var setRepositoryMock: SetRepository

  lateinit var classUnderTest: SetServiceImpl

  companion object {
    const val TEST_ABBR = "TST"
    const val TEST_NAME = "Test Name"
    val ALL_SETS = listOf(SetFixture.SET, SetFixture.SET)
    val FILTERED_SETS = listOf(SetFixture.SET)
  }

  @Before
  fun setUp() {
    initMocks(this)

    `when`(setRepositoryMock.all()).thenReturn(ALL_SETS)
    `when`(setRepositoryMock.byName("%$TEST_NAME%")).thenReturn(FILTERED_SETS)

    classUnderTest = SetServiceImpl(setRepositoryMock)
  }

  @Test
  fun testIndex_whenThereIsANameInTheQueryParamsItReturnsAFilteredList() {
    val result = classUnderTest.index(mapOf("name" to TEST_NAME))

    assertThat(result, equalTo(FILTERED_SETS))
  }

  @Test
  fun testIndex_whenThereIsNotANameInTheQueryParamsItReturnsAllTheSets() {
    val result = classUnderTest.index(mapOf("another_key" to "another_value"))

    assertThat(result, equalTo(ALL_SETS))
  }

  @Test
  fun testIndex_whenThereIsNothingInTheQueryParamsItReturnsAllTheSets() {
    val result = classUnderTest.index(mapOf())

    assertThat(result, equalTo(ALL_SETS))
  }

  @Test
  fun testView_whenTheAbbrExistsItReturnsASet() {
    `when`(setRepositoryMock.byAbbr(TEST_ABBR)).thenReturn(Optional.of(SetFixture.SET))
  }

  @Test
  fun testView_whenTheAbbrDoesNotExistItReturnsAnEmptyOptional() {
    `when`(setRepositoryMock.byAbbr(TEST_ABBR)).thenReturn(Optional.empty())
  }
}