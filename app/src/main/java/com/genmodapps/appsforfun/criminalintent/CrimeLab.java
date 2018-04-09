package com.genmodapps.appsforfun.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.genmodapps.appsforfun.criminalintent.database.CrimeBaseHelper;
import com.genmodapps.appsforfun.criminalintent.database.CrimeCursorWrapper;
import com.genmodapps.appsforfun.criminalintent.database.CrimeDbSchema;
import com.genmodapps.appsforfun.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Asus on 13.03.2018.
 */

public class CrimeLab {

    private static CrimeLab crimeLab;
    private Context context;
    private SQLiteDatabase database;

    public static CrimeLab get(Context context){
        if(crimeLab == null){
            crimeLab = new CrimeLab(context);
        }
        return crimeLab;
    }

    private CrimeLab(Context context){
        this.context = context.getApplicationContext();
        database = new CrimeBaseHelper(this.context).getWritableDatabase();
    }

    public void addCrime(Crime crime){
        ContentValues values = getContentValues(crime);
        database.insert(CrimeTable.NAME, null, values);
    }

    public void deleteCrime(Crime crime){
        ContentValues values = getContentValues(crime);
        database.delete(CrimeTable.NAME, CrimeTable.Cols.UUID + " = ?", new String[]{crime.getId().toString()});
    }


    public List getCrimes(){
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );
        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime){
        String uuidString = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        database.update(CrimeTable.NAME, values,
                CrimeTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = database.query(
                CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        return values;
    }

}
