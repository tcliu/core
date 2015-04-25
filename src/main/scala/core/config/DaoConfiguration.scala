package core.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@Lazy
@Configuration
@ComponentScan(basePackages = Array("core.dao"), lazyInit = true)
class DaoConfiguration {

}