package com.example.android.yagami;



import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class DetailForce {
    @SerializedName("description")
    private Desc desc;
    private String url;
    @SerializedName("engagement_methods")
    private ArrayList<EngagementMethods> engagement_methods;
    private String telephone;
    private String id;
    private String name;


    public String getDesc() {
        if(desc == null)
            return "Description : null\n";
        return "Description : " + desc.getDescription() + "\n";
    }

    public String getUrl(){
        return "Url : " + url + "\n";
    }

    public String getEngagementMethods(){
        String info = "";
        for(EngagementMethods engagement_method : engagement_methods){
           info += "Engagement Methods :\n url : " + engagement_method.getUrl() +"\n Type : " + engagement_method.getType() + "\n Description : " + engagement_method.getDescrip() + "\n Title : " + engagement_method.getTitle() + "\n";
        }
        return info;
    }
    public String getTelephone(){
        return "telephone : " + telephone + "\n";
    }
    public String getID(){
        return "Id : " + id + "\n";
    }
    public String getName(){
        return "Name : " + name;
    }

    private class Desc{
        private String description;

        private String getDescription(){
            return description;
        }
    }

    private class EngagementMethods{
        @SerializedName("url")
        private String url;
        private String type;
        @SerializedName("description")
        private String descrip;
        private String title;

        private String getDescrip() {
            if(descrip == null)
                return "null";
            return descrip.substring(3, descrip.length()-4);
        }

        private String getUrl() {
            return url;
        }
        private String getType() {
            return type;
        }

        private String getTitle() {
            return title;
        }
    }

}
