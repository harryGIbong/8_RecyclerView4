package com.example.taylordesroches.a8_recyclerview4;

public class Person {

    public int id_;
    public String sName_;
    public String sEmail_;
    public String sAge_;

    public Person(int id_, String sName_, String sEmail_, String sAge_) {
        this.id_ = id_;
        this.sName_ = sName_;
        this.sEmail_ = sEmail_;
        this.sAge_ = sAge_;
    }

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public String getsName_() {
        return sName_;
    }

    public void setsName_(String sName_) {
        this.sName_ = sName_;
    }

    public String getsEmail_() {
        return sEmail_;
    }

    public void setsEmail_(String sEmail_) {
        this.sEmail_ = sEmail_;
    }

    public String getsAge_() {
        return sAge_;
    }

    public void setsAge_(String sAge_) {
        this.sAge_ = sAge_;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id_=" + id_ +
                ", sName_='" + sName_ + '\'' +
                ", sEmail_='" + sEmail_ + '\'' +
                ", sAge_='" + sAge_ + '\'' +
                '}';
    }
}

