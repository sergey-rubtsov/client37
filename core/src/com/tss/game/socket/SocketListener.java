package com.tss.game.socket;

public interface SocketListener {

    public void opened();

    public void closed();

    public void receive(String message);
}