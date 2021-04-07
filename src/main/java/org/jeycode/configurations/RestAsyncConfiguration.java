package org.jeycode.configurations;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync(proxyTargetClass = true)
public class RestAsyncConfiguration
{

      @Bean
      public Executor sendMailServiceTaskExecutor()
      {
            var executor = new ThreadPoolTaskExecutor();
            var availableProcessors = Runtime.getRuntime()
                                             .availableProcessors();
            executor.setMaxPoolSize(availableProcessors);
            executor.setQueueCapacity(availableProcessors);
            executor.setThreadGroupName("Application-Task");
            executor.setThreadNamePrefix("Jey-Code-BCF");
            executor.initialize();
            return executor;
      }
}
