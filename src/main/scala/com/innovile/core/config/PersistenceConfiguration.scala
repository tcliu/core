package com.innovile.core.config

import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import javax.persistence.EntityManagerFactory
import javax.persistence.Persistence

@Configuration
@EnableTransactionManagement
class PersistenceConfiguration {

	@Bean
	def entityManagerFactory : EntityManagerFactory = {
		Persistence.createEntityManagerFactory("PU-H2")
	}
}