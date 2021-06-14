package com.daniel.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTime {
    public static String getSystemTimeString(){
        Date date = new Date();
        String format = new SimpleDateFormat("yyyy-MM").format(date);
        return format;
    }
}
