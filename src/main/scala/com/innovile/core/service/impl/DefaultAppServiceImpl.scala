package com.innovile.core.service.impl

import com.innovile.core.BaseService
import com.innovile.core.service.DefaultAppService
import com.innovile.core.service.ResourceService
import javax.annotation.Resource
import java.util.Locale

class DefaultAppServiceImpl extends BaseService with DefaultAppService {
	
	@Resource var resourceService : ResourceService = _
	
	override def name = prop("name")

	override def prop(key: String) = prop(key, null)
	
	override def prop(key: String, locale: Locale) = resourceService.getProperty("properties/app", key, locale)

	override def msg(key: String) = msg(key, null)
	
	override def msg(key: String, locale: Locale) = resourceService.getProperty("properties/messages", key, locale)
	
	override def defaultLocale = new Locale(prop("default.locale"))
	
	override def defaultDateFormat = prop("default.dateFormat")
	
	override def defaultTimeFormat = prop("default.timeFormat")
	
	override def defaultArchiveDateFormat = prop("default.archiveDateFormat")
}