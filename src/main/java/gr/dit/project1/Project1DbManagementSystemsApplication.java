package gr.dit.project1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import gr.dit.project1.services.FillTablesService;

@SpringBootApplication
public class Project1DbManagementSystemsApplication {

  public static void main(String[] args) {
    SpringApplication.run(Project1DbManagementSystemsApplication.class, args);
  }

  @Bean
  public CommandLineRunner testSubscriptionService(FillTablesService fillTablesService) {
    return args -> {
      fillTablesService.fillTables();
    };
  }

  // @Bean
  // public CommandLineRunner testDate(FillTablesService fillTablesService) {
  // return args -> {
  // LocalDateTime a = fillTablesService.stringToLocalDateTime("12/Dec/2015:18:31:25 +0100");
  // System.out.println(a);
  // };
  // }

}
