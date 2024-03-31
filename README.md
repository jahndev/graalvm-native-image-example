# gfm-recurring

Simple CLI application implemented in Java, compiled with GraalVM, CodeChallenge Style.

## Prerequisites

Before compiling and running the application, ensure you have the following installed:

- [SDKMAN](https://sdkman.io/)
- [JDK 22 (Oracle GraalVM)](https://www.graalvm.org/downloads/)

#### Use java -version to verify your java configuration:
```bash
$ java -version
java version "22" 2024-03-19
Java(TM) SE Runtime Environment Oracle GraalVM 22+36.1 (build 22+36-jvmci-b02)
Java HotSpot(TM) 64-Bit Server VM Oracle GraalVM 22+36.1 (build 22+36-jvmci-b02, mixed mode, sharing)
```

#### Configure JAVA_HOME
```bash
export JAVA_HOME="/usr/lib/jvm/graalvm-ce-java8-20.0.0"
```

#### Configuration in GNU/Linux

You can use `update-alternatives` command to select GraalVM manually as follows:
```bash
$ sudo update-alternatives --config java
[sudo] password for <user>:
There are 3 choices for the alternative java (providing /usr/bin/java).

Selection      Path                                             Priority   Status
------------------------------------------------------------
  0            /usr/lib/jvm/jdk-21-oracle-x64/bin/java          352337920 auto mode
* 1            /usr/lib/jvm/graalvm-ce-java11-20.0.0/bin/java   2         manual mode
  2            /usr/lib/jvm/java-17-openjdk-amd64/bin/java      1711      manual mode
  3            /usr/lib/jvm/jdk-21-oracle-x64/bin/java          352337920 manual mode

Press <enter> to keep the current choice[*], or type selection number:
```

### Step 1: Compile Java Source Files

To compile the Java source files, follow these steps:

1. Open a terminal.
2. Navigate to the root directory of the project.
3. Run the following command to compile the Java source files into `.class` files:

```bash
$ find src/main/java -name "*.java" -print | xargs javac -d out
```
### Step 2: Generate Native Executable with GraalVM
To generate the native executable using GraalVM, follow these steps:
Make sure you have GraalVM installed and configured.
Open a terminal.
Navigate to the root directory of the project.
Run the following command to generate the native executable:
```bash
$ native-image -cp out Main -o gfm-recurring
```
### Step 3: Running the Application

##### Interactive mode execution:
```bash
 ./gfm-recurring
```

Below an example of use processing a .txt file with commands by param using a pipe                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       for linux command expression:
```bash
$  cat src/Resources/input.txt | ./gfm-recurring
gfm-recurring
Enter command line (press Enter after each line, enter an empty line to exit):
Donors:
Greg: Total: $300.0 Average: $150.0
Janine: Total: $50.0 Average: $50.0

Campaigns:
HelpTheKids: Total: $200.0
SaveTheDogs: Total: $150.0
```

The same result will be gotten executing as follows:
```bash
$ ./gfm-recurring src/Resources/input.txt 
```
## But, Why to implement a CLI without use of popular CLI libs or frameworks like Spring bash or Picocli?

Basically to keep it simple and intended to avoid external dependencies hence reducing resources required to keep it all up to date plus security 
updates etc., reducing compile-time, to only update the Graalvm and JDK if needed.

## Structure tree:
```
.
├── gfm-recurring
├── gfm-recurring.iml
├── main
├── out
│             ├── com
│             │             └── gfm
│             │                 └── fundraising
│             │                     ├── command
│             │                     │             ├── CommandAddCampaign.class
│             │                     │             ├── CommandAddDonation.class
│             │                     │             ├── CommandAddDonor.class
│             │                     │             ├── Command.class
│             │                     │             ├── CommandProcessor.class
│             │                     │             ├── DFACommandParser$1.class
│             │                     │             ├── DFACommandParser$State.class
│             │                     │             └── DFACommandParser.class
│             │                     ├── model
│             │                     │             ├── Campaign.class
│             │                     │             ├── Donation.class
│             │                     │             └── Donor.class
│             │                     └── service
│             │                         └── DonationService.class
│             └── Main.class
├── README.md
└── src
├── main
│             └── java
│                 ├── com
│                 │             └── gfm
│                 │                 └── fundraising
│                 │                     ├── command
│                 │                     │             ├── CommandAddCampaign.java
│                 │                     │             ├── CommandAddDonation.java
│                 │                     │             ├── CommandAddDonor.java
│                 │                     │             ├── Command.java
│                 │                     │             ├── CommandProcessor.java
│                 │                     │             └── DFACommandParser.java
│                 │                     ├── model
│                 │                     │             ├── Campaign.java
│                 │                     │             ├── Donation.java
│                 │                     │             └── Donor.java
│                 │                     └── service
│                 │                         └── DonationService.java
│                 └── Main.java
└── Resources
└── input.txt
```

The Command evaluator implemented in DFACommandParser class was made following a simple Deterministic finite automata 
model to handle states between every reserved word supported by the available commands: Add [Donor|Campaign], Donation, 
returning a Command object to be evaluated by the evaluate method in the CommandProcessor class.

Is important to bold the use of Maps to store Donors and Campaigns such that can be easily accessed by name

Finally, the DonationService class contains the logic needed to store a new Donor, store a new Campaign, add a new
Donation and generate the summary according to the acceptance criteria.

# Initial problem:

You are designing a command-line implementation of a new recurring donation
feature for Tedalia LLC. We want donors to be able to specify a monthly recurring
donation limit and make recurring donations of specified amounts to individual
campaigns. To support this our command-line interface will need to accept input
from STDIN or from a file passed as an argument to the command-line tool. For
example:

``` shell
cat input.txt | gfm-recurring

gfm-recurring input.txt
```

should produce the same output.

The command-line tool will need to accept 3 different commands, one to add a
campaign, one to add a donor, and one to set up a recurring donation for a given
donor to a given campaign. The following commands will set up a recurring monthly
donation of \$100 from a donor named Greg with a limit of \$1000 to a campaign
named SaveTheDogs:

``` text
Add Donor Greg $1000
Add Campaign SaveTheDogs
Donate Greg SaveTheDogs $100
```

Any `Donate` command that would cause the monthly total donation to go over the
limit specified in the `Add Donor` command should be ignored. After the entire
input is consumed by the tool, it should print a summary and exit with a
successful exit code. The summary should consist of each Donor's total donations
for a month, each Donor's average donation size, and each Campaign's total
received donations for a month. There should be separate sections for Donors and
Campaigns, and the Donors and Campaigns should be printed in alphabetical order.
The following input:

``` text
Add Donor Greg $1000
Add Donor Janine $100
Add Campaign SaveTheDogs
Add Campaign HelpTheKids
Donate Greg SaveTheDogs $100
Donate Greg HelpTheKids $200
Donate Janine SaveTheDogs $50
```

Should result in exactly the following output:

``` text
Donors:
Greg: Total: $300 Average: $150
Janine: Total: $50 Average: $50

Campaigns:
HelpTheKids: Total: $200
SaveTheDogs: Total: $150
```

# Requirements

This problem is designed to be possible to do with the standard libraries of
most languages; please do not use external libraries or tools. Exceptions to
these guidelines are test libraries and build tools. Our goal is to
understand how you would solve this problem, not if you know the correct
libraries and tools to find to solve the problem.

Your submission should include:
- The full source for the implementation of your solution to the above problem.
- Tests that verify that your solution works correctly
- An informative README that
    1. explains the steps necessary to build and run your solution.

       You may assume that evaluators have the ability to install the necessary
       tools to build your solution, but you should not assume they have any tools
       installed beyond a terminal and a shell.

    2. describes how your solution should be tested
    3. describes the process and rationale behind the solution you have submitted
- After an evaluator follows your instructions, there should be a single
  executable file named `gfm-recurring` in the submission directory. This file
  can be an executable or a shell script. The evaluator will test that your
  submissions works by invoking the following commands and comparing the output
  to the specified correct output:

```shell
$ cat input.txt | ./gfm-recurring
$ ./gfm-recurring input.txt
```

Please submit your take-home as a zipped file via the linked provided and do not share it on any public pages.

# Evaluation Criteria

We will evaluate your submission in the following areas:

1. Correctness
   Does your solution produce the intended output given the sample input?
2. Domain Modeling
   Is your code structured in a way that related data and/or behavior is
   organized together? Note: this does not necessarily mean that your submission
   must be implemented in an object-oriented style.
3. Best practices
   Does your submission adhere to the practices and idioms of
   your chosen language? Are variables, functions, etc. well named to clarify
   intent? Is your submission free of obvious errors, clean of commented-out or
   unreachable code, simple?
4. Documentation
   Does your submission's README include adequate instructions for
   the evaluator to compile your submission? Does your submission include
   adequate instructions for the evaluator to run any tests you include in your
   submission? Is your submission mostly self-documenting with text
   documentation added to clarify sections that might otherwise be unobvious?

   Does your README describe the process and rationale behind the solution you have submitted