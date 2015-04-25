package core.service.test

import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.is
import org.junit.Test
import core.service.DataService
import javax.annotation.Resource
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import core.test.ServiceTest

class DataServiceTest extends ServiceTest {

	@Resource var dataService : DataService = _

	@Test def testInject = logger.info("DataService = {}", dataService)

	@Test def testJoin = {
		assertThat("1,2,3", is(dataService.join(",", 1, 2, 3)))
	}

}