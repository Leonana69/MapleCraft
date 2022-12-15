package net.maplecraft.item.use;

import net.maplecraft.entity.projectile.DiamondArrowForBowEntity;
import net.maplecraft.item.MapleItemProperties;
import net.maplecraft.entity.MapleProjectileEntity;
import net.maplecraft.item.MapleProjectileItem;
import net.maplecraft.utils.MapleRarity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class UseDiamondArrowForBowItem extends MapleProjectileItem {
    public static String itemName = "use_diamond_arrow_for_bow";
    public UseDiamondArrowForBowItem() {
        super(new MapleItemProperties()
                .itemName(itemName)
                .mapleRarity(MapleRarity.UNIQUE));
        this.bonusDamage = 3;
    }

    @Override
    public MapleProjectileEntity createArrow(Level world, LivingEntity entity) {
        return new DiamondArrowForBowEntity(world, entity);
    }
}