package core.test.model

import scala.beans.BeanProperty

class Person {
	@BeanProperty var id: String = null
	@BeanProperty var name: String = null
	override def toString = id + " -> " + name
}