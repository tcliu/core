package com.innovile.core.dao.test

import javax.annotation.Resource
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.ContextConfiguration
import com.innovile.core.dao.UserDao
import com.innovile.core.config.CoreConfiguration
import com.innovile.core.config.PersistenceConfiguration
import com.innovile.core.Logging

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes=Array(classOf[CoreConfiguration], classOf[PersistenceConfiguration]))
class UserDaoTest extends Logging {

	@Resource var userDao : UserDao = _
	
	@Test
	def testDao {
		logger.info( s"${userDao}" )
	}
	
	@Test
	def testGetUsers = {
		val users = userDao.getUsers
		logger.info( s"${users.size} users retrieved." )
	}
	
}