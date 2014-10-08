package app.test

import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import com.innovile.core.config.CoreConfiguration
import org.junit.Test
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver
import com.innovile.core.Logging
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By
import org.openqa.selenium.htmlunit.HtmlUnitDriver
import org.openqa.selenium.ie.InternetExplorerDriver
import org.openqa.selenium.Keys

@RunWith(classOf[SpringJUnit4ClassRunner])
@ContextConfiguration(classes=Array(classOf[CoreConfiguration]))
class SeleniumTest extends Logging {

	
	@Test
	def runHtmlUnit {
		val driver : WebDriver = new HtmlUnitDriver
		google("HtmlUnit", driver)
	}

	@Test
	def runFirefox {
		val driver : WebDriver = new FirefoxDriver
		google("Firefox", driver)
	}
	
	@Test
	def runChrome {
		System.setProperty("webdriver.chrome.driver", "D:/dev/tools/chromedriver/chromedriver.exe")
		val driver : WebDriver = new ChromeDriver
		loginChief("Chrome", driver)
	}
	
	@Test
	def runInternetExplorer {
		System.setProperty("webdriver.ie.driver", "D:/dev/tools/iedriver/IEDriverServer.exe")
		val driver : WebDriver = new InternetExplorerDriver
		google("Internet Explorer", driver)
	}
	
	def google(client: String, driver: WebDriver) {
		driver.get("http://www.google.com")
		val text = driver.findElement(By.cssSelector("#gbgs4")).getText
		logger.info(s"${client} gets ${text}")
		driver.quit
	}

	def aastocks(client: String, driver: WebDriver) {
		driver.get("http://www.aastocks.com")
		val quoteElem = driver.findElement(By.id("txtHKQuote")) 
		quoteElem.sendKeys("2888")
		quoteElem.sendKeys(Keys.ENTER)
	}
	
	def loginSc(client: String, driver: WebDriver) {
		driver.get("https://ibank.standardchartered.com.hk/nfs/login.htm")
		driver.findElement(By.cssSelector("[name='j_username']")).sendKeys("xxxxxxx")
		driver.findElement(By.cssSelector("[name='j_password']")).sendKeys("xxxxxxx")
		driver.findElement(By.cssSelector("[name='j_password']")).sendKeys(Keys.ENTER)
	}
	
	def loginChief(client: String, driver: WebDriver) {
		driver.get("https://top3.chiefgroup.com.hk/web/logon.do")
		driver.findElement(By.cssSelector("[name='ClntCode']")).sendKeys("xxxxxxx")
		driver.findElement(By.cssSelector("[name='Password']")).sendKeys("xxxxxxx")
		driver.findElement(By.cssSelector("[name='Password']")).sendKeys(Keys.ENTER)
	}
	
	
}