package io.sylvanlibrary.api.repositories

import io.sylvanlibrary.api.daos.StatusDao
import io.sylvanlibrary.api.mocks.MockDbConnection
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations.initMocks


class StatusRepositoryImplTest {
  @Mock lateinit var statusDaoMock: StatusDao
  lateinit var dbConnectionMock: MockDbConnection

  lateinit var classUnderTest: StatusRepositoryImpl

  @Before
  fun setUp() {
    initMocks(this)

    dbConnectionMock = MockDbConnection(statusDaoMock)
    classUnderTest = StatusRepositoryImpl(dbConnectionMock)
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
}
