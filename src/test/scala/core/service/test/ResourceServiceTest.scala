package core.service.test

import java.util.Locale
import org.junit.Test
import core.service.ResourceService
import javax.annotation.Resource
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import core.test.ServiceTest

class ResourceServiceTest extends ServiceTest {

	@Resource var resourceService : ResourceService = _

	@Test def testProperties {
		val props = Seq("name")
		props.map(s => logger.info(s"${s} = ${resourceService.getProperty("properties/app", s)}"))
	}

	@Test def testGetMessages {
		val locales = Seq("en", "zh", "zh_HK").map(s => new Locale(s))
		val props = Seq("submit", "selectFile", "path")
		props.map(s => locales.map(l => logger.info("{} ({}) = {}", s, l, resourceService.getProperty("properties/labels", s, l))) )
	}

}