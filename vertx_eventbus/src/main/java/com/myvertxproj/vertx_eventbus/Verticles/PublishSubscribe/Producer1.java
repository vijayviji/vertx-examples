package com.myvertxproj.vertx_eventbus.Verticles.PublishSubscribe;

import static com.myvertxproj.vertx_eventbus.Verticles.Common.PUBLISH_SUBSCRIBE_ADDR;

import io.vertx.core.AbstractVerticle;

public class Producer1 extends AbstractVerticle {
  @Override
  public void start() {
    System.out.println("Starting publish/subscribe Producer1 Verticle");

    String msg = "publish/subscribe";
    for (int i = 0; i < 10; i++) {
      String actualMsg = msg + "-" + i;
      System.out.println("Msg sent by producer: " + actualMsg);
      vertx.eventBus().request(PUBLISH_SUBSCRIBE_ADDR, actualMsg);
    }
  }
}
