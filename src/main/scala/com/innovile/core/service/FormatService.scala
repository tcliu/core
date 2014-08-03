package com.innovile.core.service

import java.io.File
import java.util.Date
import org.apache.commons.io.FileUtils
import org.apache.commons.io.FilenameUtils
import org.apache.commons.lang3.time.DateFormatUtils
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import com.fasterxml.jackson.databind.ObjectMapper
import com.innovile.core.BaseService

trait FormatService {

	def formatDate(date: Date, format: String) : String
	def formatSize(size: Long) : String
	def getFileExtension(name: String) : String
	def isTextFile(file: File) : Boolean
	def toJsonString(obj: Any) : String
	def readValue[T](input: Any, clazz: Class[T]): T
}
