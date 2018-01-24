/**
 * 
 */
package com.billingapi.registration;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.billingapi.pojo.UserDetails;

/**
 * @author Shriram
 *
 */
@RestController
public class Register {
	String licensePath = System.getProperty("user.home")+"\\tsCode\\license.txt"; 

	@RequestMapping("/")
	public boolean createLicense() {
		// TODO Auto-generated method stub
		System.out.println("Inside method....!!");
		String licenseData="";
		File file = new File(System.getProperty("user.home")+"\\tsCode");
		file.mkdirs();
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("tscode");
		String encrypted= encryptor.encrypt(licenseData);  
		FileWriter fw = null;
		try {
			fw = new FileWriter(licensePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(fw);
		try {
			bw.write(encrypted);
			bw.close();
			fw.close();
			System.out.println("Done");
			return true;	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
	public static void main(String[] args) {
		Register regi= new Register();
		try {
//		    regi.registerMe(null);
			regi.decryptFile();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void decryptFile() throws IOException {
		// TODO Auto-generated method stub
		String everything = "";
		File file = new File(licensePath);
		BufferedReader br = new BufferedReader(new FileReader(licensePath));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} finally {
		    br.close();
		}
		
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword("tscode");

		String decrypted = encryptor.decrypt(everything);
	
		System.out.println(decrypted);
	}
	
	public Register() {
	}

	
	
}
