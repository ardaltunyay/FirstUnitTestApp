package net.altunyay.myunittestapp.data

import android.util.Log
import io.kotest.core.spec.BeforeTest
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.altunyay.myunittestapp.data.model.LoggedInUser
import org.junit.Before
import org.junit.Test
import java.io.IOException

@ExperimentalCoroutinesApi
internal class LoginRepositoryTest {

    private lateinit var sut: LoginRepository

    private lateinit var dataSource: LoginDataSource

    @Before
    fun setUp() {
        dataSource = mockk(relaxed = true)
        sut = spyk(LoginRepository(dataSource))
    }

    @Test
    fun `setLoggedInUser method should call when data source result is success`() =
        runBlockingTest {

            val email = "email"
            val password = "password"
            val result = Result.Success(LoggedInUser(email, password))

            coEvery { dataSource.login(email, password) } returns result

            sut.login(email, password)

            coVerify {
                sut.setLoggedInUser(LoggedInUser(email, password))
            }
        }

    @Test
    fun `login should return success when loginDataSource return success`() = runBlockingTest {
        val email = "email"
        val password = "password"

        coEvery {
            dataSource.login(email, password)
        } returns Result.Success(LoggedInUser(email, password))

        val actual = sut.login("email", "password") is Result.Success

        actual shouldBe true

    }

    @Test
    fun `login should return error when loginDataSource return error`() = runBlockingTest {
        val email = "email"
        val password = "password"

        coEvery {
            dataSource.login(email, password)
        } returns Result.Error(IOException())

        val actual = sut.login("email", "password") is Result.Error

        actual shouldBe true

    }

    @Test
    fun `user should save in memory when setLoggedInUser call`() = runBlockingTest {
        val user = LoggedInUser("random", "Test test")

        sut.setLoggedInUser(user)
        val actual = sut.user

        actual shouldBe user

    }

    /*
    @Test
    fun `user should logout in memory when logout method should call`() = runBlockingTest {
        //FIXME Loginken logout olup olmama durumunu test edemedik (değişken private)
    }
     */

    @Test
    fun `data source logout method should call when logout method should call`() = runBlockingTest {

        sut.logout()

        coVerify {
            dataSource.logout()
        }

    }


}