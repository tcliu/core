package core.service.test

import collection.JavaConversions._

import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers.is
import org.junit.Test
import core.service.DataService
import javax.annotation.Resource
import org.junit.runner.RunWith
import core.test.ServiceTest

class DataServiceTest extends ServiceTest {

	@Resource var dataService : DataService = _

	@Test def testJoin = {
		assertThat("1,2,3", is(dataService.join(",", 1, 2, 3)))
	}

	@Test def toJson {
		val m = mapAsJavaMap(Map("a" -> "1")).asInstanceOf[java.util.Map[String,Object]]
		val jsonStr = dataService.toJson(m)
		logger.info(s"JSON string = ${jsonStr}")
		assertThat(jsonStr, is("{\"a\":\"1\"}"))
	}

	@Test def fromJson {
		val jsonStr = "{\"a\":\"1\"}"
		val json = dataService.fromJson(jsonStr, classOf[java.util.Map[String,Object]])
		val m = mapAsJavaMap(Map("a" -> "1")).asInstanceOf[java.util.Map[String,Object]]
		logger.info(s"JSON = ${json}")
		assertThat(json, is(m))
	}
}