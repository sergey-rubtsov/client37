package com.tss.game.control;

public interface SocketListener {

    public void opened();

    public void closed();

    public void receive(String message);
}