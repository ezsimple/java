package net.ion.nipa.system;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.ion.nipa.common.resolver.CommandMapArgumentResolver;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	  @Override
	  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
	      // equivalent to <mvc:argument-resolvers>
	      argumentResolvers.add(new CommandMapArgumentResolver());
	  }

}
