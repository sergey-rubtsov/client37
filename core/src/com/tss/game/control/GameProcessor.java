package com.tss.game.control;

import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.Queue;

import org.java_websocket.WebSocket.READYSTATE;

import com.tss.game.control.commands.Command;
import com.tss.game.control.commands.ReceiveCommand;
import com.tss.game.control.commands.ReceiveCommand.ReceiveCommandListener;
import com.tss.game.control.commands.SendMessageCommand;
import com.tss.game.model.Board;
import com.tss.game.model.Cell;
import com.tss.game.model.Dice;
import com.tss.game.model.GameState;
import com.tss.game.model.Player;

public class GameProcessor implements SocketListener, BoardListener, InputListener, ReceiveCommandListener {
    
    Board board;
    
    ControllerListener controllerListener;
    
    GameSocket socket;
    
    GameState state;
    
    Queue<Command> commands;
    
    private boolean connectionOpened;
    
    public GameProcessor() {
	setConnectionOpened(false);
	try {
	    socket = new GameSocket(this);
	} catch (URISyntaxException e) {
	    
	}
	socket.connect();
	this.state = new GameState();
	commands = new LinkedList<Command>();
    }    

    public ControllerListener getListener() {
        return controllerListener;
    }

    public void setListener(ControllerListener listener) {
        this.controllerListener = listener;
    }

    @Override
    public void messageReceived(String message) {
	commands.add(new ReceiveCommand(message, this));	
    }

    @Override
    public void opened() {
	setConnectionOpened(true);	
    }

    public boolean isConnectionOpened() {
	return connectionOpened;
    }

    public void setConnectionOpened(boolean connectionOpened) {
	this.connectionOpened = connectionOpened;
    }
    
    public void update() {
	if (!commands.isEmpty()) {
            Command c = commands.peek();
            c.execute();
            commands.remove();            
        }
    }

    @Override
    public void pickUp(Dice dice) {
	//listener.
    }

    @Override
    public void touch(Cell cell) {
	System.out.println(cell.getIndex());
    }

    @Override
    public void closed() {
		
    }

    public void close() {
	socket.close();	
    }

    @Override
    public void input(String text) {
	commands.add(new SendMessageCommand(text, null, socket));
    }
    
    //sent: bot2 // we start a game with the bot
    public void bot() {
	
    }
    
    //received: game 2 bot;anonymous		
    //state transition to "GAME" we are the player #2 
    public void game(String[] playersId) {
	
    }
    
    //received: sync 1 1 from 12 log		
    //every game starts with the "sync" for uniformity
    //sync <step::int> <current_seat::int> from <your_dice:int> log <seat> <from_hex> <die_face> <to_hex> ...
    public void sync(int step) {
	
    }
    
    //received: from 1 0 1			
    //bot rolls his spare die -- face 1 came up
    public void from(int step, Player player, Dice dice) {
	
    }
    
    //received: to 1 49			
    //bot puts the die to the hex49
    public void to(int step, Cell cell) {
	
    }
    
    //received: next 1 2			
    //end of bot's turn
    public void next(int step) {}
    
    //sent: take2 0
    //we roll a spare die
    public void take(int step) {
	
    }
    
    //sent: move2 41				
    // we put the die to hex41 
    public void move(int step, Cell cell) {
	
    }    
    
    //sent: quit				
    //we give up
    public void quit() {
	
    }
    
    //received: eog 6 quit 2 winners 1	
    //bot declared a victor    
    public void eog(int step) {
	
    }

    //received: lobby	
    public void lobby() {
	
    }
    

    @Override
    public void click(Button button) {
	//commands.add(new SendMessageCommand(PING, null, socket));	
    }
    
    public void newGame(Player[] players) {
	this.state = new GameState(players);
    }
    
    //if (this.socket.getReadyState() == READYSTATE.OPEN) {
    	//this.socket.send(PING);
    //}
    
    
}
