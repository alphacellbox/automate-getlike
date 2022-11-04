package solo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;

import javafx.application.Platform;

public class Main {
//	//div[@aria-label='Any time']/div[@class='mn-hd-txt' and text()='Any time']"
	
	
	public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
		  
		Platform.startup(() ->
		 {
		     
		 });
		
		func_start();
			
		   
	
			 
		
			
			
			
			


		
			
		}
	public static void func_start() throws InterruptedException, IOException, URISyntaxException {
		 donework d=new donework(); 
		try {
			 
			 d.setOnFailed(e -> {

					try {
						System.out.println("restart");
						func_start();
					} catch (InterruptedException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch (Exception e2) {
						e2.printStackTrace();
					}

				});
				d.start();
				
		 }catch (Exception e) {
			e.printStackTrace();
		}
	}
		
		
	}
	
	


