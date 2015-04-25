package core.dao

trait BaseDao {

	def persist(entity: Object)

	def merge(entity: Object)
}