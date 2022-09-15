package com.myvertxproj.vertx_eventbus;

import com.myvertxproj.vertx_eventbus.Verticles.PointToPoint.Consumer;
import com.myvertxproj.vertx_eventbus.Verticles.PointToPoint.Producer;
import com.myvertxproj.vertx_eventbus.Verticles.PublishSubscribe.Consumer1;
import com.myvertxproj.vertx_eventbus.Verticles.PublishSubscribe.Consumer2;
import com.myvertxproj.vertx_eventbus.Verticles.PublishSubscribe.Producer1;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  static Logger logger = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) {
    logger.info("Deploying verticles");
    Vertx vertx = Vertx.vertx();

    //deployPointToPoint(vertx);
    deployRequestReply(vertx);
    //deployPublishSubscribe(vertx);
  }

  private static void deployPointToPoint(Vertx vertx) {
    // point-to-point
    vertx.deployVerticle(new Producer());
    vertx.deployVerticle(new Consumer());
  }

  private static void deployRequestReply(Vertx vertx) {
    // request-reply
    vertx.deployVerticle(new com.myvertxproj.vertx_eventbus.Verticles.RequestReply.Producer());
    vertx.deployVerticle(new com.myvertxproj.vertx_eventbus.Verticles.RequestReply.Consumer());
  }

  private static void deployPublishSubscribe(Vertx vertx) {
    // publish/subscribe
    vertx.deployVerticle(new Producer1());
    vertx.deployVerticle(new Consumer1());
    vertx.deployVerticle(new Consumer2());
  }
}
