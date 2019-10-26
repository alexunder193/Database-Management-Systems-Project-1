package gr.dit.project1.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FillTablesService {

  static final Logger logger = LoggerFactory.getLogger(FillTablesService.class);

  public void fillTables() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("logs.txt");
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(classPathResource.getInputStream()))) {

      String strLine;
      while ((strLine = br.readLine()) != null) {
        String[] data = strLine.split(" ");

        int length = data.length;
        data[11] = data[11].substring(1);
        data[length - 2] = data[length - 2].substring(0, data[length - 2].length() - 1);

        String ipAddress = data[0];
        System.out.println(ipAddress);
        String userId = data[1];
        if (userId.equals("-")) {
          // saveAndFlush null value
        }
        System.out.println(userId);
        String stringTimestamp = strLine.substring(strLine.indexOf("[") + 1, strLine.indexOf("]"));
        LocalDateTime timestamp = stringToLocalDateTime(stringTimestamp);
        System.out.println(timestamp);
        String method = data[5];
        method = method.substring(1);
        System.out.println(method);
        String resource = data[6];
        System.out.println(resource);
        long responseStatus = Long.parseLong(data[8]);
        long responseSize = Long.parseLong(data[9]);
        System.out.println(responseStatus);
        System.out.println(responseSize);
        String referer = data[10];
        referer = referer.substring(1);
        referer = referer.substring(0, referer.length() - 1);
        if (referer.equals("-")) {
          // saveAndFlush null value
        }
        System.out.println(referer);
        StringBuilder userAgentBuilder = new StringBuilder();
        for (int i = 11; i < data.length - 1; i++) {
          userAgentBuilder.append(data[i]);
          if (i != data.length - 2) {
            userAgentBuilder.append(" ");
          }
        }
        String userAgent = userAgentBuilder.toString();
        if (userAgent.equals("-")) {
          // saveAndFlush null value
        }
        System.out.println(userAgent);
        System.out.println("---------------");
        // for (String s : data) {
        // System.out.println(s);
        // }
        // break;
      }
    } catch (Exception e) {
      logger.error("Error adding users", e);
    }


  }


  private LocalDateTime stringToLocalDateTime(String date) {
    try {
      DateTimeFormatter sdf =
          DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z").withLocale(Locale.ENGLISH);
      LocalDateTime timeWithZone = LocalDateTime.parse(date, sdf);
      return timeWithZone;
    } catch (DateTimeParseException exception) {
      logger.error("Error parsing Date ", exception);
      return null;
    }
  }


}
