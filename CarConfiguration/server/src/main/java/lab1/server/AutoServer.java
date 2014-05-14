package lab1.server;

import lab1.exception.AutomotiveException;
import lab1.model.Automotive;

import java.io.InputStream;

public interface AutoServer {
    public Automotive buildAutoMotive(InputStream inputStream) throws AutomotiveException;
}
