package com.pet.project.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
 public static Time getCurrTime(){
     return new Time((new Date()).getTime());
 }
    public static Date getTime(String datetime) throws ParseException {
        String format="yyyy-MM-dd hh:mm";
        SimpleDateFormat formatter=new SimpleDateFormat(format);
        return formatter.parse(datetime);
    }
}
