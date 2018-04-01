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
        for (int i = 0; i < 100; i++) {
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0); // Для каждого второго объекта
            crime.setRequiresPolice(i % 3 == 0); // Для каждого третьего объекта
            crimes.add(crime);
        }
        crimes.get(0).setTitle("Test");
        crimes.get(0).setDate(new Date(2018, 5, 12));

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
