package lab1.model;

import lab1.exception.AutomotiveException;

import java.io.Serializable;
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
    private OptionSet[] optionSets;  // list of option set that available for this automotive


    //Constructor for automotive class with combinations of properties
    public Automotive() {
        optionSets = new OptionSet[0];  //avoid NullPointerException
    }

    public Automotive(String name, int basePrice, int optionSetSize) {
        this.name = name;
        this.basePrice = basePrice;
        optionSets = new OptionSet[optionSetSize];    //avoid NullPointerException

        for (int i = 0; i < optionSets.length; i++) {
            optionSets[i] = new OptionSet();     //avoid NullPointerException
        }
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

    protected OptionSet[] getOptionSets() {
        return optionSets;
    }

    protected void setOptionSets(OptionSet[] optionSets) {
        this.optionSets = optionSets;
    }

    public OptionSet getOptionSet(String name) throws AutomotiveException {
        try {
            return optionSets[findOptionSet(name)];
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot find option.", th);
        }
    }

    protected void setOptionSet(int i, OptionSet optionSet) throws AutomotiveException {
        try {
            optionSets[i] = optionSet;
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot add option to this position", th);
        }
    }

    /*If you notice, array cannot increase size if it has already initiated.
    here is a trick to increase it, copy old array to new array with new capacity.
    This trick is used in ArrayList (Java core). You can look at source code for it.
    */
    public void addOptionSet(String optionSetName, String optionName, int price) throws AutomotiveException {
        int i = findOptionSet(optionSetName);
        OptionSet optSet = null;
        if (i == -1) {
            optSet = new OptionSet(optionSetName);
            int oldSize = optionSets.length;
            optionSets = Arrays.copyOf(optionSets, oldSize + 1);
            optionSets[oldSize] = optSet;
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


    //private helper method
    private int findOptionSet(String name) {
        try {
            if (optionSets != null && optionSets.length > 0) {
                for (int i = 0; i < optionSets.length; i++) {
                    if (optionSets[i].getName().equals(name)) {
                        return i;
                    }
                }
            }
        } catch (Exception e) {
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
