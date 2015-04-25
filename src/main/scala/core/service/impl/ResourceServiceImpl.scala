package core.service.impl

import org.springframework.stereotype.Service
import core.BaseService
import core.service.ResourceService
import core.MessageSource
import java.util.Locale
import java.io.File
import core.ResourceBundleMessageSource
import java.util.ResourceBundle
import collection.JavaConversions._

@Service
class ResourceServiceImpl extends BaseService with ResourceService {

	lazy val messageSourceCache = new collection.mutable.WeakHashMap[String,MessageSource]
	lazy val lessCache = new collection.mutable.WeakHashMap[File,String]
	lazy val lessCompiler = new org.lesscss.LessCompiler

	override def getProperty(bundleName: String, key: String, locale: Locale) = {
		getMessageSource(bundleName).getMessage(key, locale)
	}

	override def getPropertyKeys(bundleName: String, locale: Locale) : Array[String] = {
		getResourceBundle(bundleName, locale).getKeys.toArray
	}

	override def getProperties(bundleName: String, locale: Locale) = {
		val properties = new collection.mutable.HashMap[String,String]
		getPropertyKeys(bundleName, locale).map(key => properties.put(key, getProperty(bundleName, key, locale)))
		properties.toMap
	}

	override def compileLess(less: String) = lessCompiler.compile(less)

	override def compileLess(lessFile: File) = lessCompiler.compile(lessFile)

	override def compileLess(lessFile: File, cssFile: File) = lessCompiler.compile(lessFile, cssFile)

	override def getCss(lessFile: File) = {
		if (lessFile.isFile) {
			lessCache.getOrElseUpdate(lessFile, {
				val css = compileLess(lessFile)
				logger.info(s"Compiled and cached LESS for ${lessFile}")
				css
			})
		} else ""
	}

	protected def getResourceBundle(bundleName: String, locale: Locale) = ResourceBundle.getBundle(bundleName, locale)

	protected def getMessageSource(bundleName: String) = {
		messageSourceCache.getOrElseUpdate(bundleName, buildMessageSource(bundleName))
	}

	protected def buildMessageSource(bundleName: String) = {
		val messageSource = new ResourceBundleMessageSource
		messageSource.setBasename(bundleName)
		messageSource.setDefaultEncoding("UTF-8")
		messageSource.setCacheSeconds(30)
		logger.info(s"Message source created: ${messageSource}")
		messageSource
	}

}