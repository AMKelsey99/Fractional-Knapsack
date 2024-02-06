package fractionalknapsack;

// Java program to solve fractional Knapsack Problem
import java.util.Arrays;
import java.util.Comparator;
  
// Greedy approach
public class FractionalKnapsack {
    // function to get maximum value
    private static double getMaxValue(int[] wt, int[] val,
                                      int capacity)
    {
        ItemValue[] iVal = new ItemValue[wt.length];
  
        for (int i = 0; i < wt.length; i++) {
            iVal[i] = new ItemValue(wt[i], val[i], i);
        }
  
        // sorting items by value;
        Arrays.sort(iVal, new Comparator<ItemValue>() {
            @Override
            public int compare(ItemValue o1, ItemValue o2)
            {
                int out = o2.cost.compareTo(o1.cost);
                System.out.printf("\n%d",out);
                return out;
            }
        });
  
        double totalValue = 0;
  
        for (ItemValue i : iVal) {
  
            int curWt = (int)i.wt;
            int curVal = (int)i.val;
  
            if (capacity - curWt >= 0) {
                // this weight can be picked while
                capacity = capacity - curWt;
                totalValue += curVal;
                System.out.printf("\n%d %d %f", curWt,curVal,totalValue);
            }
            else {
                // item cant be picked whole
                double fraction
                    = ((double)capacity / (double)curWt);
                totalValue += (curVal * fraction);
                capacity
                    = (int)(capacity - (curWt * fraction));
                break;
            }
        }
  
        return totalValue;
    }
  
    // item value class
    static class ItemValue {
        Double cost;
        double wt, val, ind;
  
        // item value function
        public ItemValue(int wt, int val, int ind)
        {
            this.wt = wt;
            this.val = val;
            this.ind = ind;
            cost = new Double((double)val / (double)wt);
        }
    }
  
    // Driver code
    public static void main(String[] args)
    {
        int[] wt = { 2, 3, 2, 4 };
        int[] val = { 6, 9, 10, 8 };
        int capacity = 5;
  
        double maxValue = getMaxValue(wt, val, capacity);
  
        // Function call
        System.out.println("Maximum value we can obtain = "
                           + maxValue);
    }
}