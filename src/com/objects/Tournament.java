package com.objects;

import API.Player;
import API.Renderer;

import java.util.Scanner;

public class Tournament {
    private final Scanner in = new Scanner(System.in);
    private final PlayerFactory playerFactory = new PlayerFactory();
    private final Renderer renderer;
    private final Player player1 = playerFactory.buildPlayer("human");
    private final Player player2;
    private final int round;
    private Game game;
    private Winner winner;

    public Tournament() {
        RendererFactory rendererFactory = new RendererFactory();
        this.renderer = rendererFactory.buildRenderer(buildRenderer());
        this.player2 = playerFactory.buildPlayer(buildPlayer());
        this.round = setRounds();
    }

    private String buildRenderer() {
        return "console";
    }

    private int setRounds() {
        int round;
        System.out.print("How much round do you want to play? = ");
        round = in.nextInt();
        System.out.println("---------------------------");
        return round;

    }

    /**
     * the player choice against who to play
     *
     * @return 1 for easy level, 2 for hard level, 3 for play against a person
     */
    private int againstWhoToPlay() {
        int choice;
        System.out.println("Welcome to Tic Tac Toe game");
        System.out.println("For easy level press 1");
        System.out.println("For hard level press 2");
        System.out.println("if you wants to play against a person 3");
        System.out.print("your choice = ");
        choice = in.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("You are entering invalid numbers, please try again");
            System.out.print("your choice = ");
            choice = in.nextInt();
        }
        return choice;
    }

    //get number from againstWhoToPlay() and return a string
    public String buildPlayer() {
        int number = againstWhoToPlay();
        return switch (number) {
            case 1 -> "EasyPlayer";
            case 2 -> "HardPlayer";
            case 3 -> "human";
            default -> null;
        };
    }

    // the main game to run
    public void playTournament() {
        int[] victories = new int[3];
        int X_Win = 0;
        int o_Win = 0;
        int numOfDraw = 0;
        for (int i = 0; i < round; i++) {
            game = new Game(renderer);
            int numOfRounds = i + 1;
            System.out.println("Round number: " + numOfRounds);
            winner = game.run(player1, player2);

            switch (winner) {
                case X_WIN -> X_Win++;
                case O_WIN -> o_Win++;
                case DRAW -> numOfDraw++;
            }
            System.out.println("-------Score table-----");
            System.out.println("Player X Victories : " + X_Win);
            System.out.println("Player 0 Victories : " + o_Win);
            System.out.println("number of draw     : " + numOfDraw);
        }
        victories[0] = X_Win;
        victories[1] = o_Win;
        victories[2] = numOfDraw;
        if (round % 2 == 0 && victories[0] == victories[1]) {
            winner = Winner.DRAW;
            System.out.println("The result seems to be a draw");
            System.out.print("Do you want to play one last game to decide who wins? (y/n) = ");
            finalGame();
        } else {
            if (victories[whoWon(victories)] == 0)
                winner = Winner.X_WIN;
            else if (victories[whoWon(victories)] == 1)
                winner = Winner.O_WIN;
            else winner = Winner.DRAW;
            System.out.println("the winner is = " + winner);
        }
    }

    // return the bigger index in the array
    private int whoWon(int[] arr) {
        int bigger = 0;
        int index = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > bigger) {
                bigger = arr[i];
                index = i;
            }
        }
        return index;
    }

    // if the scour is draw the players need to choice if to play one more game
    private void finalGame() {
        char answer = in.next().charAt(0);
        while (answer != 'y' && answer != 'n') {
            System.out.print("(y/n)? = ");
            answer = in.next().charAt(0);
        }
        if (answer == 'y') {
            game = new Game(renderer);
            winner = game.run(player1, player2);
        }
        System.out.println("the winner is = " + winner);
    }

    public static void main(String[] args) {
        Tournament tournament = new Tournament();
        tournament.playTournament();

    }
}
