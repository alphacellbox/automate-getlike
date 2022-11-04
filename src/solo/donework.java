package solo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.commons.io.FileUtils;

public class donework extends Service<String> {
	ProcessBuilder telnetProcessBuilder;
	Process telnetProcess;
	BufferedReader input;
	BufferedWriter output;
////chgange temp dir and systemusername
	@Override
	protected Task<String> createTask() {

		return new Task<String>() {
			String username="ADMINI~1";

	//	String username=System.getProperty("user.name");
		
			
			File timefile = new File("C:\\Users\\"+username+"\\Desktop\\time.txt");
		
			String instaname;
			ArrayList<usersinfo> users = new ArrayList<usersinfo>();
			ArrayList<insatdone> doness = new ArrayList<insatdone>();
             LocalDateTime alltime=null;
             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			@Override
			protected String call() throws Exception {
			
		
			
				File usersdone = new File("C:\\Users\\"+username+"\\Desktop\\done.txt");
				File usersfile = new File("C:\\Users\\"+username+"\\Desktop\\users.txt");
				
				
				boolean v = usersfile.exists();
				if (v) {
					try {
						Scanner scanner = new Scanner(usersfile);

						while (scanner.hasNextLine()) {
							String line = scanner.nextLine();

							String[] info = line.split("\t\t");

							users.add(new usersinfo(info[0], info[1], info[2]));

						}

					} catch (FileNotFoundException e) {

					}

				} else {
					System.out.println("users file dont exist");
					System.exit(0);
				}
				Collections.shuffle(users);

				/////////////////////////////////////////////////////////////////////////////////////////
				
				v = usersdone.exists();
				if (v) {
					try {
						Scanner scanner = new Scanner(usersdone);

						while (scanner.hasNextLine()) {
							String line = scanner.nextLine();

							String[] info = line.split("\t\t\t\t");
							
							doness.add(new insatdone(info[0], Integer.valueOf(info[1]), LocalDateTime.parse(info[2],formatter)));

						}

					} catch (FileNotFoundException e) {

					}

				} else {
					usersdone.createNewFile();
					System.out.println("done file dont exist,making a one");
					StringBuilder s = new StringBuilder();
					for (int i = 0; i < users.size(); i++) {

						String dateString = "2022-04-29 13:30:00";
						
						s.append(users.get(i).getInstaname() + "\t\t\t\t0\t\t\t\t" + dateString + "\n");
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

						LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
						doness.add(new insatdone(users.get(i).getInstaname(), 0, dateTime));
					}
					try {
						BufferedWriter f_writer = new BufferedWriter(new FileWriter(usersdone));
						f_writer.write(s.toString());

						f_writer.close();
					} catch (Exception e) {
						// TODO: handle exception
					}

				}
////////////////////////////////////////////////////////////////////////////////////////				
				int totaldone = 0;

				for (int i = 0; i < users.size(); i++) {
					System.out.println(users.get(i).getInstaname());
				}

			
					
					for (int j = 0; j < j+1; j++) {
						// Thread.sleep(180000);
					
						for (int i = 0; i < users.size(); i++) {
							Thread.sleep(2000);
							int total_time = 0;
							boolean total_flag = false;
							int count = 1;
							int wichdone = -1;
							int done = 1;
							
							for (int k = 0; k < doness.size(); k++) {
								
								if (doness.get(k).getInsta().equals(users.get(i).getInstaname())) {
									System.out.println(k);
									wichdone = k;
									
									break;
									
								}
							}
							
							if (doness.get(wichdone).getDone() < 150) {
//////////////////////////////////////////////////////////////////////////////////////
								// check if its 15 minute after last follow
								
								
									
									if (!LocalDateTime.now().isAfter(doness.get(wichdone).getTime().plusSeconds(5))) {
										total_flag=true;
										
									}
									
								
/////////////////////////////////////////////////////////////////////////////////////////////
								
								 instaname = users.get(i).getInstaname();
								String profname = users.get(i).getProfname();
							//	String vpn = users.get(i).getVpn();
							
								// case 2:
								// profname = "2";
								// instaname="aliakbar1231231";
								// break;
								// case 3:
								// profname = "3";
								// instaname="aliakbar1231232";
								// break;

								// case 5:
								// profname = "5";
								// instaname="aliakbar1231235";
								// break;

								// moz-extension://21537c8b-cfaa-4bdc-add7-afa974beb185/popup.html

								System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
								ProfilesIni allProfiles = new ProfilesIni();
								FirefoxProfile profile = allProfiles.getProfile(profname);
								FirefoxOptions options = new FirefoxOptions();
						  	options.setHeadless(true);

								options.setProfile(profile);
								options.addPreference("permissions.default.image", 2);
								WebDriver driver = new FirefoxDriver(options);
							
							 

								///////////////////////////////////////////////////////////////////////////////////////////////////////////////
								// go to getlike

								driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
								long t = System.currentTimeMillis();
								try {

									System.out.println("xxxxxxxxxxxx");
									driver.get("https://getlike.io/en/tasks/twitter/like/");
									System.out.println("xxxxxxxxxxxx");

									System.out.println("xxxxxxxxxxxx");
									System.out.println("Task   done in " + (System.currentTimeMillis() - t) + "ms");
								} catch (Exception e) {
									System.out
											.println("Task  aborted after " + (System.currentTimeMillis() - t) + "ms");
									total_flag = true;
								}
								System.out.println("conti  " + total_flag);

								/*
								 * /////////////////////////////////////////////////////////////////////////////
								 * //////////
								 * 
								 * 
								 * // click on switch while (true) { Thread.sleep(1000); total_time++;
								 * System.out.println("look"); if (total_time > 80) { total_flag = true;
								 * total_time = 0;
								 * 
								 * } if (total_flag) { break; }
								 * 
								 * try { // if
								 * (driver.findElement(By.xpath("//span[.//text()='switch']")).isDisplayed()) {
								 * driver.findElement(By.xpath("//span[.//text()='switch']")).click();
								 * System.out.println(1);
								 * 
								 * total_time = 0; break; }
								 * 
								 * } catch (Exception ex) {
								 * 
								 * } }
								 * 
								 * Thread.sleep(1000);
								 * 
								 * /////////////////////////////////////////////////////////////////////////////
								 * ////////// // see how much account there is int s = 0; while (true) {
								 * Thread.sleep(1000); total_time++; System.out.println("look how much"); if
								 * (total_time > 80) { total_flag = true; total_time = 0;
								 * 
								 * } if (total_flag) { break; } // try { // if (driver .findElement(
								 * By.xpath("//i[contains(@class, 'far fa-camera-retro text-instagram fa-fw')]"
								 * )) .isDisplayed()) { s = driver .findElements(By
								 * .xpath("//i[contains(@class, 'far fa-camera-retro text-instagram fa-fw')]"))
								 * .size();
								 * 
								 * //
								 * 
								 * System.out.println(s); driver.navigate().refresh(); total_time = 0; break; }
								 * 
								 * } catch (Exception ex) {
								 * 
								 * } }
								 * 
								 * 
								 * 
								 */
								// Thread.sleep(10000);

							
								
								
								

								///////////////////////////////////////////////////////////////////////////////////////
								int faild=0;
								while (done <= 5) {

									///////////////////////////////////////////////////////////////////////////////////////
									// click on switch

									
									total_time++;
									if (total_time > 80 || faild>=5) {
										total_flag = true;
										total_time = 0;
										System.out.println("faild");

									}
									if (total_flag) {
										break;
									}

//////////////////////////////////////////////////////////////////////////////////////////////////				
									// cost click
									
									 
									while (true) {
										
										Thread.sleep(1000);
										total_time++;
                                       
                                        
										if (total_time > 20 || faild>=5) {
											total_flag = true;
											total_time = 0;
											done=15+done;
											faild=0;

										}
										
										if (total_flag) {
											break;
										}
										System.out.println("finding task...");
										try {
											if (driver
													.findElement(By.xpath(
															"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																	+ count + "]/div/div/div/div[4]/a"))
													.isDisplayed()) {
											
												driver.findElement(By
														.xpath("/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																+ count + "]/div/div/div/div[4]/a"))
														.click();
											
											
												System.out.println(instaname);
												System.out.println(1);
												
												
												total_time = 0;
												break;
											}

										} catch (Exception ex) {

										}
										
									}

									//////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////////////////	
									boolean co=false;
									// two way of stop or check
									while (true) {
										Thread.sleep(1000);
										total_time++;
										if (total_time >20) {
											total_flag = true;
											total_time = 0;
											faild++;
										}
										if (total_flag) {
											break;
										}
										System.out.println("yyyy");
                                        
										// *************************************************************************************************
										// check if its stopped
										try {

											if (driver
													.findElement(By.xpath(
															"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																	+ count + "]/div/div/div/div[4]/span"))
													.isDisplayed()) {

												System.out.println(driver.findElement(By
														.xpath("/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																+ count + "]/div/div/div/div[4]/span"))
														.getText());
												try {
													if (driver.findElement(By.xpath(
															"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																	+ count + "]/div/div/div/div[4]/span"))
															.getText().equals("STOPPED")) {
														total_time = 0;

														System.out.println(2);

														count++;
														break;
													}
												} catch (Exception ex) {

												}

											}

										} catch (Exception ex) {

										}
										
										
									
										// *************************************************************************************************

										System.out.println("hhhh");
										// *************************************************************************************************
										// check if its on "check"
										try {
                                                       
											if (driver
													.findElement(By.xpath(
															"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																	+ count + "]/div/div/div/div[4]/a[2]"))
													.isDisplayed()) {
												System.out.println(driver.findElement(By
														.xpath("/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																+ count + "]/div/div/div/div[4]/a[2]"))
														.getText());
												System.out.println("cccc");
												if (driver.findElement(By
													//	/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article[1]/div/div/div/div[4]/a[2]
														.xpath("/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																+ count + "]/div/div/div/div[4]/a[2]"))
														.getText().equals(" Do")) {
													
													try {
														driver.findElement(By
																//	/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article[1]/div/div/div/div[4]/a[2]
																	.xpath("/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																			+ count + "]/div/div/div/div[4]/a[2]")).click();
													} catch (Exception e) {
														// TODO: handle exception
													}
													
													System.out.println("vvvvvvvv");
													total_time = 0;
													System.out.println("Do");
													System.out.println(3);
													for (String winHandle : driver.getWindowHandles()) {
														driver.switchTo().window(winHandle);
													}
													int time = 0;
													/////////////////////////////////////////// ******************************************************
													// go to instagram and follow button
													while (true) {
														Thread.sleep(1000);
														System.out.println("loading...");

														total_time++;
														if (total_time >35) {
															total_flag = true;
															total_time = 0;
                                                         done=done+15;
														}
														if (total_flag) {
															break;
														}
														time++;

														if (time > 25) {
															driver.navigate().refresh();
															time = 0;

														}

														/*
														 * int u = 0; for (String winHandle : driver.getWindowHandles())
														 * { driver.switchTo().window(winHandle); u++; } if (u < 2) {
														 * total_flag = true; }
														 * 
														 */
													

														try {
															if (driver
																	.findElement(
																			By.cssSelector("[aria-label=Like]"))
																	.isDisplayed()) {
																// String u = (String)((JavascriptExecutor)
																// driver).executeScript("return document.URL");
																

																System.out.println("vvvv");

																System.out.println("vvvv");

																System.out.println("first");
																System.out.println("..");
																total_time = 0;
																time = 0;
																System.out.println(4);
																 done++;
																	
																	int c = doness.get(wichdone).getDone();
																	c++;
																	doness.get(wichdone).setDone(c);
																	
																	
																	doness.get(wichdone).setTime(LocalDateTime.now());
																	
																	
																	changedone();

																driver
																.findElement(
																		By.cssSelector("[aria-label=Like]"))
																		.click();
															
															
																int heretime = 0;
																System.out.println(".");
																while (true) {
																	System.out.println("check if liked");
																	Thread.sleep(1000);
																	heretime++;
																	
																	try {
																		if (driver.findElement(
																				By.cssSelector("[data-testid=unlike]"))
																		.isDisplayed()) {
																			break;
																		}
																	} catch (Exception e) {
																		// TODO: handle exception
																	}
																	
																	if (heretime > 20) {
																		heretime=0;
																		total_flag = true;
																		break;

																	}
																	

																}
															
																	
																break;

															}

														} catch (Exception ex) {

														}

														try {

															if (driver
																	.findElement(
																			By.cssSelector("[aria-label=Liked]"))
																	.isDisplayed()) {
																
																total_time = 0;
																time = 0;
																System.out.println(4.5);
																break;
															}

														} catch (Exception ex) {

														}
														try {
															String strUrl = driver.getCurrentUrl();
															
															if (strUrl.equals("https://twitter.com/account/access")) {
															//	users.remove(i);
																faild=faild+15;
																total_time = 0;
																time = 0;
																System.out.println(4.3);
																sendmail();
																total_flag = true;
																break;
															}

														} catch (Exception ex) {

														}
														try {

															if (driver.findElement(By.xpath(
																	"//span[text()='Hmm...this page doesn’t exist. Try searching for something else.']"))
																	.isDisplayed()) {
															//	users.remove(i);
																total_time = 0;
																time = 0;
																System.out.println(4.4);
																total_flag = true;
																break;
															}

														} catch (Exception ex) {

														}
														try {

															if (driver.findElement(By.xpath(
																	"//span[text()='You’re unable to view this Tweet because this account owner limits who can view their Tweets. ']"))
																	.isDisplayed()) {
															//	users.remove(i);
																total_time = 0;
																time = 0;
																System.out.println(4.7);
																total_flag = true;
																break;
															}

														} catch (Exception ex) {

														}

														
													}

													/////////////////////////////////////////// ******************************************************

													// swith to getlike
													try {
														driver.close();
													} catch (Exception e) {
													}

													for (String winHandle : driver.getWindowHandles()) {
														driver.switchTo().window(winHandle);
													}
													System.out.println(driver.getTitle());

													////////////////////////////////////////// ??????????????????????????????????????????????????????
													// click on check button
													while (true) {
														Thread.sleep(1000);
														total_time++;
														if (total_time > 30) {
															total_time = 0;
															total_flag = true;
															break;
														}
														if (total_flag) {
															break;
														}

														try {

															if (driver.findElement(By.xpath(

																	"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																			+ count + "]/div/div/div/div[4]/a"))
																	.isDisplayed()) {
																driver.findElement(By.xpath(
																		"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																				+ count + "]/div/div/div/div[4]/a"))
																		.click();

																System.out.println(5);
																break;

															}

														} catch (Exception ex) {

														}

													}
													////////////////////////////////////////// ??????????????????????????????????????????????????????

													//////////////////////////////////////// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
													// check if its done

													
													while (true) {
														Thread.sleep(1000);
														System.out.println("waiting...");

														total_time++;
														if (total_time > 20) {
														//	
															try {
																if (driver.findElement(By.xpath(
																		"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["+count+"]/div/div/div/div[5]/div/button"))
																		.isDisplayed()) {
																	driver.findElement(By.xpath(
																			"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["+count+"]/div/div/div/div[5]/div/button"))
																			.click();
																	Thread.sleep(2000);
																	
																	driver.findElement(By.xpath(
																			"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["+count+"]/ul/li[1]/a")).click();
																	Thread.sleep(3000);
																	
																	System.out.println("Hided");
																	
																}

															} catch (Exception ex) {

															}
															total_flag = true;
															total_time = 0;
															/*
															 * done++;
															 * 
															 * int c =doness.get(wichdone).getDone(); c++;
															 * doness.get(wichdone).setDone(c); changedone();
															 */
															driver.navigate().refresh();
															System.out.println(80);
														}

														if (total_flag) {
															System.out.println("80all");
															break;
														}
														
														/*
														 * if (time2 > 15) { try { driver.navigate().refresh(); }catch
														 * (Exception e) {
														 * 
														 * }
														 * 
														 * time2 = 0; if (done == 2) { Thread.sleep(30000); }
														 * System.out.println(15); //done--; break;
														 * 
														 * }
														 */
														// check if its done or still on "check"
														try {
															if (driver.findElement(By.xpath(
																	"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																			+ count + "]/div/div/div/div[4]/span"))
																	.isDisplayed()) {
																WebElement e = driver.findElement(By.xpath(
																		"/html/body/div[6]/div[1]/div/div[2]/div/div[2]/article["
																				+ count + "]/div/div/div/div[4]/span"));
																System.out.println(e.getText());
																if (e.getText().equals("DONE")) {
																	total_time = 0;
																	
																	System.out.println(6);
																	totaldone++;
																	System.out.println("done: " + totaldone);
																} else {
																	total_time = 0;
																	
																	System.out.println(7);
																	// done--;
																	System.out.println("done: " + totaldone);
																}

																break;
															}

														} catch (Exception ex) {

														}
													}
													//////////////////////////////////////// &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

													if (total_flag == false) {

														// done++;

													} else {
														total_flag = false;
														
													//	wichdone = -1;

													}
													count++;
													co=true;
													
													time = 0;
													

													break;
												}
											}

										} catch (Exception ex) {

										}
										// *************************************************************************************************
									}
									total_flag = false;
									
									if (!co) {
										count++;
										
									}
									
								
								}
								try {
									Thread.sleep(2000);
									driver.quit();
								} catch (Exception ex) {

								}

							}
							
							
							Thread.sleep(2000);
							if (timefile.exists()) {
								Scanner scanner = new Scanner(timefile);

								while (scanner.hasNextLine()) {
								String tempalltime=scanner.nextLine();
								
								alltime=LocalDateTime.parse(tempalltime,formatter);
								
								System.out.println(alltime+"and exit");
								break;
								}
								scanner.close();
								
							}else {
								
								
								
								StringBuilder s = new StringBuilder();
								s.append(LocalDateTime.now().format(formatter));
								
								alltime=LocalDateTime.now();
								System.out.println("not exist");
								try {
									 usersdone.delete();
									BufferedWriter f_writer = new BufferedWriter(new FileWriter(timefile));
									f_writer.write(s.toString());

									f_writer.close();
									
								} catch (Exception e) {
									// TODO: handle exception
								}
								
							}
							
							if (LocalDateTime.now().isAfter(alltime.plusDays(1))) {
								System.out.println("ggggggggggggggggggggggggggggggggggggggggggggggggg");
								try {
									
								    usersdone.delete();
								    System.out.println("done delete");
								   
								    
									
									
								} catch (Exception e) {
									e.printStackTrace();
								}
								//File timefile = new File("C:\\Users\\"+username+"\\Desktop\\time.txt");
								StringBuilder s = new StringBuilder();
								s.append(LocalDateTime.now().format(formatter));
								
							   
								try {
									BufferedWriter f_writer = new BufferedWriter(new FileWriter(timefile));
									f_writer.write(s.toString());

									f_writer.close();
								} catch (Exception e) {
									// TODO: handle exception
								}
								
								
								
								 
								int[] a=new int[5];
								System.out.println(a[10]);
								
							}
						}

						// Thread.sleep(2700000);
					}
					
				
				return null;
			
				
			}

			public void changedone() throws IOException {

			
				
				File usersdone = new File("C:\\Users\\" + username + "\\Desktop\\done.txt");

			

				usersdone.createNewFile();
				
				System.out.println("change done file");
				StringBuilder s = new StringBuilder();
				for (int i = 0; i < users.size(); i++) {
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					s.append(doness.get(i).getInsta() + "\t\t\t\t" + doness.get(i).getDone() + "\t\t\t\t"
							+ doness.get(i).getTime().format(formatter) + "\n");

				}
				
				try {
					BufferedWriter f_writer = new BufferedWriter(new FileWriter(usersdone));
					f_writer.write(s.toString());

					f_writer.close();
					
				
				//	FileUtils.cleanDirectory(new File("C:\\Users\\" + username + "\\AppData\\Local\\Temp"));
					FileUtils.cleanDirectory(new File("C:\\Users\\" + username + "\\AppData\\Local\\Temp\\2"));
				} catch (Exception e) {
					// TODO: handle exception
				}

			}
			
			
			public void sendmail() {
				String recipient = "aliakbar311231128@gmail.com";
				 
			      // email ID of  Sender.
				
			      String sender = "alphaapple3112@gmail.com";
			      String password="pzniracubsvixeud";
			 
			   
			      
			 
			      // Getting system properties
			      Properties properties = new Properties();
			 
			      // Setting up mail server
			      properties.put("mail.smtp.host", "smtp.gmail.com");
			      properties.put("mail.smtp.port", "587");
			      properties.put("mail.smtp.auth", "true");
			      properties.put("mail.smtp.starttls.enable", "true");
			      
			 
			      // creating session object to get properties
			      Session session = Session.getInstance(properties, new Authenticator() {
			    	  @Override
			    	  protected PasswordAuthentication getPasswordAuthentication() {
			    		  return new PasswordAuthentication(sender, password);
			    	  }
				}
			      );
			 
			      try
			      {
			         // MimeMessage object.
			         MimeMessage message = new MimeMessage(session);
			 
			         // Set From Field: adding senders email to from field.
			         message.setFrom(new InternetAddress(sender));
			 
			         // Set To Field: adding recipient's email to from field.
			         message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			 
			         // Set Subject: subject of the email
			         message.setSubject("hola cellbox");
			 
			         // set body of the email.
			         message.setText(instaname);
			 
			         // Send email.
			         Transport.send(message);
			         System.out.println("Mail successfully sent");
			      }
			      catch (MessagingException mex)
			      {
			         mex.printStackTrace();
			      }
			}
			
		};

	}

}
