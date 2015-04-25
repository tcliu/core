package core.model

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import javax.persistence.Id
import javax.persistence.NamedQueries

@NamedQueries(Array(

))
@Entity
@Table(name = "USER")
class User extends StandardEntity {

	@Id
	@Column(name = "ID")
	var _id : Int = _

	@Column(name = "FIRSTNAME", length = 100)
	var _firstName : String = _

	@Column(name = "MIDDLENAME", length = 100)
	var _middleName : String = _

	@Column(name = "LASTNAME", length = 100)
	var _lastName : String = _

	@Column(name = "USERNAME", length = 20)
	var _userName : String = _

	@Column(name = "PASSWORD", length = 20)
	var _password : String = _

}