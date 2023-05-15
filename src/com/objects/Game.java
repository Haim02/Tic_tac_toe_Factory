package com.objects;

import API.Player;
import API.Renderer;

import java.util.Scanner;

public class Game {
    private final Renderer renderer;
    private Winner winner = Winner.DRAW;
    private final Board board = new Board();

    public Game(Renderer renderer) {
        this.renderer = renderer;
    }

    public Winner run(Player player1, Player player2) {
        while (!board.isGameOver()) {
            if (winner != Winner.DRAW)
                break;
            System.out.println("Player X turn");
            player1.playTurn(board, Mark.X);
            renderer.renderBoard(board);
            winner = board.howIsWin(Mark.X);

            if (winner != Winner.DRAW || board.isGameOver())
                break;
            System.out.println("Player O turn");
            player2.playTurn(board, Mark.O);
            renderer.renderBoard(board);
            winner = board.howIsWin(Mark.O);
        }
        return winner;
    }

    public static void main(String[] args) {
        Renderer renderer = new ConsoleRenderer();
        Player playerX = new HumanPlayer();
        Player playerO = new EasyPlayer();
        Game game = new Game(renderer);
        System.out.println(game.run(playerX, playerO));
    }
}
