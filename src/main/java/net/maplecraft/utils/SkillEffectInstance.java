package net.maplecraft.utils;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class SkillEffectInstance {
    public String skillName;
    public int animeCount = 0;
    public int currentAnime = -1;
    public float tick = -1;
    public int delay = 0;
    public boolean hitEffectOnHit = true;
    public float tickPerFrame = 2F;
    public int textureWidth;
    public int textureHeight;
    public boolean fixedPosition = true;
    public List<Vec3> renderPos;
    public List<LivingEntity> targets;

    public SkillEffectInstance(SkillEffectInstance other) {
        this.skillName = other.skillName;
        this.animeCount = other.animeCount;
        this.currentAnime = other.currentAnime;
        this.tick = other.tick;
        this.delay = other.delay;
        this.hitEffectOnHit = other.hitEffectOnHit;
        this.tickPerFrame = other.tickPerFrame;
        this.textureWidth = other.textureWidth;
        this.textureHeight = other.textureHeight;
        this.fixedPosition = other.fixedPosition;
    }

    public SkillEffectInstance() {}

    public SkillEffectInstance skillName(String s) {
        this.skillName = s;
        return this;
    }

    public SkillEffectInstance animeCount(int i) {
        this.animeCount = i;
        return this;
    }

    public SkillEffectInstance delay(int i) {
        this.delay = i;
        return this;
    }

    public SkillEffectInstance textureSize(int w, int h) {
        this.textureWidth = w;
        this.textureHeight = h;
        return this;
    }

    public SkillEffectInstance hitEffectOnHit(boolean b) {
        this.hitEffectOnHit = b;
        return this;
    }

    public SkillEffectInstance tickPerFrame(float f) {
        this.tickPerFrame = f;
        return this;
    }

    public SkillEffectInstance fixedPosition(boolean b) {
        this.fixedPosition = b;
        return this;
    }
}