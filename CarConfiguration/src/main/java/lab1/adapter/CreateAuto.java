package lab1.adapter;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;

public interface CreateAuto {
    public void buildAuto(String fileName) throws AutomotiveException;
    public void printAuto();
}
