package com.innovile.core

import org.slf4j.profiler._

trait Profiling extends Logging {
	
	protected lazy val profiler : Profiler = new Profiler(getClass.getName)
	
	protected def profile(name: String, nested: Boolean = false)(f: => Unit) : TimeInstrument = {
		if (!nested)
			profiler.start(name)
		else
			profiler.startNested(name)
		logger.info(s"Profile [${name}] started.")
		f
		profiler.stop
	}
	
	protected def nestedProfile(name: String)(f: => Unit) : TimeInstrument = profile(name, true)(f)
	
}