package lab1.model;

import lab1.exception.AutomotiveException;

import java.io.Serializable;
import java.util.Arrays;

//OptionSet class that can be serialized into stream
class OptionSet implements Serializable {
    private String name;  // name of OptionSet (color, transmission, ... )
    private Option [] options; // list of option for each option set


    //constructor for optionset with combination of properties. They have protected modifier to prevent access from outside
    protected OptionSet() {
        options = new Option[0];
    }

    protected OptionSet(String name) {
        this();
        this.name = name;
    }

    protected OptionSet(String name, int count) {
        this.name = name;
        options = new Option[count];
        for(int i = 0; i < options.length; i++) {
            options[i] = new Option();
        }
    }


    //getter and setter ( modified by hand to get and set single option from option list ) with all CRUD
    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected Option[] getOptions() {
        return options;
    }

    protected void setOptions(Option[] options) {
        this.options = options;
    }

    protected void setOption(int i, String name, int price) throws AutomotiveException {
        try {
            options[i].setName(name);
            options[i].setPrice(price);
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot set option at " + i, th);
        }
    }

    protected void addOption(String name, int price) throws AutomotiveException {
        try {
            Option option = new Option(name, price);
            int oldSize = options.length;
            options = Arrays.copyOf(options, oldSize + 1);
            options[oldSize] = option;
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot add option ", th);
        }
    }

    protected Option getOption(String name) throws AutomotiveException {
        try {
            return options[findOption(name)];
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot find option." + name, th);
        }
    }

    protected int getOptionPrice(String name) throws AutomotiveException {
        try {
            return getOption(name).getPrice();
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot find option " + name, th);
        }
    }

    protected void deleteOption(String name) throws AutomotiveException {
        try {
            options[findOption(name)] = null;
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot delete option " + name, th);
        }
    }


    //private helper method
    private int findOption(String name) {
        if(options != null) {
            for(int i = 0; i < options.length; i++) {
                if(options[i].getName().equals(name))
                    return i;
            }
        }

        return -1;
    }

    //toString method uses StringBuilder
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("OptionSet{name='");
        result.append(name);
        result.append("'");
        result.append(", options=");
        result.append(Arrays.toString(options));
        result.append("}");
        return result.toString();
    }

    //Option class is inner class of OptionSet class, can be serialized
    private class Option implements Serializable {
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
