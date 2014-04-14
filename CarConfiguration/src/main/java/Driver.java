import lab1.exception.AutomobileException;
import lab1.model.Automotive;
import lab1.util.FileIO;

public class Driver {
    public static void main(String[] args) throws AutomobileException {
        //Build Automotive Object from a file.
        Automotive FordZTW = FileIO.readFile("FordZTW.txt");
        //Print attributes before serialization
        FordZTW.print();
        //Serialize the object
        FileIO.serializeAuto(FordZTW, "auto.ser");
        //Deserialize the object and read it into memory.
        Automotive newFordZTW = FileIO.DeserializeAuto("auto.ser");
        //Print new attributes.
        newFordZTW.print();
    }
}

