package com.katherinefreeman.queensdomdefender.card.deck;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;

import java.util.ArrayList;
import java.util.Arrays;

import static com.katherinefreeman.queensdomdefender.card.model.CardType.BUILDING;
import static com.katherinefreeman.queensdomdefender.card.model.CardType.CHARACTER;

public class Deck {

    public static ArrayList<Card> availableDeck = new ArrayList<>(Arrays.asList(
            new Card("Archer", R.drawable.character_card_09_archer, 3, 4, 6, CHARACTER),
            new Card("Army", R.drawable.character_card_13_army, 9, 8, 9, CHARACTER),
            new Card("Statue", R.drawable.character_card_15_statue, 5, 2, 0, CHARACTER),
            new Card("Skull", R.drawable.character_card_18_skull, 1, 1, 0, CHARACTER),
            new Card("Golem", R.drawable.character_card_38_golem, 5, 3, 4, CHARACTER),
            new Card("Wood Man", R.drawable.character_card_43_wood_man, 2, 1, 0, CHARACTER),
            new Card("Fire Monk", R.drawable.character_card_46_fire_monk, 7, 2, 1, CHARACTER),
            new Card("Villager", R.drawable.character_card_48_villager, 3, 1, 2, CHARACTER),
            new Card("Scarecrow", R.drawable.character_card_51_scarecrow, 2, 1, 0, CHARACTER),
            new Card("Fish", R.drawable.character_card_52_fish, 1, 1, 1, CHARACTER),
            new Card("Phoenix", R.drawable.character_card_57_phoenix, 4, 3, 7, CHARACTER),
            new Card("A. Knight", R.drawable.character_card_63_a_knight, 5, 3, 7, CHARACTER),
            new Card("Horse", R.drawable.character_card_66_horse, 2, 1, 3, CHARACTER),
            new Card("Wild Horse", R.drawable.character_card_67_wild_horse, 2, 1, 2, CHARACTER),
            new Card("H. Soldier", R.drawable.character_card_68_h_soldier, 3, 2, 3, CHARACTER),
            new Card("H. Knight", R.drawable.character_card_78_h_knight, 7, 4, 8, CHARACTER),
            new Card("Barbarian", R.drawable.character_card_82_barbarian, 9, 7, 9, CHARACTER),
            new Card("S. Knight", R.drawable.character_card_88_s_knight, 7, 3, 3, CHARACTER),
            new Card("Monk", R.drawable.character_card_91_monk, 2, 1, 1, CHARACTER),
            new Card("Cow Skull", R.drawable.character_card_92_cow_skull, 1, 2, 3, CHARACTER),
            new Card("Marcher", R.drawable.character_card_100_marcher, 3, 1, 2, CHARACTER),
            new Card("Familiar", R.drawable.character_card_154_familiar, 1, 2, 5, CHARACTER),
            new Card("Farmer", R.drawable.character_card_157_farmer, 2, 1, 2, CHARACTER),

            new Card("Gatehouse", R.drawable.building_card_19_gatehouse, 5, 1, 0, BUILDING),
            new Card("H. Castle", R.drawable.building_card_20_h_castle, 6, 2, 0, BUILDING),
            new Card("Cathedral", R.drawable.building_card_25_cathedral, 4, 3, 0, BUILDING),
            new Card("Watchtower", R.drawable.building_card_26_watchtower, 3, 2, 0, BUILDING),
            new Card("G. Castle", R.drawable.building_card_30_g_castle, 8, 4, 0, BUILDING),
            new Card("Mausoleum", R.drawable.building_card_37_mausoleum, 2, 1, 0, BUILDING),
            new Card("Big House", R.drawable.building_card_49_big_house, 3, 1, 0, BUILDING),
            new Card("House", R.drawable.building_card_70_house, 3, 1, 0, BUILDING),
            new Card("Nice House", R.drawable.building_card_72_nice_house, 4, 2, 0, BUILDING),
            new Card("Stables", R.drawable.building_card_75_stables, 1, 1, 0, BUILDING),
            new Card("Cavern", R.drawable.building_card_76_cavern, 6, 3, 0, BUILDING),
            new Card("Lighthouse", R.drawable.building_card_81_lighthouse, 2, 1, 0, BUILDING),
            new Card("Mineshaft", R.drawable.building_card_89_mineshaft, 2, 1, 0, BUILDING),
            new Card("S. Castle", R.drawable.building_card_95_s_castle, 8, 4, 0, BUILDING),
            new Card("Pyramid", R.drawable.building_card_105_pyramid, 4, 2, 0, BUILDING),
            new Card("Guard Post", R.drawable.building_card_109_guardpost, 3, 1, 0, BUILDING),
            new Card("Barn", R.drawable.building_card_110_barn, 2, 1, 0, BUILDING),
            new Card("Tent", R.drawable.building_card_133_tent, 1, 1, 0, BUILDING),
            new Card("Cave", R.drawable.building_card_140_cave, 5, 2, 0, BUILDING),
            new Card("Bank", R.drawable.building_card_144_bank, 4, 1, 0, BUILDING),
            new Card("Caravan", R.drawable.building_card_145_caravan, 2, 1, 0, BUILDING),
            new Card("Well", R.drawable.building_card_152_well, 1, 1, 0, BUILDING),
            new Card("Farmhouse", R.drawable.building_card_159_farmhouse, 3, 2, 0, BUILDING)
    ));

}
