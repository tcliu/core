package com.innovile.core.service

import java.io.File
import java.util.Locale
import java.util.ResourceBundle
import scala.Array.canBuildFrom
import scala.collection.JavaConversions.enumerationAsScalaIterator
import org.lesscss.LessCompiler
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import com.innovile.core.MessageSource
import com.innovile.core.ResourceBundleMessageSource
import javax.annotation.Resource
import com.innovile.core.BaseService

trait ResourceService {

	def getProperty(bundleName: String, key: String, locale: Locale = null) : String
	def getPropertyKeys(bundleName: String, locale: Locale = null) : Array[String]
	def getProperties(bundleName: String, locale: Locale = null) : Map[String,String]
	def compileLess(less: String) : String
	def compileLess(lessFile: File) : String
	def compileLess(lessFile: File, cssFile: File)
	def getCss(lessFile: File) : String
}
