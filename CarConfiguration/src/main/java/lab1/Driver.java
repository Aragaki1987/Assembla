package lab1;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;
import lab1.util.FileIO;


//Step 8 – Test and Submit (work successful)
public class Driver {


    public static void main(String[] args) throws AutomotiveException {
        //Build Automobile Object from a file.
        Automotive FordZTW = FileIO.readFile(FileIO.FILE_CONFIGURATION);
//Print attributes before serialization
        FordZTW.print();
//Serialize the object
        FileIO.serializeAuto(FordZTW, FileIO.FILE_SERIALIZATION);
//Deserialize the object and read it into memory.
        Automotive newFordZTW = FileIO.DeserializeAuto(FileIO.FILE_SERIALIZATION);
//Print new attributes.
        newFordZTW.print();
    }
}
