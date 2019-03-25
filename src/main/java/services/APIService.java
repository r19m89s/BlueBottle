package services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class APIService {
    public static void main(String[] args) {
        try {
            DBUtils dbUtils = new DBUtils();
            dbUtils.createDatabase();
            dbUtils.createTableCafeDetails();
            dbUtils.createTableCafeHours();
            dbUtils.populateCafeDetails();
            SpringApplication.run(APIService.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
