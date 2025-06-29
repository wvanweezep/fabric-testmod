package com.wvanw.testmod.mixin;

import com.wvanw.testmod.behavior.StatusEffect;
import com.wvanw.testmod.behavior.StatusEffectable;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LivingEntity.class)
public class LivingEntityMixin implements StatusEffectable {


    @Override
    public double getStatusBuild(StatusEffect status) {
        return 0;
    }

    @Override
    public void tickStatusBuild(StatusEffect status) {

    }
}
