package com;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class SchoolWrite {

    public static void main(String[] args) {

        School school = new School();
        school.setName("实验小学");
        school.setAddress("山东济南市");

        List<Student> students = new ArrayList<Student>();
        Student student1 = new Student();
        student1.setStudentNum("001");
        student1.setStudentName("xiaoDy");
        student1.setAge(25);
        student1.setStudentGrade("1");

        Student student2 = new Student();
        student2.setStudentNum("002");
        student2.setStudentName("xiaoWf");
        student2.setAge(22);
        student2.setStudentGrade("2");

        students.add(student1);
        students.add(student2);
        school.setStudents(students);
        try {
            File file = new File("src/school.xml");
            JAXBContext context = JAXBContext.newInstance(School.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            mar.marshal(school, file);//内容写入文件
            mar.marshal(school, System.out);//输出文件
        } catch (JAXBException e) {
            e.printStackTrace();
        }


    }

}