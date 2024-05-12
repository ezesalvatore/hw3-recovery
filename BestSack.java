import java.util.ArrayList;

public class BestSack {
    ArrayList<Integer> items;

    int value;

    public BestSack(ArrayList<Integer> items, int value){
        this.items = items;
        this.value = value;
    }

    public ArrayList<Integer> getItems(){
        return items;
    }

    public int getValue() {
        return value;
    }

    public int getCapicityNumber(){
        int sum = 0;
        for(int i = 0; i < items.size(); i++){
            sum += items.get(i);
        }
        return sum;
    }

    public int getItemSize(){
        return items.size();
    }

}
