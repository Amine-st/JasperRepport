package com.oauth.labapi.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormate {
    public  Date getDate(String str,String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(str);
            String output = dateFormat.format(date);

            return date;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
