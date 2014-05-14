package lab1.server;

import lab1.model.Automotive;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Properties;

public class BuildCarModelOptions {
    public Automotive buildAutoMotive(Properties autoProperties) {
        Automotive automotive = new Automotive();
        return automotive;
    }

    public Automotive buildAutoMotive(InputStream autoStream) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream((autoStream));
        Properties autoProperties = (Properties)ois.readObject();
        return buildAutoMotive(autoProperties);
    }
}
