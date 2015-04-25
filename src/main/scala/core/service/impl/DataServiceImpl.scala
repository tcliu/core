package core.service.impl

import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service

import core.BaseService
import core.service.DataService

@Service
class DataServiceImpl extends BaseService with DataService {

	override def isEmpty(str: CharSequence) = StringUtils.isEmpty(str)

	override def isBlank(str: CharSequence) = StringUtils.isBlank(str)

	override def join(delim: String, tokens: Any*) = tokens.mkString(delim)

}