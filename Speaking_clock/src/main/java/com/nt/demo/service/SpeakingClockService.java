package com.nt.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.nt.demo.exception.InvalidTimeException;

@Service
public class SpeakingClockService {

	public static String convertTimeToWords(String time) throws InvalidTimeException {
        try {
            // Parse input time
            SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm");
            Date date = inputFormat.parse(time);

            // Convert hours and minutes to words
            String hoursInWords = convertToWords(date.getHours());
            String minutesInWords = convertToWords(date.getMinutes());

            // Construct the result
            return "It's " + hoursInWords + " " + (date.getMinutes() == 0 ? "o'clock" : minutesInWords);

        } catch (ParseException e) {
            throw new InvalidTimeException("Invalid time format. Please use HH:mm");
        }
    }

    private static String convertToWords(int number) throws InvalidTimeException {
        if (number < 0 || number > 59) {
            throw new InvalidTimeException("Invalid minutes value");
        }

        String[] units = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine","ten"};
        String[] teens = {"", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty"};

        if (number == 0) {
            return "zero";
        } else if (number <= 10) {
            return units[number];
        } else if (number < 20) {
            return teens[number - 10];
        } else {
            int tensDigit = number / 10;
            int unitsDigit = number % 10;
            return tens[tensDigit] + (unitsDigit > 0 ? " " + units[unitsDigit] : "");
        }
    }

    // You can add additional methods for handling midday and midnight cases if needed.
}
