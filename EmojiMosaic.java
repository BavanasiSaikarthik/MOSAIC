package mosaic;
import java.awt.Color;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.io.File;

public class EmojiMosaic {
	BufferedImage image;
	File file=null;
	int NFiles;
	File[] files;
	int Rvalues[] ;
    int Gvalues[] ;
    int Bvalues[] ;
    
    
    EmojiMosaic(String emojifolder)
   {
 
        file = new File(emojifolder);
        NFiles=file.list().length;
        files = file.listFiles();
        
         Rvalues = new int[NFiles];
         Gvalues= new int[NFiles];
         Bvalues= new int[NFiles];
        
      
    }
  
   
    public void makeRGBlist()
    {
       
        int width=1,height=1,count=0;
      
        for(File F: files){
            try {
               int avgB,avgG,avgR;
               avgR=avgB=avgG=0;
                image = ImageIO.read(F);
                if(image!=null){
                 width=image.getWidth();
                    height=image.getHeight();

                    Color c;
                    for(int l=0; l<height; l++){

                        for(int m=0; m<width; m++){


                            c = new Color(image.getRGB(m,l));
                            avgR=avgR+c.getRed();
                            avgG=avgG+ c.getGreen();
                            avgB=avgB+c.getBlue();

                            
                        }
                    }

                    Rvalues[count]=avgR/(width*height);
                    Gvalues[count]=avgG/(width*height);
                    Bvalues[count]=avgB/(width*height);

                    count++;
                }

            }catch(IOException e) {
                System.out.println("Error"+e);
            }
        }
    }   

}

