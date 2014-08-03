package com.innovile.core.service.impl

import org.springframework.stereotype.Service
import com.innovile.core.BaseService
import com.innovile.core.service.DataService
import org.apache.commons.lang3.StringUtils

@Service
class DataServiceImpl extends BaseService with DataService {
	
	override def isEmpty(str: CharSequence) = StringUtils.isEmpty(str)
	
	override def isBlank(str: CharSequence) = StringUtils.isBlank(str)
	
	override def join(delim: String, tokens: Any*) = tokens.mkString(delim)
	
}