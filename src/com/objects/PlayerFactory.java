package com.objects;

import API.Player;

public class PlayerFactory {

    public Player buildPlayer(String player){
        return switch (player) {
            case "human" -> new HumanPlayer();
            case "HardPlayer" -> new HardPlayer();
            case "EasyPlayer" -> new EasyPlayer();
            default -> null;
        };
    }
}
