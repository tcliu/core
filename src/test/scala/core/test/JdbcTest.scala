package core.test

import org.junit.Test
import java.sql.DriverManager
import java.sql.Connection
import core.Logging

class JdbcTest extends Logging {

	val jdbcUrl = "jdbc:h2:~/db/h2/coredb"

	@Test
	def simpleSelect {
		val conn = getConnection
		val stmt = conn.createStatement
		val rs = stmt.executeQuery("select 1 c from dual")
		while (rs.next) {
			println(s"c = ${rs.getInt("c")}")
		}
		rs.close
		stmt.close
		conn.close
	}

	@Test
	def preparedStatement {
		val conn = getConnection
		val stmt = conn.prepareStatement("select ? c from dual")
		stmt.setInt(1, 2)
		val rs = stmt.executeQuery
		while (rs.next) {
			println(s"c = ${rs.getInt("c")}")
		}
		rs.close
		stmt.close
		conn.close
	}

	def getConnection : Connection = {
		DriverManager.getConnection(jdbcUrl, "sa", "")
	}
}