package com.rs.game.player.content.commands;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.actionHandling.Handler;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.content.Notes;
//import com.rs.game.player.content.interfaces.Teleportation;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.impl.base.Banker;
import com.rs.game.player.dialogues.impl.base.GearSelection;
import com.rs.game.player.dialogues.impl.base.SimpleMessage;
import com.rs.game.player.dialogues.impl.base.TestDialogue;
import com.rs.game.player.info.RanksManager;
import com.rs.game.player.quests.QuestHandler;
import com.rs.game.player.quests.QuestManager;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.game.world.Region;
import com.rs.game.world.World;
import com.rs.game.world.WorldObject;
import com.rs.utils.stringUtils.TextUtils;
import javafx.beans.property.SimpleMapProperty;

import java.util.ArrayList;
import java.util.List;

import static com.rs.game.actionHandling.HandlerManager.HandlerConstants.*;
import static com.rs.game.actionHandling.HandlerManager.registerDeveloperCommand;
import static com.rs.game.actionHandling.HandlerManager.registerInterfaceAction;
import static com.rs.game.actionHandling.HandlerManager.registerNpcDialogue;

/**
 * Created by Peng on 25.11.2016 15:22.
 */
public class Developer implements Handler {
    @Override
    public void register() {
        registerDeveloperCommand((player, command, params) -> {
            player.getPoison().makePoisoned(200);
            return RETURN;
        }, "makepoisoned");

        registerDeveloperCommand((player, command, params) -> {
            for (RanksManager.Ranks rank : RanksManager.Ranks.values()) {
                player.sendMessage(rank.getMessageStart(player) + " test");
            }
            return RETURN;
        }, "listranks");

        registerDeveloperCommand((player, command, params)->{
            //Teleportation.openTeleportationInterface(player);
            return RETURN;
        },"resendteleinter");

        registerDeveloperCommand((player, command, params) -> {
            try {
                for (int i = Integer.valueOf(params[0]); i < Integer.valueOf(params[1]); i++)
                    player.sendMessage("<img=" + i + ">");
            } catch (NumberFormatException nfe) {
                player.sendMessage("Invalid number input");
            }
            return RETURN;
        }, "listcrowns");

        registerDeveloperCommand((player, command, params) -> {
            Region region = World.getRegion(player.getRegionId());
            for (WorldObject object : region.getSpawnedObjects()) {
                player.sendMessage(object.toString());
            }
            return RETURN;
        }, "listWorldObjects");

        registerDeveloperCommand((player, command, params) -> {
            System.out.println(ItemDefinitions.getItemDefinitions(Integer.valueOf(params[0])).toString());
            return RETURN;
        }, "printitemdefs");

        registerDeveloperCommand((player, command, params) -> {
            System.out.println(NPCDefinitions.getNPCDefinitions(Integer.valueOf(params[0])).toString());
            return RETURN;
        }, "printnpcdefs");

        registerDeveloperCommand((player, command, params) -> {
            QuestHandler.sendConfigs(player);
            return RETURN;
        }, "testquests");

        registerDeveloperCommand((player, command, params) -> {
            player.getPackets().sendGlobalConfig(Integer.valueOf(params[0]), Integer.valueOf(params[1]));
            return RETURN;
        }, "globalconfig");

        registerDeveloperCommand((player, command, params) -> {
            int[] arr = new int[Math.abs(params.length - 4)];
            for(int i = 4; i < params.length;i++) {
                arr[i] = Integer.valueOf(params[i]);
            }
            player.getPackets().sendUnlockIComponentOptionSlots(Integer.valueOf(params[0]), Integer.valueOf(params[1]), Integer.valueOf(params[2]), Integer.valueOf(params[3]), arr);
            return RETURN;
        }, "sendUnlockIComponentOptionSlots".toLowerCase());

        registerDeveloperCommand((player, command, params) -> {
            Notes.addNote(player, new StringBuilder(TextUtils.combine(params)));
            return RETURN;
        }, "addnote");

        registerDeveloperCommand((player, command, params) -> {
            QuestHandler.showQuestInterface(player);
            return RETURN;
        }, "testquest");

        registerInterfaceAction(CLICK_1, (player, componentId, slotId, slotId2, buttonId, interfaceId) -> {
            if (componentId == 32) QuestHandler.showQuestInterface(player);
            return DEFAULT;
        }, 473);

        registerDeveloperCommand((player, command, params) -> {
            int startInter = 1;
            if (params.length > 0) startInter = Integer.valueOf(params[0]);
            int finalStartInter = startInter;
            WorldTasksManager.schedule(new WorldTask() {
                int currentInter = finalStartInter;

                @Override
                public void run() {
                    if (player.hasFinished()) stop();
                    player.sendMessage("Inter: " + currentInter);
                    player.getInterfaceManager().sendInterface(currentInter++);
                }
            }, 1, 1);
            return RETURN;
        }, "loopinterfaces");

        registerDeveloperCommand((player, command, params) -> {
            Dialogue testdialogue = new TestDialogue();
            player.getDialogueManager().startDialogue(testdialogue);
            return RETURN;
        }, "startdialogue");

        registerDeveloperCommand((player, command, params) -> {
            Dialogue gearselection = new GearSelection();
            player.getDialogueManager().startDialogue(gearselection);
            return RETURN;
        }, "gear");

        registerDeveloperCommand((player, command, params) -> {
            if (params.length + 1 < 3) {
                player.getPackets().sendPanelBoxMessage("Use: config id value");
                return RETURN;
            }
            try {
                player.getPackets().sendConfigByFile(Integer.valueOf(params[0]), Integer.valueOf(params[1]));
            } catch (NumberFormatException e) {
                player.getPackets().sendPanelBoxMessage("Use: config id value");
            }
            return RETURN;
        }, "configbyfile");

        registerDeveloperCommand((player, command, params) -> {
            QuestManager q = player.getQuestManager();
            int a =(q.getStage(Integer.parseInt(params[0])));
            player.getPackets().sendMessage(101,String.valueOf(a), player);
            return RETURN;
        }, "questgetstate");

        registerDeveloperCommand((player, command, params) -> {
            QuestManager q = player.getQuestManager();
            q.setStage(Integer.parseInt(params[0]), Integer.parseInt(params[1]));
            return RETURN;
        }, "questsetstate");

        registerDeveloperCommand((player, command, params) -> {
            for (Item item : player.getInventory().getItems().getItems()) {
                if (item != null) System.out.print("new Item(" + item.getId() + "),");
            }
            return RETURN;
        }, "printinventoryasset");

        registerDeveloperCommand((player, command, params) -> {
            System.out.println("Inventory:");
            for (Item item : player.getInventory().getItems().getItems()) {
                if (item != null) System.out.print("new Item(" + item.getId() + "," + item.getAmount() + "),");
            }
            System.out.println("Equipment:");
            for (Item item : player.getEquipment().getItems().getItems()) {
                if (item != null) System.out.print("new Item(" + item.getId() + "," + item.getAmount() + "),");
            }
            return RETURN;
        }, "printfullset");

        registerDeveloperCommand((player, command, params) -> {
            int startInter = 1;
            if (params.length > 0) startInter = Integer.valueOf(params[0]);
            int finalStartInter = startInter;
            WorldTasksManager.schedule(new WorldTask() {
                int currentTab = finalStartInter;

                @Override
                public void run() {
                    if (player.hasFinished()) stop();
                    player.getPackets().closeInterface(currentTab);
                    player.sendMessage("Tab: " + currentTab);
                    player.getInterfaceManager().sendTab(currentTab++, 754);
                }
            }, 1, 1);
            return RETURN;
        }, "loopinterontab");

        registerDeveloperCommand((player, command, params) -> {
            try {
                for (int i = 0; i < Integer.valueOf(params[2]); i++) {
                    player.getPackets().sendItemOnIComponent(Integer.valueOf(params[0]),
                            Integer.valueOf(params[1]) + i, 1, i);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                player.sendMessage("Invalid params");
            }
            return RETURN;
        }, "testiteminter");
    }
}
