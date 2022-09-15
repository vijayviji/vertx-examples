package com.myvertxproj.vertx_eventbus.Verticles.RequestReply;

import static com.myvertxproj.vertx_eventbus.Verticles.Common.REQUEST_REPLY_ADDR;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.Message;

public class Consumer extends AbstractVerticle {
  @Override
  public void start() {
    System.out.println("Starting request/reply Consumer Verticle");
    vertx.eventBus().consumer(REQUEST_REPLY_ADDR, this::processMsg);
  }

  public void processMsg(Message<String> msg) {
    System.out.println("Msg received by Consumer: " + msg.body());

    String reply = "request/reply-reply";
    System.out.println("Reply sent by Consumer: " + reply);
    msg.reply(reply);
  }
}
