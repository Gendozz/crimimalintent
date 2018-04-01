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

    //Здесь начинается задание
    private boolean requiresPolice;

    public Crime(){
        id = UUID.randomUUID();
        date = new Date();
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

    public boolean isRequiresPolice() {
        return requiresPolice;
    }

    public void setRequiresPolice(boolean requiresPolice) {
        this.requiresPolice = requiresPolice;
    }
}
