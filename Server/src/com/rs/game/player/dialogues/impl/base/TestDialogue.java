package com.rs.game.player.dialogues.impl.base;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.FaceAnimations;


public class TestDialogue extends Dialogue {

    int npcId;
    @Override
    public void start() {
        npcId = 1;
        sendNpcDialogue(NPCDefinitions.getNPCDefinitions(npcId).name, "TEST DIALOGUE NPCID = 1", npcId, 9827);
    }

    @Override
    public void run(int interfaceId, int componentId) {
        if (stage == -1) {
            stage = 0;
            sendOptions("opt1" , "opt2",
                    "opt3","opt4" ,"opt5", "opt6");
        } else if (stage == 0) {
            if (componentId == 1) {
                end();
            } else if (componentId == 2) {
                end();
            } else if (componentId == 3) {
                end();
            } else if (componentId == 4) {
                stage = 1;
                sendPlayerDialogue(player.getDisplayName(), "Response", player.getIndex(), FaceAnimations.ANGRY);
            } else end();
        } else if (stage == 1) {
            stage = 2;
            sendNpcDialogue(NPCDefinitions.getNPCDefinitions(npcId).name,
                    "This is branch of the Bank of " + Settings.SERVER_NAME
                            + ". We have branches in many towns.", npcId, 9827);
        } else if (stage == 2) {
            stage = 3;
            sendOptions("What would you like to say?", "And what do you do?",
                    "Didnt you used to be called the " + "Bank of Varrock?");
        } else if (stage == 3) {
            end();
        } else {end();}
    }
    @Override
    public void finish() {

    }

}
