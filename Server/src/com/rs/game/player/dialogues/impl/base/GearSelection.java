package com.rs.game.player.dialogues.impl.base;

import com.rs.game.player.dialogues.Dialogue;

public class GearSelection extends Dialogue {

    @Override
    public void start() {
        sendText("Warning!                This command will reset your inventory and worn equipment.");
    }

    @Override
    public void run(int interfaceId, int componentId) {
        if (stage == -1) {
            stage = 0;
            sendOptions("Choose account build", "Pure", "Voider", "Zerk", "Main");
        } else if (stage == 0) {
            stage = 1;
            player.sendMessage("You chose option: "  +  componentId);
        } else if (stage == 1) {
            end();
        }

    }

    @Override
    public void finish() {

    }
}
