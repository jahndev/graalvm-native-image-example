package com.gfm.fundraising.service;

import com.gfm.fundraising.model.*;

import java.util.*;
import java.util.stream.*;
public class DonationService {
    private final Map<String, Donor> donors = new HashMap<>();
    private final Map<String, Campaign> campaigns = new HashMap<>();
    public void addDonor(Donor donor) {
        donors.putIfAbsent(donor.getName(), donor);
    }
    public void addCampaign(Campaign campaign) {
        campaigns.putIfAbsent(campaign.getName(), campaign);
    }
    public void addDonation(Donation donation) {
        if(donors.get(donation.getDonor().getName()).canDonate(donation.getAmount())) {
            //update campaigns.
            campaigns.get(donation.getCampaign().getName())
                    .addDonationAmount(donation.getAmount());

            //update donor.
            donors.get(donation.getDonor().getName())
                    .getDonations()
                    .add(donation);
        }
    }

    public void generateSummary() {
        System.out.println("Donors:");
        donors.keySet().stream()
                .sorted(String::compareToIgnoreCase)
                .forEach(donorName -> {
                    Donor donor = donors.get(donorName);

                    double totalDonation = donor.getTotalDonation();

                    double averageDonation = donor.getDonations().isEmpty() ? 0 :
                            totalDonation / donor.getDonations().size();

                    System.out.printf("%s: Total: $%.2f Average: $%.2f%n", donorName, totalDonation, averageDonation);
                });

        System.out.println("\nCampaigns:");
        campaigns.keySet().stream()
                .sorted(String::compareToIgnoreCase)
                .forEach(campaignName -> {
                    System.out.printf("%s: Total: $%.2f", campaignName, campaigns.get(campaignName).getTotalDonation());
                });
    }
}
