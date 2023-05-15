package com.objects;

import API.Player;

import java.util.Scanner;

public class HumanPlayer implements Player {
    Scanner in = new Scanner(System.in);

    @Override
    public void playTurn(Board board, Mark mark) {
        System.out.println("enter 2 digit number (row,col)...");
        System.out.print("row= ");
        int row = in.nextInt() - 1;
        System.out.print("col= ");
        int col = in.nextInt() - 1;
        boolean loop = board.putMark(mark, row, col);
        while (!loop) {
            System.out.println("You are entering invalid numbers, please try again");
            System.out.print("row= ");
            row = in.nextInt() - 1;
            System.out.print("col= ");
            col = in.nextInt() - 1;
            loop = board.putMark(mark, row, col);
        }
    }
}
