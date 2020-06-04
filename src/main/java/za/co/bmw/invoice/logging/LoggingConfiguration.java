package za.co.bmw.invoice.logging;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("za.co.bmw.invoice.logging")
@EnableAspectJAutoProxy
public class LoggingConfiguration {
}
