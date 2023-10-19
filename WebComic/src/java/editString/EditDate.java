/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package editString;

import dal.CRUDDAO;
import dal.StoryDAO;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import model.Author;
import model.Story;

/**
 *
 * @author HP
 */
public class EditDate {

    public String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentTime = formatter.format(date);
        return currentTime;

    }

    public String getDistanceDate(String s1, String s2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(s1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(s2, formatter);

        Duration duration = Duration.between(dateTime1, dateTime2);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        if (days > 0) {
            if (days == 1) {
                return "1 day ago";
            }
            return days + " days ago";
        }
        if (days == 0 && hours > 0) {
            if (hours == 1) {
                return "1 hours ago";
            }
            return hours + " hours ago";

        }
        if (days == 0 && hours == 0) {
            return "Few minutes ago";
        }

        return null;
    }
      public long getDistanceDate2(String s1, String s2) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime1 = LocalDateTime.parse(s1, formatter);
        LocalDateTime dateTime2 = LocalDateTime.parse(s2, formatter);

        Duration duration = Duration.between(dateTime1, dateTime2);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
return days;
    }


    public static void main(String[] args) {
        CRUDDAO cd = new CRUDDAO();
        StoryDAO sd = new StoryDAO();
        EditDate ed = new EditDate();
        String currentTime = ed.getCurrentTime();
        System.out.println(ed.getDistanceDate2("2023-07-08 19:23:55", currentTime));
       
    }
}
