package gr.dit.project1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project1DbManagementSystemsApplication {

  public static void main(String[] args) {
    SpringApplication.run(Project1DbManagementSystemsApplication.class, args);
  }

//   @Bean
//   public CommandLineRunner testSubscriptionService(FillTablesService fillTablesService) {
//	   return args -> {
//		   fillTablesService.parseAccessLog();
//	   };
//   }

//   @Bean
//   public CommandLineRunner testSubscriptionService(FillTablesService fillTablesService) {
//	   return args -> {
//		   fillTablesService.parseDataXceiver();
//	   };
//   }

//  @Bean
//  public CommandLineRunner testSubscriptionService3(FillTablesService fillTablesService) {
//    return args -> {
//      fillTablesService.parseNameSystem();
//    };
//  }

  // @Bean
  // public CommandLineRunner testDate(FillTablesService fillTablesService) {
  // return args -> {
  // LocalDateTime a = fillTablesService.stringToLocalDateTime("12/Dec/2015:18:31:25 +0100");
  // System.out.println(a);
  // };
  // }
  
//   @Bean
//   public CommandLineRunner testDate(FillTablesService fillTablesService) {
//	   return args -> {
//		   fillTablesService.executeQuery3();
//	   };
//   }

}
