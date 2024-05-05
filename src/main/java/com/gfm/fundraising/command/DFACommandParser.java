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
        START, ADD, ADD_DONOR, ADD_CAMPAIGN, DONOR_DONATION_LIMIT, DONATE, END, DONOR, CAMPAIGN
    }

    public static Command parseCommand(String inputLine) {
        State currentState = State.START;
        Command command = null;

        for (String token : " ".split(inputLine)) {
            switch (currentState) {
                case START:
                    if (ADD.name().equalsIgnoreCase(token)) {
                        currentState = ADD;
                    } else if (DONATE.name().equalsIgnoreCase(token)) {
                        currentState = DONATE;
                    }
                    break;
                case ADD:
                    if (DONOR.name().equalsIgnoreCase(token)) {
                        currentState = ADD_DONOR;
                    } else if(CAMPAIGN.name().equalsIgnoreCase(token)) {
                        currentState = ADD_CAMPAIGN;
                    }
                    break;
                case ADD_DONOR:
                    if(!token.isEmpty()) {
                        command = new CommandAddDonor(token);
                            ((CommandAddDonor)command).setDonationLimit(Double.valueOf(token.substring(1)));
                        currentState = END;                    }
                    break;
                case ADD_CAMPAIGN:
                    if (!token.isEmpty()) {
                        command = new CommandAddCampaign(token);
                    }
                    currentState = END;
                    break;
                case DONATE:
                    command = new CommandAddDonation(
                            token,
                            inputLine.split(" ")[2],
                            Double.parseDouble(inputLine.split(" ")[3].substring(1))
                    );
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

