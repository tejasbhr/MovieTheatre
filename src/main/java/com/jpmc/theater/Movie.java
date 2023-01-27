package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class Movie {
    private static final int MOVIE_CODE_SPECIAL = 1;
    private static final LocalTime DISC_START_TIME = LocalTime.of(10, 59, 59, 999);
    private static final LocalTime DISC_END_TIME = LocalTime.of(16, 0, 0, 001);

    private String title;
    private String description;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return this.title;
    }

    public Duration getRunningTime() {
        return this.runningTime;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public int getSpecialCode() {
        return this.specialCode;
    }

    public String getDescription() {
        return this.description;
    }

    public double calculateTicketPrice(Showing showing) {
        return this.ticketPrice - getDiscount(showing);
    }

    private double getDiscount(Showing showing) { 	
        if (showing != null && showing.getStartTime() != null) {
        	double sequenceDiscount = 0;
            //show between 11AM ~ 4pm. 
            if (showing.getStartTime().toLocalTime().isAfter(this.DISC_START_TIME) && showing.getStartTime().toLocalTime().isBefore(this.DISC_END_TIME)) {
            	sequenceDiscount = showing.getMovieFee() * .25; //as 25% discount is greater than 20% discount then the immediate next "else if" condition will not execute
            }
            // special movie.
            else if (this.MOVIE_CODE_SPECIAL == this.specialCode) {
            	double tempDiscount = ticketPrice * 0.2; // if above "if" condition does not execute then execute this "else if" condition
            	sequenceDiscount = sequenceDiscount < tempDiscount ? tempDiscount : sequenceDiscount;
            } 
            if (showing.getSequenceOfTheDay() == 1) { // $3 discount for 1st show
            	double tempDiscount = 3;
            	sequenceDiscount = sequenceDiscount < tempDiscount ? tempDiscount : sequenceDiscount;
            } else if (showing.getSequenceOfTheDay() == 2) {
            	double tempDiscount = 2;
            	sequenceDiscount = sequenceDiscount < tempDiscount ? tempDiscount : sequenceDiscount; // $2 discount for 2nd show
            } else if (showing.getSequenceOfTheDay() == 7) {
            	double tempDiscount = 1;
            	sequenceDiscount = sequenceDiscount < tempDiscount ? tempDiscount : sequenceDiscount;  // $1 discount for 7th show
            }
            return sequenceDiscount;

        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.getTicketPrice(), this.ticketPrice) == 0
                && Objects.equals(this.title, movie.getTitle())
                && Objects.equals(this.description, movie.getDescription())
                && Objects.equals(this.runningTime, movie.getRunningTime())
                && Objects.equals(this.specialCode, movie.getSpecialCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}