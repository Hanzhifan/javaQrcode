import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRcode {
	public static void main(String[] args) throws IOException {
		//创建对象
		Qrcode qrcdoe = new Qrcode();
		qrcdoe.setQrcodeEncodeMode('B');
		String base64="iVBORw0KGgoAAAANSUhEUgAAAA4AAAAPCAMAAADjyg5GAAAAXVBMVEUAAAAwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEJDHfCJAAAAHnRSTlMA+OfkgQ7uWlEF89nVq5toYVM7KiNKQrKSjYh3dm+nOjsrAAAAh0lEQVQI1zXPRxLDMAhAUSGi6qi7F+5/zKDY/iveDCwQnKol51IVj11BA6fD7VEOkSgOcny5Iq4vG8YPF7F1uU2bK+fL6M0xE2hrpslYDUmIKgn2vrwDySomlOC/nAeJs3BtRvqHc+vHLtwMjN5JtCxEp7g7iFIiOh4a8NZ6MA9VsUrZ0j/4AYQfCIJmJH3lAAAAAElFTkSuQmCC";
		String str = "BEGIN:VCARD\n"+
					"PHOTO;ENCODING=b:"+base64+"\n"+
					"FN:韩志凡 \n"+
					"EMAIL: hanzhfian@xxx.com \n"+
					"TEL:12345678912 \n"+
					"ADR:河北科技师范学院 \n"+
					"ORG:秦皇岛 \n"+
					"TITLE:java软件工程师\n "+
					"URL:http://www.baidu.com\n "+
					"NOTE:科技1601班\n"+
					"END:VCARD";
		boolean[][] calQrcode = qrcdoe.calQrcode(str.getBytes());
		int  imageSize = 67+12*(qrcdoe.getQrcodeVersion()-1);
		//图片缓存对象
		Image image = ImageIO.read(new File("d:/2.jpg"));
		BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
		//创建画板
		Graphics2D gs = bufferedImage.createGraphics();
		//设置背景色
		gs.setBackground(Color.WHITE);
		//设置前景色
		gs.setColor(Color.BLACK);
		//情况画板
		gs.clearRect(0, 0, imageSize, imageSize);
		
		
	    
		int startR = 142;
		int startG = 164;
		int startB = 16;
		
		int endR = 230;
		int endG = 15;
		int endB = 102;
		
		//得到二位数组  可以确定二维码那个点有颜色
		
		System.out.println(calQrcode.length);
		int x = 2;
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode.length; j++) {
				if(calQrcode[i][j]){
					int num1 = startR + (endR - startR) * (j+1)/calQrcode.length;
					int num2 = startG + (endG - startG) * (j+1)/calQrcode.length;
					int num3 = startB + (endB - startB) * (j+1)/calQrcode.length;
					
					Color color = new Color(num1, num2, num3);
					
					gs.setColor(color);
					//填充颜色
					gs.fillRect(i*3+x, j*3+x, 3, 3);
				}
			}
		}
		int logosize = 30;
		int size = (imageSize-logosize)/2;
		gs.drawImage(image,size,size,logosize,logosize,null);
		gs.dispose();
		bufferedImage.flush();
		//输出二维码图片
		ImageIO.write(bufferedImage, "png", new File("d:/qrcode_1.png"));
	}
}
