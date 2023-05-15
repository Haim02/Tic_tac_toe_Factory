package com.objects;

import API.Player;

public class HardPlayer implements Player {

    private int[] bestMove(Board board, Mark mark) {
        int bestScore = Integer.MIN_VALUE;
        int[] move = new int[2];
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j <= i; j++) {
                if (board.getMark(i, j) == Mark.BLANK)
                    board.putMark(mark, i, j);
                int score = minimax(board, 0, false);
                if (score > bestScore) {
                    bestScore = score;
                    move[0] = i;
                    move[1] = j;
                }
            }
        }
        return move;
    }

    private int scores(Winner winner) {
        return switch (winner) {
            case X_WIN -> -1;
            case O_WIN -> 1;
            case DRAW -> 0;
        };
    }

    private int minimax(Board board, int depth, boolean isMaximizing) {
        Winner result = board.howIsWin(Mark.BLANK);
        if (result != Winner.DRAW) {
            return scores(result);
        }

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getMark(i, j) == Mark.BLANK) {
                        board.putMark(Mark.O, i, j);
                        int score = minimax(board, depth + 1, false);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < Board.SIZE; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getMark(i, j) == Mark.BLANK) {
                        board.putMark(Mark.X, i, j);
                        int score = minimax(board, depth + 1, true);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

    @Override
    public void playTurn(Board board, Mark mark) {
        int[] move = bestMove(board, mark);
        board.putMark(mark, move[0], move[1]);
    }
}

