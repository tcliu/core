package core.dao

import core.model.User

trait UserDao extends BaseDao {

	def getUsers : List[User]

	def getUser(id: Long) : User

	def deleteUser(id: Long) : User
}