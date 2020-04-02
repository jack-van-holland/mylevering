package com.example.mylevering;

import android.view.View;

public class FreshMenuOption extends MenuOption {
    public static final String[] baseNames = {"Brown Rice", "Farro", "Quinoa", "Spinach", "Mixed Greens", "No Base"};
    public static final String[] spreadNames = {"Sesame Hummus", "Spicy Hummus", "Crazy Feta", "Tzatziki","Harissa", "No Spreads"};
    public static final String[] toppingNames = {"Bell Peppers", "Feta Cheese", "Cucumbers", "Pita Chips", "Olives", "Beans", "Chickpeas",
                                    "Broccoli", "Carrots", "Baby Corn", "Croutons", "No Toppings"};
    public static final String[] proteinNames = {"Falafel", "Herb Chicken", "Tofu", "No Protein"};
    public static final String[] dressingNames = {"Italian", "Caesar", "Ranch", "Tahini", "Balsamic Vinaigrette", "No Dressing"};
    private int base;
    private int[] spreads;
    private int[] toppings;
    private int protein;
    private int dressing;
    public FreshMenuOption(int b, int[] s, int[] t, int p, int d) {
        base = b;
        spreads = s;
        toppings = t;
        protein = p;
        dressing = d;
        title = "Build-Your-Own-Salad";
        setDescription();

    }

    public void setDescription() {
        String baseName = baseNames[base];
        String speadString = "";
        for (int i = 0; i < spreads.length; i++) {
            if (spreads[i] == 1) {
                speadString += spreadNames[i];
                if (i != spreads.length - 1) {
                    speadString += ", ";
                }
            }
        }
        String toppingString = "";
        int countToppings = 0;
        for (int i = 0; i < toppings.length; i++){
            if (toppings[i] == 1) {
                toppingString += toppingNames[i];
                if (i != toppings.length - 1) {
                    toppingString += ", ";
                }
                if (i == 2) {
                    toppingString += "<br>    ";
                }
                countToppings += 1;
            }
        }
        String proteinName = proteinNames[protein];
        String dressingName = dressingNames[dressing];

        description += "<b>Choice of Base</b><br>    ";
        description += baseName;
        description += "<br><b>Choice of Spreads</b><br>    ";
        description += speadString;
        description += "<br><b>Choice of Toppings</b><br>    ";
        description += toppingString;
        description += "<br><b>Choice of Protein</b><br>    ";
        description += proteinName;
        description += "<br><b>Choice of Dressing</b><br>    ";
        description += dressingName;
    }
}
