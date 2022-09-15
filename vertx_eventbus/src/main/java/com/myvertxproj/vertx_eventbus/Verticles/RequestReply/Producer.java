package com.myvertxproj.vertx_eventbus.Verticles.RequestReply;

import static com.myvertxproj.vertx_eventbus.Verticles.Common.REQUEST_REPLY_ADDR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.eventbus.Message;

public class Producer extends AbstractVerticle {
  @Override
  public void start() {
    System.out.println("Starting request/reply Producer Verticle");

    String msg = "request/reply-msg";
    sendAndGetReply1(msg + "-method1");
    sendAndGetReply2(msg + "-method2");
  }

  // First method to send msg and get reply.
  public void sendAndGetReply1(String msg) {
    System.out.println("Msg sent by producer in method1: " + msg);
    vertx.eventBus().request(REQUEST_REPLY_ADDR, msg, result -> {
      if (result.succeeded()) {
        System.out.println("Reply received by producer in method1: " + result.result().body());
      } else {
        assert result.failed();
        System.out.println("Msg sending failed by producer in method1: " + result.cause());
      }
    });
  }

  // A second method to send msg and get reply.
  public void sendAndGetReply2(String msg) {
    System.out.println("Msg sent by producer in method2: " + msg);
    vertx.eventBus().request(REQUEST_REPLY_ADDR, msg).compose(reply -> {
      System.out.println("Reply received by producer in method2: " + reply.body());
      return Future.succeededFuture(reply);
    });
  }

  public void receiveResponse(AsyncResult<Message<String>> result) {

  }
}
