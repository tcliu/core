package core.config

import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.Persistence
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource
import org.springframework.jdbc.datasource.DriverManagerDataSource
import java.util.Properties
import org.hibernate.jpa.HibernatePersistenceProvider
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Lazy
@Configuration
@EnableTransactionManagement
class PersistenceConfiguration {

	@Bean
	def entityManagerFactory : LocalContainerEntityManagerFactoryBean = {
		val em = new LocalContainerEntityManagerFactoryBean
		em.setPersistenceUnitName("PU-CORE")
		em.setDataSource(dataSource)
		em.setPersistenceProviderClass(classOf[HibernatePersistenceProvider])
		em.setPackagesToScan("core.model")
		val vendorAdapter = new HibernateJpaVendorAdapter
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties)
		em
	}

	@Bean
	def dataSource : DataSource = {
		val ds = new DriverManagerDataSource
		ds.setDriverClassName("org.h2.Driver")
		ds.setUrl("jdbc:h2:~/db/h2/coredb")
		ds.setUsername("sa")
		ds.setPassword("")
		ds
	}

	@Bean
	def transactionManager(emf: EntityManagerFactory) : PlatformTransactionManager = new JpaTransactionManager(emf)

	@Bean
	def additionalProperties : Properties = {
		val props = new Properties
		props.setProperty("hibernate.show_sql", "true")
		props.setProperty("hibernate.hbm2ddl.auto", "create-drop")
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect")
		props
	}
}