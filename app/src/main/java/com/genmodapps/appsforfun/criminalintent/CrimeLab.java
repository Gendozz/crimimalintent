package com.genmodapps.appsforfun.criminalintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Asus on 13.03.2018.
 */

public class CrimeLab {

    private static CrimeLab crimeLab;

    private List<Crime> crimes;

    public static CrimeLab get(Context context){
        if(crimeLab == null){
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    private CrimeLab(Context context){
        crimes = new ArrayList<>();
    }

    public void addCrime(Crime crime){
        crimes.add(crime);
    }

    public void deleteCrime(UUID id){
        crimes.remove(getCrime(id));
    }

    public List getCrimes(){
        return crimes;
    }

    public Crime getCrime(UUID id){
        for (Crime crime : crimes) {
            if(crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
