
public class Event {

	private String evtName, evtLocation, evtDate, evtTime,evtCity;
	
	public Event(){
		
	}
	
	public Event(String date, String name, String time, String place, String city){
		evtName = name;
		evtLocation = place;
		evtDate = date;		
		evtCity=city;
		evtTime = time;
	}
	
	public String getName(){
		return evtName;		
	}
	
	public String getTime(){
		return evtTime;
	}
	
	public String getLocation(){
		return evtLocation;
	}
	
	public String getDate(){
		return evtDate;
	}
	
	public String getCity(){
		return evtCity;
	}
	
	public void setCity(String c){
		evtCity=c;
	}
	
	public void setName(String n){
		evtName = n;
	}
	
	public void setTime(String t){
		evtTime = t;
	}
	
	public void setLocation(String l){
		evtLocation = l;
	}
	
	public void setDate(String d){
		evtDate = d;
	}
}
