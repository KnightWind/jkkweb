package com.jkkp.utils;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

@SuppressWarnings("finally")
public class QRcodeUtils {

	/**
	 * 成功
	 */
	private final static int CREATE_SUCCESS_CODE = 1;
	/**
	 * 失败
	 */
	private final static int CREATE_FILED_CODE   = 0;
	
	/**
	 * 
	 * @param width    二维码宽度
	 * @param height   二维码宽度
	 * @param code     绘制内容
	 * @param savePath 保存路径
	 * @param imgType  保存类型(png,jpg...)
	 * @param open     保存后是否使用window图片浏览器打开预览
	 */
	public static int createQrcodeImages(int width, int height,String code, String savePath,String imgType,boolean open) throws UnsupportedEncodingException{
		int resultCode = CREATE_SUCCESS_CODE;
		Qrcode qrcode =new Qrcode(); 
		qrcode.setQrcodeErrorCorrect('M'); 
		qrcode.setQrcodeEncodeMode('B'); 
		qrcode.setQrcodeVersion(7); 
		byte[] d = code.getBytes("GBK"); 
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY); 
        Graphics2D g = bi.createGraphics(); 
        g.setBackground(Color.WHITE); 
        g.clearRect(0, 0, width, height); 
        g.setColor(Color.BLACK);
        drowContent(qrcode, d, g);  
        g.dispose(); 
        bi.flush(); 
        File f = new File(savePath); 
        try {
			ImageIO.write(bi, imgType, f);
			if(open)Desktop.getDesktop().open(f); 
		} catch (IOException e) {
			resultCode = CREATE_FILED_CODE;
			throw new RuntimeException("绘制二维码失败!", e);
		}finally{
			return resultCode;
		}
	}

	/**
	 * 绘制二维码内容
	 * @param qrcode
	 * @param d
	 * @param g
	 */
	private static void drowContent(Qrcode qrcode, byte[] d, Graphics2D g) {
		if (d.length > 0){  
            boolean[][] b = qrcode.calQrcode(d);  
            for (int i=0;i<b.length;i++){  
                for (int j=0;j<b.length;j++){  
                    if (b[j][i]) {  
                        g.fillRect(j*3+2,i*3+2,3,3);  
                    }  
                }  
            }  
        }
	}
	
}
