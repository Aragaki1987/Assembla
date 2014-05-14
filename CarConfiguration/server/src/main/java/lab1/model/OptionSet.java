package lab1.model;

import lab1.exception.AutomotiveException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

//OptionSet class that can be serialized into stream
public class OptionSet implements Serializable {
    private String name;  // name of OptionSet (color, transmission, ... )
    private ArrayList<Option> options; // list of option for each option set
    private Option choice;


    //constructor for optionset with combination of properties. They have protected modifier to prevent access from outside
    protected OptionSet() {
        options = new ArrayList<Option>();
    }

    protected OptionSet(String name) {
        this();
        this.name = name;
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected synchronized Option getOption(String name) {
        for (Option option : options) {
            if (option.getName().equals(name)) {
                return option;
            }
        }

        return null;
    }

    protected synchronized void setOption(int i, String name, int price) throws AutomotiveException {
        try {
            options.add(i, new Option(name, price));
        } catch (IndexOutOfBoundsException e) {
            throw new AutomotiveException("Position is out of bound");
        }
    }

    protected synchronized void deleteOption(String name) {
        for (Option option : options) {
            if (option.getName().equals(name)) {
                options.remove(option);
            }
        }
    }

    protected synchronized void addOption(String name, int price) {
        options.add(new Option(name, price));
    }

    public Option getChoice() {
        return choice;
    }

    public void setChoice(String optionName) {
        this.choice = getOption(optionName);
    }

    protected ArrayList<Option> getOptions() {
        return options;
    }

    protected void setOptions(ArrayList<Option> options) {
        this.options = options;
    }

    public void setOptionPrice(String optionName, int newprice) throws AutomotiveException {
        try {
            getOption(optionName).setPrice(newprice);
        } catch (NullPointerException e) {
            throw new AutomotiveException("Do not find option with option name = " + optionName, e);
        }
    }

    //toString method uses StringBuilder
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("OptionSet{name='");
        result.append(name);
        result.append("'");
        result.append(", choice=");
        result.append(choice);
        result.append(", options=");
        result.append(options);
        result.append("}");
        return result.toString();
    }

    //Option class is inner class of OptionSet class, can be serialized
    protected class Option implements Serializable {
        private String name; // name of option (auto, manual, ...)
        private int price;  // price of option


        //2 constructors with combination of properties
        private Option() {
        }

        protected Option(String name, int price) {
            this.name = name;
            this.price = price;
        }


        //getter and setter for each properties
        protected String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected int getPrice() {
            return price;
        }

        protected void setPrice(int price) {
            this.price = price;
        }

        //toString method uses StringBuilder
        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("Option{name='");
            result.append(name);
            result.append("'");
            result.append(", price=");
            result.append(price);
            result.append("}");
            return result.toString();
        }
    }

}
