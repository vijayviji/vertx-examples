package com.myvertxproj.timer;

import io.vertx.core.Handler;
import java.lang.Thread.UncaughtExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OverallExceptionHandler {
  public static final MainThreadExceptionHandler MAIN_THREAD_EXCEPTION_HANDLER = new MainThreadExceptionHandler();
  public static final VertxExceptionHandler VERTX_EXCEPTION_HANDLER = new VertxExceptionHandler();
  private static final Logger logger =
      LoggerFactory.getLogger(OverallExceptionHandler.class);

  public static class MainThreadExceptionHandler implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
      System.err.println("Uncaught exception from main thread: " +  t + e);
    }
  }

  public static class VertxExceptionHandler implements Handler<Throwable> {
    @Override
    public void handle(Throwable event) {
      System.err.println("Uncaught exception from verticles: " + event);
    }
  }
}
