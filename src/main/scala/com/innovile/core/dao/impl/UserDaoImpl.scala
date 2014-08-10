package com.innovile.core.dao.impl

import org.springframework.stereotype.Repository
import com.innovile.core.bean.User
import com.innovile.core.dao.UserDao
import javax.annotation.Resource
import javax.persistence.EntityManager
import collection.JavaConversions._
import org.springframework.stereotype.Component
import javax.persistence.EntityManagerFactory

@Repository
class UserDaoImpl extends UserDao {

	@Resource var entityManagerFactory : EntityManagerFactory = _
	
	def entityManager = entityManagerFactory.createEntityManager
	
	override def getUsers : List[User] = {
		entityManager.createQuery("from User", classOf[User]).getResultList.toList
	}
	
}