package gr.dit.project1.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class FillTablesService {

  static final Logger logger = LoggerFactory.getLogger(FillTablesService.class);

  public void parseAccessLog() throws IOException {
    ClassPathResource classPathResource = new ClassPathResource("access.log");
    try (BufferedReader br =
        new BufferedReader(new InputStreamReader(classPathResource.getInputStream()))) {

      String strLine;
      int val = 1;
      while ((strLine = br.readLine()) != null) {
        String[] data = strLine.split(" ");
        System.out.println("length: " + data.length);
        if (data[7].equals("")) {
        	List<String> list = new ArrayList<String>(Arrays.asList(data));
        	//List<String> list = Arrays.asList(data);
        	list.remove(7);
        	Object[]  data1 = list.toArray();
            int length = data1.length;
            data1[11] = ((String) data1[11]).substring(1);
            data1[length - 2] = ((String) data1[length - 2]).substring(0, ((String) data1[length - 2]).length() - 1);

            String ipAddress = (String) data1[0];
            System.out.println(ipAddress);
            String userId = (String) data1[1];
            if (userId.equals("-")) {
              // saveAndFlush null value
            }
            System.out.println(userId);
            String stringTimestamp = strLine.substring(strLine.indexOf("[") + 1, strLine.indexOf("]"));
            LocalDateTime timestamp = stringToLocalDateTimeAccessLog(stringTimestamp);
            System.out.println(timestamp);
            String method = (String) data1[5];
            method = method.substring(1);
            System.out.println(method);
            String resource = (String) data1[6];
            System.out.println(resource);
            if (data1[8].equals("-")) {
            	//save and flush null
            }
            else {
            	long responseStatus = Long.parseLong((String) data1[8]);
            	System.out.println(responseStatus);
            }
            if (data1[9].equals("-")) {
            	//save and flush null
            }
            else {
            	long responseSize = Long.parseLong((String) data1[9]);
            	System.out.println(responseSize);
            }
            String referer = (String) data1[10];
            referer = referer.substring(1);
            referer = referer.substring(0, referer.length() - 1);
            if (referer.equals("-")) {
              // saveAndFlush null value
            }
            System.out.println(referer);
            StringBuilder userAgentBuilder = new StringBuilder();
            for (int i = 11; i < data1.length - 1; i++) {
              userAgentBuilder.append(data1[i]);
              if (i != data1.length - 2) {
                userAgentBuilder.append(" ");
              }
            }
            String userAgent = userAgentBuilder.toString();
            if (userAgent.equals("-")) {
              // saveAndFlush null value
            }
            System.out.println(userAgent);
            System.out.println("row = " + val);
            System.out.println("---------------");
            val++;
          }       	
          else {
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
            LocalDateTime timestamp = stringToLocalDateTimeAccessLog(stringTimestamp);
            System.out.println(timestamp);
            String method = data[5];
            method = method.substring(1);
            System.out.println(method);
            String resource = data[6];
            System.out.println(resource);
            if (data[8].equals("-")) {
            	//save and flush null
            }
            else {
            	long responseStatus = Long.parseLong(data[8]);
            	System.out.println(responseStatus);
            }
            if (data[9].equals("-")) {
            	//save and flush null
            }
            else {
            	long responseSize = Long.parseLong(data[9]);
            	System.out.println(responseSize);
            }
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
            System.out.println("row = " + val);
            System.out.println("---------------");
            val++;
        }

        
      }
    } catch (Exception e) {
      logger.error("Error adding users", e);
    }


  }


  private LocalDateTime stringToLocalDateTimeAccessLog(String date) {
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
  
  private LocalDateTime stringToLocalDateTimeDataXceiver(String date) {
	    try {
	      DateTimeFormatter sdf =
	          DateTimeFormatter.ofPattern("ddMMyyHHmmss");
	      LocalDateTime timeWithZone = LocalDateTime.parse(date, sdf);
	      return timeWithZone;
	    } catch (DateTimeParseException exception) {
	      logger.error("Error parsing Date ", exception);
	      return null;
	    }
  }
  
  public void parseDataXceiver() throws IOException {
	  ClassPathResource classPathResource = new ClassPathResource("HDFS_DataXceiver.log");
	    try (BufferedReader br =
	        new BufferedReader(new InputStreamReader(classPathResource.getInputStream()))) {
	    	String strLine;
	    	int c = 0;
	    	while ((strLine = br.readLine()) != null) {
	    		System.out.println(c);
	    		c++;
	    		String[] data = strLine.split(" ");
	    		if (data[5].equals("writeBlock")) {
	    			continue;
	    		}
	    		if (data[3].equals("ERROR")) {
	    			continue;
	    		}
	    		String stringTimestamp = data[0] + data[1];
	    		LocalDateTime timestamp = stringToLocalDateTimeDataXceiver(stringTimestamp);
	    		System.out.println(timestamp);
	    		//String strBlockId = data[2];
	    		for (String s : data) {
	    			if (s.startsWith("blk_")) {
	    				System.out.println(s);
	    				break;
	    			}
	    		}
	    		int srcPosition = 0;
	    		int destPosition = 0;
	    		for (String s : data) {
	    			if (s.equals("src:")) {
	    				break;
	    			}
	    			srcPosition ++;
	    		}
	    		for (String s : data) {
	    			if (s.equals("dest:")) {
	    				break;
	    			}
	    			destPosition ++;
	    		}
	    		//if src is not exist
	    		if (srcPosition == data.length) {
	    			String ipAddress = data[5];
	    			if (ipAddress.startsWith("/")) {
	    				ipAddress = ipAddress.substring(1);
	    			}
	    			if (ipAddress.contains(":")) {
	    				ipAddress = ipAddress.substring(0, ipAddress.indexOf(":"));
	    			}
	    			System.out.println(ipAddress);
	    			int destPos = 0;
	    			for (String s : data) {
		    			if (s.equals("to")) {
		    				break;
		    			}
		    			destPos++;
		    		}
	    			String destAddress = data[destPos + 1];
	    			if (destAddress.startsWith("/")) {
	    				destAddress = destAddress.substring(1);
	    			}
	    			if (destAddress.contains(":")) {
	    				destAddress = destAddress.substring(0, destAddress.indexOf(":"));
	    			}
	    			System.out.println(destAddress);
	    			if (data[3].equals("INFO")) {
	    				String type = data[6];
	    				System.out.println(type);
	    			}
	    			else {
	    				System.out.println("exception");
	    				//to type einai exception mallon null
	    			}
	    		}
	    		//if src exist
	    		else {
	    			String ipAddress = data[srcPosition + 1];
	    			String destAddress = data[destPosition + 1];
	    			if (ipAddress.startsWith("/")) {
	    				ipAddress = ipAddress.substring(1);
	    			}
	    			if (ipAddress.contains(":")) {
	    				ipAddress = ipAddress.substring(0, ipAddress.indexOf(":"));
	    			}
	    			if (destAddress.startsWith("/")) {
	    				destAddress = destAddress.substring(1);
	    			}
	    			if (destAddress.contains(":")) {
	    				destAddress = destAddress.substring(0, destAddress.indexOf(":"));
	    			}
	    			System.out.println(ipAddress);
	    			System.out.println(destAddress);
	    			String type = data[5];
	    			System.out.println(type);
	    		}
	    		if (data[data.length - 2].equals("size")) {
	    			Long size = Long.parseLong(data[data.length - 1]);
	    			System.out.println(size);
	    		}
	    		else {
	    			System.out.println("Does not exists");
	    			//vale ston pinaka null
	    		}
	    		System.out.println("------------------");
	    	}
	    }
  }
  


}
