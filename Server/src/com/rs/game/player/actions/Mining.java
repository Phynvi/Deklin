package com.rs.game.player.actions;

import com.rs.game.player.Player;

/*
 * TODO: implement mining
 */
public class Mining extends Action {


    //TODO: find out the actual model id
    public static final int EMPTY_ORE_ROCK = 1;
    public enum Rocks {
        // level, time in seconds or ticks? til refresh, xp granted
        IRON(1, 5, 500.0),
        COPPER(1, 5, 500.0),
        COAL(1, 5, 500.0);

        public int levelRequired;
        public int life;
        public double xpGranted;

        Rocks(int levelRequired, int life, double xpGranted) {
            this.levelRequired = levelRequired;
            this.life = life;
            this.xpGranted = xpGranted;
        }
    }

    @Override
    public boolean start(Player player) {

        // 1. position player infront of rock
        // 2. check if player has appropriate pick axe for level and rock in inventory or equipped.
        // 2.1 if the player can't mine then send a helpful message and return
        // 2.2 if the player can mine the rock, then start pickaxe animation etc
        return false;
    }

    @Override
    public boolean process(Player player) {
        // return true if the player has free inventory space for ores
        return false;
    }

    @Override
    public int processWithDelay(Player player) {
        return 0;
    }

    @Override
    public void stop(Player player) {

    }
}
