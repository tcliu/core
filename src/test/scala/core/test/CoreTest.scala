package core.test

import core.Logging
import core.Profiling
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.ActiveProfiles
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import core.config.{ServiceConfiguration, DaoConfiguration, PersistenceConfiguration}

@RunWith(classOf[SpringJUnit4ClassRunner])
abstract class CoreTest extends Logging with Profiling

@ContextConfiguration(classes=Array(classOf[ServiceConfiguration]))
abstract class ServiceTest extends CoreTest

@ContextConfiguration(classes=Array(classOf[DaoConfiguration], classOf[PersistenceConfiguration]))
abstract class DaoTest extends CoreTest