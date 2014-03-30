package com.san.test.client;

import com.san.test.stub.TempConvert;
import com.san.test.stub.TempConvertImplService;

import javax.xml.ws.BindingProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ClientTC {
    private static final String endpoint = "https://localhost:8443/TomcatAuthen/tc";

    static {
        Properties properties = System.getProperties();

        properties.put("javax.net.ssl.trustStore","/opt/Workspace/TomcatAuthen/.keystore");
        properties.put("javax.net.ssl.trustStorePassword","changeit");
        properties.put("javax.net.ssl.keyStore","/opt/Workspace/TomcatAuthen/.keystore");
        properties.put("javax.net.ssl.keyStorePassword","changeit");

        System.setProperties(properties);

        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier(){

                    public boolean verify(String hostname,
                                          javax.net.ssl.SSLSession sslSession) {
                        if (hostname.equals("localhost")) {
                            return true;
                        }
                        return false;
                    }
                });
    }


    public static void main(String args[ ]) throws Exception {


        TempConvertImplService service = new TempConvertImplService();
        TempConvert port = service.getTempConvertImplPort();

        Map<String, Object> req_ctx = ((BindingProvider) port).getRequestContext();
        req_ctx.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpoint);
        //req_ctx.put(BindingProvider.USERNAME_PROPERTY, "san");
        //req_ctx.put(BindingProvider.PASSWORD_PROPERTY, "meokeugaugau");


        System.out.println("f2c(-40.1) ==> " + port.f2C(-40.1f));
        System.out.println("c2f(-40.1) ==> " + port.c2F(-40.1f));
        System.out.println("f2c(+98.7) ==> " + port.f2C(+98.7f));
    }
}
