package com.raslab.bdtour.pojo;

public class HotelModel {
    String hotelNAMES;
    String hotelAddress;
    String hotelDistricts;
    String hotelGmapLoc;
    String hotelEmail;
    String nonAcSingleRoom;
    String nonAcDoubleRoom;
    String nonAcPremiumRoom;
    String acSingleRoom;
    String acDoubleRoom;
    String acPremiumRoom;
    String description;
    String id;
    String phoneNumber;
    String coverImageUrl;
    String link;




    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }






    public String getNonAcSingleRoom() {
        return nonAcSingleRoom;
    }

    public void setNonAcSingleRoom(String nonAcSingleRoom) {
        this.nonAcSingleRoom = nonAcSingleRoom;
    }

    public String getNonAcDoubleRoom() {
        return nonAcDoubleRoom;
    }

    public void setNonAcDoubleRoom(String nonAcDoubleRoom) {
        this.nonAcDoubleRoom = nonAcDoubleRoom;
    }

    public String getNonAcPremiumRoom() {
        return nonAcPremiumRoom;
    }

    public void setNonAcPremiumRoom(String nonAcPremiumRoom) {
        this.nonAcPremiumRoom = nonAcPremiumRoom;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAcSingleRoom() {
        return acSingleRoom;
    }

    public void setAcSingleRoom(String acSingleRoom) {
        this.acSingleRoom = acSingleRoom;
    }

    public String getAcDoubleRoom() {
        return acDoubleRoom;
    }

    public void setAcDoubleRoom(String acDoubleRoom) {
        this.acDoubleRoom = acDoubleRoom;
    }

    public String getAcPremiumRoom() {
        return acPremiumRoom;
    }

    public void setAcPremiumRoom(String acPremiumRoom) {
        this.acPremiumRoom = acPremiumRoom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public HotelModel(String hotelNAMES, String hotelAddress, String hotelDistricts, String hotelGmapLoc, String hotelEmail,
                      String nonAcSingleRoom, String nonAcDoubleRoom, String nonAcPremiumRoom, String acSingleRoom, String acDoubleRoom,
                      String acPremiumRoom, String description, String id, String phoneNumber, String coverImageUrl,String link) {
        this.hotelNAMES = hotelNAMES;
        this.hotelAddress = hotelAddress;
        this.hotelDistricts = hotelDistricts;
        this.hotelGmapLoc = hotelGmapLoc;
        this.hotelEmail = hotelEmail;
        this.nonAcSingleRoom = nonAcSingleRoom;
        this.nonAcDoubleRoom = nonAcDoubleRoom;
        this.nonAcPremiumRoom = nonAcPremiumRoom;
        this.acSingleRoom = acSingleRoom;
        this.acDoubleRoom = acDoubleRoom;
        this.acPremiumRoom = acPremiumRoom;
        this.description = description;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.coverImageUrl = coverImageUrl;
        this.link=link;

    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public String getHotelNAMES() {
        return hotelNAMES;
    }

    public void setHotelNAMES(String hotelNAMES) {
        this.hotelNAMES = hotelNAMES;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getHotelDistricts() {
        return hotelDistricts;
    }

    public void setHotelDistricts(String hotelDistricts) {
        this.hotelDistricts = hotelDistricts;
    }

    public String getHotelGmapLoc() {
        return hotelGmapLoc;
    }

    public void setHotelGmapLoc(String hotelGmapLoc) {
        this.hotelGmapLoc = hotelGmapLoc;
    }



    public HotelModel() {
    }

}
