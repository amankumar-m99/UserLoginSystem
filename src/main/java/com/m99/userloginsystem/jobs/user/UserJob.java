package com.m99.userloginsystem.jobs.user;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@EnableAsync
@Service
public class UserJob {

	@Scheduled(initialDelay = 2000, fixedDelay = 2000) //fixedDelay for sequential calling
	public void printHelloInSequence() {
		System.out.println("Hello sequence");
	}
	/*

	@Scheduled(fixedRate = 2000) //fixedRate for independent calling
	@Async
	public void printHelloInParallel() {
		System.out.println("Hello parallel");
	}

	@Scheduled(fixedDelayString = "PT02S")
	public void IsoDurationFormat() {
		System.out.println("ISO Duration format");
	}

	 @Scheduled(fixedDelayString = "${interval}")
	 //interval=PT02S
	 private void usingPropertyInApplicationProperty() {
		 System.out.println("using application.properties");
	 }

	 @Scheduled(cron = "${interval-in-cron}")
	 private void cronJob() {
		 System.out.println("Unix style cron");
	 }

	 @Scheduled(cron = "@hourly")
	 private void cronMacros() {
		 System.out.println("cron macros");
	 }
	 */
}
