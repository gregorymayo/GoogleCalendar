import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * CalendarModel.java
 * @author Tyler Lorenzi, Gregory Mayo, Ealrada Piroyan
 * @version 1.0 08/02/19
 */
public class CalendarModel {
	private String textToDisplay = ""; // use this inside of the view with a getter 
    private boolean changeInTextDisplay = false;
	private int current; 
	private boolean monthChanged = false;
	private int numberOfDaysInCurrentMonth;
	private ArrayList<ChangeListener> changeListeners = new ArrayList<>();
	private HashMap<LocalDate, ArrayList<Event>> calendar;
	private GregorianCalendar gregorianCal = new GregorianCalendar();
	private boolean ChangeInSelectedDay = false;
	/**
	 * Constructor for the model in the MVC
	 */
	public CalendarModel() {
		numberOfDaysInCurrentMonth = gregorianCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		current = gregorianCal.get(Calendar.DATE);
		calendar = new HashMap<LocalDate, ArrayList<Event>>();
	}
	/**
     * @return true if there has been a change in text display
     */
    public boolean getChangeInDisplay() {
        return changeInTextDisplay;
    }
    /**
     * @return String of events to be displayed to the view
     */
    public String getTextDisplay() {
        return textToDisplay;
    }
    /**
     * gets events on single/given day in order of start time
     * @param d LocalDate to get events of
     */
    public void getEvents(LocalDate d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");      
        changeInTextDisplay = true;
        String eventsForTheDay = "";
        if(!calendar.containsKey(d))
            textToDisplay =   "\n" +  "No events today";
        else {
            sortDay(calendar.get(d));
            ArrayList<Event> events = calendar.get(d);
            for(Event event : events) {
                eventsForTheDay = eventsForTheDay + "                                           " +
                            formatter.format(d) + "\n" + event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " +
                            event.getName() + "\n";
            }
        }
        textToDisplay = eventsForTheDay;
        update();
    }
	/**
	 * Gets events for a given time frame
	 * @param d1 LocalDate - start date
	 * @param d2 LocalDate  - end date
	 */
	public void getEvents(LocalDate d1, LocalDate d2) {
		changeInTextDisplay = true;
		String listOfAllEvents = new String();  
		LocalDate current = d1;
		while (!current.equals(d2)) {
				if (calendar.containsKey(current)) {
					getEventsHelper(current);
                listOfAllEvents = listOfAllEvents + "      "  + "\n" + textToDisplay ; 
				}
			current = current.plusDays(1);
		}
		if (!d1.equals(d2) && calendar.containsKey(d2)) {
            getEventsHelper(d2);
            listOfAllEvents = listOfAllEvents + "      "  + "\n" + textToDisplay + "\n";
        }
        textToDisplay = listOfAllEvents;
        update();
	}
	/**
     * gets events on single/given day in order of start time
     * @param d LocalDate to get events of
     */
    public void getEventsHelper(LocalDate d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");      
        changeInTextDisplay = true;
        String eventsForTheDay = "";
        if(!calendar.containsKey(d))
            textToDisplay =   "\n" +  "No events today";
        else {
            sortDay(calendar.get(d));
            ArrayList<Event> events = calendar.get(d);
            for(Event event : events) {
                eventsForTheDay = eventsForTheDay + "                                           " +
                            formatter.format(d) + "\n" + event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " +
                            event.getName() + "\n";
            }
        }
        textToDisplay = eventsForTheDay;
    }
    /**
	 * Gets events for day
	 * @param d1 LocalDate - start date
	 */
	public void getEventsDay(LocalDate d) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");      
        changeInTextDisplay = true;
        String eventsForTheDay = "";
        if(!calendar.containsKey(d))
            eventsForTheDay = "                                           " + formatter.format(d) + "\n"+"No events today";
        else {
            sortDay(calendar.get(d));
            ArrayList<Event> events = calendar.get(d);
            eventsForTheDay =  "                                           " + formatter.format(d) + "\n";
            for(Event event : events) {
                eventsForTheDay = eventsForTheDay +
                              event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " +
                            event.getName() + "\n";
            }
        }
        textToDisplay = eventsForTheDay;
        update();
	}
	/**
	 * Gets events for week
	 * @param d1 LocalDate - start date
	 * @param d2 LocalDate  - end date
	 */
	public void getEventsWeek(LocalDate d1, LocalDate d2) {
		changeInTextDisplay = true;
		String listOfAllEvents = new String();  
		LocalDate current = d1;
		while (!current.equals(d2)) {
				if (calendar.containsKey(current)) {
					getEventsHelperWeek(current);
                listOfAllEvents = listOfAllEvents   + textToDisplay ; 
				}
			current = current.plusDays(1);
		}
		if (!d1.equals(d2) && calendar.containsKey(d2)) {
            getEventsHelperWeek(d2);
            listOfAllEvents = listOfAllEvents   + textToDisplay ;
        }
        textToDisplay = listOfAllEvents + "\n";
        update();
	}
	public void getEventsHelperWeek(LocalDate d) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        changeInTextDisplay = true;
        String eventsForTheDay = "";
        if(calendar.containsKey(d)){
            sortDay(calendar.get(d));
            ArrayList<Event> events = calendar.get(d);
            eventsForTheDay =  "                                           " + formatter.format(d) + "\n";
            for(Event event : events) {
                eventsForTheDay = eventsForTheDay + event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " + event.getName() + "\n";
            }
            eventsForTheDay = eventsForTheDay + "\n";
        } else {
        	eventsForTheDay = "No event this week!";
        }
        textToDisplay = eventsForTheDay;
    }
	/**
	 * Gets events for month
	 * @param d1 LocalDate - start date
	 * @param d2 LocalDate  - end date
	 */
	public void getEventsMonth(LocalDate d1, LocalDate d2) {
		changeInTextDisplay = true;
		String listOfAllEvents = new String();  
		LocalDate current = d1;
		while (!current.equals(d2)) {
				if (calendar.containsKey(current)) {
					getEventsHelperMonth(current);
                listOfAllEvents = listOfAllEvents   + textToDisplay ; 
				}
			current = current.plusDays(1);
		}
		if (!d1.equals(d2) && calendar.containsKey(d2)) {
            getEventsHelperMonth(d2);
            listOfAllEvents = listOfAllEvents   + textToDisplay + "\n";
        }
        textToDisplay = listOfAllEvents;
        update();
	}
	public void getEventsHelperMonth(LocalDate d) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
		changeInTextDisplay = true;
        String eventsForTheDay = "";
        if(!calendar.containsKey(d)){
        	eventsForTheDay = "No event this month!";
        } else {
            sortDay(calendar.get(d));
            ArrayList<Event> events = calendar.get(d);
            for(Event event : events) {
                eventsForTheDay = eventsForTheDay + "                                           " + formatter.format(d) + "\n" + event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " + event.getName() + "\n";
            }
        }
        textToDisplay = eventsForTheDay;
		
    }
	/**
	 * 
	 * @param date LocalDate to see if there is an overlap on
	 * @param e Event to see if there is an overlap before adding to calendar
	 * @return true if there is a time conflict
	 */
	public boolean overlap(LocalDate date, Event e) {
		if (calendar.containsKey(date)) {
			ArrayList<Event> list = calendar.get(date);
			for (Event current: list) {
				int currentStart = current.getStartTime();
				int checkStart = e.getStartTime();
				int currentEnd = current.getEndTime();
				int checkEnd = e.getEndTime();
				if (currentStart == checkStart || checkEnd == currentEnd || currentStart == checkEnd || checkStart == currentEnd)
					return true;
				else if (currentStart >= checkStart && checkEnd >= currentEnd)
					return true;
				
				else if (checkEnd >= currentStart && currentEnd >= checkStart)
						return true;
			}
		}
		return false;
	}
	/*
	 * This function is to check which month we are going to use
	 * @param int, the number of the month
	 * @return String, the name of the month
	 */
	public String getStringMonth(int i) { // USE THIS IN VIEW
		switch(i) {
			case 1:
				return "January";
			case 2:
				return "Febuary";
			case 3: 
				return "March";
			case 4:
				return "April";
			case 5:
				return "May";
			case 6: 
				return "June";
			case 7: 
				return "July";
			case 8: 
				return "August";
			case 9:
				return "September";
			case 10:
				return "October";
			case 11: 
				return "November";
			case 12: 
				return "December";						
		}
		return "incorrect month input";   //DONT REMOVE: useful for debugging
	}
	/**
	 * sorts a day's events by starting time, since events cannot overlap we could also sort by ending time
	 * @param events - the ArrayList<Event> representing the day's events
	 */
	public void sortDay(ArrayList<Event> events) {
		class order implements Comparator<Event> {
			public int compare(Event first, Event second) {
				return Integer.compare(first.getStartTime(), second.getStartTime());
			}
		}
		Collections.sort(events, new order());
	}
	public void resetChangeInTestDisplay() {
        changeInTextDisplay = false;
    }
	/**
	 * attaches a changeListner to the array containing all the listeners
	 * @param listener ChangeListener
	 */
	public void attach(ChangeListener listener) {
		changeListeners.add(listener);
	}
	/**
	 * update the ChangeListeners by calling statechanged() for each listener
	 */
	public void update() {
		for (ChangeListener l : changeListeners) {
			l.stateChanged(new ChangeEvent(this));
		}
	}
	/**
	 * allows for the selection of a specified day
	 * @param date - the desired day to be selected
	 */
	public void setCurrentlySelectedDay(int date) {
		ChangeInSelectedDay = true;
        current = date;
        update();
	}
	/*
	 * this function is to get if the button is selected or not
	 * @return ChangeInSelectedDay
	 */
	public boolean getChangeInSelectedDay() {
        return ChangeInSelectedDay;
    }
	/*
	 * this function is to reset the day button
	 */
    public void resetChangeInSelectedDay() {
        ChangeInSelectedDay = false;
    }
	/**
	 * Getter for the currently selected date
	 * @return date
	 */
	public int getCurrentlySelectedDay() {
		return current;
	}
	/**
	 * Getter for the current year
	 * @return the current year
	 */
	public int getCurrentYear() {
		return gregorianCal.get(Calendar.YEAR);
	}
	/**
	 * getter for current month 
	 * @return current month
	 */
	public int getCurrentMonth() {
		return gregorianCal.get(Calendar.MONTH);
	}
	/**
	 * getter for day of week as an
	 * @param day - (int) date of the month
	 * @return int representing the day of the week 
	 */
	public int getDayInWeek(int day) {   // note: starts at 1
		gregorianCal.set(Calendar.DAY_OF_MONTH, day);
		return gregorianCal.get(Calendar.DAY_OF_WEEK);
	}
	/**
	 * getter for number of days in a moth
	 * @return number of days in the month
	 */
	public int getNumberOfDaysInMonth() {
		return numberOfDaysInCurrentMonth;
	}
	/**
	 * moves calendar a month forward
	 */
	public void nextMonth() { // edit
		gregorianCal.add(Calendar.MONTH, 1);
		monthChanged = true;
		numberOfDaysInCurrentMonth = gregorianCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		update();
	}
	/**
	 * moves calendar backward a month.
	 */
	public void previousMonth() { // edit
		gregorianCal.add(Calendar.MONTH, -1);
		monthChanged = true;
		numberOfDaysInCurrentMonth = gregorianCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		update();
	}
	public LocalDate getCurrentLocalDate() {
        return LocalDate.of(getCurrentYear(), getCurrentMonth() + 1, getCurrentlySelectedDay());
    }
	/**
	 * moves current day forward by a single day
	 */
	public void nextDay() { // edit
		if (current + 1 > gregorianCal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			nextMonth();
			current = 1;
		}
		else current++;
		update();
	}
	/**
	 * moves current day backward by a single day
	 */
	public void previousDay() { // edit
		if (current == 1) {
			previousMonth();
			current = gregorianCal.getActualMaximum(Calendar.DAY_OF_MONTH);
		}
		else current--;
		update();
	}
	/**
	 * see if the current month has been changed
	 * @return
	 */
	public boolean monthStateChanged() {
		return monthChanged;
	}
	/**
	 * sets the boolean variable representing a change in month back to false
	 */
	public void resetChangeOfMonth() {  // !!!! do we even need this
		monthChanged = false;
	}
	/**
	 * creates an Event
	 * @param name Event's name
	 * @param start Event's start time
	 * @param end Event's end time
	 */
	public void createEvent(String name, int start, int end) { // creates events through  the create button 
		LocalDate day = LocalDate.of(getCurrentYear(), getCurrentMonth() + 1, current);
		Event e = new Event(name, start, end);
		if (calendar.containsKey(day)) {
			ArrayList<Event> list = calendar.get(day);
			list.add(e);
			calendar.put(day, list);
		} else {
			ArrayList<Event> list = new ArrayList<Event>();
			list.add(e);
			calendar.put(day, list);
		}
	}
	/**
	 * adds a specified event to a specified date 
	 * @param d - LocalDate to put event on
	 * @param e - Event to add
	 */
	public void addEvent(LocalDate d, Event e) {
		if (calendar.containsKey(d)) {
			ArrayList<Event> list = calendar.get(d);
			list.add(e);
			calendar.put(d, list);
		} else {
			ArrayList<Event> list = new ArrayList<Event>();
			list.add(e);
			calendar.put(d, list);
		}
	}
	/**
	 * reads input from a file, creates events, and adds events to the calendar
	 * note: only takes regular events and assumes only regular events are in file and only adds events if there is no time conflict
	 * @throws FileNotFoundException 
	 */
	public  void fileReader(String fileName) throws FileNotFoundException {  //rename      // this only inputs regular events
			FileInputStream file = new FileInputStream(fileName);   // let the user specify the file path
			Scanner input = new Scanner(file);
			String name;
			int start;
			int end;
			int startMonth;
			int endMonth;
			int year;
			String[] days;
			int dayInt = 0;
			while (input.hasNextLine()) {
				String current = input.nextLine();
				String[] values = current.split(";");
				name = values[0];
				year = Integer.parseInt(values[1]);
				startMonth = Integer.parseInt(values[2]);
				endMonth = Integer.parseInt(values[3]);
				days = values[4].split(""); 
				start = Integer.parseInt(values[5]);
				end = Integer.parseInt(values[6]);
				LocalDate startDay = LocalDate.of(year, startMonth, 1);				
				LocalDate endDay = LocalDate.of(year, endMonth + 1, 1);				
				Event event = new Event(name, start, end);
				while(!startDay.equals(endDay)) {
					for(int i = 0; i < days.length; i++) {
						switch(days[i]) {
							case "M":
								dayInt = 1;
								break;
							case "T":
								dayInt = 2;
								break;
							case "W":
								dayInt = 3;
								break;
							case "H":
								dayInt = 4;
								break;
							case "F":
								dayInt = 5;
								break;
							case "A":
								dayInt = 6;
								break;
							case "S":
								dayInt = 7;
								break;
						}
						if(startDay.getDayOfWeek().getValue() == dayInt && !(overlap(startDay, event))) 
							 addEvent(startDay, event);
					}
					startDay = startDay.plusDays(1);
				}
			}
			input.close();
	}
} 
