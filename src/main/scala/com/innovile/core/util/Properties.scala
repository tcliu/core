package com.innovile.core.util

import scala.beans.BeanProperty
import scala.collection.mutable.Map
import scala.collection.mutable.LinkedHashMap

class Properties {

	@BeanProperty
	var nodeName : String = _
	
	@BeanProperty
	var id : String = _
	
	val _properties : Map[String, String] = new LinkedHashMap[String,String]
	
	def setProperty(key: String, v: String) = _properties.put(key, v)
	
	def getProperty(key: String) = _properties.get(key)
	
	def getProperties = _properties
	
}