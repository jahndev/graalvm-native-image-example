package com.gfm.fundraising.model;


import java.util.*;

public class Donation {
    private Donor donor;

    private Campaign campaign;

    private double amount;

    private final Date date;

    public Donor getDonor() {
        return donor;
    }

    public void setDonor(Donor donor) {
        this.donor = donor;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Donation(Donor donor, Campaign campaign, double amount) {
        this.donor = donor;
        this.campaign = campaign;
        this.amount = amount;
        this.date = new Date();
    }
}
