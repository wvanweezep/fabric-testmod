package com.wvanw.testmod.behavior.modstatus;

import java.util.List;

public interface ModStatusEffectable {

    /**
     * Sets the immunity for a certain {@link ModStatusEffect}.
     *
     * @param status the StatusEffect to set immunity for
     * @param isImmune state of immunity for the StatusEffect
     */
    public void setImmunity(ModStatusEffect status, boolean isImmune);

    /**
     * Getter for the current status buildup for a certain {@link ModStatusEffect}.
     *
     * @param status the StatusEffect to check for
     * @return the current buildup for the requested StatusEffect.
     */
    public double getStatusBuild(ModStatusEffect status);

    /**
     * Adds buildup for to a certain {@link ModStatusEffect}.
     *
     * @param status the StatusEffect to add buildup to
     * @param amount the amount of buildup to add
     */
    public void addStatusBuild(ModStatusEffect status, double amount);

    /**
     * Sets a new resistance for a certain {@link ModStatusEffect}.
     *
     * @param status the StatusEffect to set a resistance for
     * @param resistance the new resistance value
     */
    public void setStatusResistance(ModStatusEffect status, double resistance);
}
