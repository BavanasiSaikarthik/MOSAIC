package mosaic;
import java.util.Scanner;
import java.io.File;
import java.awt.Graphics2D;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
public class FinalImage {

	public static void main(String[] args) {
		
		int no;
		Scanner sc = new Scanner(System.in);
		File folder = new File("G:\\inputimages\\");
		File[] listoffiles = folder.listFiles();
		String emojifolder = "C:\\Users\\Saikarthik\\Downloads\\EmojiOne_4.0_32x32_png";
		System.out.println("Enter the index of the image in input image folder which you want to change it to mosaic");
		no = sc.nextInt();
		String ii = listoffiles[no].getPath();
		EmojiMosaic e = new EmojiMosaic(emojifolder);
		ManipulatedImage iimag = new ManipulatedImage(ii);
		System.out.println("Creating mosaic...");
		iimag.DividePic();
		iimag.makeRGBMatrix();
		e.makeRGBlist();
		
		
		BufferedImage finalimg = new BufferedImage(iimag.w*iimag.ScaleFactor1,iimag.h*iimag.ScaleFactor2,BufferedImage.TYPE_INT_RGB);
		Graphics2D gd= finalimg.createGraphics();  
		 BufferedImage timage=null;  
		 int widthcurrent=0;
		 try {for(int i=0;i<iimag.ScaleFactor1;i++)
		{   int heightcurrent=0;
			
			for(int j=0;j<iimag.ScaleFactor2;j++)
			{
			 int index=iimag.FindStandardDeviation(i,j,e);
			  
			  timage=iimag.resize(e.files[index].getPath(),iimag.w,iimag.h);
			  gd.drawImage(timage,widthcurrent,heightcurrent,null);
			  
			  heightcurrent+=timage.getHeight();
			}
			widthcurrent+=timage.getWidth();
		}
		gd.dispose();
		
		
	   File of = new File("G:\\outputimage\\o.jpg");
	   ImageIO.write(finalimg,"jpg",of);
	   System.out.println("Saved at path: G:\\\\outputimage\\\\o.jpg ");
		 }catch(IOException p) {
			 System.out.println("error:"+p);
		 }
	}

}
