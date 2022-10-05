package com.example.VacationPayCalculator.service;

import com.groupstp.isdayoff.IsDayOff;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Service
public class VacationPayCalculatorImpl implements VacationPayCalculator{
    @Override
    public double Calculate(double salary, String vacationStart, String vacationEnd) {
        String regex = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

        if (vacationStart.matches(regex) && vacationEnd.matches(regex) && salary>0){
            double salaryPerDay = salary*12/365;
            double result = salaryPerDay*WeekendCount(CreateDate(vacationStart),CreateDate(vacationEnd));
            return result;
        }
        else
            return -1;
    }

    private Calendar CreateDate(String date){
        String[] arr = date.split("\\.|-|,");
        Calendar Date = GregorianCalendar.getInstance();
        Date.set(Integer.parseInt(arr[2]),Integer.parseInt(arr[1])-1,Integer.parseInt(arr[0]));
        return Date;
    }

    private int WeekendCount(Calendar start, Calendar end){
        IsDayOff isDayOff = IsDayOff.Builder().build();
        int count = 0;
        end.add(Calendar.SECOND, 1);

        while (start.before(end)) {
            if (isDayOff.dayType(start.getTime()).toString()=="WORKING_DAY") {
                count++;
            }
            start.add(Calendar.DAY_OF_MONTH, 1);
        }

        return count;
    }

}
