package net.maplecraft.utils;

import net.maplecraft.init.ItemsInit;
import net.maplecraft.init.TabsInit;
import net.maplecraft.item.UseArrowForBowItem;
import net.maplecraft.item.UseBronzeArrowForBowItem;
import net.maplecraft.item.UseDiamondArrowForBowItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.Objects;

import static net.minecraft.world.entity.ai.attributes.Attributes.ATTACK_DAMAGE;

public class WeaponBowItem extends WeaponItem {
    public boolean setUsingAnime = false;
    /* typical projectile damage is proportional to power * damage */
    // affect projectile damage, here we use BaseEquipItem.baseStats.values.get(1) // attack
    // public float damage = 5.0F;
    // affect projectile damage and speed
    public static float power = 3.0F;
    // affect accuracy, 0.0F means precise
    public static float accuracy = 0.5F;

    public WeaponBowItem(EquipBaseData data) {
        super(data.category(EquipCategory.BOW));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack itemstack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack itemstack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (!player.getAbilities().instabuild && findAmmo(player).isEmpty()) {
            return InteractionResultHolder.fail(itemStack);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.success(itemStack);
        }
    }

    @Override
    public void releaseUsing(ItemStack itemStack, Level world, LivingEntity livingEntity, int timeLeft) {
        if (!world.isClientSide() && livingEntity instanceof ServerPlayer player) {
            ItemStack ammoStack = findAmmo(player);

            if (!ammoStack.isEmpty() || player.getAbilities().instabuild) {
                int duration = this.getUseDuration(itemStack) - timeLeft;
                float powerScale = getPowerForTime(duration);

                if (powerScale >= 0.1) {
                    if (ammoStack.isEmpty()) {
                        ammoStack = new ItemStack(ItemsInit.USE_ARROW_FOR_BOW.get());
                    }

                    MapleProjectileItem ammoItem = (MapleProjectileItem) ammoStack.getItem();
                    AbstractArrow ammoEntity = ammoItem.createArrow(world, player);

                    ammoEntity.shoot(player.getViewVector(1).x, player.getViewVector(1).y, player.getViewVector(1).z, power * powerScale, accuracy);
                    ammoEntity.setBaseDamage((player.getAttributeValue(ATTACK_DAMAGE) * 1.2 + ammoItem.bonusDamage) / power);
                    if (powerScale > 0.6)
                        ammoEntity.setKnockback(1);

                    world.addFreshEntity(ammoEntity);

                    itemStack.hurtAndBreak(1, player, e -> e.broadcastBreakEvent(player.getUsedItemHand()));

                    if (!player.getAbilities().instabuild) {
                        ammoStack.shrink(1);
                        if (ammoStack.isEmpty()) {
                            player.getInventory().removeItem(ammoStack);
                        }
                    }

                    world.playSound(null, player.getX(), player.getY(), player.getZ(),
                            Objects.requireNonNull(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("maplecraft:sound_attack_bow"))),
                            SoundSource.PLAYERS, 1, 1.0F / (world.getRandom().nextFloat() * 0.1F + 1.6F) + powerScale * 0.4F);
                }
            }
        }
    }

    public static ItemStack findAmmo(Player player) {
        if (isValidProjectile(player.getItemInHand(InteractionHand.OFF_HAND).getItem())) {
            return player.getItemInHand(InteractionHand.OFF_HAND);
        } else if (isValidProjectile(player.getItemInHand(InteractionHand.MAIN_HAND).getItem())) {
            return player.getItemInHand(InteractionHand.MAIN_HAND);
        } else {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack itemStack = player.getInventory().getItem(i);
                if (isValidProjectile(itemStack.getItem())) {
                    return itemStack;
                }
            }
            return ItemStack.EMPTY;
        }
    }

    public static boolean isValidProjectile(Item item) {
        return item instanceof UseArrowForBowItem
                || item instanceof UseBronzeArrowForBowItem
                || item instanceof UseDiamondArrowForBowItem;
    }

    public static float getPowerForTime(int time) {
        float f = (float)time / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        return Math.min(f, 1.0F);
    }
}

