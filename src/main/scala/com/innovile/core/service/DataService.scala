package com.innovile.core.service

import org.apache.commons.lang3.StringUtils
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service
import com.innovile.core.BaseService

trait DataService {

	def isEmpty(str: CharSequence) : Boolean
	def isBlank(str: CharSequence) : Boolean
	def join(delim: String, tokens: Any*) : String
}
