package com.san.development;

import java.io.IOException;

public class DevelopmentServer {
    public static void main(String[] args) {

    }

    public void execute(String command) throws IOException {
        Runtime runTime = Runtime.getRuntime();
        Process process = runTime.exec(command);
    }
}
