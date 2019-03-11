package com.github.fhr.qrcode.util;

import java.io.IOException;

import static org.junit.Assert.*;

public class QrCodeUtilsTest {

    @org.junit.Test
    public void encodeQRCode() throws IOException {
        QrCodeUtils.encodeQRCode("ranrandemo", "./testqrcode2.jpg");
    }

    @org.junit.Test
    public void decodeQRCode() throws IOException {
        System.out.println(QrCodeUtils.decodeQRCode("./testqrcode2.jpg"));
    }
}