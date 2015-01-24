package com.tss.game.control;

import com.tss.game.model.Player;

public interface InputListener {
    
    enum Button {SEND, DICE, MENU}
    
    public void input(String text);
    
    public void click(Button button);

    public void newGame(Player[] players);

}
