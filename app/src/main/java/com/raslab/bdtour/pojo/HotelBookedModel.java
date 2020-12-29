package com.raslab.bdtour.pojo;

public class HotelBookedModel {
    String startDates,lastDate,adultCountS, childCounts,dif,hotelNames,hotelDestrict,rentTT,totalR,roomSelections,totalRoom;

    public HotelBookedModel() {
    }

    public HotelBookedModel(String startDates, String lastDate, String adultCountS, String childCounts, String dif, String hotelNames, String hotelDestrict, String rentTT, String totalR, String roomSelections, String totalRoom) {
        this.startDates = startDates;
        this.lastDate = lastDate;
        this.adultCountS = adultCountS;
        this.childCounts = childCounts;
        this.dif = dif;
        this.hotelNames = hotelNames;
        this.hotelDestrict = hotelDestrict;
        this.rentTT = rentTT;
        this.totalR = totalR;
        this.roomSelections = roomSelections;
        this.totalRoom = totalRoom;
    }

    public String getStartDates() {
        return startDates;
    }

    public void setStartDates(String startDates) {
        this.startDates = startDates;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getAdultCountS() {
        return adultCountS;
    }

    public void setAdultCountS(String adultCountS) {
        this.adultCountS = adultCountS;
    }

    public String getChildCounts() {
        return childCounts;
    }

    public void setChildCounts(String childCounts) {
        this.childCounts = childCounts;
    }

    public String getDif() {
        return dif;
    }

    public void setDif(String dif) {
        this.dif = dif;
    }

    public String getHotelNames() {
        return hotelNames;
    }

    public void setHotelNames(String hotelNames) {
        this.hotelNames = hotelNames;
    }

    public String getHotelDestrict() {
        return hotelDestrict;
    }

    public void setHotelDestrict(String hotelDestrict) {
        this.hotelDestrict = hotelDestrict;
    }

    public String getRentTT() {
        return rentTT;
    }

    public void setRentTT(String rentTT) {
        this.rentTT = rentTT;
    }

    public String getTotalR() {
        return totalR;
    }

    public void setTotalR(String totalR) {
        this.totalR = totalR;
    }

    public String getRoomSelections() {
        return roomSelections;
    }

    public void setRoomSelections(String roomSelections) {
        this.roomSelections = roomSelections;
    }

    public String getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(String totalRoom) {
        this.totalRoom = totalRoom;
    }
}
