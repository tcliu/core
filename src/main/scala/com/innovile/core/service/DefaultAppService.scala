package com.innovile.core.service

import java.util.Locale

trait DefaultAppService {
	
	def name : String
	def prop(key: String) : String
	def prop(key: String, Locale: Locale) : String
	def msg(key: String) : String
	def msg(key: String, Locale: Locale) : String
	def defaultLocale : Locale
	def defaultDateFormat : String
	def defaultTimeFormat : String
	def defaultArchiveDateFormat : String
	
}
