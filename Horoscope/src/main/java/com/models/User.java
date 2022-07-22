package com.models;

public class User {
    private Integer user_id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String  birth_date;
    private String zodiac_sign;
    private String mood;

    public User(){


    }

    public User(String first_name, String last_name,String email ,String zodiac_sign) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.zodiac_sign = zodiac_sign;
    }

    public User(String username, String first_name, String last_name, String email, String birth_date, String zodiac_sign, String mood) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.zodiac_sign = zodiac_sign;
        this.mood = mood;
    }

    public User(Integer user_id, String username, String first_name, String last_name, String email, String birth_date, String zodiac_sign, String mood) {
        this.user_id = user_id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.birth_date = birth_date;
        this.zodiac_sign = zodiac_sign;
        this.mood = mood;
    }



    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getZodiac_sign() {
        return zodiac_sign;
    }

    public void setZodiac_sign(String zodiac_sign) {
        this.zodiac_sign = zodiac_sign;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
