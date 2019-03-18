import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainLottoApp {

    public static void main(String[] args) {
        boolean keepPlaying = true;
        List<String[]> history = new ArrayList();
        Scanner scanner = new Scanner(System.in);

        do {
            Lotto lottoObj = new Lotto(scanner);


            int[][] userLines = lottoObj.getUserLines();
            int[] matchesPerLine = lottoObj.check(userLines);
            String[] winnings = lottoObj.calculateWinnings(matchesPerLine);

            String[] currentGameScore = new String[lottoObj.getRowAmount()];

            for (int i = 0; i < lottoObj.getRowAmount(); i++) {
                String output = "";

                output += "For line " + (i + 1) + " you entered:";
                output += Arrays.toString(userLines[i]);
                output += " - You had " + matchesPerLine[i] + " matches on this line - Winning " + winnings[i];

                System.out.println(output);

                currentGameScore[i] = output;
                
            }

            history.add(currentGameScore);

            System.out.println("Would you like to play again? y/n");

            boolean validSelection = false;

            while (!validSelection) {
                String response = scanner.next();
                switch (response) {
                    case "y":
                    case "Y":
                        validSelection = true;
                        break;
                    case "n":
                    case "N":
                        validSelection = true;
                        keepPlaying = false;
                        break;
                    default:
                        System.out.println("Invalid Selection");
                        System.out.println("Would you like to play again? y/n");
                        break;
                }
            }

        } while (keepPlaying);

        for (int i = 0; i < history.size(); i++) {
            System.out.println("You had the following stats on Game " + (i + 1) + ":");

            String[] gameResults = history.get(i);
            for (int j = 0; j < gameResults.length; j++) {
                System.out.println("\t" + gameResults[j]);
            }

            System.out.println();
        }
    }

}



		
		
