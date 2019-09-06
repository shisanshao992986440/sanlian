package sanlian.robot.eureka.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class SlRobotEurekaClientApplication 
{
	 public static void main(String[] args) {
	       SpringApplication.run(SlRobotEurekaClientApplication.class, args);
	    }
}
