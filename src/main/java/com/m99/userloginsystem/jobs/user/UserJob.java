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
	}
	/*

	@Scheduled(fixedRate = 2000) //fixedRate for independent calling
	@Async
	public void printHelloInParallel() {
	}

	@Scheduled(fixedDelayString = "PT02S")
	public void IsoDurationFormat() {
	}

	 @Scheduled(fixedDelayString = "${interval}")
	 //interval=PT02S
	 private void usingPropertyInApplicationProperty() {
	 }

	 @Scheduled(cron = "${interval-in-cron}")
	 private void cronJob() {
	 }

	 @Scheduled(cron = "@hourly")
	 private void cronMacros() {
	 }
	 */
}
