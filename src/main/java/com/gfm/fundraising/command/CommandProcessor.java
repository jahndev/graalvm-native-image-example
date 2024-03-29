package com.gfm.fundraising.command;

import com.gfm.fundraising.model.*;
import com.gfm.fundraising.service.*;
public class CommandProcessor {
    private final DonationService donationService;

    public CommandProcessor(DonationService donationService) {
        this.donationService = donationService;
    }
    public void evaluate(Command command) {
        if (command == null) {
            System.err.println("No command provided.");
            return;
        }
        if(command instanceof CommandAddDonor) {
            donationService.addDonor(
                    new Donor(command.getName(),
                    ((CommandAddDonor)command).getDonationLimit()));
        }else if(command instanceof CommandAddCampaign) {
            donationService.addCampaign(new Campaign(command.getName()));
        }else {
            donationService.addDonation(
                    new Donation(
                            new Donor(((CommandAddDonation)command).getDonorName()),
                            new Campaign(((CommandAddDonation)command).getCampaignName()),
                            ((CommandAddDonation)command).getDonationAmount()
            ));
        }
    }
}
