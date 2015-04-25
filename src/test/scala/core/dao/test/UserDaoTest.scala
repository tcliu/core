package core.dao.test

import javax.annotation.Resource
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertThat
import org.hamcrest.CoreMatchers._
import core.dao.UserDao
import core.model.User
import core.test.DaoTest

class UserDaoTest extends DaoTest {

	@Resource var userDao : UserDao = _

	@Test
	def getUsers {
		val users = userDao.getUsers
		logger.info( s"${users.size} users retrieved." )
	}

	@Test
	def createUser {
		val deletedUser = userDao.deleteUser(1)
		println(s"Deleted user = ${deletedUser}")
		val user = prepareUser
		userDao.persist(user)
		val dbUser = userDao.getUser(1)
		assertThat(dbUser, is(user))
	}

	def prepareUser : User = {
		val user = new User
		user._id = 1
		user._firstName = "John"
		user._lastName = "Hung"
		user._userName = "jhung"
		user
	}

}