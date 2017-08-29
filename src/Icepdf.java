import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Created by shiyushou on 2017/4/10.
 */
public class Icepdf {
  public static void pdf2Pic(String pdfPath, String path,int outImgNum){
    Document document = new Document();
    document.setFile(pdfPath);
    float scale = 0.5f;//缩放比例
    float rotation = 0f;//旋转角度
    int outImageNum = document.getNumberOfPages();
    if(outImgNum!=0){
      outImageNum = outImgNum;
    }
    for (int i = 0; i < outImageNum; i++) {
      BufferedImage image = (BufferedImage)
          document.getPageImage(i, GraphicsRenderingHints.SCREEN, org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);
      RenderedImage rendImage = image;
      try {
        String imgName = ".jpg";
        System.out.println(imgName);
        File file = new File(path + imgName);
        ImageIO.write(rendImage, "jpg", file);
      } catch (IOException e) {
        e.printStackTrace();
      }
      image.flush();
    }
    document.dispose();
  }
  public static void main(String[] args) {
    String filePath = "E:\\新闻舆情开发计划预估2017.3.14.pdf";
    pdf2Pic(filePath, "E:\\新闻舆情开发计划预估2017.3.14.jpg",1);
  }
}
