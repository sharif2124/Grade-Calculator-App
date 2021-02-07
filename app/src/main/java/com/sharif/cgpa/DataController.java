package com.sharif.cgpa;

import com.sharif.cgpa.home.HomeFragment;
import com.sharif.cgpa.home.HomeFragmentInterface;
import com.sharif.cgpa.model.Semister;

public class DataController {
    public static DataController instance;
    public static DataController getInstance(){
        if(instance==null)
            instance=new DataController();
        return instance;
    }

    HomeFragmentInterface homeFragmentInterface;
    Semister currentSemister;




    // getter setter function
    public HomeFragmentInterface getHomeFragmentInterface() {
        return homeFragmentInterface;
    }

    public void setHomeFragmentInterface(HomeFragmentInterface homeFragmentInterface) {
        this.homeFragmentInterface = homeFragmentInterface;
    }

    public Semister getCurrentSemister() {
        return currentSemister;
    }

    public void setCurrentSemister(Semister currentSemister) {
        this.currentSemister = currentSemister;
    }
}
