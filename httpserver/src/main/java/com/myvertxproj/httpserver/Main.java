package com.myvertxproj.httpserver;

import com.myvertxproj.httpserver.verticles.HttpServer;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  static Logger logger = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) {
    logger.info("Deploying verticles");
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new HttpServer());
  }
}
