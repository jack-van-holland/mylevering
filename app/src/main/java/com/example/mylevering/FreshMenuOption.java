package com.example.mylevering;

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
    public FreshMenuOption(int b, int[] s, int[] t, int p, int d, Price pr) {
        base = b;
        spreads = s;
        toppings = t;
        protein = p;
        price = pr;
        dressing = d;
        title = "Build-Your-Own-Salad";
        setDescription();

    }

    public void setDescription() {
        String baseName = baseNames[base];
        String speadString = "";
        for (int i = 0; i < spreads.length; i++) {
            speadString += spreadNames[spreads[i]];
            if (i != spreads.length - 1) {
                speadString += ", ";
            }
        }
        String toppingString = "";
        for (int i = 0; i < toppings.length; i++){
            toppingString += toppingNames[toppings[i]];
            if (i != toppings.length - 1) {
                toppingString += ", ";
            }
            if (i == 2) {
                toppingString += "<br>    ";
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
