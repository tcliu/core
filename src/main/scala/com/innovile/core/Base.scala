package com.innovile.core

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

trait BaseApp extends App with Profiling

trait BaseComponent extends Profiling {

	@PostConstruct 
	def init = logger.debug(s"${this} initialized.")
	
	@PreDestroy 
	def destroy = logger.debug(s"${this} destroyed.")
	
}

trait BaseService extends BaseComponent