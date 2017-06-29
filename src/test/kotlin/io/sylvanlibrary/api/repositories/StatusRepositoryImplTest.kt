package io.sylvanlibrary.api.repositories

import io.sylvanlibrary.api.daos.StatusDao
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.*
import org.skife.jdbi.v2.DBI
import org.hamcrest.CoreMatchers.*
import org.junit.Assert.assertThat


class StatusRepositoryImplTest {
  @Mock lateinit var dbiMock: DBI
  @Mock lateinit var statusDaoMock: StatusDao

  lateinit var classUnderTest: StatusRepositoryImpl

  @Before
  fun setUp() {
    initMocks(this)

    `when`(dbiMock.open(StatusDao::class.java)).thenReturn(statusDaoMock)

    classUnderTest = StatusRepositoryImpl(dbiMock)
  }

  @Test
  fun testCheck_itReturnsTrueWhenTheDbHasMigrations() {
    `when`(statusDaoMock.check()).thenReturn(1)

    val result = classUnderTest.check()

    assertThat(result, equalTo(true))
  }

  @Test
  fun testCheck_itReturnsFalseWhenTheDbHasNoMigrations() {
    `when`(statusDaoMock.check()).thenReturn(0)

    val result = classUnderTest.check()

    assertThat(result, equalTo(false))
  }

  @Test
  fun testCheck_itClosesTheDbConnection() {
    classUnderTest.check()
    verify(statusDaoMock, times(1)).close()
  }
}