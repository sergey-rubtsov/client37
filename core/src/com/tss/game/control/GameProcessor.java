package com.tss.game.control;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Queue;

import org.java_websocket.WebSocket.READYSTATE;

import com.tss.game.control.commands.Command;
import com.tss.game.control.commands.ReceiveCommand;
import com.tss.game.control.commands.send.Message;
import com.tss.game.control.commands.send.Move;
import com.tss.game.control.commands.send.Take;
import com.tss.game.control.commands.SendCommand;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;
import com.tss.game.model.GameState;

public class GameProcessor implements SocketListener, BoardListener,
	InputListener {

    ControllerListener controllerListener;

    GameSocket socket;

    Queue<Command> commands;

    private GameState state;

    public GameProcessor() {
	try {
	    socket = new GameSocket(this);
	} catch (URISyntaxException e) {

	}
	socket.connect();
	state = new GameState();
	commands = new LinkedList<Command>();
    }

    public void setListener(ControllerListener listener) {
	this.controllerListener = listener;
    }

    @Override
    public void receive(String message) {
	commands.add(new ReceiveCommand(message, state, controllerListener));
    }

    public void update() {
	if (!commands.isEmpty()) {
	    Command c = commands.peek();
	    c.execute();
	    commands.remove();
	}
    }

    // command from board
    @Override
    public void take(Dice dice) {
	take(dice.getCell().getIndex());
    }

    // command from board
    @Override
    public void touch(Cell cell) {
	if (cell.getDice() != null) {
	    take(cell.getIndex());
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
	commands.add(new SendCommand(new Take(state, hex), socket));
    };

    // sent: move2 41
    // we put the die to hex41
    public void move(Cell cell) {	
	commands.add(new SendCommand(new Move(state, cell), socket));
    };

    // sent: quit
    // we give up
    public void quit() {
	controllerListener.reset();
	state.reset();
	commands.add(new SendCommand(new Message() {
	    @Override
	    public String getCommandText() {
		return "quit";
	    }
	}, socket));
    };

    // sent: bot2
    // we start a game with the bot
    public void bot() {
	commands.add(new SendCommand(new Message() {
	    @Override
	    public String getCommandText() {
		return "bot2";
	    }
	}, socket));
    }
    

    public GameState getState() {
        return state;
    }

    // if (this.socket.getReadyState() == READYSTATE.OPEN)

}
