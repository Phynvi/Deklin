package com.rs.game.player.dialogues.impl.base;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Gear;

public class GearSelection extends Dialogue {


    @Override
    public void start() {
        sendText("Warning! This command will reset your inventory and worn equipment.");
    }

    @Override
    public void run(int interfaceId, int componentId) {
        if (stage == -1) {
            stage = 0;
            sendOptions("Choose Account Build", "Pure", "Zerk", "Main");
        } else if (stage == 0) {
            switch(componentId) {
                case 1: //pure
                    stage = 1;
                    sendOptions("Choose Pure Type", "Ranged 2h", "Nh", "Coming soon...");
                    break;
                case 2: //Zerk
                    stage = 2;
                    sendOptions("Choose Zerk Type", "Classic", "Hybrid", "Ranged", "Ranged (void)");
                    break;
                case 3: //Main
                    stage = 3;
                    sendOptions("Choose Main Type", "Dharok's", "Hybrid", "Rags");
                    break;
                default:
                    end();
                    break;
            }
        } else if (stage == 1) {
            //Pure types
            switch(componentId) {
                case 1: //Ranged 2h
                    set_pure_opt1();
                    break;
                case 2: //Nh
                    set_pure_opt2();
                    break;
                case 3: //TODO: think of something else, f2p maybe?
                    set_pure_opt3();
                    break;
                default:
                    end();
                    break;
            }
            end();
        } else if (stage == 2) {
            //Zerk types
            switch(componentId) {
                case 1: //Classic zerker loadout, random spec wep etc
                    set_zerk_opt1();
                    break;
                case 2: //hybrid zerker. mystics etc
                    set_zerk_opt2();
                    break;
                case 3: // ranged zerker. torso and dhide chaps imo tbh
                    set_zerk_opt3();
                    break;
                case 4: // void ranged zerker stats
                    set_zerk_opt4();
                    break;
                default:
                    end();
                    break;
            }
            end();
        } else if (stage == 3) {
            // Main types
            switch(componentId) {
                case 1: // dharoks
                    set_main_opt1();
                    break;
                case 2: // hybrid
                    set_main_opt2();
                    break;
                case 3: // rags
                    set_main_opt3();
                    break;
            }
            end();
        } else {
            end();
        }

    }

    //
    // The below functions will call functions defined in another file to prevent this one from becoming HUGE.
    //


    // dharoks
    void set_main_opt1() {}
    // hybrid
    void set_main_opt2() {}
    // rags
    void set_main_opt3() {
        // All of this is testing. WIP
        Gear.setEquip(player, Equipment.SLOT_HAT, 1044, false);
        Gear.setEquip(player, Equipment.SLOT_HANDS, 7462, false);
        player.getInventory().reset();
        player.getInventory().addItem(new Item(995, 10000));
        player.getInventory().refresh();
        //player.getEquipment().refresh(Equipment.SLOT_HAT);
        //player.getAppearance().resetAppearance();
        player.reset();
    }

    // classic zerker
    void set_zerk_opt1() {}
    // hybrid
    void set_zerk_opt2() {}
    // ranged zerker
    void set_zerk_opt3() {}
    // void ranged zerker
    void set_zerk_opt4() {}

    //ranged 2h
    void set_pure_opt1() {}
    // Nh
    void set_pure_opt2() {}
    // idk
    void set_pure_opt3() {}


    @Override
    public void finish() {

    }
}
