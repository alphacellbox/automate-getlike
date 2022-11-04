package solo;

public class usersinfo {
String profname;
String instaname;
String vpn;
public usersinfo(String profname, String instaname, String vpn) {
	
	this.profname = profname;
	this.instaname = instaname;
	this.vpn = vpn;
}
public String getProfname() {
	return profname;
}
public void setProfname(String profname) {
	this.profname = profname;
}
public String getInstaname() {
	return instaname;
}
public void setInstaname(String instaname) {
	this.instaname = instaname;
}
public String getVpn() {
	return vpn;
}
public void setVpn(String vpn) {
	this.vpn = vpn;
}

}
