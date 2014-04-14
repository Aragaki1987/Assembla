package Lab1.model;

public class OptionSet {
    private String name;
    private Option [] options;


    public OptionSet() {
    }

    public OptionSet(String name) {
        this.name = name;
    }

    public OptionSet(String name, int count) {
        this.name = name;
        options = new Option[count];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Option[] getOptions() {
        return options;
    }

    public void setOptions(Option[] options) {
        this.options = options;
    }

    public void setOption(int i, String name, int price) {
        if(options[i] != null) {
            options[i].setName(name);
            options[i].setPrice(price);
        }
    }

    public Option getOption(String name) {
        return options[findOption(name)];
    }

    public int getOptionPrice(String name) {
        return getOption(name).getPrice();
    }

    private int findOption(String name) {
        for(int i = 0; i < options.length; i++) {
            if(options[i].getName().equals(name))
                return i;
        }

        return -1;
    }

    private class Option {
        private String name;
        private int price;

        Option(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

}
