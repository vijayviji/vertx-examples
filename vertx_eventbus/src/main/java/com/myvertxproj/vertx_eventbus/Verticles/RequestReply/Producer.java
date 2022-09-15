package com.myvertxproj.vertx_eventbus.Verticles.RequestReply;

import static com.myvertxproj.vertx_eventbus.Verticles.Common.REQUEST_REPLY_ADDR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;

public class Producer extends AbstractVerticle {
  @Override
  public void start() {
    System.out.println("Starting request/reply Producer Verticle");

    String msg = "request/reply-msg";
    System.out.println("Msg sent by producer: " + msg);
    vertx.eventBus().request(REQUEST_REPLY_ADDR, msg, this::receiveResponse);
  }

  public void receiveResponse(AsyncResult<Message<String>> result) {
    if (result.succeeded()) {
      System.out.println("Reply received by producer: " + result.result().body());
    } else {
      assert result.failed();
      System.out.println("Msg sending failed by producer: " + result.cause());
    }
  }
}
