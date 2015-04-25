package core.dao.impl

import scala.collection.JavaConversions.asScalaBuffer
import org.springframework.stereotype.Repository
import core.dao.UserDao
import core.model.User
import javax.annotation.Resource
import javax.persistence.Entity
import javax.persistence.EntityManagerFactory
import javax.persistence.NamedQueries
import javax.persistence.Table
import javax.transaction.Transactional

@Repository
class UserDaoImpl extends BaseDaoImpl with UserDao {

	lazy val userQuery = entityManager.createQuery("from User where id = :id", classOf[User])

	lazy val usersQuery = entityManager.createQuery("from User", classOf[User])

	override def getUsers : List[User] = usersQuery.getResultList.toList

	override def getUser(id: Long) : User = {
		val users = userQuery.setParameter("id", id.toInt).getResultList
		if (users.length == 1) users(0) else null
	}

	override def deleteUser(id: Long): User = {
		val user = getUser(id)
		if (user != null) {
			entityManager.remove(user)
		}
		user
	}

}