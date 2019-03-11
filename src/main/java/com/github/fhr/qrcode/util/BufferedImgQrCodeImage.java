package com.github.fhr.qrcode.util;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * @author Fan Huaran
 * created on 2019/3/11
 * @description
 */
public class BufferedImgQrCodeImage implements QRCodeImage {

    //BufferedImage作用将一幅图片加载到内存中
    BufferedImage bufImg;

    public BufferedImgQrCodeImage(BufferedImage bufImg) {
        this.bufImg = bufImg;
    }

    @Override
    public int getWidth() {
        return bufImg.getWidth();//返回像素宽度
    }

    @Override
    public int getHeight() {
        return bufImg.getHeight();//返回像素高度
    }

    @Override
    public int getPixel(int i, int i1) {
        return bufImg.getRGB(i, i1);//得到长宽值，即像素值，i,i1代表像素值
    }
}
