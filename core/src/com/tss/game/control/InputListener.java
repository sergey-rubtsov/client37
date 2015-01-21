package com.tss.game.control;

public interface InputListener {
    
    enum Button {SEND, DICE, MENU}
    
    public void input(String text);
    
    public void click(Button button);

}
