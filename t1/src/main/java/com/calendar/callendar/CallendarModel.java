package com.calendar.callendar;

import java.time.Month;
import java.time.YearMonth;
import java.util.Calendar;

// NOTE: java.util.Calendar uses 0-11 to index the months
// but java.time uses 1-12. Since java.time handles the
// computation with the indices, which are obtained through
// java.util.Calendar, I had to add 1 to the month index from java.util.Calendar

public class CallendarModel {
    Calendar dateToday; // date today (year, month, day)
    Calendar dateSelected; // used to display correct month and year

    public CallendarModel() {
        this.dateToday = Calendar.getInstance();
        this.dateSelected = Calendar.getInstance();
    }

    public Calendar getDateToday() {
        return this.dateToday;
    }

    public Calendar getDateSelected() {
        return this.dateSelected;
    }

    public String getMonthSelected() {
        int selectedMonth = this.dateSelected.get(Calendar.MONTH) + 1;
        return Month.of(selectedMonth).name();
    }

    public String getYearSelected() {
        return "" + this.dateSelected.get(Calendar.YEAR);
    }

    public int getSelectedMonthDays() {
        // returns the number of days in the month selected
        int selectedYear = this.dateSelected.get(Calendar.YEAR);
        int selectedMonth = this.dateSelected.get(Calendar.MONTH) + 1;

        YearMonth ymObj = YearMonth.of(selectedYear, selectedMonth);
        return ymObj.lengthOfMonth();
    }

    public void decrementMonth() {
        this.dateSelected.add(Calendar.MONTH, -1);
    }

    public void incrementMonth() {
        this.dateSelected.add(Calendar.MONTH, 1);
    }
}
