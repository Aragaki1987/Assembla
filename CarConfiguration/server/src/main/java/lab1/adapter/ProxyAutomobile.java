package lab1.adapter;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;
import lab1.model.OptionSet;
import lab1.server.BuildCarModelOptions;
import lab1.util.FileIO;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Set;

public abstract class ProxyAutomobile {
    public static LinkedHashMap<String, Automotive> autoMotives = new LinkedHashMap<String, Automotive>();

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

    public Automotive buildAutoMotive(InputStream inputStream) throws AutomotiveException {
        try {
            return new BuildCarModelOptions().buildAutoMotive(inputStream);
        } catch (IOException e) {
            throw new AutomotiveException("Cannot build Automotive", e);
        } catch (ClassNotFoundException e) {
            throw new AutomotiveException("Cannot build Automotive", e);
        }
    }
}
