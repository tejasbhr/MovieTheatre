package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public LocalDateTime getStartTime() {
        return this.showStartTime;
    }

    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    public double getMovieFee() {
        return this.movie.getTicketPrice();
    }

    public int getSequenceOfTheDay() {
        return this.sequenceOfTheDay;
    }

    private double calculateFee(int audienceCount) {
        return this.movie.calculateTicketPrice(this) * audienceCount;
    }

}
