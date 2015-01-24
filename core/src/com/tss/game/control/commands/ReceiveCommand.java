package com.tss.game.control.commands;

public class ReceiveCommand implements Command {

    public interface ReceiveCommandListener {
	// received: game 2 bot;anonymous
	// state transition to "GAME" we are the player #2
	public void game(String[] playersId);

	// received: sync 1 1 from 12 log
	// every game starts with the "sync" for uniformity
	// sync <step::int> <current_seat::int> from <your_dice:int> log <seat>
	// <from_hex> <die_face> <to_hex> ...
	public void sync(int step, int currentSeat, int yourDice, String[] args);

	// received: from 1 0 1
	// bot rolls his spare die -- face 1 came up
	public void from(int step, int cell, int dice);

	// received: to 1 49
	// bot puts the die to the hex49
	public void to(int step, int hex);

	// received: next 1 2
	// end of bot's turn
	public void next(int step);

	// received: eog 6 quit 2 winners 1
	// bot declared a victor
	public void eog(int step, int p, int w);

	// received: lobby
	public void lobby();

    }

    String message;

    ReceiveCommandListener listener;

    public ReceiveCommand(String message, ReceiveCommandListener listener) {
	this.listener = listener;
	this.message = message;
    }

    @Override
    public void execute() {
	String[] array = message.split(" ");
	String comm = array[0];
	if (comm.equals("from")) {
	    listener.from(Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]));
	} else if (comm.equals("to")) {
	    listener.to(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
	} else if (comm.equals("next")) {
	    listener.next(Integer.parseInt(array[1]));
	} else if (comm.equals("sync")) {
	    listener.sync(Integer.parseInt(array[1]), 0, 0, null);
	}
	System.out.println(message);
    }

}
