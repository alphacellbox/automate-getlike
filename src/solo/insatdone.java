package solo;

import java.time.LocalDateTime;
import java.util.Date;

public class insatdone {
String insta;

	int done;
	LocalDateTime  time;
	
	public insatdone(String insta, int done, LocalDateTime time) {
		super();
		this.insta = insta;
		this.done = done;
		this.time = time;
	}
	
	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public String getInsta() {
		return insta;
	}
	public void setInsta(String insta) {
		this.insta = insta;
	}
	public int getDone() {
		return done;
	}
	public void setDone(int done) {
		this.done = done;
	}

}
