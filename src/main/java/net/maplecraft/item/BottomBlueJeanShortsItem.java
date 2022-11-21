package net.maplecraft.item;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.EquipCategory;
import net.maplecraft.utils.MapleArmorItem;
import net.minecraft.world.item.crafting.Ingredient;

public class BottomBlueJeanShortsItem extends MapleArmorItem {
    public static String itemName = "bottom_blue_jean_shorts";
    public BottomBlueJeanShortsItem() {
        super(itemName,
                50,
                new EquipBaseData().category(EquipCategory.BOTTOM)
                        .addStat("ARMOR", 2),
                () -> { return Ingredient.of(ItemsInit.ETC_MESO_TINY.get()); });
    }
}