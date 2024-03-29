package com.gfm.fundraising.command;

public class DFACommandParser {
    private enum State {
        START, ADD, ADD_DONOR, ADD_CAMPAIGN, DONOR_DONATION_LIMIT, DONATE, END
    }

    public static Command parseCommand(String inputLine) {
        State currentState = State.START;

        Command command = null;
        for (String token : inputLine.split(" ")) {
            switch (currentState) {
                case START:
                    if (token.equalsIgnoreCase("Add")) {
                        currentState = State.ADD;
                    } else if (token.equalsIgnoreCase("Donate")) {
                        currentState = State.DONATE;
                    }
                    break;
                case ADD:
                    if (token.equalsIgnoreCase("Donor")) {
                        currentState = State.ADD_DONOR;
                    } else if(token.equalsIgnoreCase("Campaign")) {
                        currentState = State.ADD_CAMPAIGN;
                    }
                    break;
                case ADD_DONOR:
                        if(!token.isEmpty()) {
                            command = new CommandAddDonor(token);
                            currentState = State.DONOR_DONATION_LIMIT;
                        }
                    break;
                case DONOR_DONATION_LIMIT:
                    ((CommandAddDonor)command).setDonationLimit(Double.valueOf(token.substring(1)));
                    currentState = State.END;
                    break;
                case ADD_CAMPAIGN:
                    if (!token.isEmpty()) {
                        command = new CommandAddCampaign(token);
                    }
                    currentState = State.END;
                    break;
                case DONATE:
                    command = new CommandAddDonation(
                            token,
                            inputLine.split(" ")[2],
                            Double.parseDouble(inputLine.split(" ")[3].substring(1))
                    );
                    currentState = State.END;
                    break;
                case END:
                    // Command already detected, ignore remaining characters
                    return command;
            }
        }

        // If the loop completes without reaching END state, it means the command is invalid
        if (currentState != State.END) {
            System.out.println("Invalid command");
        }

        return command;
    }
}

