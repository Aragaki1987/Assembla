package lab1;

import lab1.adapter.BuildAuto;
import lab1.adapter.CreateAuto;
import lab1.adapter.UpdateAuto;
import lab1.exception.AutomotiveException;
import lab1.model.Automotive;
import lab1.util.FileIO;


//Step 8 – Test and Submit (work successful)
public class Driver {
    public static void main(String[] args) throws AutomotiveException {
       //Create and print an Auto instance through CreateAuto interface
        CreateAuto buildAuto = new BuildAuto();
        buildAuto.buildAuto("FordZTW.txt");
        buildAuto.printAuto();

        //Update properties
        UpdateAuto updateAuto = (UpdateAuto)buildAuto;
        updateAuto.updateOptionPrice("Color", "Fort Knox Gold Clearcoat Metallic", 100);
        updateAuto.updateOptionSetName("Color", "ColorType");

        //print with new properties
        buildAuto.printAuto();
    }
}
