package com.objects;

import java.util.Arrays;

public class Board {
    public static int SIZE = 3;
    public static final int WIN_STREAK = SIZE;
    private int boardRow;
    private int boardCol;
    private final Mark[][] board = new Mark[SIZE][SIZE];

    // fill the bord with blank marks
    public Board() {
        for (Mark[] marks : board) {
            Arrays.fill(marks, Mark.BLANK);
        }
    }

    // put mark in the bord
    public boolean putMark(Mark mark, int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE || board[row][col] != Mark.BLANK)
            return false;
        boardRow = row;
        boardCol = col;
        board[boardRow][boardCol] = mark;
        return true;
    }

    public Mark getMark(int row, int col) {
        return board[row][col];
    }


    //check if all the blocks on the board are full
    public boolean isGameOver() {
        int countBlock = 0;
        for (Mark[] marks : board) {
            for (Mark mark : marks) {
                if (mark == Mark.BLANK)
                    countBlock++;
            }
        }
        return countBlock == 0;
    }


    public Winner howIsWin(Mark mark) {
        if (countMarkRightToLeft(boardRow, mark) == WIN_STREAK ||
                countMarkUpAndDown(boardCol, mark) == WIN_STREAK ||
                countMarkInSlant(mark) == WIN_STREAK ||
                countMarkInSlant2(mark) == WIN_STREAK) {
            if (mark == Mark.O) {
                return Winner.O_WIN;
            } else if (mark == Mark.X) {
                return Winner.X_WIN;
            }
        }
        return Winner.DRAW;
    }

    //count the marks in the given row and returns the amount of marks equal to the given mark
    private int countMarkRightToLeft(int row, Mark mark) {
        int count = 0;
        for (int i = 0; i < board[row].length && board[row][i] == mark; i++) {
            count += 1;
        }
        return count;
    }

    //count the marks in the given column and returns the amount of marks equal to the given mark
    private int countMarkUpAndDown(int col, Mark mark) {
        int count = 0;
        for (int i = 0; i < board.length && board[i][col] == mark; i++) {
            count += 1;
        }
        return count;
    }

    //count the marks from board[0][0] to bo the end in slant and returns the amount of marks equal to the given mark
    private int countMarkInSlant(Mark mark) {
        int count = 0;
        for (int i = 0; i < board.length && board[i][i] == mark; i++) {
            count++;
        }
        return count;
    }

    private int countMarkInSlant2(Mark mark) {
        int count = 0;
        int row = 0;
        for (int i = board.length - 1; i >= 0 && board[row][i] == mark; i--) {
            count++;
            row++;
        }
        return count;
    }
}
