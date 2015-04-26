package core.service.test

import org.junit.Test

import core.service.FormatService
import core.test.ServiceTest
import core.test.model.Person
import javax.annotation.Resource

class FormatServiceTest extends ServiceTest {

	@Resource var formatService: FormatService = _

	@Test def testReadValueAsJson {
		val jsonStr = "{\"id\": \"1\", \"name\": \"John\"}"
		val person = formatService.readValue(jsonStr, classOf[Person])
		logger.info(s"Person = ${person}")
	}

	@Test def testReadValueAsJavaMap {
		val jsonStr = "{\"id\": \"1\", \"name\": \"John\"}"
		val personMap = formatService.readValue(jsonStr, classOf[java.util.HashMap[_,_]])
		logger.info(s"Person map = ${personMap}")
	}

	@Test def testToJsonString {
		val person = new Person
		person.setId("1")
		person.setName("name")
		val jsonStr = formatService.toJsonString(person)
		logger.info(s"Person JSON = ${jsonStr}")
	}
}