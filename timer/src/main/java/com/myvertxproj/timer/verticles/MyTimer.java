package com.myvertxproj.timer.verticles;

import com.myvertxproj.timer.VerticleBase;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.eventbus.MessageProducer;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyTimer extends VerticleBase {
  private static final Logger logger = LoggerFactory.getLogger(MyTimer.class);

  @Override
  public void start0(Promise<Void> startPromise) {
    logger.info("Starting timer verticle");

    createProducerConsumer();
    createOneshotTimer();
    //createPeriodicTimer();
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    logger.info("Stopping verticle");
    super.stop(stopPromise);
  }

  private void createProducerConsumer() {
    EventBus eb = vertx.eventBus();
    int prodconsId = ThreadLocalRandom.current().nextInt();

    MessageProducer<Integer> sender = eb.sender("prodcons" + prodconsId);
    MessageConsumer<Integer> receiver = eb.consumer("prodcons" + prodconsId);
    receiver.handler(msg -> {
      logger.info("Received int: {}", msg.body());
    });

    vertx.setPeriodic(1000, id -> {
      int randomNo = ThreadLocalRandom.current().nextInt(0, 100);
      logger.info("Sending int: {}", randomNo);
      sender.write(randomNo);
    });
  }

  private void createOneshotTimer() {
    vertx.setTimer(1000, id -> {
      logger.info("From one-shot timer task {}", id);
    });
  }

  private void createPeriodicTimer() {
    vertx.setPeriodic(1000, id -> {
      logger.info("From periodic exception throwing task {}", id);
      throw new RuntimeException("exception in timer");
    });

    Random random = new Random();
  }
}
