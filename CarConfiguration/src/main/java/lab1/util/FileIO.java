package lab1.util;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;

import java.io.*;

public class FileIO {
    public static Automotive readFile(String fileLocation) throws AutomotiveException {
        Automotive automotive = new Automotive();
        try {
            FileReader file = new FileReader(fileLocation);
            BufferedReader buff = new BufferedReader(file);
            boolean eof = false;
            while (!eof) {
                String line = buff.readLine();
                if (line == null) {
                    eof = true;
                } else {
                    if (line.contains("name")) {
                        automotive.setName(line.split("=")[1]);
                    } else if (line.contains("basePrice")) {
                        automotive.setBasePrice(Integer.parseInt(line.split("=")[1]));
                    } else {
                        String[] temp = line.split(",");
                        if (temp.length == 3)
                            automotive.addOptionSet(temp[0], temp[1], Integer.parseInt(temp[2]));
                    }
                }
            }
            buff.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomotiveException("Cannot read configuration file", e);
        }

        return automotive;
    }

    public static void serializeAuto(Automotive fordZTW, String fileLocation) throws AutomotiveException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("auto.ser"));
            out.writeObject(fordZTW);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomotiveException("Cannot serialize FordZTW", e);
        }
    }

    public static Automotive DeserializeAuto(String file) throws AutomotiveException {
        Automotive automotive = null;
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            automotive = (Automotive) in.readObject();
        } catch (Exception e) {
            throw new AutomotiveException("Cannot deserialize FordZTW", e);
        }

        return automotive;
    }
}
