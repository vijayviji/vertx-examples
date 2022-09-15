package com.myvertxproj.vertx_eventbus.Verticles.PublishSubscribe;

import static com.myvertxproj.vertx_eventbus.Verticles.Common.PUBLISH_SUBSCRIBE_ADDR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

public class Consumer2 extends AbstractVerticle {
  @Override
  public void start() {
    System.out.println("Starting publish/subscribe Consumer2 Verticle");
    vertx.eventBus().consumer(PUBLISH_SUBSCRIBE_ADDR, this::processMsg);
  }

  public void processMsg(Message<String> msg) {
    System.out.println("Msg received by Consumer2: " + msg.body());
  }
}
