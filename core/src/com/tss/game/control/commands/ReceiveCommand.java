package com.tss.game.control.commands;

import com.tss.game.control.ControllerListener;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;
import com.tss.game.model.GameState;
import com.tss.game.model.Player;

public class ReceiveCommand implements Command {

    GameState state;

    String message;

    ControllerListener controllerListener;

    public ReceiveCommand(String message, GameState state,
	    ControllerListener controllerListener) {
	this.message = message;
	this.state = state;
	this.controllerListener = controllerListener;
    }

    @Override
    public void execute() {
	System.out.println("r:" + message);
	String[] array = message.split(" ");
	String comm = array[0];
	if (comm.equals("from")) {
	    from(Integer.parseInt(array[1]), Integer.parseInt(array[2]),
		    Integer.parseInt(array[3]));
	} else if (comm.equals("to")) {
	    to(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
	} else if (comm.equals("next")) {
	    next(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
	} else if (comm.equals("sync")) {
	    sync(Integer.parseInt(array[1]), Integer.parseInt(array[2]), 0, 0, null);
	} else if (comm.equals("game")) {
	    game(Integer.parseInt(array[1]), array[2].split(";"));
	}
    }
    
    // received: sync 1 1 from 12 log
    // every game starts with the "sync" for uniformity
    // sync <step::int> <current_seat::int> from <your_dice:int> log <seat>
    // <from_hex> <die_face> <to_hex> ...
    public void sync(int step, int current, int yourDice, int yourSeat,
	    String[] args) {
	//state.setStep(step);
	//state.setMySeat(yourSeat);
    }    

    // received: game 2 bot;anonymous
    // state transition to "GAME" we are the player #2
    // game <your_seat::int> <player_names::list>
    public void game(int mySeat, String[] playersId) {
	state.newGame(mySeat, playersId);
    }

    // received: from 1 0 1
    // bot rolls his spare die -- face 1 came up
    public void from(int step, int hex, int dice) {
	state.setStep(step);
	state.setTakenDice(dice);
	Player p = state.getCurrentPlayer();
	if (hex == 0) {
	    p.setTakenDice(p.getDices().remove());
	    p.getTakenDice().setNumber(Dice.Number.values()[dice]);
	} else {
	    Cell c = controllerListener.getCell(hex);
	    Dice d = c.getDice();
	    d.setNumber(Dice.Number.values()[dice]);
	    controllerListener.getDices().remove(d);
	    controllerListener.removeFrom(c);
	    p.setTakenDice(d);	    
	}
    }

    // received: to 1 49
    // bot puts the die to the hex49
    public void to(int step, int hex) {
	state.setStep(step);
	state.setTakenDice(0);
	Dice d = state.getCurrentPlayer().getTakenDice(); // check on null
	Cell c = controllerListener.getCell(hex);
	c.setDice(d);
	d.setCell(c);
	controllerListener.moveTo(c, d);
    }

    // received: next 1 2
    // end of bot's turn
    public void next(int step, int next) {
	state.setStep(step);
	state.setNext(next);
    }

    // received: eog 6 quit 2 winners 1
    // bot declared a victor
    public void eog(int step, int p, int w) {

    }

    // received: lobby
    public void lobby() {
    };

}
