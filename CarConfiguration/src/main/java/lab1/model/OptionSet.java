package lab1.model;

import lab1.exception.AutomotiveException;

import java.util.Arrays;

class OptionSet {
    private String name;
    private Option [] options;


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

    protected void setOption(int i, String name, float price) throws AutomotiveException {
        try {
            options[i].setName(name);
            options[i].setPrice(price);
        } catch (Throwable th) {
            throw new AutomotiveException("Cannot set option at " + i, th);
        }
    }

    protected void addOption(String name, float price) throws AutomotiveException {
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

    protected float getOptionPrice(String name) throws AutomotiveException {
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

    private int findOption(String name) {
        if(options != null) {
            for(int i = 0; i < options.length; i++) {
                if(options[i].getName().equals(name))
                    return i;
            }
        }

        return -1;
    }

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

    private class Option {
        private String name;
        private float price;

        private Option() {
        }

        protected Option(String name, float price) {
            this.name = name;
            this.price = price;
        }

        protected String getName() {
            return name;
        }

        protected void setName(String name) {
            this.name = name;
        }

        protected float getPrice() {
            return price;
        }

        protected void setPrice(float price) {
            this.price = price;
        }

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
