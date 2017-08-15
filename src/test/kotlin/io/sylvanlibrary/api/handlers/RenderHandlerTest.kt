package io.sylvanlibrary.api.handlers

import io.sylvanlibrary.api.fixtures.SetFixture
import io.sylvanlibrary.api.models.QueryResult
import org.junit.Test
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.junit.Assert.assertThat
import org.junit.Before
import ratpack.func.Action
import ratpack.jackson.internal.DefaultJsonRender
import ratpack.test.handling.RequestFixture
import java.util.*

class RenderHandlerTest {

  lateinit var classUnderTest: RenderHandler

  @Before
  fun setUp() {
    classUnderTest = RenderHandler()
  }

  @Test
  fun testHandler_noQueryResultThrows404() {
    val result = RequestFixture.handle(classUnderTest, Action<RequestFixture>({ _: RequestFixture? -> }))

    assertThat(result.status.code, equalTo(404))
  }

  @Test
  fun testHandler_unknownAcceptHeaderReturns406() {
    val result = RequestFixture.handle(
      classUnderTest,
      Action<RequestFixture>({ f: RequestFixture? -> configure(f, "application/pdf", SetFixture.SET) }))

    assertThat(result.status.code, equalTo(406))
  }

  @Test
  fun testHandler_validDataRendersJson() {
    val result = RequestFixture.handle(
      classUnderTest,
      Action<RequestFixture>({ f: RequestFixture? -> configure(f, "application/json", SetFixture.SET )}))

    assertThat(result.status.code, equalTo(200))

    val rendered = result.rendered(DefaultJsonRender::class.java)

    assertThat(rendered, notNullValue())
  }

  @Test
  fun testHandler_htmlAcceptTypeRendersJson() {
    val result = RequestFixture.handle(
      classUnderTest,
      Action<RequestFixture>({ f: RequestFixture? -> configure(f, "text/html", SetFixture.SET)}))

    assertThat(result.status.code, equalTo(200))

    val rendered = result.rendered(DefaultJsonRender::class.java)

    assertThat(rendered, notNullValue())
  }

  @Test
  fun testHandler_handlesOptionalContent() {
    val result = RequestFixture.handle(
      classUnderTest,
      Action<RequestFixture>({ f: RequestFixture? -> configure(f, "application/json", Optional.ofNullable(SetFixture.SET)) }))

    assertThat(result.status.code, equalTo(200))

    val rendered = result.rendered(DefaultJsonRender::class.java)

    assertThat(rendered, notNullValue())
    assertThat(rendered.`object`, equalTo(SetFixture.SET as Any))
  }

  @Test
  fun testHandler_handlesEmptyOptionalContent() {
    val result = RequestFixture.handle(
      classUnderTest,
      Action<RequestFixture>({ f: RequestFixture? -> configure(f, "application/json", Optional.ofNullable<io.sylvanlibrary.api.models.Set>(null))})
    )

    assertThat(result.status.code, equalTo(200))

    val rendered = result.rendered(DefaultJsonRender::class.java)

    assertThat(rendered, notNullValue())
    assertThat(rendered.`object`, nullValue())
  }

  fun configure(f: RequestFixture?, accept: String, queryResult: Any) {
    f!!.header("accept", accept).registry.add(QueryResult(queryResult))
  }
}
