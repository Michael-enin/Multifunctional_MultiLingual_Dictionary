package com.example.finalapp;

import android.provider.BaseColumns;

public final class ContractDescription  {
    private ContractDescription(){}
    public static class DescriptionTable implements BaseColumns{
        public static final String TableName="description_table1";
        public static final String Column_Description = "Description";
        public static final String Column_Answer_option = "answers";
        public static final String Column_option2 = "No_Answer_I";
        public static final String Column_option3 = "No_Answer_II";
        public static final String Column_option4 = "No_Answer_III";
        public static final String Column_Ans = "answer_nr";

    }
}
