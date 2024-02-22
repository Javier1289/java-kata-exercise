package com.codurance.dip;

import java.time.MonthDay;

public class Clock implements ClockRepository{
    public MonthDay monthDay(){
        return MonthDay.now();
    }
}