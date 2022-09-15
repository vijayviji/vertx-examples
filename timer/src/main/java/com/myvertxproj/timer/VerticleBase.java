package com.myvertxproj.timer;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public abstract class VerticleBase extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    context.exceptionHandler(OverallExceptionHandler.VERTX_EXCEPTION_HANDLER);
    start0(startPromise);
  }

  public abstract void start0(Promise<Void> startPromise);
}
