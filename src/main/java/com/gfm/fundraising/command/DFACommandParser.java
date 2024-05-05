package com.gfm.fundraising.command;

import static com.gfm.fundraising.command.DFACommandParser.State.ADD;
import static com.gfm.fundraising.command.DFACommandParser.State.CAMPAIGN;
import static com.gfm.fundraising.command.DFACommandParser.State.DONATE;
import static com.gfm.fundraising.command.DFACommandParser.State.DONOR;
import static com.gfm.fundraising.command.DFACommandParser.State.ADD_DONOR;
import static com.gfm.fundraising.command.DFACommandParser.State.ADD_CAMPAIGN;
import static com.gfm.fundraising.command.DFACommandParser.State.END;

public class DFACommandParser {
    public enum State {
        START, ADD, ADD_DONOR, ADD_CAMPAIGN, DONATE, END, DONOR, CAMPAIGN;

        public boolean equals(String str) {
            return name().equalsIgnoreCase(str);
        }
    }

    public static Command parseCommand(String inputLine) {
        State currentState = State.START;
        Command command = null;

        for (String token : inputLine.split(" ")) {
            switch (currentState) {
                case START:
                    if (ADD.equals(token)) {
                        currentState = ADD;
                    } else if (DONATE.equals(token)) {
                        currentState = DONATE;
                    }
                    break;
                case ADD:
                    if (DONOR.equals(token)) {
                        currentState = ADD_DONOR;
                    } else if(CAMPAIGN.equals(token)) {
                        currentState = ADD_CAMPAIGN;
                    }
                    break;
                case ADD_DONOR:
                    if(command == null) {
                        command = new CommandAddDonor(token);
                        currentState = ADD_DONOR;
                    } else {
                        ((CommandAddDonor)command).setDonationLimit(Double.valueOf(token.substring(1)));
                        currentState = END;
                    }
                    break;
                case ADD_CAMPAIGN:
                    if (!token.isEmpty()) {
                        command = new CommandAddCampaign(token);
                    }
                    currentState = END;
                    break;
                case DONATE:
                    //if(inputLine.matches("^[\\p{L} .'-]+$")) {
                        String[] tokens = inputLine.split(" ");
                        String campaignName = tokens[2];
                        double donationAmount = Double.parseDouble(tokens[3].substring(1));
                        command = new CommandAddDonation(token, campaignName, donationAmount);
                   // }
                    currentState = END;
                    break;
                case END:
                    // Command already detected, ignore remaining characters
                    return command;
                default:
                    throw new IllegalStateException("Unexpected value: " + currentState);
            }
        }

        // If the loop completes without reaching END state, it means the command is invalid
        if (currentState != END) {
            System.out.println("Invalid command");
        }

        return command;
    }
}

