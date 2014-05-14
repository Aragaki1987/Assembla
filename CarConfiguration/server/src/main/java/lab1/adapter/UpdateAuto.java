package lab1.adapter;

import lab1.exception.AutomotiveException;

public interface UpdateAuto {
    public void updateOptionSetName(String autoName, String optionSetname, String newName) throws AutomotiveException;

    public void updateOptionPrice(String autoName, String optionSetName, String optionName, int newprice) throws AutomotiveException;
}
