package lab1.adapter;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;
import lab1.model.OptionSet;
import lab1.util.FileIO;

import java.util.LinkedHashMap;
import java.util.Set;

public abstract class ProxyAutomobile {
    private LinkedHashMap<String, Automotive> autoMotives = new LinkedHashMap<String, Automotive>();

    public Automotive buildAuto(String fileName) throws AutomotiveException {
        return new FileIO().readFile(fileName);
    }

    public void printAuto() {
        Set<String> keys = autoMotives.keySet();
        for(String key : keys) {
            System.out.println(autoMotives.get(key));
        }
    }

    public void updateOptionSetName(String autoName, String optionSetName, String newName) throws AutomotiveException {
        autoMotives.get(autoName).setOptionSetName(optionSetName, newName);
    }

    public void updateOptionPrice(String autoName, String optionSetName, String optionName, int newprice) throws AutomotiveException {
        autoMotives.get(autoName).setOptionPrice(optionSetName, optionName, newprice);
    }

    public Automotive getAutoMotive(String name) {
        return autoMotives.get(name);
    }

    public void setAutoMotive(Automotive auto) {
        autoMotives.put(auto.getName(), auto);
    }

    public void deleteAutoMotive(String name) {
        autoMotives.remove(name);
    }
}
