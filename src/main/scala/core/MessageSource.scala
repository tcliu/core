package core

import javax.annotation.Resource
import java.util.Locale
import org.springframework.context.MessageSourceResolvable
import java.util.Properties

trait MessageSource extends org.springframework.context.MessageSource {

	def getMessage(code: String, locale: Locale) : String
	def getMessage(code: String, args: Array[Object] = Array()) : String
	
}

class ResourceBundleMessageSource extends org.springframework.context.support.ReloadableResourceBundleMessageSource with MessageSource {
	
	override def getMessage(code: String, locale: Locale) = super.getMessage(code, Array(), locale)
	override def getMessage(code: String, args: Array[Object]) : String = super.getMessage(code, args, null)

}