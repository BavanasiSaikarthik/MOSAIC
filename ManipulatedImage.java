package mosaic;
import java.awt.Color;


import java.io.IOException;

import java.awt.Graphics2D;

import java.io.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ManipulatedImage {
	
	BufferedImage InputImage=null;
    int ScaleFactor1=10,ScaleFactor2=10,Iwidth,Iheight,w,h;
    int [][]Rvalue,Gvalue,Bvalue;
    File inf ;
    ManipulatedImage(String IImageloc)
    {
    	try{inf=new File(IImageloc);
    		
    	
    	 InputImage = ImageIO.read(inf);
    	 
    	 
    	}catch(IOException e) {
    		System.out.println("error"+e);
    	}
    }
   
    
    void DividePic()
    { 
       
    	Iwidth = InputImage.getWidth();
        Iheight = InputImage.getHeight();
        w = Iwidth/ScaleFactor1;
        h=Iheight/ScaleFactor2;
     

    }
    void makeRGBMatrix()
    { 
        Rvalue = new int[ScaleFactor2][ScaleFactor1];
        Gvalue = new int[ScaleFactor2][ScaleFactor1];
        Bvalue = new int[ScaleFactor2][ScaleFactor1];
        int avg_R,avg_B,avg_G;

        Color cr;
        
       
        for(int x=0;x<ScaleFactor1-1;x++)
        {
            for(int y=0;y<ScaleFactor2-1;y++)
            {  avg_B =avg_G=avg_R=0;
                for(int i=x*w;i<x*w+w;i++)
                {
                    for(int j=y*h;j<y*h+h;j++)
                    {  
                         cr = new Color(InputImage.getRGB(i,j));
                         avg_B+=cr.getBlue(); 
                         avg_G+=cr.getGreen();
                         avg_R+=cr.getRed();
                    }
                }

                Rvalue[x][y]=avg_R/(w*h);
                Gvalue[x][y]=avg_G/(w*h);
                Bvalue[x][y]=avg_B/(w*h);
            }
        }
    }
    
 
    int FindStandardDeviation(int x,int y,EmojiMosaic obj)
    {
        int index=0;
        double sd,leastsd=1000;
        for(int i=0;i<obj.NFiles;i++)
        {
            sd = Math.sqrt(Math.pow(obj.Rvalues[i]-Rvalue[x][y],2)+Math.pow(obj.Gvalues[i]-Gvalue[x][y],2)+Math.pow(obj.Bvalues[i]-Bvalue[x][y],2));
            if(sd<leastsd)
            {
                leastsd = sd;
                index = i;
            }
        }

        return index;
    }
 
	
	
   BufferedImage resize(String inputImagePath,int scaledWidth,int scaledHeight)
            throws IOException {
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        BufferedImage outputImage = new BufferedImage(scaledWidth,scaledHeight,BufferedImage.TYPE_INT_RGB);
 
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        return outputImage;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
