package com;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class SchoolRead {

    public static void main(String[] args) {

        File file = new File("src/school.xml");
        boolean exists = file.exists();
        if(!exists){
            System.out.println("文件不存在,开始创建");
//            try {
//                file.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }

        try {

            JAXBContext context = JAXBContext.newInstance(School.class);
            Unmarshaller unMar = context.createUnmarshaller();
            School school = (School) unMar.unmarshal(file);
            System.out.println(school);
            List<Student>students = school.getStudents();
            for(Student student:students){
                System.out.println(student);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
