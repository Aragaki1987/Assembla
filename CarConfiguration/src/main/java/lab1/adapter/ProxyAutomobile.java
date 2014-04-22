package lab1.adapter;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;
import lab1.model.OptionSet;
import lab1.util.FileIO;

public abstract class ProxyAutomobile{
    private Automotive a1;

    public void buildAuto(String fileName) throws AutomotiveException {
        a1 =  new FileIO().readFile(fileName);
    }

    public void printAuto() {
        System.out.println(a1);
    }

    public void updateOptionSetName(String optionSetName, String newName) throws AutomotiveException {
        a1.setOptionSetName(optionSetName, newName);
    }

    public void updateOptionPrice(String optionSetName, String optionName, int newprice) throws AutomotiveException {
        a1.setOptionPrice(optionSetName, optionName, newprice);
    }

    public Automotive getA1() {
        return a1;
    }

    public void setA1(Automotive a1) {
        this.a1 = a1;
    }
}
