package com.gfm.fundraising.command;

public class CommandAddDonation extends Command{
    private double donationAmount;
    private String donorName;
    private String campaignName;

    public CommandAddDonation(String donorName, String campaignName, double donationAmount) {
        this.donationAmount = donationAmount;
        this.donorName = donorName;
        this.campaignName = campaignName;
    }

    public double getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(double donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }
}
