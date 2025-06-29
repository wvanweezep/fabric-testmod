package com.wvanw.testmod.behavior.modstatus;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;

import java.util.function.Consumer;

/**
 * Status effect that can be applied to a LivingEntity
 * <p>
 * Possible Status Effects are:
 * <ul>
 *     <li>{@code POISON} - passively damage the entity;</li>
 *     <li>{@code FROSTBITE} - increased damage from attack;</li>
 *     <li>{@code HEMORRHAGE} - percentage-based damage;</li>
 *     <li>{@code SLEEP} - entity falls asleep and is unable to act;</li>
 *     <li>{@code DEATH} - entity dies on proc.</li>
 * </ul>
 */
public enum ModStatusEffect {
    POISON(entity -> {
        entity.addStatusEffect(new StatusEffectInstance(
                StatusEffects.POISON, 200, 0));
    }),

    FROSTBITE(entity -> {

    }),

    HEMORRHAGE(entity -> {
        if (!entity.getWorld().isClient && entity.getWorld() instanceof ServerWorld serverWorld) {
            entity.damage(serverWorld, entity.getDamageSources().magic(),
                    entity.getMaxHealth() * .15F + 2);
        }
    }),

    SLEEP(entity -> {

    }),

    DEATH(entity -> {
        if (!entity.getWorld().isClient && entity.getWorld() instanceof ServerWorld serverWorld) {
            entity.kill(serverWorld);
        }
    });

    private final Consumer<LivingEntity> effectFunction;

    ModStatusEffect(Consumer<LivingEntity> effectFunction) {
        this.effectFunction = effectFunction;
    }

    public void apply(LivingEntity entity) {
        effectFunction.accept(entity);
    }
}
