import Lab1.model.Automotive;

class Driver {
    public static void main(String[] args) {
        //Build Automotive Object from a file.
        Automotive FordZTW = Lab1.autoutil.FileIO.readFile("FordZTW.txt");
        //Print attributes before serialization
        FordZTW.print();
        //Serialize the object
        Lab1.autoutil.FileIO.serializeAuto(FordZTW);
        //Deserialize the object and read it into memory.
        Automotive newFordZTW = Lab1.autoutil.FileIO.DeserializeAuto("auto.ser");
        //Print new attributes.
        newFordZTW.print();
    }
}

