package lab1;

import lab1.adapter.BuildAuto;
import lab1.adapter.CreateAuto;
import lab1.adapter.ProxyAutomobile;
import lab1.adapter.UpdateAuto;
import lab1.exception.AutomotiveException;
import lab1.model.Automotive;
import lab1.util.FileIO;

import java.util.LinkedHashMap;


//Step 8 – Test and Submit (work successful)
public class Driver {
    public static void main(String[] args) throws AutomotiveException {
        //Create and print an Auto instance through CreateAuto interface
        CreateAuto buildAuto = new BuildAuto();
        Automotive auto1 = buildAuto.buildAuto("FordZTW.txt");
        auto1.setName("Auto 1");
        auto1.setOptionChoice("Color", "Sangria Red Clearcoat Metallic");
        auto1.setOptionChoice("Transmission", "manual");
        auto1.setOptionChoice("Brakes", "ABS with Advance Trac");
        auto1.setOptionChoice("Side Impact Air Bags", "present");
        auto1.setOptionChoice("Power Moonroof", "present");

        Automotive auto2 = buildAuto.buildAuto("FordZTW.txt");
        auto2.setName("Auto 2");
        auto2.setOptionChoice("Color", "Liquid Grey Clearcoat Metallic");
        auto2.setOptionChoice("Transmission", "automatic");
        auto2.setOptionChoice("Brakes", "ABS");
        auto2.setOptionChoice("Side Impact Air Bags", "not present");
        auto2.setOptionChoice("Power Moonroof", "present");

        ((ProxyAutomobile)buildAuto).setAutoMotive(auto1);
        ((ProxyAutomobile)buildAuto).setAutoMotive(auto2);

        buildAuto.printAuto();
    }
}
