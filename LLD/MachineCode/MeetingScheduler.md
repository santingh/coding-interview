### Meeting Scheduler

```java
import java.util.*;
import java.lang.*;
import java.io.*;

// Flexible Meeting Rooms Your company’s office has multiple meeting rooms. Given existing booked time intervals, implement book(start, end) so it checks for overlap and, if free, schedules a new meeting—both in O(log n) time per booking. Design the interval-management system.

class MeetingScheduler{
    
    HashMap<String, User> userMap = new HashMap<>();
    
    HashMap<String, Room> roomMap = new HashMap<>();
    
    NotificationService notification; 
    
    public MeetingScheduler(NotificationService notificationService, HashMap<String, User> userMap, HashMap<String, Room> roomMap){
        this.notificationService = notificationService;
        this.userMap = userMap;
        this.roomMap = roomMap;
    }
    
    public boolean scheduleEvent(Event event, List<User>invitees, Room room) throws Exception{
        // validate room
        if(! roomMap.containsKey(room.getRoomId())){
            throw new Exception("Invalid room.");
        }
        
        if(room.blockRoom(event)){
            // iterate over invitees and send notification
            for(User user : invitees){
                notificationService.notify(user);
            }
            
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isRoomAvailable(String roomId, Interval interval){
        assert roomMap.containsKey(roomId) : "Room not found";
        
        return roomMap.get(roomId).isRoomAvailable(interval);
    }
    
    public boolean isUserAvailable(User userMap){
        
        
    }
    
    
	public static void main (String[] args) throws java.lang.Exception
	{
		User a = new User("a");
		

	}
}

class User{
    private String userName,
    private String emailId,
    MeetingScheduler scheduler; 
    
    EventCalender userCalender;
    
    public void scheduleEvent(String eventId, Interval eventInterval, List<User> invitees, Room room){
        // create Event
        Event event = new Event(eventId, eventInterval, false);
        userCalender.addEvent(event);
        
        scheduler.scheduleEvent(event, invitees, room);
    }
    
    public void acceptEvent(Event event){
        userCalender.addEvent(event);
        scheduler.notifyAcceptance(event);
    }
}

class Room{
    private String roomName,
    private String roomId,
    private final ReentrantLock lock = new ReentrantLock();
    
    EventCalender roomCalender;
    
    public Room(String roomName, String roomId){
        this.roomName = roomName;
        this.roomId = roomId;
        this.roomCalender = new EventCalender();
    }
    
    public boolean blockRoom(Event event){
        
        lock.lock();
        
        try{
            
            if(!isRoomAvailable(event.getEventInterval())){
                return false;
            }
            
            roomCalender.addEvent(event);
            
        } finally {
            lock.unlock();
        }
        
    }
    
    public isRoomAvailable(Interval askedInterval){
        Event prev = roomCalender.getPrevEvent(askedInterval);
        Event next = roomCalender.getNextEvent(askedInterval);
        
        if(prev.isOverlap(askedInterval) || next.isOverlap(nextInterval)){
            return false;
        }
        
        else return true;
    }
}

class EventCalender{
    // Scheduled Events(Interval) - sorted on event startTime
    TreeSet<Event> scheduledEvents = new TreeSet<>(Comparator.comparing(Event::getEventStartTime).thenComparing(Event::getEventEndTime).thenComparing(Event::getEventId));
    
    
    public Event getPrevEvent(Interval interval){
        return scheduledEvents.floor(new Event(interval));
    }
    
    public Event getNextEvent(Interval interval){
        return scheduledEvents.ceiling(interval);
    }
    
    public addEvent(Event){
        scheduledEvents.add(Event);
    }
    
    public removeEvent(Event event){
        scheduledEvents.remove(event);
    }
}

enum RSVP{
    ACCEPT,
    DECLINE,
    MAYBE
}


class Event{
    private String eventId;
    private Interval interval;
    private boolean reccuring;
    private Map<User, RSVP> invitees = new HashMap<>();
    private Room room;
    
    public Event(String eventId, Interval interval, boolean recurring){
        assert eventId == null || eventId.isEmpty() : "eventId is invalid" ;
        assert interval == null : "invalid interval";
        this.eventId = eventId;
        this.interval = interval;
        this.recurring = recurring;
    }
    
    public String getEventId(){
        return this.eventId;
    }
    
    public ZonedDateTime getEventStartTime(){
        return this.interval.getIntervalStartTime();
    }
    
    public ZonedDateTime getEventEndTime(){
        return this.interval.getIntervalEndTime();
    }
    
    public Interval getEventInterval(){
        return this.interval;
    }
    
    @Override
    public boolean equals(Object o){
        
        if(this == o){
            retrun true;
        }
        
        if(! o instanceof Event){
            return false;
        }
        
        this.eventId.equals(((Event) o).getEventId());
    }
    
    @Override
    public int hashCode(){
        return Objects(eventId.hashCode());
    }
}

class Interval{
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    
    public Interval(ZonedDateTime intervalStartTime, ZonedDateTime intervalEndTime){
        assert intervalStartTime.isBefore(intervalEndTime) : "invalid Interval"
        this.startTime = intervalStartTime;
        this.endTime = intervalEndTime;
    }
    
    public ZonedDateTime getIntervalStartTime(){
        return this.startTime;
    }
    
    public ZonedDateTime getIntervalEndTime(){
        return this.endTime;
    }
    
    // return true if there is an overlap 
    public boolean isOverlap(Interval otherInterval){
        
        Interval s1;
        Interval g1;
        
        if(this.intervalStartTime.isBfore(otherInterval.getIntervalStartTime()) || this.intervalStartTime.isEqual(otherInterval.getIntervalStartTime())){
            s1 = this;
            g1 = otherInterval;
        } else {
            s1 = otherInterval;
            g1 = this;
        }
        
        if(s1.getIntervalEndTime().isAfter(g1.getIntervalStartTime()) || g1.getIntervalStartTime().isBefore(s1.getIntervalEndTime())){
            return true;
        }
        
        return false;
    }
}
```
