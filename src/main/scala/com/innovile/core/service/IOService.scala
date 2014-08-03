package com.innovile.core.service

import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.Collection
import scala.collection.JavaConversions.asScalaBuffer
import sys.process._
import org.apache.commons.io.FileUtils
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import org.apache.commons.io.IOUtils
import java.io.Writer
import scala.io.Source
import scala.collection.mutable.Buffer
import com.innovile.core.BaseService

trait IOService {
	
	def readLines(file: File) : Buffer[String]
	def write(file: File, str: String)
	def writeLines(file: File, c: Collection[_])
	def createParentDirs(file: File) : Boolean
	def copy(is: InputStream, output: Any)

}
