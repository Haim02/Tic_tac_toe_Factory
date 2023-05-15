package com.objects;

import API.Player;

public class EasyPlayer implements Player {
    @Override
    public void playTurn(Board board, Mark mark) {
        int row = ran() - 1;
        int col = ran() - 1;
        boolean loop = board.putMark(mark, row, col);
        while (!loop) {
            row = ran() - 1;
            col = ran() - 1;
            loop = board.putMark(mark, row, col);
        }
    }

    //Return a random number between 1-3
    private int ran() {
        return (int) ((Math.random() * 3) + 1);
    }
}

