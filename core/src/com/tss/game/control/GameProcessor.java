package com.tss.game.control;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Queue;

import org.java_websocket.WebSocket.READYSTATE;

import com.tss.game.control.commands.Command;
import com.tss.game.control.commands.ReceiveCommand;
import com.tss.game.control.commands.ReceiveCommand.ReceiveCommandListener;
import com.tss.game.control.commands.SendCommand;
import com.tss.game.control.commands.SendCommand.SendCommandListener;
import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;
import com.tss.game.model.GameState;

public class GameProcessor implements SocketListener, BoardListener,
	InputListener, ReceiveCommandListener, SendCommandListener {

    Board board;

    ControllerListener controllerListener;

    GameSocket socket;

    Queue<Command> commands;
    
    GameState state;

    public GameProcessor() {
	try {
	    socket = new GameSocket(this);
	} catch (URISyntaxException e) {

	}
	socket.connect();
	state = new GameState();
	commands = new LinkedList<Command>();
    }

    public ControllerListener getListener() {
	return controllerListener;
    }

    public void setListener(ControllerListener listener) {
	this.controllerListener = listener;
    }

    @Override
    public void receive(String message) {
	commands.add(new ReceiveCommand(message, this));
    }

    public void update() {
	if (!commands.isEmpty()) {
	    Command c = commands.peek();
	    c.execute();
	    commands.remove();
	}
    }

    @Override
    public void take(Dice dice) {
	take(dice.getCell().getIndex());
    }

    @Override
    public void touch(Cell cell) {
	if (cell.getDice() != null) {
	    take(cell.getDice());
	    return;
	}
	move(cell);
    }

    public void close() {
	socket.close();
    }

    @Override
    public void opened() {
    }

    @Override
    public void closed() {
    }
    
    public void take() {
	take(0);
    }

    // sent: take2 0
    // 0 - we roll a spare die
    // not 0 - we take dice from board
    public void take(int hex) {
	final String text = state.getStep() + " " + hex;
	commands.add(new SendCommand("take" + text, this));
    };

    // sent: move2 41
    // we put the die to hex41
    public void move(Cell cell) {
	final String text = state.getStep() + " " + cell.getIndex();
	commands.add(new SendCommand("move" + text, this));
    };

    // sent: quit
    // we give up
    public void quit() {
	commands.add(new SendCommand("quit", this));
    };

    // sent: bot2
    // we start a game with the bot
    public void bot() {
	commands.add(new SendCommand("bot2", this));
    }

    @Override
    public void send(String send) {
	//if (this.socket.getReadyState() == READYSTATE.OPEN) 
	this.socket.send(send);    
    }

    @Override
    public void game(String[] playersId) {
	
    }

    @Override
    public void sync(int step, int currentSeat, int yourDice, String[] args) {
	state.setStep(step);
    }

    @Override
    public void from(int step, int cell, int dice) {
	state.setStep(step);
    }

    @Override
    public void to(int step, int hex) {
	state.setStep(step);
    }

    @Override
    public void next(int step) {
	step++;
	state.setStep(step);
	//state
    }

    @Override
    public void eog(int step, int p, int w) {
    }

    @Override
    public void lobby() {
    };
}
