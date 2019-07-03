package com.example.android.yagami;


public class DetailPost {
    private String category;
    private String location_type;
    private Location location;
    private String context;
    private OutcomeStatus outcome_status;
    private String persistent_id;
    private int id;
    private String location_subtype;
    private String month;

    public String getCategory(){
        return category;
    }
    public  String getLocationType(){
        return "Location Type : " + location_type + "\n";
    }
    public String getLocation(){
        return "Location : " + "\n\t\t" + "Latitude : " + location.getLatitude() + "\n\t\t" + "Street : " + location.getStreet() + "\n\t\t" + "Longitude : " + location.getLongitude() + "\n";
    }

    public String getContext(){
        return "Context : " + context + "\n";
    }

    public String getOutcomeStatus(){
        if(outcome_status == null){
            return "Outcome Status : null\n";
        }
        return "Outcome Status : " + "\n\t\t" + "Category : " + outcome_status.getOutcomeCategory() + "\n\t\t" + "Date : " + outcome_status.getDate() + "\n";
    }


    public String getPersistentId(){
        if(persistent_id == null){
            return "Persistent ID : null\n";
        }
        return "Persistent Id : " + persistent_id + "\n";
    }

    public String getID(){
        return "ID : " + id + "\n";
    }
    public String getLocationSubtype(){
        if(location_subtype == null){
            return "Location Subtype : null\n";
        }
        return "Location Subtype : " + location_subtype + "\n";
    }

    public String getMonth(){
        return "Month : " + month + "\n";
    }

    private static class Location {
        private String latitude;
        private String longitude;
        private Street street;

        private String getLatitude(){
            return latitude;
        }
        private String getLongitude(){
            return longitude;
        }
        private String getStreet(){
            return "\t\t\t\tID : " + street.getId() + "\t\t" + "Name : " +  street.getName();
        }
    }

    private static class Street {
        private int id;
        private String name;

        private String getId(){
            String strId = String.valueOf(id);
            return strId;
        }

        private String getName(){
            return name;
        }
    }

    private static class OutcomeStatus{
        private String category;
        private String date;

        private String getOutcomeCategory(){
            return category;
        }

        private String getDate(){
            return date;
        }
    }

}
