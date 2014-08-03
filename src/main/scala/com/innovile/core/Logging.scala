package com.innovile.core

import org.slf4j.LoggerFactory

trait Logging {
	
	lazy protected val logger = LoggerFactory.getLogger(getClass) 

}