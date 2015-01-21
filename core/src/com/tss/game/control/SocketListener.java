package com.tss.game.control;

public interface SocketListener {
    public void messageReceived(String message);

    public void opened();

    public void closed();
}