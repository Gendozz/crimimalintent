package com.genmodapps.appsforfun.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Asus on 11.03.2018.
 */

public class Crime {

    private UUID id;
    private String title;
    private Date date;
    private Date time;
    private boolean solved;
    private String suspect;

    //Здесь начинается задание
    private boolean requiresPolice;

    public Crime(){
        this(UUID.randomUUID());
    }

    public Crime(UUID id){
        this.id = id;
        this.date = new Date();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public boolean isRequiresPolice() {
        return requiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        this.requiresPolice = requiresPolice;
    }
}
