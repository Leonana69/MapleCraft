package net.maplecraft.item;

import net.maplecraft.utils.EquipBaseData;
import net.maplecraft.utils.WeaponSwordItem;

public class SwordMapleSwordItem extends WeaponSwordItem {
    public SwordMapleSwordItem() {
        super(new EquipBaseData()
                .levelReq(15)
                .addStat("ATTACK", 8)
                .addStat("ATTACK_SPEED", 2));
    }
}
