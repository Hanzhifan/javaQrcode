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
		//��������
		Qrcode qrcdoe = new Qrcode();
		qrcdoe.setQrcodeEncodeMode('B');
		String base64="iVBORw0KGgoAAAANSUhEUgAAAA4AAAAPCAMAAADjyg5GAAAAXVBMVEUAAAAwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEIwPEJDHfCJAAAAHnRSTlMA+OfkgQ7uWlEF89nVq5toYVM7KiNKQrKSjYh3dm+nOjsrAAAAh0lEQVQI1zXPRxLDMAhAUSGi6qi7F+5/zKDY/iveDCwQnKol51IVj11BA6fD7VEOkSgOcny5Iq4vG8YPF7F1uU2bK+fL6M0xE2hrpslYDUmIKgn2vrwDySomlOC/nAeJs3BtRvqHc+vHLtwMjN5JtCxEp7g7iFIiOh4a8NZ6MA9VsUrZ0j/4AYQfCIJmJH3lAAAAAElFTkSuQmCC";
		String str = "BEGIN:VCARD\n"+
					"PHOTO;ENCODING=b:"+base64+"\n"+
					"FN:��־�� \n"+
					"EMAIL: hanzhfian@xxx.com \n"+
					"TEL:12345678912 \n"+
					"ADR:�ӱ��Ƽ�ʦ��ѧԺ \n"+
					"ORG:�ػʵ� \n"+
					"TITLE:java�������ʦ\n "+
					"URL:http://www.baidu.com\n "+
					"NOTE:�Ƽ�1601��\n"+
					"END:VCARD";
		boolean[][] calQrcode = qrcdoe.calQrcode(str.getBytes());
		int  imageSize = 67+12*(qrcdoe.getQrcodeVersion()-1);
		//ͼƬ�������
		Image image = ImageIO.read(new File("d:/2.jpg"));
		BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
		//��������
		Graphics2D gs = bufferedImage.createGraphics();
		//���ñ���ɫ
		gs.setBackground(Color.WHITE);
		//����ǰ��ɫ
		gs.setColor(Color.BLACK);
		//�������
		gs.clearRect(0, 0, imageSize, imageSize);
		
		
	    
		int startR = 142;
		int startG = 164;
		int startB = 16;
		
		int endR = 230;
		int endG = 15;
		int endB = 102;
		
		//�õ���λ����  ����ȷ����ά���Ǹ�������ɫ
		
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
					//�����ɫ
					gs.fillRect(i*3+x, j*3+x, 3, 3);
				}
			}
		}
		int logosize = 30;
		int size = (imageSize-logosize)/2;
		gs.drawImage(image,size,size,logosize,logosize,null);
		gs.dispose();
		bufferedImage.flush();
		//�����ά��ͼƬ
		ImageIO.write(bufferedImage, "png", new File("d:/qrcode_1.png"));
	}
}
