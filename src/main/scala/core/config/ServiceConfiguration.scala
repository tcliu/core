package core.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Lazy
@Configuration
@Import(Array(classOf[DaoConfiguration]))
@ComponentScan(basePackages = Array("core.service"), lazyInit = true)
class ServiceConfiguration {

}