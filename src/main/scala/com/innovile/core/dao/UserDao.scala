package com.innovile.core.dao

import com.innovile.core.bean.User

trait UserDao {

	def getUsers : List[User]
	
}