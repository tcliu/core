package core.service.test

import java.io.File
import org.junit.Test
import core.service.IOService
import javax.annotation.Resource
import core.test.ServiceTest

class IOServiceTest extends ServiceTest {

	@Resource var ioService : IOService = _

	@Test def readLines {
		val lines = ioService.readLines(new File("build.gradle"))
		logger.info(s"Line count = ${lines.size}")
	}
}