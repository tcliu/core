package core.util.test

import org.springframework.test.context.ContextConfiguration
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Test
import core.util.PropertiesReader
import scala.io.Source
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers._

class PropertiesTest {

	@Test
	def testProperties {
		val propReader = new PropertiesReader
		val is = getClass.getResourceAsStream("/xml/settings.xml")
		val propList = propReader.read(is)
		assertThat(propList, notNullValue())
	}
}