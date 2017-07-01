package io.sylvanlibrary.api.repositories

import com.zaxxer.hikari.HikariDataSource
import io.sylvanlibrary.api.daos.StatusDao
import io.sylvanlibrary.api.factories.DaoFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.*
import org.mockito.Mockito.*
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat

class DbConnectionImplTest {
  @Mock lateinit var daoFactoryMock: DaoFactory
  @Mock lateinit var hikariConnMock: HikariDataSource
  @Mock lateinit var testDao: StatusDao

  lateinit var classUnderTest: DbConnection

  @Before
  fun setUp() {
    initMocks(this)

    `when`(daoFactoryMock.get(StatusDao::class.java, hikariConnMock)).thenReturn(testDao)

    classUnderTest = DbConnectionImpl(daoFactoryMock, hikariConnMock)
  }

  @Test
  fun testOpen_itGetsADao() {
    classUnderTest.open(StatusDao::class.java) {}
    verify(daoFactoryMock, times(1)).get(StatusDao::class.java, hikariConnMock)
  }

  @Test
  fun testOpen_itCallsTheDbOperation() {
    var testOperationCalled = 0
    val testOperation: (StatusDao) -> Unit = { testOperationCalled += 1 }

    classUnderTest.open(StatusDao::class.java, testOperation)

    assertThat(testOperationCalled, equalTo(1))
  }

  @Test
  fun testOpen_itClosesTheConnections() {
    classUnderTest.open(StatusDao::class.java) {}
    verify(testDao, times(1)).close()
    verify(hikariConnMock, times(1)).close()
  }
}
