package com.san.test;

import javax.jws.WebService;

@WebService(endpointInterface = "com.san.test.TempConvert")
public class TempConvertImpl implements TempConvert{


    @Override
    public float c2f(float t) {
        return 32.0f + (t * 9.0f / 5.0f);
    }

    @Override
    public float f2c(float t) {
        return (5.0f / 9.0f) * (t - 32.0f);
    }
}
