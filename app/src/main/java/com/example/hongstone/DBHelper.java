package com.example.hongstone;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name , factory, version);

        db = getWritableDatabase();
    }

    private SQLiteDatabase db;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE MOVE " +
                "(ITEM_KEY INTEGER PRIMARY KEY AUTOINCREMENT " +
                ", tfcwkerMvmnCnterNm VARCHAR(100)" +
                ", rdnmadr VARCHAR(200)" +
                ", lnmadr VARCHAR(200)" +
                ", latitude VARCHAR(200)" +
                ", longitude VARCHAR(100)" +
                ", carHoldCo VARCHAR(100)" +
                ", carHoldKnd VARCHAR(100)" +
                ", slopeVhcleCo VARCHAR(100)" +
                ", liftVhcleCo VARCHAR(100)" +
                ", rceptPhoneNumber VARCHAR(100)" +
                ", rceptItnadr VARCHAR(100)" +
                ", appSvcNm VARCHAR(100)" +
                ", weekdayRceptOpenHhmm VARCHAR(100)" +
                ", weekdayRceptColseHhmm VARCHAR(100)" +
                ", wkendRceptOpenHhmm VARCHAR(100)" +
                ", wkendRceptCloseHhmm VARCHAR(100)" +
                ", weekdayOperOpenHhmm VARCHAR(100)" +
                ", weekdayOperColseHhmm VARCHAR(100)" +
                ", wkendOperOpenHhmm VARCHAR(100)" +
                ", wkendOperCloseHhmm VARCHAR(100)" +
                ", beffatResvePd VARCHAR(100)" +
                ", useLmtt VARCHAR(100)" +
                ", insideOpratArea VARCHAR(100)" +
                ", outsideOpratArea VARCHAR(100)" +
                ", useTrget VARCHAR(100)" +
                ", useCharge VARCHAR(100)" +
                ", institutionNm VARCHAR(100)" +
                ", phoneNumber VARCHAR(100)" +
                ", referenceDate VARCHAR(100)" +
                ", instt_code VARCHAR(100)" +
                ", instt_nm VARCHAR(100));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS MOVE");
        onCreate(db);
    }
    public void insert(String tfcwkerMvmnCnterNm, String rdnmadr, String lnmadr, String latitude, String longitude, String carHoldCo, String carHoldKnd, String slopeVhcleCo, String liftVhcleCo, String rceptPhoneNumber, String rceptItnadr, String appSvcNm, String weekdayRceptOpenHhmm, String weekdayRceptColseHhmm, String wkendRceptOpenHhmm, String wkendRceptCloseHhmm, String weekdayOperOpenHhmm, String weekdayOperColseHhmm, String wkendOperOpenHhmm, String wkendOperCloseHhmm, String beffatResvePd, String useLmtt, String insideOpratArea, String outsideOpratArea, String useTrget, String useCharge, String institutionNm, String phoneNumber, String referenceDate, String instt_code, String instt_nm){
        db.execSQL("INSERT INTO MOVE (tfcwkerMvmnCnterNm, rdnmadr, lnmadr, latitude, longitude, carHoldCo, carHoldKnd, slopeVhcleCo, liftVhcleCo, rceptPhoneNumber, rceptItnadr, appSvcNm, weekdayRceptOpenHhmm, weekdayRceptColseHhmm, wkendRceptOpenHhmm, wkendRceptCloseHhmm, weekdayOperOpenHhmm, weekdayOperColseHhmm, wkendOperOpenHhmm, wkendOperCloseHhmm, beffatResvePd, useLmtt, insideOpratArea, outsideOpratArea, useTrget, useCharge, institutionNm, phoneNumber, referenceDate, instt_code, instt_nm) VALUES('"+tfcwkerMvmnCnterNm+"', '"+rdnmadr+"', '"+lnmadr+"', '"+latitude+"', '"+longitude+"', '"+carHoldCo+"', '"+carHoldKnd+"', '"+slopeVhcleCo+"', '"+liftVhcleCo+"', '"+rceptPhoneNumber+"', '"+rceptItnadr+"', '"+appSvcNm+"', '"+weekdayRceptOpenHhmm+"', '"+weekdayRceptColseHhmm+"', '"+wkendRceptOpenHhmm+"', '"+wkendRceptCloseHhmm+"', '"+weekdayOperOpenHhmm+"', '"+weekdayOperColseHhmm+"', '"+wkendOperOpenHhmm+"', '"+wkendOperCloseHhmm+"', '"+beffatResvePd+"', '"+useLmtt+"', '"+insideOpratArea+"', '"+outsideOpratArea+"', '"+useTrget+"', '"+useCharge+"', '"+institutionNm+"', '"+phoneNumber+"', '"+referenceDate+"', '"+instt_code+"', '"+instt_nm+"');");
    }

    public void update(int itemKey, String tfcwkerMvmnCnterNm) {
        String update = "UPDATE MOVE SET GB_1 = '"+tfcwkerMvmnCnterNm+"' WHERE ITEM_KEY = "+itemKey;

        db.execSQL(update);
    }

    public void delete() {
        //db = getWritableDatabase();
        String delete = "DELETE FROM MOVE";
        db.execSQL(delete);
    }


    public SQLiteDatabase getDb(){
        //db = getReadableDatabase();
        return db;
    }

    //select * from move where rdnmadr in "seoul"
    //select id, lat, lon from move;
    //2070, 36.1231, 125.6323
    //2060,

    public ArrayList<Item> selectLatLng() {
        Cursor cursor = db.rawQuery("select latitude, longitude, tfcwkerMvmnCnterNm from MOVE;", null);
        ArrayList<Item> dataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setlatitude(cursor.getString(0));
            item.setlongitude(cursor.getString(1));
            item.settfcwkerMvmnCnterNm(cursor.getString(2));
            dataList.add(item);
        }
        return dataList;
    }

    public ArrayList<Item> selectdata(){
        Cursor cursor = db.rawQuery("SELECT tfcwkerMvmnCnterNm, phoneNumber, rdnmadr, rceptPhoneNumber, weekdayRceptOpenHhmm, weekdayRceptColseHhmm, wkendRceptOpenHhmm, wkendRceptCloseHhmm  FROM MOVE;", null);
        ArrayList<Item> dataList = new ArrayList<>();
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.settfcwkerMvmnCnterNm(cursor.getString(0));
            item.setphoneNumber(cursor.getString(1));
            item.setrdnmadr(cursor.getString(2));
            item.setrceptPhoneNumber(cursor.getString(3));
            item.setweekdayRceptOpenHhmm(cursor.getString(4));
            item.setweekdayRceptColseHhmm(cursor.getString(5));
            item.setwkendRceptOpenHhmm(cursor.getString(6));
            item.setwkendRceptCloseHhmm(cursor.getString(7));
            dataList.add(item);
        }
        return dataList;
    }

