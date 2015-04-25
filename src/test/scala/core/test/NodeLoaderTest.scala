package core.test

import org.junit.Test

import core.util.NodePool;
import scala.collection.JavaConversions._

class NodeLoaderTest {

	@Test
	def load {
		val is = getClass.getResourceAsStream("/xml/settings.xml")
		val nodePool = new NodePool(is)
		// nodePool.getNodes.foreach( println )
		println( nodePool.resolveNode("daughter") )
		println( nodePool.resolveNode("testing") )
	}
}