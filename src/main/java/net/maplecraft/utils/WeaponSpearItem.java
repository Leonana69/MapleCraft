package net.maplecraft.utils;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class WeaponSpearItem extends WeaponItem {
    public WeaponSpearItem(EquipBaseData data) {
        super(data.category(EquipCategory.SPEAR));
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, Level world, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SWORD_ACTIONS.contains(toolAction);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity p_43279_, LivingEntity player) {
        itemStack.hurtAndBreak(1, player, (e) -> {
            e.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

//    @Override
//    public String getSwingSound() {
//        return "maplecraft:sound_spear_attack";
//    }
}