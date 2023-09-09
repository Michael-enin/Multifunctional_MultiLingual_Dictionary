package com.example.finalapp;

import java.util.ArrayList;
import java.util.Map;

public class GameStore {
    private ArrayList<String> listStore, listResponse;
   public GameStore(){}
    Map<String, String> mpContainer;
   public ArrayList<String> getStorage(){
       listStore = new ArrayList<String>();
       for(int i=0;i<getDescriptions().length;i++){
           listStore.add(getDescriptions()[i]);
       }
       return listStore;
   }
   public ArrayList<String> getResponseStore(){
       listResponse = new ArrayList<>();
       for(int i=0;i<getResponses().length;i++){
           listResponse.add(getResponses()[i]);
       }
       return listResponse;
   }

    public void setMpContainer(Map<String, String> mpContainer) {
        this.mpContainer = mpContainer;
        for(int i=0;i<getStorage().size();i++){
            mpContainer.put(getResponseStore().get(i),getStorage().get(i));
        }
    }
    public Map<String, String> getMpContainer() {
        return mpContainer;
    }
    // I can use Map Container to map Answer to the Description, Map<String ans, String Desc> mp;
   public String[] getDescriptions(){
       String[] source= new String[]{
               "Desc 1", "Desc 2", "Desk 3", "Desk 4", "Desk 5", "Desk 6"};
       return source;
   }
   public String[] getResponses(){
       String[]source = new String[]{
               "Resp1", "Resp2", "Resp3", "Resp4", "Resp5", "Resp6"};
       return source;
   }

}
