package com.genspark.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class Student {
    private int studentID;
    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", contactNumbers=" + contactNumbers +
                ", address=" + address +
                '}';
    }

    private List<String> contactNumbers;
    @Autowired
    private Address address;




    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(List<String> contactNumbers) {
        this.contactNumbers = contactNumbers;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void study() {
        System.out.println("Student is studying");
    }
}
