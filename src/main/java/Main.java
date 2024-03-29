import com.gfm.fundraising.command.*;
import com.gfm.fundraising.service.*;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("gfm-recurring \n" +
                "Enter command line (press Enter after each line, enter an empty line to exit):");

        DonationService donationService = new DonationService();
        CommandProcessor commandProcessor = new CommandProcessor(donationService);

        Scanner scanner;
        if(args.length > 0 && args[0].toLowerCase().endsWith(".txt")) {
            File file = new File(args[0]);
            scanner = new Scanner(file);
        } else {
            scanner = new Scanner(System.in);
        }

        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.isEmpty()) {
                System.out.println("Exiting.");
                break;
            }
            commandProcessor.evaluate(DFACommandParser.parseCommand(line));
        }
        donationService.generateSummary();
        scanner.close();
    }
}