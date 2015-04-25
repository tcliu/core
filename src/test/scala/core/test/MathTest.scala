package core.test

import org.apache.commons.math3.distribution.NormalDistribution
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.ContextConfiguration
import core.Logging

class MathTest extends Logging {

	@Test
	def testInverseProbabilty {
		val normDist = new NormalDistribution(0.0, 1.0)
		val invCumProb = normDist.inverseCumulativeProbability(0.65)
		logger.info(s"Inverse cumulative probability = ${invCumProb}")
	}

}