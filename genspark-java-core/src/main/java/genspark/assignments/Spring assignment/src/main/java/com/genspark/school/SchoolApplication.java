package com.genspark.school;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SchoolApplication {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("SpringConfig.xml");

        Student student = (Student) context.getBean("student");
        student.study();
        System.out.println(student.toString());
    }
}