//    public Cursor select(String sql){
//        Cursor cursor = db.rawQuery(sql, null);
//        return cursor;
//    }

    public ArrayList<Item> selecta(String sql){
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<Item> dataList = new ArrayList<>();
        while (cursor.moveToNext()){
            Item item = new Item();
            item.settfcwkerMvmnCnterNm(cursor.getString(0));
            item.setlatitude(cursor.getString(1));
            item.setlongitude(cursor.getString(2));
            dataList.add(item);
        }
        return dataList;
    }




    public ArrayList<Item> selectAll(int start, int end) {
        //db = getReadableDatabase();
        String selectAll = "SELECT ITEM_KEY, tfcwkerMvmnCnterNm, rdnmadr, lnmadr, longitude, carHoldCo, carHoldKnd, slopeVhcleCo, liftVhcleCo, rceptPhoneNumber, rceptItnadr, appSvcNm, weekdayRceptOpenHhmm, weekdayRceptColseHhmm, wkendRceptOpenHhmm, wkendRceptCloseHhmm, weekdayOperOpenHhmm, weekdayOperColseHhmm, wkendOperOpenHhmm, wkendOperCloseHhmm, beffatResvePd, useLmtt, insideOpratArea, outsideOpratArea, useTrget, useCharge, institutionNm, phoneNumber, referenceDate, instt_code, instt_nm FROM MOVE LIMIT "+end+" OFFSET "+start;
        Cursor cursor = db.rawQuery(selectAll, null);
       // Cursor cursor = db.rawQuery("SELECT * FROM MOVE;", null);

            ArrayList<Item> dataList = new ArrayList<>();
            while (cursor.moveToNext()) {
                Item item = new Item();
                item.setItemKey(cursor.getInt(0));
                item.settfcwkerMvmnCnterNm(cursor.getString(1));
                item.setrdnmadr(cursor.getString(2));
                item.setlnmadr(cursor.getString(3));
//                item.setlongitude(cursor.getString(5));
//                item.setcarHoldCo(cursor.getString(6));
//                item.setcarHoldKnd(cursor.getString(7));
//                item.setslopeVhcleCo(cursor.getString(8));
//                item.setliftVhcleCo(cursor.getString(9));
//                item.setrceptPhoneNumber(cursor.getString(10));
//                item.setrceptItnadr(cursor.getString(11));
//                item.setappSvcNm(cursor.getString(12));
//                item.setweekdayRceptOpenHhmm(cursor.getString(13));
//                item.setweekdayRceptColseHhmm(cursor.getString(14));
//                item.setwkendRceptOpenHhmm(cursor.getString(15));
//                item.setwkendRceptCloseHhmm(cursor.getString(16));
//                item.setweekdayOperOpenHhmm(cursor.getString(17));
//                item.setweekdayOperColseHhmm(cursor.getString(18));
//                item.setwkendOperOpenHhmm(cursor.getString(19));
//                item.setwkendOperCloseHhmm(cursor.getString(20));
//                item.setbeffatResvePd(cursor.getString(21));
//                item.setuseLmtt(cursor.getString(22));
//                item.setinsideOpratArea(cursor.getString(23));
//                item.setoutsideOpratArea(cursor.getString(24));
//                item.setuseTrget(cursor.getString(25));
//                item.setuseCharge(cursor.getString(26));
//                item.setinstitutionNm(cursor.getString(27));
//                item.setphoneNumber(cursor.getString(28));
//                item.setreferenceDate(cursor.getString(29));
//                item.setinstt_code(cursor.getString(30));
//                item.setinstt_nm(cursor.getString(31));
                dataList.add(item);
            }

            return dataList;
        }
    }
