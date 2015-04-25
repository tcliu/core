package core.service.impl

import org.springframework.stereotype.Service
import core.BaseService
import core.service.FormatService
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang3.time.DateFormatUtils
import java.util.Date
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import java.io.File

@Service
class FormatServiceImpl extends BaseService with FormatService {

	lazy val objectMapper = new ObjectMapper

	lazy val textFileTypes = List("classpath", "clj", "bash", "bat", "css", "dtd", "gitignore", "gradle", "htm", "html", "java",
			"js", "json", "jsp", "log", "ora", "pl", "prefs", "project", "properties", "py", "rb", "sbt", "scala", "sh", "sql",
			"txt", "xhtml", "xml")

	override def formatDate(date: Date, format: String) = DateFormatUtils.format(date, format)

	override def formatSize(size: Long) = FileUtils.byteCountToDisplaySize(size)

	override def getFileExtension(name: String) = FilenameUtils.getExtension(name)

	override def isTextFile(file: File) = {
		val fileExt = getFileExtension(file.getName)
		textFileTypes.contains(fileExt)
	}

	override def toJsonString(obj: Any) = objectMapper.writeValueAsString(obj)

	override def readValue[T](input: Any, clazz: Class[T]) = input match {
		case str: String => objectMapper.readValue(str, clazz)
		case bytes: Array[Byte] => objectMapper.readValue(bytes, clazz)
	}

}