package net.maplecraft.item.skill;

import net.maplecraft.utils.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SkillThunderBolt extends SkillItem {
    public static String itemName = "skill_thunder_bolt";
    public static int skillID = 2201005;
    public SkillThunderBolt() {
        super(itemName,
                new SkillBaseData()
                        .skillID(skillID)
                        .damage(120)
                        .delay(10)
                        .jobReq(JobCategory.ICE_LIGHTNING)
                        .weaponReq(EquipCategory.WAND)
                        .manaCost(4),
                new SkillHitEffect()
                        .skillName(itemName)
                        .animeCount(8)
                        .delay(10)
                        .textureSize(68, 219));
    }

    @Override
    public void skillEffect(Player player) {
        if (!player.level.isClientSide) {
            List<LivingEntity> target = getEntitiesInFrontOfPlayer(player, 5, 0);
            if (!target.isEmpty()) {
                scheduleDamage(player, target);
            }
        }
    }
}