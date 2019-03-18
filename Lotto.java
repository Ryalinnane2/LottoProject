import java.util.*;

public class Lotto {

    private Scanner scanner;
    private final int[] lottoNumbers;
    private int rowAmount;

    public Lotto(Scanner scanner) {
        this.scanner = scanner;
        this.lottoNumbers = new Random().ints(1, 40).distinct().limit(6).toArray();
    }

    public int[] getLottoNumbers() {
        return lottoNumbers;
    }

    public int getRowAmount() {
        return rowAmount;
    }

    public int[][] getUserLines() {
        //set amount of rows for 2D array
    	System.out.println("Please select the number of lines you would like to play between 1 and 3, followed by enter:");
        rowAmount = scanner.nextInt();
        while (rowAmount < 1 || rowAmount > 3) {
            System.out.println("Error - Number must be between 1 and 3");
            System.out.println("Please select the number of lines you would like to play between 1 and 3, followed by enter:");
            rowAmount = scanner.nextInt();
        }

        int[][] userSelections = new int[rowAmount][6];

        //Allow user to enter selections into Array
        for (int currentLine = 0; currentLine < rowAmount; currentLine++) {
            System.out.println("Line " + (currentLine + 1) + ": Please choose 6 numbers between 1 - 40. (Pressing enter after each number)");
            for (int i = 0; i < 6; i++) { // used for loop for user input

                boolean cantContinue = true;
                while (cantContinue) {
                    int userInput = scanner.nextInt();
                    boolean duplicateNo = false;

                    for (int j = 0; j < i; j++) {
                        if (userInput == userSelections[currentLine][j]) {
                            duplicateNo = true;
                        }
                    }

                    if (userInput < 1 || userInput > 40) {
                        System.out.println("Error - Number must be between 1 and 40");
                    } else if (duplicateNo) {
                        System.out.println("You have already entered that number");
                    } else {
                        cantContinue = false;
                        userSelections[currentLine][i] = userInput; // Assigned users value to array
                    }
                }
            }
        }

        return userSelections;
    }

    public int[] check(int[][] userSelections) {
        int[] matchesArray = new int[3];
        for (int currentLine = 0; currentLine < rowAmount; currentLine++) {
            int matches = 0;

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (userSelections[currentLine][i] == lottoNumbers[j]) {
                        matches++;
                    }
                }
            }

            matchesArray[currentLine] = matches;
        }

        return matchesArray;
    }

    public String[] calculateWinnings(int[] matchesPerRow) {

        String[] winnings = new String[matchesPerRow.length];

        for (int i = 0; i < matchesPerRow.length; i++) {
            switch (matchesPerRow[i]) {
                case 0:
                case 1:
                case 2:
                    winnings[i] = "€0";
                    break;
                case 3:
                    winnings[i] = "€100";
                    break;
                case 4:
                    winnings[i] = "€250";
                    break;
                case 5:
                    winnings[i] = "€1000";
                    break;
                case 6:
                    winnings[i] = "Congratulations, You Won the Lotto!!";
                    break;
            }
        }

        return winnings;
    }
}