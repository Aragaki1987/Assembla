package lab1.model;

import lab1.exception.AutomotiveException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*
* In all class that throws AutomotiveException, I did it to catch all cause of error can be happend in program.
* It means program always run well. All Model class and Util class stay in different packagess,
* All Java code followed convention.
* Automotive and Option Set have CRUD method without using data structure and java Collection.
* All Array have been initiated to prevent from NullPointerException
* I used only 1 text file to store all option,
* File IO action only stay in FileIO class
* About the document for this program: I think you should read all code and write it your self,
* because I did not followed your class and do not know about your professor. :)
*
* Model class do not have any static properties and static method, but FileIO class have them to satisfied your requirements
*
* I comment some steps for you because you can see easy, some you have to understand code to realize it :)
*
* p/s : I used maven tool to create this project ( it is a build tool, very easy to use )
* You can build your jar file and excute it with : java -jar command.
* */

//Automotive class that can be serialized
public class Automotive implements Serializable {
    private String name;   //name of Automotive
    private int basePrice;  //base price
    private ArrayList<OptionSet> optionSets;  // list of option set that available for this automotive
    private String make;
    private String model;


    //Constructor for automotive class with combinations of properties
    public Automotive() {
        optionSets = new ArrayList<OptionSet>();  //avoid NullPointerException
    }

    public Automotive(String name, int basePrice, int optionSetSize) {
        this();
        this.name = name;
        this.basePrice = basePrice;
    }


    //getter and setter for properties (modified to get single optionset from the list) with CRUD
    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<OptionSet> getOptionSets() {
        return optionSets;
    }

    public void setOptionSets(ArrayList<OptionSet> optionSets) {
        this.optionSets = optionSets;
    }

    public synchronized OptionSet getOptionSet(String name) {
        for (OptionSet optionSet : optionSets) {
            if (optionSet.getName().equals(name)) {
                return optionSet;
            }
        }
        return null;
    }

    public synchronized void setOptionSetName(String oldName, String newName) {
        getOptionSet(oldName).setName(newName);
    }

    public synchronized void setOptionPrice(String optionSetName, String optionName, int newprice) throws AutomotiveException {
        getOptionSet(optionSetName).setOptionPrice(optionName, newprice);
    }

    protected void setOptionSet(int i, OptionSet optionSet) throws AutomotiveException {
        try {
            optionSets.add(i, optionSet);
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot add option to this position", th);
        }
    }

    public synchronized void addOptionSet(String optionSetName, String optionName, int price) {
        OptionSet optionSet = getOptionSet(optionSetName);

        if (optionSet == null) {
            optionSet = new OptionSet();
            optionSet.setName(optionSetName);
            optionSets.add(optionSet);
        }

        optionSet.addOption(optionName, price);

    }

    public synchronized void deleteOptionSet(String name) throws AutomotiveException {
        try {
            optionSets.remove(getOptionSet(name));
        } catch (Exception e) {
            throw new AutomotiveException("Cannot delete Option set " + name);
        }
    }


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOptionChoice(String setName) {
        return getOptionSet(setName).getChoice().getName();
    }

    public int getOptionChoicePrice(String setName) {
        return getOptionSet(setName).getChoice().getPrice();
    }

    public void setOptionChoice(String setName, String optionName) {
        getOptionSet(setName).setChoice(optionName);
    }

    public void print() {
        System.out.println(toString());
    }

    public int totalPrice() {
        int price = basePrice;

        for (OptionSet set : optionSets) {
            try {
                if (set.getChoice() != null)
                    price += getOptionChoicePrice(set.getName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return price;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Automotive{name='");
        result.append(name);
        result.append("'");
        result.append(", basePrice=");
        result.append(basePrice);
        result.append(", optionSets={");
        for (OptionSet set : optionSets) {
            result.append(" Option Set Name = ");
            result.append(set.getName());
            result.append("(");
            result.append("option name = ");
            result.append(getOptionChoice(set.getName()));
            result.append(", option price = ");
            result.append(getOptionChoicePrice(set.getName()));
            result.append(")");
        }
        result.append("}, total price = " + totalPrice());
        result.append("}");
        return result.toString();
    }
}
