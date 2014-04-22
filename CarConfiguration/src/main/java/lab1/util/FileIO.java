package lab1.util;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;

import java.io.*;

public class FileIO {
    //Step 6 – Populating data in Model Package using a text file.
    public Automotive readFile(String fileLocation) throws AutomotiveException {
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
                    if (line.contains("name=")) {
                        automotive.setName(line.split("=")[1]);
                    } else if (line.contains("basePrice=")) {
                        automotive.setBasePrice(Integer.parseInt(line.split("=")[1]));
                    } else {
                        String[] temp = line.split(",");
                        if (temp.length == 3)
                            automotive.addOptionSet(temp[0], temp[1], Integer.parseInt(temp[2]));
                    }
                }
            }
            buff.close();

            if(automotive.getName() == null) {
                throw new AutomotiveException("AutoMotive name is missing");  //1st Exception if cannot get name from file
            }
            if(automotive.getBasePrice() == 0) {
                throw new AutomotiveException("AutoMotive baseprice is missing or equals 0"); //2nd Exception if cannot get baseprice from file or baseprice equals 0
            }

            if(automotive.getOptionSets().length == 0) {
                throw new AutomotiveException("AutoMotive does not have any OptionSet"); //3rd Exception if cannot get any optionset from file.
            }


        } catch (FileNotFoundException e) {
            throw new AutomotiveException("Cannot locate the file", e);  //4th Exception if cannot reach the file, or fileName is wrong
        } catch (IOException e) {
            throw new AutomotiveException("Cannot read configuration file", e); //5th Exception if have errors while reading file
        }

        return automotive;
    }

    //Step 7: Serialization and DeSerialization
    public void serializeAuto(Automotive fordZTW, String fileLocation) throws AutomotiveException {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("auto.ser"));
            out.writeObject(fordZTW);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AutomotiveException("Cannot serialize FordZTW", e);
        }
    }

    public Automotive DeserializeAuto(String file) throws AutomotiveException {
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
