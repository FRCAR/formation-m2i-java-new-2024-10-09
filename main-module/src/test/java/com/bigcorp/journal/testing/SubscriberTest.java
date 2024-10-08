package com.bigcorp.journal.testing;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.bigcorp.project.main.asynchronous.MySubscriber;

public class SubscriberTest {

	@Test
	public void testSubscribe() throws InterruptedException {
		// given
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		MySubscriber<String> subscriber1 = new MySubscriber<>("subscriber 1");
		MySubscriber<String> subscriber2 = new MySubscriber<>("subscriber 2");
		publisher.subscribe(subscriber1);
		publisher.subscribe(subscriber2);
		List<String> items = Arrays.asList("Message 1", "Message 2", "Message 3",
				"Message 4", "Message 5", "Message 6",
				"Message 7");

		// when
		Assertions.assertEquals(2, publisher.getNumberOfSubscribers());
		items.forEach(publisher::submit);
		publisher.close();

		Thread.sleep(1000);

		//then
		Assertions.assertEquals(items.size(), subscriber1.getCounter());
		Assertions.assertEquals(items.size(), subscriber2.getCounter());
	}

}
