package com.gfg;

public abstract class Car implements IndGovtNorms, EUNorms{

    abstract void startEngine();

    @Override
    public String getInsurance() {
        return null;
    }

    @Override
    public String getPUC() {
        return null;
    }

    @Override
    public String getEnvtPolutionLevel() {
        return null;
    }
}
