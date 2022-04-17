package com.gfg;

public interface IndGovtNorms  extends EnvtContract{

    String getRC();

    String getInsurance();

    String getPUC();

//    String getLiveLocation();

    default String getLiveLocation(){
        return "";
    }

}
