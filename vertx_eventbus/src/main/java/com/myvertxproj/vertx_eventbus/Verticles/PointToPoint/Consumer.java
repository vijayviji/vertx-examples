package com.myvertxproj.vertx_eventbus.Verticles.PointToPoint;

import static com.myvertxproj.vertx_eventbus.Verticles.Common.POINT_TO_POINT_ADDR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

public class Consumer extends AbstractVerticle {
  @Override
  public void start() {
    System.out.println("Starting Point-to-point Consumer Verticle");
    vertx.eventBus().consumer(POINT_TO_POINT_ADDR, this::processMsg);
  }

  public void processMsg(Message<String> msg) {
    System.out.println("Msg received by Consumer: " + msg.body());
  }
}
