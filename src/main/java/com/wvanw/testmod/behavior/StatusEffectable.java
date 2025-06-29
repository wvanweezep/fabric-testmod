package com.wvanw.testmod.behavior;

public interface StatusEffectable {

    public double getStatusBuild(StatusEffect status);
    public void tickStatusBuild(StatusEffect status);
}
