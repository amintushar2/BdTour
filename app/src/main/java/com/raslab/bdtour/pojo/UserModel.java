package com.raslab.bdtour.pojo;

public class UserModel {
    public UserModel() {
    }

    String userid, mobilePhoneEdts,firstNameEdts,lastNameEdts,emailEdts,dobEdts,adressEdts,generateToken;

    public UserModel(String userid,String mobilePhoneEdts, String firstNameEdts, String lastNameEdts, String emailEdts, String dobEdts, String adressEdts, String generateToken) {
        this.userid= userid;
        this.mobilePhoneEdts = mobilePhoneEdts;
        this.firstNameEdts = firstNameEdts;
        this.lastNameEdts = lastNameEdts;
        this.emailEdts = emailEdts;
        this.dobEdts = dobEdts;
        this.adressEdts = adressEdts;
        this.generateToken = generateToken;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMobilePhoneEdts() {
        return mobilePhoneEdts;
    }

    public void setMobilePhoneEdts(String mobilePhoneEdts) {
        this.mobilePhoneEdts = mobilePhoneEdts;
    }

    public String getFirstNameEdts() {
        return firstNameEdts;
    }

    public void setFirstNameEdts(String firstNameEdts) {
        this.firstNameEdts = firstNameEdts;
    }

    public String getLastNameEdts() {
        return lastNameEdts;
    }

    public void setLastNameEdts(String lastNameEdts) {
        this.lastNameEdts = lastNameEdts;
    }

    public String getEmailEdts() {
        return emailEdts;
    }

    public void setEmailEdts(String emailEdts) {
        this.emailEdts = emailEdts;
    }

    public String getDobEdts() {
        return dobEdts;
    }

    public void setDobEdts(String dobEdts) {
        this.dobEdts = dobEdts;
    }

    public String getAdressEdts() {
        return adressEdts;
    }

    public void setAdressEdts(String adressEdts) {
        this.adressEdts = adressEdts;
    }

    public String getGenerateToken() {
        return generateToken;
    }

    public void setGenerateToken(String generateToken) {
        this.generateToken = generateToken;
    }
}
