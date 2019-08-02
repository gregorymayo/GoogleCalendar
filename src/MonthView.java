import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.Container;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/**
 * MonthView.java
 * @author Tyler Lorenzi, Gregory Mayo, Ealrada Piroyan
 * @version 1.0 08/02/19
 */
public class MonthView implements View{
	private CalendarModel model;
    public void layoutText(Container c, String text){
    /*
        JTextArea textArea =  (JTextArea)c;
        textArea.setText(text);
    */
    }
    public void layoutText(Container c, ArrayList<Event> events){
    	/*
        JTextArea textArea =  (JTextArea)c;
        if (events.size()==0){
            textArea.setText("No Events this month!");
        }
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            String eventsForTheMonth = "";
            for(Event event : events) {
                eventsForTheMonth = eventsForTheMonth + "                                           " + formatter.format(event.getDate()) + "\n" + event.getStartTime() + ":00" + " - " + event.getEndTime() + ":00  " +event.getName() + "\n";
            }
            eventsForTheMonth = eventsForTheMonth + "\n";
            textArea.setText(eventsForTheMonth);
        }
        */
    }
    /*
	 * A mutator function to call the view of the day
	 * @param m, the CalendarModel 
	 * @param d, the selected date
	 */
	public void mutate(CalendarModel m, LocalDate date) {
		// TODO Auto-generated method stub
		model = m;
		LocalDate start = LocalDate.of(date.getYear(), date.getMonth(), 1);
		LocalDate end = date.plusMonths(1).withDayOfMonth(1).minusDays(1);
		model.getEventsMonth(start, end);
	}
}
