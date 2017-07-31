/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.registry.management.system;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author jeany
 */
class Patient {
    int pId;
    String fName, lName, gender, address, phonenum, 
            medCon,comments,ward, room;
    Date dob, admDate;
    Time admTime;
    
    public Patient(int pId, String fName, String lName, String gender, 
            String address, String phonenum, String medCon, String comments,
            String ward, String room, Date dob, Date admDate, Time admTime){
        this.pId = pId;
        this.fName = fName;
        this.lName = lName;
        this.gender = gender;
        this.address = address;
        this.phonenum = phonenum;
        this.medCon = medCon;
        this.comments = comments;
        this.ward = ward;
        this.room = room;
        this.dob = dob;
        this.admDate = admDate;
        this.admTime = admTime;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getMedCon() {
        return medCon;
    }

    public void setMedCon(String medCon) {
        this.medCon = medCon;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getAdmDate() {
        return admDate;
    }

    public void setAdmDate(Date admDate) {
        this.admDate = admDate;
    }

    public Time getAdmTime() {
        return admTime;
    }

    public void setAdmTime(Time admTime) {
        this.admTime = admTime;
    }
    
}
