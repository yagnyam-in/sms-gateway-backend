package `in`.yagnyam.sms_gateway

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig
import com.google.appengine.tools.development.testing.LocalServiceTestHelper
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.io.IOException

@RunWith(SpringRunner::class)
@SpringBootTest
class ProxyCentralApplicationTest {

    private val helper = LocalServiceTestHelper(
            LocalDatastoreServiceTestConfig()
    )

    @Before
    fun setUp() {
        this.helper.setUp()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        this.helper.tearDown()
    }

    @Test
    fun contextLoads() {
    }

}
