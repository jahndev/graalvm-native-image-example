package com.gfm.fundraising.command;

public class CommandAddDonor extends Command{
    private Double donationLimit;

    public CommandAddDonor(String name) {
        this.setName(name);
    }

    public void setDonationLimit(Double donationLimit) {
        this.donationLimit = donationLimit;
    }

    public Double getDonationLimit() {
        return donationLimit;
    }
}
