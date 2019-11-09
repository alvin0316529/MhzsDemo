package com.qfh.domain;

public class User {
    private String userId;
    private String userName;
    private String realName;
    private int age;
    private String professional;
    private String city;

    public User() {
    }

    public User(String userId, String userName, String realName, int age, String professional, String city) {
        this.userId = userId;
        this.userName = userName;
        this.realName = realName;
        this.age = age;
        this.professional = professional;
        this.city = city;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", age=" + age +
                ", professional='" + professional + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
