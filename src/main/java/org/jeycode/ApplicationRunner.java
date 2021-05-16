package org.jeycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class ApplicationRunner
{

      public static void main(String[] args)
      {
            SpringApplication.run(ApplicationRunner.class,args);
      }
}
