package com.tss.game.model;

import com.badlogic.gdx.graphics.Color;

public class GameState {

    Player[] players;

    private Player currentPlayer;

    private Player nextPlayer;

    private Color myColor;

    private int takenDice;

    int step;

    int mySeat;

    public enum Next {ME, NOT_ME, NULL}

    public enum Status {WAIT_SERVER_ANSWER, WAIT_USER_ACTION}

    public GameState() {
        this.takenDice = 0;
        this.step = 0;
        this.myColor = new Color(1, 1, 1, 1);
    }

    public void newGame(int mySeat, String[] playersId) {
        this.mySeat = mySeat;
        this.takenDice = 0;
        this.step = 0;
        this.players = new Player[playersId.length];
        for (int i = 0; i < playersId.length; i++) {
            Player p;
            if (i == (mySeat - 1)) {
                p = new Player(playersId[i], true, i + 1);
                myColor = p.getColor();
            } else p = new Player(playersId[i], i + 1);
            players[i] = p;
        }
        setCurrentPlayer(this.players[0]);
        setNextPlayer(this.players[1]);
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public void setMySeat(int mySeat) {
        this.mySeat = mySeat;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    ;

    public Next whoNext() {
        if (currentPlayer.isMe) return Next.ME;
        return Next.NOT_ME;
    }

    public void setNext(int next) {
        setCurrentPlayer(this.players[next - 1]);
        if (next > this.players.length) {
            setNextPlayer(this.players[0]);
            return;
        }
        setNextPlayer(this.players[next - 1]);
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    private void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void reset() {
    }

    public int getTakenDice() {
        return takenDice;
    }

    public void setTakenDice(int takenDice) {
        this.takenDice = takenDice;
    }

    public Color getMyColor() {
        return myColor;
    }

    public void setMyColor(Color myColor) {
        this.myColor = myColor;
    }

}
