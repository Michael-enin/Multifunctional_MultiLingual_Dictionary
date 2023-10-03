package com.example.finalapp;

public class Database {
    public static String[] getData(int id){
        if(id==(R.id.Ke_Am)){
            return getKe_Am();
        }
        else if(id==(R.id.am_ke)){
            return getAm_Ke();
        }
        else if(id==(R.id.ke_En)){
            return getKe_En();
        }
        return new String[0];
    }
    public static String[] getKe_Am(){
        String[] source = new String[]{
                "መተው",
                "ችሎታ",
                "መቻል",
                "ስለ",
                "ከላይ",
                "በውጭ አገር",
                "አለመኖር",
                "መቅረት",
                "ፍፁም",
                "ረቂቅ",
                "አላግባብ",
                "ቀላል",
                "አካዴሚያዊ",
                "ተቀበል",
                "ተቀባይነት ያለው",
                "ተቀባይነት",
                "ድረስበት",
                "አደጋ",
                "አብሮ መሄድ",
                "መሠረት",
                "መለያ",
                "የሂሳብ ባለሙያ",
                "ትክክለኛ"
        };
        return source;
    }
    public static String[] getAm_Ke(){
        String[] source = new String[]{
                "lag1",
                "lag2",
                "lag3",
                "lag4",
                "lag5",
                "lag6",
                "lag7",
                "lag8",
                "lag9",
                "lag10",
                "lag11",
                "lag12",
                "lag13",
                "lag14",
                "lag15",
                "lag16",
                "lag17",
                "lag18",
                "lag19",
                "lag20",
                "lag21",
                "lag22",
                "lag23",
                "lag24",
                "lag25",
                "lag26",
                "lag27",
                "lag28",
                "lag29",
                "lag30"
        };
        return source;
    }
    public static  String[] getKe_En(){
        String [] source =new String []{
                "wd1",
                "wd2",
                "wd3",
                "wd4",
                "wd5",
                "wd6",
                "wd7",
                "wd8",
                "wd9",
                "wd10",
                "wd11",
                "wd12",
                "wd13",
                "wd14",
                "wd15",
                "wd16",
                "wd17",
                "wd18",
                "wd19",
                "wd20",
                "wd21",
                "wd22",
                "wd23",
                "wd24",
                "wd25",
                "wd26",
                "wd27",
                "wd28",
                "wd29",
                "wd30"
        };
        return source;
    }
}