package com.dbs.paynow;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class PaynowApplication {

	public static void main(String[] args) {
		DayOfWeek dayOfWeek = DayOfWeek.from(LocalDate.now());
		
		if(dayOfWeek.toString().equals("SATURDAY") || dayOfWeek.toString().equals("SUNDAY")) {
			System.out.print("Am I here? ");
			ConfigurableApplicationContext ctx = new SpringApplicationBuilder(PaynowApplication.class)
					  .web(WebApplicationType.NONE).run();
			SpringApplication.exit(ctx, () -> 0);
		}
		else
			SpringApplication.run(PaynowApplication.class, args);
	}

}
