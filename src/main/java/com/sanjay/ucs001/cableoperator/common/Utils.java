package com.sanjay.ucs001.cableoperator.common;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class Utils {

    public Long getDaysLeft(LocalDate date) {
        LocalDate currentDate = LocalDate.now();
        long between = ChronoUnit.DAYS.between(currentDate, date);
        if (between < 0) {
            return 0L;
        }
        return between;
    }

}
