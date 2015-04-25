package core.service

import java.io.File
import java.util.Date

trait FormatService {

	def formatDate(date: Date, format: String) : String
	def formatSize(size: Long) : String
	def getFileExtension(name: String) : String
	def isTextFile(file: File) : Boolean
	def toJsonString(obj: Any) : String
	def readValue[T](input: Any, clazz: Class[T]): T
}
