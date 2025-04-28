package com.snhu.sslserver;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}
//FIXME: Add route to enable check sum return of static data example:  String data = "Hello World Check Sum!";

//Updated code from assignment 5-1 to comply with this assignment

@RestController
class ServerController{
	//RESTful route to generate and return the requested information
    @RequestMapping("/hash")
    public String myHash()
    {
    	String data = "Hello World Check Sum! Daniel Jones 2025"; //Updated string data to provided sample
    	 String hashValue = generateSHA256Hash(data); //From my findings this was the security recommendations for this application and its use
         return "<p>data: " + data + "</p><p>SHA-256 Checksum: " + hashValue + "</p>";
    }
    // Method to generate SHA-256 hash //From my findings this was the security recommendations for this application and its use
    private String generateSHA256Hash(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Added this implementation based on the reading and troubleshooting found on stackoverflow
            byte[] hashBytes = digest.digest(data.getBytes());
            return bytesToHex(hashBytes); // Converting bytes to hex using helper function
        } catch (NoSuchAlgorithmException e) {
            return "Error generating Checksum"; //verifies hash generation 
        }
    }

    // Helper function to convert bytes to hex
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b)); // Converting bytes to hex
        }
        return hexString.toString(); //Returns hex values
    }
}