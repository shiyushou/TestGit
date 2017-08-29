import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by shiyushou on 2017/3/13.
 */
public class PDFToImageUtil {

  /**
   * PDF缩略图
   * @Method PDFToImage
   * @Description TODO
   * @param pdfpath PDF文档路径
   * @param outImagePath 输出图片的文件夹路径，如果没有会自动创建
   * @param imgWidth 缩略图宽度，0按照文档宽度
   * @param imgHeight 缩略图高度，0按照文档高度
   * @param pageNum 需要打印的页数，0打印所有，其他打印对应页数
   * @Return void
   */
  public static void PDFToImage(String pdfPath, String outImgPath, int imgWidth, int imgHeight, int outImgNum){
    try {
      File file = new File(pdfPath);
      if(!file.exists()){
        System.out.println("PDF文档不存在！");
      }
      RandomAccessFile raf = new RandomAccessFile(file, "r");//r表示只读
      FileChannel channel = raf.getChannel();
      ByteBuffer buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
      PDFFile pdffile = new PDFFile(buf);

      System.out.println("PDF页数： " + pdffile.getNumPages());

      int outImageNum = pdffile.getNumPages();
      if(outImgNum!=0){
        outImageNum = outImgNum;
      }
      for (int i=1; i<=outImageNum; i++){
        PDFPage page = pdffile.getPage(i);
        Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(), (int) page.getBBox().getHeight());
        int width = rect.width;
        int height = rect.height;
        if(imgWidth!=0){
          width = imgWidth;
        }
        if(imgHeight!=0){
          height = imgHeight;
        }
        Image img = page.getImage(width, height,rect,null,true,true);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(img, 0, 0, width, height, null);
        File imgPath = new File(outImgPath);
        //如果图输出文件夹不存在，创建文件夹
        if(!imgPath.exists()){
          imgPath.mkdir();
        }
        FileOutputStream out = new FileOutputStream(imgPath.toString() + "//" + file.getName() + "_" + i + ".jpg"); // 输出到文件流
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        encoder.encode(tag); // JPEG编码
        out.close();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    PDFToImageUtil.PDFToImage("F://成功.pdf", "F://", 240, 338, 2);
  }
}
