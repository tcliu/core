package core.util

import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import java.io.InputStream
import scala.collection.mutable.ArrayBuffer
import core.Logging

class PropertiesReader extends Logging {

	val dbf = DocumentBuilderFactory.newInstance
	val db = dbf.newDocumentBuilder

	def read(is: InputStream) : Seq[Properties] = {
		val propList = new ArrayBuffer[Properties]
		val doc = db.parse(is)
		val docElement = doc.getDocumentElement

		logger.info(s"docElement.getNodeName = ${docElement.getNodeName}")

		val prop = new Properties

		val nodeList = docElement.getChildNodes
		val l = Seq(0,nodeList.getLength - 1).map(node => {
			node
		})


		is.close
		propList
	}

}