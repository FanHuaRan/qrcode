package com.github.fhr.qrcode.util;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Fan Huaran
 * created on 2019/3/11
 * @description
 */
public class QrCodeUtils {
    /**
     * 输出二维码的缺省宽度
     */
    private static final int DEFAULT_WIDTH = 67 + 12 * (7 - 1);
    /**
     * 输出二维码的缺省高度
     */
    private static final int DEFAULT_HEIGHT = 67 + 12 * (7 - 1);
    /**
     * 数据编码集
     */
    public static final String CHARSET = "utf-8";

    /**
     * 创建二维码
     *
     * @param data    生成二维码中要存储的信息
     * @param imgPath 二维码图片存储路径
     * @throws IOException
     */
    public static void encodeQRCode(String data, String imgPath) throws IOException {
        encodeQRCode(data, imgPath, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    /**
     * 创建二维码
     *
     * @param data    生成二维码中要存储的信息
     * @param imgPath 二维码图片存储路径
     * @param width   长度
     * @param height  高度
     * @throws IOException
     */
    public static void encodeQRCode(String data, String imgPath, int width, int height) throws IOException {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("data must not be null or empty");
        }

        if (data.getBytes("utf-8").length > 120) {
            throw new IllegalArgumentException("data must less than 120 bytes");
        }

        if (new File(imgPath).canWrite()) {
            throw new IllegalArgumentException("img path is not work");
        }

        Qrcode qrcode = new Qrcode();
        qrcode.setQrcodeErrorCorrect('M');//纠错等级（分为L、M、H三个等级）
        qrcode.setQrcodeEncodeMode('B');//N代表数字，A代表a-Z，B代表其它字符
        qrcode.setQrcodeVersion(7);//版本

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //绘图
        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        gs.clearRect(0, 0, width, height);//清除下画板内容

        //设置下偏移量,如果不加偏移量，有时会导致出错。
        int pixOff = 2;

        boolean[][] dataBits = qrcode.calQrcode(data.getBytes(CHARSET));
        for (int i = 0; i < dataBits.length; i++) {
            for (int j = 0; j < dataBits.length; j++) {
                if (dataBits[j][i]) {
                    gs.fillRect(j * 3 + pixOff, i * 3 + pixOff, 3, 3);
                }
            }
        }

        gs.dispose();
        bufferedImage.flush();
        ImageIO.write(bufferedImage, "png", new File(imgPath));
    }

    /**
     * 解析二维码（QRCode）
     *
     * @param imgPath 图片路径
     * @return
     */
    public static String decodeQRCode(String imgPath) throws IOException {
        //QRCode 二维码图片的文件
        File imageFile = new File(imgPath);

        // read the img
        BufferedImage bufImg = ImageIO.read(imageFile);
        // decode qrcode
        QRCodeDecoder decoder = new QRCodeDecoder();
        return new String(decoder.decode(new BufferedImgQrCodeImage(bufImg)), "utf-8");
    }

}
