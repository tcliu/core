package core.service.impl

import org.springframework.stereotype.Service
import core.BaseService
import core.service.IOService
import scala.io.Source
import java.io.File
import org.apache.commons.io.FileUtils
import java.util.Collection
import java.io.InputStream
import java.io.OutputStream
import java.io.Writer
import org.apache.commons.io.IOUtils

@Service
class IOServiceImpl extends BaseService with IOService {

	override def readLines(file : File) = Source.fromFile(file).getLines.toBuffer

	override def write(file: File, str: String) = FileUtils.write(file, str, "UTF-8")

	override def writeLines(file : File, c: Collection[_]) = FileUtils.writeLines(file, c, "UTF-8")

	override def createParentDirs(file : File) = !file.exists && file.getParentFile.mkdirs

	override def copy(is: InputStream, output: Any) = output match {
		case os: OutputStream =>
			val buffer = new Array[Byte](1024)
			Iterator.continually(is.read(buffer)).takeWhile(_ > 0).foreach( length => os.write(buffer, 0, length) )
		case writer: Writer => IOUtils.copy(is, writer)
	}

}