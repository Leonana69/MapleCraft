package net.maplecraft.items;

import net.maplecraft.init.TabsInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;

import java.util.List;

public class EtcMesoTinyItem extends Item {
    public EtcMesoTinyItem() {
        super(new Item.Properties().tab(TabsInit.TAB_MAPLE_CRAFT)
                .stacksTo(64)
                .rarity(Rarity.COMMON));
    }

    @Override
    public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
        super.appendHoverText(itemstack, world, list, flag);
        list.add(Component.translatable("item.maplecraft.etc_meso_tiny_description"));
    }
}