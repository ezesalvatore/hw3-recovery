// Finish the head comment with Abstract, ID, Name, and Date.
/*
 * Title: hw3-3
 * Abstract: This is the KnapSack problem. In the KnapSack problem you find the best capacity for the best value
 * Name: Salvatore Eze
 * Date: 02/23/2024
 */

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> index = new ArrayList<>();

        int size = scanner.nextInt();

        Sack sack;

        BestSack bestSack;

        ArrayList<Sack> sackArrayList = new ArrayList<>();

        ArrayList<Integer> binary = new ArrayList<>();

        ArrayList<ArrayList<Integer>> possibleSack = new ArrayList<>();

        ArrayList<Integer> possibleValue = new ArrayList<>();

        if (size > 15) {
            System.out.println("Invalid items");
        }

        int exponent = 1;

        int maxCapacity = scanner.nextInt();

        for (int i = 0; i < size; i++) {
            int capacity = scanner.nextInt();
            int value = scanner.nextInt();

            sack = new Sack(capacity, value);

            sackArrayList.add(sack);

            binary.add(0);

            exponent *= 2;

            index.add(capacity);
        }

        int hit = 0;

        for (int i = 0; i < exponent; i++) {

            int temp = i;

            // Get combination
            for (int b = size - 1; b >= 0; b--) {
                int bit = temp % 2;
                binary.set(b, bit);
                temp /= 2;
            }

            // Addition
            int sumCapacity = 0;
            int sumValue = 0;

            ArrayList<Integer> fatsack = new ArrayList<>();

            for (int s = 0; s < sackArrayList.size(); s++) {
                if (binary.get(s) == 1) {
                    sumCapacity += sackArrayList.get(s).getCapacity();
                    sumValue += sackArrayList.get(s).getValue();
                    fatsack.add(sackArrayList.get(s).getCapacity());
                }
            }

            // Check if combination if possible
            if (sumCapacity <= maxCapacity) {
                possibleSack.add(new ArrayList<>());

                for(int p = hit; p < possibleSack.size(); p++){
                    for(int f = 0; f < fatsack.size(); f++){
                        possibleSack.get(p).add(fatsack.get(f));
                    }
                }
                possibleValue.add(sumValue);
                hit++;
            }
        }



        ArrayList<BestSack> potentialSack = new ArrayList<>();
        for (int i = 0; i < possibleSack.size(); i++) {
            ArrayList<Integer> currentSack = possibleSack.get(i);
            int currentValue = possibleValue.get(i);

            bestSack = new BestSack(currentSack, currentValue);
            potentialSack.add(bestSack);
        }

        //Get max possible Value
        int maxSack = 0;
        for(int i = 0; i < potentialSack.size(); i++){
            if(potentialSack.get(i).getValue() > maxSack){
                maxSack = potentialSack.get(i).getValue();
            }
        }

        //Check if there's multiple solutions and get solution

        int counter = 0;


        BestSack solution = null;

        for(int i = 0; i < potentialSack.size(); i++){
            if(potentialSack.get(i).getValue() == maxSack){
                counter++;
                solution = potentialSack.get(i);
            }
        }

        boolean multipleChoices = false;

        if(counter > 1){
            multipleChoices = true;
        }

        // Print out everything

        System.out.print("Item:");
        if (solution.getItemSize() == 0) {
            System.out.println("No valid solution");
        } else if (multipleChoices) {
            System.out.println("Multiple solutions");
        } else {
            int indexSkipper = 0;
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < solution.getItemSize(); i++){
                for(int j = indexSkipper; j < index.size(); j++){
                    if( solution.getItems().get(i) == index.get(j)){
                        sb.append(j + 1).append(" ");
                        indexSkipper++;
                        break;
                    }
                }
            }
            String results = sb.toString().trim();

            System.out.println(results);

        }

        System.out.println("Capacity:" + solution.getCapicityNumber());

        System.out.print("Value:" + solution.getValue());

    }
}
