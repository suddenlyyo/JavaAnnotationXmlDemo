package com;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Student {


    private String studentNum;
    private String studentName;
    private String studentGrade;
    private int age;

    @XmlElement(name="num")
    public String getStudentNum() {
        return studentNum;
    }
    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }
    @XmlElement(name="name")
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    @XmlAttribute(name="grade")
    public String getStudentGrade() {
        return studentGrade;
    }
    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }
    @XmlElement
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student [studentNum=" + studentNum + ", studentName="
                + studentName + ", studentGrade=" + studentGrade + ", age="
                + age + "]";
    }



}