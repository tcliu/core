package com.innovile.core.util.test

import org.springframework.test.context.ContextConfiguration
import org.junit.runner.RunWith
import com.innovile.core.config.CoreConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Test
import com.innovile.core.util.PropertiesReader
import scala.io.Source
import org.junit.Assert._

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes=Array(classOf[CoreConfiguration]))
class PropertiesTest {

	@Test
	def testProperties {
		val propReader = new PropertiesReader
		val is = getClass.getResourceAsStream("/xml/settings.xml")
		val propList = propReader.read(is)
		assertNotNull(propList != null)
	}
}