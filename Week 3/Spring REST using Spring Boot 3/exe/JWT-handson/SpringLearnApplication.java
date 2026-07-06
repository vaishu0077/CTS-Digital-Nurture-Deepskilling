package com.cognizant.springlearn;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringLearnApplication {
    public static void displayDate() throws Exception {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("date-format.xml");

        SimpleDateFormat format =
                context.getBean("dateFormat", SimpleDateFormat.class);

        Date date = format.parse("31/12/2018");

        System.out.println(date);

    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringLearnApplication.class, args);
        displayDate();
    }
}
