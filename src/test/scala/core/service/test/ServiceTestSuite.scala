package core.service.test

import org.junit.runner.RunWith
import org.junit.runners.Suite
import core.config.ServiceConfiguration

@RunWith(classOf[Suite])
@Suite.SuiteClasses(
	Array(classOf[DataServiceTest], classOf[FormatServiceTest],
		classOf[IOServiceTest], classOf[ResourceServiceTest])
)
class ServiceTestSuite {

}