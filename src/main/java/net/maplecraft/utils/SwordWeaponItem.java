package net.maplecraft.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class SwordWeaponItem extends BaseEquipItem {
    public float attackSpeed;

    public SwordWeaponItem(Properties itemProperties, BonusStats bs) {
        super(itemProperties, EquipCategory.SWORD, bs);
    }

    @Override
    public boolean canAttackBlock(BlockState blockState, Level world, BlockPos blockPos, Player player) {
        return !player.isCreative();
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, BlockState blockState) {
        return blockState.is(Blocks.COBWEB) ? 15.0F : 1.5F;
    }


}
