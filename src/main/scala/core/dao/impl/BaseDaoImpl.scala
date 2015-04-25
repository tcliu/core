package core.dao.impl

import core.dao.BaseDao
import javax.persistence.EntityManagerFactory
import javax.annotation.Resource
import javax.transaction.Transactional

class BaseDaoImpl extends BaseDao {

	@Resource var entityManagerFactory : EntityManagerFactory = _

	lazy val entityManager = entityManagerFactory.createEntityManager

	@Transactional
	override def persist(entity: Object): Unit = entityManager.persist(entity)

	@Transactional
	override def merge(entity: Object): Unit = entityManager.merge(entity)

}