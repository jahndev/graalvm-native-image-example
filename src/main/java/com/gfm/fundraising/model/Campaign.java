package com.gfm.fundraising.model;

import java.util.*;
public class Campaign {
    private String name;
    private double totalDonation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Campaign(String name) {
        this.name = name;
    }

    public double getTotalDonation() {
        return totalDonation;
    }

    public void setTotalDonation(double totalDonation) {
        this.totalDonation = totalDonation;
    }

    public void addDonationAmount(double donationAmount) {
        this.totalDonation += donationAmount;
    }
}
