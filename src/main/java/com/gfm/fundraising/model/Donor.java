package com.gfm.fundraising.model;

import java.util.*;

public class Donor {
    private String name;

    private double donationLimit;

    private List<Donation> donations = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDonationLimit() {
        return donationLimit;
    }

    public void setDonationLimit(double donationLimit) {
        this.donationLimit = donationLimit;
    }

    public Donor(String name, double donationLimit) {
        this.name = name;
        this.donationLimit = donationLimit;
    }

    public Donor(String name) {
        this.name = name;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public double getTotalDonation() {
        return  getDonations().isEmpty() ? 0.0 :
                getDonations().stream().mapToDouble(Donation::getAmount).sum();
    }

    public boolean canDonate(double donationAmount) {
        return  (getTotalDonation() + donationAmount) < donationLimit;
    }
}
