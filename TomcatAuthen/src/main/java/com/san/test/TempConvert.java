package com.san.test;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface TempConvert {
    @WebMethod
    float c2f(float c);

    @WebMethod
    float f2c(float f);
}

