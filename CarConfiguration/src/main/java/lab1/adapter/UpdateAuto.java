package lab1.adapter;

import lab1.exception.AutomotiveException;

public interface UpdateAuto {
    public void updateOptionSetName(String optionSetname, String newName) throws AutomotiveException;

    public void updateOptionPrice(String optionSetName, String optionName, int newprice) throws AutomotiveException;
}
