package lab1.model;

import lab1.exception.AutomotiveException;

import java.util.Arrays;

public class Automotive {
    private String name;
    private float basePrice;
    private OptionSet[] optionSets;

    public Automotive() {
        optionSets = new OptionSet[0];
    }

    public Automotive(String name, float basePrice, int optionSetSize) {
        this.name = name;
        this.basePrice = basePrice;
        optionSets = new OptionSet[optionSetSize];

        for(int i = 0; i < optionSets.length; i++) {
            optionSets[i] = new OptionSet();
        }
    }

    public float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(float basePrice) {
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OptionSet[] getOptionSets() {
        return optionSets;
    }

    public void setOptionSets(OptionSet[] optionSets) {
        this.optionSets = optionSets;
    }

    public OptionSet getOptionSet(String name) throws AutomotiveException {
        try {
            return optionSets[findOptionSet(name)];
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot find option.", th);
        }
    }

    public void setOptionSet(int i, OptionSet optionSet) throws AutomotiveException {
        try {
            optionSets[i] = optionSet;
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot add option to this position", th);
        }
    }

    public void addOptionSet(String optionSetName, String optionName, float price) throws AutomotiveException {
        int i = findOptionSet(optionSetName);
        OptionSet optSet = null;
        if(i == -1) {
            optSet = new OptionSet();
        } else {
            optSet = getOptionSet(optionSetName);
        }

        optSet.addOption(optionName, price);
    }

    public void deleteOptionSet(String name) throws AutomotiveException {
        try {
            optionSets[findOptionSet(name)] = null;
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot add option to this position", th);
        }
    }

    public int findOptionSet(String name) {
        if (optionSets != null) {
            for (int i = 0; i < optionSets.length; i++) {
                if (optionSets[i].getName().equals(name)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public void print() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Automotive{name='");
        result.append(name);
        result.append("'");
        result.append(", basePrice=");
        result.append(basePrice);
        result.append(", optionSets=");
        result.append(Arrays.toString(optionSets));
        result.append("}");
        return result.toString();
    }
}
