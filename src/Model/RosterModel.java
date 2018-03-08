package Model;

import Model.Shift.Shift;
import Model.Staff.SECTION.*;
import sun.swing.SwingUtilities2;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static Model.Staff.SECTION.*;

/**
 *  This class captures the notion of an entire RosterModel.
 *  It is comprised of several smaller lightweight classes
 *  that combine to represent a roster.
 */
public class RosterModel extends ArrayList<ArrayList<Shift>>{

    public static final int DAYS_IN_WEEK = 7;

    /**
     *  Stores the day names so that they can be manipulated (i.e shortened) easily
     */
      public enum DAY_NAMES {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
    }

    /**
     * This stores the staff for this iteration of the roster
     */
    private ArrayList<Staff> staff = new ArrayList<>();

    public RosterModel(){
        // Initialize the second dimension of the roster
        for (int i=0; i<DAYS_IN_WEEK; i++){
            this.add(new ArrayList<>());
        }
    }

    /**
     *  The normal constructor for the roster class;
     */
    public RosterModel(String str){
        // Initialize the second dimension of the roster
        for (int i=0; i<DAYS_IN_WEEK; i++){
            this.add(new ArrayList<>());
        }

        String[] staffToAdd = {"Az", "Jack", "Harry", "Jesse", "Jerome", "Bella"};
        String[] startTimes = {"9am", "10am", "11am", "12pm", "3pm", "4pm", "5pm"};
        String[] endTimes = {"3pm", "4pm", "5pm", "7pm", "9pm", "10pm", "TC"};

        for (int i=0; i<staffToAdd.length - 1; i++){
            staff.add(new Staff(staffToAdd[i]));
        }

        // Initialise the second dimension of the roster
            // TODO: 3/7/18 properly implement this
        ArrayList<Shift> test_Shifts = new ArrayList<>();

        for (Staff s: staff){
            String randomStartTime = startTimes[(int) Math.random()*(startTimes.length - 1)];
            String randomEndTime = endTimes[(int) Math.random()*(endTimes.length - 1)];
            Staff.SECTION randomSection = null;

            // Finds a random section for a staff member
            for (Staff.SECTION sec: Staff.SECTION.values()){
                if (Math.random() > 0.5) {
                    randomSection = sec;
                    break;
                }
            }
            test_Shifts.add(new Shift(s,randomStartTime, randomEndTime, randomSection));
        }

        // Adds each day of shifts at a time
        for (int i=0; i<DAYS_IN_WEEK; i++) {
            for (Shift s : test_Shifts) {
                this.get(i).add(s);
            }
        }
    }

    /**
     * This method sorts the roster by sections
     * @param section to be sorted into
     * @return only shifts from this section
     */
    public RosterModel getRosterSection(Staff.SECTION section){
        /**
         * This ia the output roster that comprises of only shifts
         * that belong to the section parameter
         */
        RosterModel output = new RosterModel();

        /**
         * This nested loop traverses the roster and isolates the
         * desired shifts that belong to the corresponding section
         */
        for (int col=0; col<DAYS_IN_WEEK; col++){ // Iterates through the columns (days)
            ArrayList<Shift> shiftList = this.get(col);
            for (Shift s: shiftList){
                // If this shift corresponds to the correct section
                if (s.getSection().equals(section))
                    output.get(col).add(s); // Add this shift to the collection
            }
        }

        /**
         * This is the completed RosterModel which only contains the section
         * specified by the parameters
         */
        return output;
    }

    public Shift getShift(int i){
        return this.get(i).get(0);
    }

    public int rows(){
        return this.get(0).size();
    }

    public int cols(){
        return this.size();
    }

    public ArrayList<ArrayList<Shift>> getRoster() {
        return this;
    }
}
