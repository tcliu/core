package com.innovile.core.bean

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.NamedQueries

@NamedQueries(Array(
	
))
@Entity
@Table(name = "USERS")
class User {

	@Id
	@Column(name = "ID")
	var _id : Int = _
	
	@Column(name = "USERNAME")
	var _userName : String = _
	
	
	
	
}