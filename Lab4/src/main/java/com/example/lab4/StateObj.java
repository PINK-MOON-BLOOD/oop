package com.example.lab4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class StateObj implements Serializable, Cloneable{
    private static String stateObj;

    public StateObj(String stateObj) {
    }

    public StateObj() {

    }

    public String getStateObj() {

        return stateObj;
    }

    public void setStateObj(int Health) {
        if (Health >= 85) {
            stateObj = "Full Health";
        } else if (Health >= 20) {
            stateObj = "Mid Health";
        } else {
            stateObj = "Low Health";
        }
    }

    public static void updataST ( ArrayList<Puppet> puppe){
        for (Puppet st : puppe){
            if (st instanceof  Lead_of_Parade){
                if (st.getHealth()>= 85) {
                    st.stateObj.setStateObj(st.getHealth());
                } else if (st.getHealth() <= 20) {
                    st.stateObj.setStateObj(st.getHealth());
                   // stateObj = "Low Health";
                } else {
                    st.stateObj.setStateObj(st.getHealth());
                   // stateObj = "Mid Health";
                }
            } else if (st instanceof  Puppet_of_the_future) {
                if (st.getHealth()>= 85) {
                    st.stateObj.setStateObj(st.getHealth());
                    //stateObj = "Full Health";
                } else if (st.getHealth() <= 20) {
                    st.stateObj.setStateObj(st.getHealth());
                   // stateObj = "Low Health";
                } else {
                    st.stateObj.setStateObj(st.getHealth());
                    //stateObj = "Mid Health";
                }
            } else if (st instanceof Puppet) {
                if (st.getHealth()>= 85) {
                    st.stateObj.setStateObj(st.getHealth());
                    //stateObj = "Full Health";
                } else if (st.getHealth() <= 20) {
                    st.stateObj.setStateObj(st.getHealth());
                   // stateObj = "Low Health";
                } else {
                    st.stateObj.setStateObj(st.getHealth());
                    //stateObj = "Mid Health";
                }
            }
        }
    }


 protected StateObj clone() throws CloneNotSupportedException{

  return (StateObj) super.clone();
 }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateObj stateObj1)) return false;
        return Objects.equals(getStateObj(), stateObj1.getStateObj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStateObj());
    }

//    public int compareTo(StateObj stateObj) {
//        if(this.stateObj > stateObj.stateObj) return 1;
//        if(this.stateObj < stateObj.stateObj) return -1;
//        else return 0;
//    }


}
