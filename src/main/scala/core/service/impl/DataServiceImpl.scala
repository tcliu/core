package core.service.impl

import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Service
import core.BaseService
import core.service.DataService
import com.google.gson.GsonBuilder

@Service
class DataServiceImpl extends BaseService with DataService {

	lazy val gson = new GsonBuilder().create

	override def isEmpty(str: CharSequence) = StringUtils.isEmpty(str)

	override def isBlank(str: CharSequence) = StringUtils.isBlank(str)

	override def join(delim: String, tokens: Any*) = tokens.mkString(delim)

	override def fromJson[T](jsonStr: String, clazz: Class[T]): T = gson.fromJson(jsonStr, clazz)

	override def toJson(obj: Object): String = gson.toJson(obj)

}