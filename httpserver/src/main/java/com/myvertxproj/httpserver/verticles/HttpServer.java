package com.myvertxproj.httpserver.verticles;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpServer extends AbstractVerticle {
  Logger logger = LoggerFactory.getLogger(HttpServer.class);
  static private int httpPort = 8080;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    logger.info("Starting HttpServer verticle");
    Router router = Router.router(vertx);
    router.get("/hello").handler(this::getHandler);

    vertx.createHttpServer()
        .requestHandler(router)
        .listen(httpPort)
        .onSuccess(ok -> {
          logger.info("Started http://127.0.0.1:{}/ ", httpPort);
          startPromise.complete();
        })
        .onFailure(startPromise::fail);
  }

  private void getHandler(RoutingContext context) {
    JsonObject object = new JsonObject()
        .put("msg", "welcome");
    context.response()
        .putHeader("Content-Type", "application/json")
        .setStatusCode(200)
        .end(object.encode() + "\n");
  }

  @Override
  public void stop(Promise<Void> stopPromise) throws Exception {
    super.stop(stopPromise);
  }
}
