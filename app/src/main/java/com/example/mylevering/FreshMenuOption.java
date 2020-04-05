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
    private int spreadCount;
    private int[] toppings;
    private int toppingCount;
    private int protein;
    private int dressing;

    public FreshMenuOption(int b, int[] s, int sc, int[] t, int tc, int p, int d) {
        base = b;
        spreads = s;
        spreadCount = sc;
        toppings = t;
        toppingCount = tc;
        protein = p;
        dressing = d;
        price = "7.29";
        title = "Build-Your-Own-Salad";
        setDescription();
    }

    public void setDescription() {
        String baseName = baseNames[base];
        String spreadString = "";
        int spreadsAdded = 0;
        for (int i = 0; i < spreads.length; i++) {
            if (spreads[i] == 1) {
                spreadString += spreadNames[i];
                if (spreadsAdded != spreadCount - 1) {
                    spreadString += ", ";
                }
                spreadsAdded++;
            }
        }
        String toppingString = "";
        int toppingsAdded = 0;
        for (int i = 0; i < toppings.length; i++){
            if (toppings[i] == 1) {
                toppingString += toppingNames[i];
                if (toppingsAdded != toppingCount - 1) {
                    toppingString += ", ";
                }
                if (toppingsAdded == 2) {
                    toppingString += "<br>&emsp;";
                }
                toppingsAdded += 1;
            }
        }
        String proteinName = proteinNames[protein];
        String dressingName = dressingNames[dressing];

        description += "<b>Choice of Base</b><br>&emsp;";
        description += baseName;
        description += "<br><b>Choice of Spreads</b><br>&emsp;";
        description += spreadString;
        description += "<br><b>Choice of Toppings</b><br>&emsp;";
        description += toppingString;
        description += "<br><b>Choice of Protein</b><br>&emsp;";
        description += proteinName;
        description += "<br><b>Choice of Dressing</b><br>&emsp;";
        description += dressingName;
    }
}
