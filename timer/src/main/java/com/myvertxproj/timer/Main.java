package com.myvertxproj.timer;

import com.myvertxproj.timer.verticles.MyTimer;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  static Logger logger = LoggerFactory.getLogger(Main.class);
  public static void main(String[] args) {
    Thread.setDefaultUncaughtExceptionHandler(OverallExceptionHandler.MAIN_THREAD_EXCEPTION_HANDLER);
    logger.info("Deploying verticles");
    Vertx vertx = Vertx.vertx();
    deployVerticle(vertx, new MyTimer());
  }

  private static void deployVerticle(Vertx vertx, Verticle verticle) {
    // let's create two instances of the verticles.
    vertx.deployVerticle(MyTimer::new, new DeploymentOptions().setInstances(2), deployment -> {
      if (deployment.failed()) {
        if (deployment.cause() != null) {
          logger.error("Deployment of {} verticle failed with exception: {}", verticle,
              deployment.cause());
        } else if (deployment.result() != null) {
          logger.error("Deployment of {} verticle failed: {}", verticle, deployment.result());
        } else {
          logger.error("Deployment of {} verticle failed with unknown reason", verticle);
        }
        return;
      }
      logger.error("Deployment of {} verticle success: {}", verticle, deployment.result());
    });
  }
}
