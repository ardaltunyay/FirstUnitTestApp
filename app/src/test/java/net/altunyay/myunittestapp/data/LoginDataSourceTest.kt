package net.altunyay.myunittestapp.data

import io.kotest.matchers.shouldBe
import io.mockk.coVerifyAll
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
internal class LoginDataSourceTest {

    private lateinit var sut: LoginDataSource

    @Before
    fun setUp() {
        sut = spyk(LoginDataSource())
    }

    @Test
    fun `result success should return from api when email and password aren't null`() =
        runBlockingTest {
            val email = "email_test"
            val password = "pasword_test"

            val actual = sut.login(email, password) is Result.Success

            actual shouldBe true

        }

    @Test
    fun `result error should return from api when email is null`() = runBlockingTest {

        val actualEmail = sut.login(email = null, password = "test") is Result.Error

        actualEmail shouldBe true
    }

    @Test
    fun `result error should return from api when password is null`() = runBlockingTest {

        val actual = sut.login(email = "test", password = null) is Result.Error

        actual shouldBe true
    }

    @Test
    fun `result error should return from api when email and password are null`() = runBlockingTest {

        val actual = sut.login(email = null, password = null) is Result.Error

        actual shouldBe true
    }

}