package com.wvanw.testmod.mixin;

import com.wvanw.testmod.behavior.modstatus.ModStatusEffect;
import com.wvanw.testmod.behavior.modstatus.ModStatusEffectable;
import com.wvanw.testmod.behavior.modstatus.ModStatusState;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.EnumMap;
import java.util.Map;

@Mixin(LivingEntity.class)
public class LivingEntityStatusEffectMixin implements ModStatusEffectable {

    private final Map<ModStatusEffect, ModStatusState> statusEffects = new EnumMap<>(ModStatusEffect.class);

    @Inject(method = "<init>", at = @At("TAIL"))
    private void onInitTail(CallbackInfo info) {
        statusEffects.put(ModStatusEffect.POISON, new ModStatusState());
        statusEffects.put(ModStatusEffect.FROSTBITE, new ModStatusState());
        statusEffects.put(ModStatusEffect.HEMORRHAGE, new ModStatusState());
        statusEffects.put(ModStatusEffect.SLEEP, new ModStatusState());
        statusEffects.put(ModStatusEffect.DEATH, new ModStatusState());
    }

    @Override
    public void setImmunity(ModStatusEffect status, boolean isImmune) {
        statusEffects.get(status).immune = isImmune;
    }

    @Override
    public double getStatusBuild(ModStatusEffect status) {
        if (!statusEffects.containsKey(status)) return -1;
        return statusEffects.get(status).buildup;
    }

    @Override
    public void addStatusBuild(ModStatusEffect status, double amount) {
        ModStatusState state = statusEffects.get(status);
        if (state == null || state.immune) return;

        state.buildup += amount;
        if (state.buildup >= state.resistance) {
            state.buildup = 0;
            status.apply((LivingEntity) (Object) this);
        }
    }

    @Override
    public void setStatusResistance(ModStatusEffect status, double resistance) {
        if (!statusEffects.containsKey(status)) return;
        statusEffects.get(status).resistance = resistance;
    }
}
