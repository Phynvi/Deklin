package com.rs.utils;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;

public class Gear {


    //TODO: Collect bigger enums of all types of items.
    // then have functions such as "Item getRandomPureBody()" etc for an aesthetically pleasing,
    // diverse (in a good way) look, because everyone will be using slightly different gear.


    public enum Head {
        NEITIZNOT(12681), // charged version b/c it looks better imo
        BERSERKER(3751),
        VOID_RANGER(11664),

        SARADOMIN_MITRE(10452), //pure stuff here
        GUTHIX_MITRE(10454),
        ZAMORAK_MITRE(10456),
        ARMADYL_MITRE(19374),
        BANDOS_MITRE(19376),
        ANCIENT_MITRE(1938),
        GUTHIX_HALO(18744),
        SARADOMIN_HALO(18745),
        ZAMORAK_HALO(18746);


        int itemId;
        Head(int itemId) {
            this.itemId = itemId;
        }
    }

    public enum Foot {
        STEADFAST(21787),
        GLAIVEN(21790),
        RAGEFIRE(21793),
        DRAGON_BOOTS(11732),
        INFINITY_BOOTS(6920),
        GROUNDING_BOOTS(17283), // good for ragging
        CLIMBING_BOOTS(3105),
        WIZARD_BOOTS(2579);

        int itemId;
        Foot(int itemId) {
            this.itemId = itemId;
        }
    }

    public enum Ring {
        BERSERKERS_RING(15220),
        SEERS_RING(15018),
        ARCHERS_RING(15019),
        ONYX_RING(15017); //TODO: add and implement ring of recoil ?

        int itemId;
        Ring(int itemId) {
            this.itemId = itemId;
        }
    }

    public enum Hand {
        MITHRIL_GLOVES(7458),
        BARROWS_GLOVES(7462),
        VOID_GLOVES(8842);

        int itemId;
        Hand(int itemId) {
            this.itemId = itemId;
        }
    }

    public enum Cape {
        SARADOMIN_CAPE(2412),
        GUTHIX_CAPE(2413),
        ZAMORAK_CAPE(2414),
        FIRE_CAPE(6570),
        AVAS(20068);

        int itemId;
        Cape(int itemId) {
            this.itemId = itemId;
        }
    }

    public enum Neck {
        GLORY(1712),
        FURY(6582),
        ARCANE_STREAM(18335),
        STRENGTH(1725);

        int itemId;
        Neck(int itemId) {
            this.itemId = itemId;
        }
    }

    public enum Food {
        SARADOMIN_BREW(6685),
        SUPER_RESTORE(3024),
        SANFEW_SERUM(3024), //Because sanfew does not work atm.
        ROCKTAIL(15272),
        SUPER_ATTACK(2436),
        SUPER_STRENGTH(2440);

        int itemId;
        Food(int itemId) {
            this.itemId = itemId;
        }
    }


    // updates player level in a skill
    public static void setLevel(Player p, int skill, int level) {
        p.getSkills().set(skill, level);
    }

    // sets an item in given equipment slot
    // if stackable, give 10k bolts/arrows
    public static void setEquip(Player p, int slot, int itemId, boolean stackable) {
        if (!stackable)
            p.getEquipment().getItems().set(slot, new Item(itemId));
        else
            p.getEquipment().getItems().set(slot, new Item(itemId, 10000));
        p.getEquipment().refresh(slot);
    }

    // resets and fills inventory
    public static void setInventory(Player p, ItemsContainer itemsContainer) {
        // reset inventory
        p.getInventory().reset();
        // adds pre-defined inventory
        p.getInventory().addAll(itemsContainer);
    }
}
