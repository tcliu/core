package core.service

import java.io.File
import java.util.Locale

import scala.Array.canBuildFrom
import scala.collection.JavaConversions.enumerationAsScalaIterator

trait ResourceService {

	def getProperty(bundleName: String, key: String, locale: Locale = null) : String
	def getPropertyKeys(bundleName: String, locale: Locale = null) : Array[String]
	def getProperties(bundleName: String, locale: Locale = null) : Map[String,String]
	def compileLess(less: String) : String
	def compileLess(lessFile: File) : String
	def compileLess(lessFile: File, cssFile: File)
	def getCss(lessFile: File) : String
}
