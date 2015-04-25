package core.service

trait DataService {

	def isEmpty(str: CharSequence) : Boolean
	def isBlank(str: CharSequence) : Boolean
	def join(delim: String, tokens: Any*) : String
}
