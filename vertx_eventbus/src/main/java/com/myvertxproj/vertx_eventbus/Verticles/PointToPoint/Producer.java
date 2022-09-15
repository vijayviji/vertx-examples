package com.myvertxproj.vertx_eventbus.Verticles.PointToPoint;

import static com.myvertxproj.vertx_eventbus.Verticles.Common.POINT_TO_POINT_ADDR;

import io.vertx.core.AbstractVerticle;

public class Producer extends AbstractVerticle {
  @Override
  public void start() {
    System.out.println("Starting Point-to-point Producer Verticle");

    String msg = "point-to-point-msg";
    System.out.println("Msg sent by producer: " + msg);
    vertx.eventBus().request(POINT_TO_POINT_ADDR, msg);
  }
}
