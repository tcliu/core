package core.service

import java.io.File
import java.io.InputStream
import java.util.Collection

import scala.collection.JavaConversions.asScalaBuffer
import scala.collection.mutable.Buffer

trait IOService {

	def readLines(file: File) : Buffer[String]
	def write(file: File, str: String)
	def writeLines(file: File, c: Collection[_])
	def createParentDirs(file: File) : Boolean
	def copy(is: InputStream, output: Any)

}
