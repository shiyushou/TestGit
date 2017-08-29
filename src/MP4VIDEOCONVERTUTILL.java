import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shiyushou on 2017/3/8.
 */
public class MP4VIDEOCONVERTUTILL {

  /**
   * 功能函数
   * @param ffmpeg_home ffmpeg安装路径
   * @param bitrate 比特率设置(1200)
   * @param resolution 分辨率 (720x480、960x720...)
   * @param inputFile  待处理视频，需带路径
   * @param outputFile 处理后视频，需带路径
   * @return
   */
  public static boolean convert(String ffmpeg_home, String inputFile, String outputFile, int bitrate,
                                String resolution) {
    if (!checkfile(inputFile)) {
      System.out.println(inputFile + " is not file");
      return false;
    }

    if (process(ffmpeg_home,inputFile, outputFile,bitrate,resolution)) {
      System.out.println("ok");
      return true;
    }
    return false;
  }

  /**
   * 功能函数 比特率设置默认832，分辨率默认960x720
   * @param ffmpeg_home ffmpeg安装路径
   *
   *
   * @param inputFile  待处理视频，需带路径
   * @param outputFile 处理后视频，需带路径
   * @return
   */
  public static boolean convert(String ffmpeg_home, String inputFile, String outputFile) {
    if (!checkfile(inputFile)) {
      System.out.println(inputFile + " is not file");
      return false;
    }
    if (process(ffmpeg_home,inputFile, outputFile,832,"960x720")) {
      System.out.println("ok");
      return true;
    }
    return false;
  }

  //检查文件是否存在
  private static boolean checkfile(String path) {
    File file = new File(path);
    if (!file.isFile()) {
      return false;
    }
    return true;
  }

  /**
   * 转换过程 ：先检查文件类型，在决定调用 processFlv还是processAVI
   *
   * @param inputFile
   * @param outputFile
   * @return
   */
  private static boolean process(String ffmpeg_home, String inputFile, String outputFile, int bitrate,
                                 String resolution) {

    int type = checkContentType(inputFile);
    boolean status = false;
    if (type == 0) {
      status = convertMp4ForFfmpeg(ffmpeg_home,inputFile,outputFile,bitrate,resolution);
    } /*else if (type == 1) {
      String avifilepath = ConvertMp4ForMencoder(type,inputFile,outputFile);
    }*/
    return status;
  }

  /**
   * 检查视频类型
   *
   * @param inputFile
   * @return ffmpeg 能解析返回0，不能解析返回1
   */
  public static int checkContentType(String inputFile) {
    String type = inputFile.substring(inputFile.lastIndexOf(".") + 1, inputFile.length()).toLowerCase();
    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
    if (type.equals("avi")) {
      return 0;
    } else if (type.equals("mpg")) {
      return 0;
    } else if (type.equals("wmv")) {
      return 0;
    } else if (type.equals("3gp")) {
      return 0;
    } else if (type.equals("mov")) {
      return 0;
    } else if (type.equals("mp4")) {
      return 0;
    } else if (type.equals("asf")) {
      return 0;
    } else if (type.equals("asx")) {
      return 0;
    } else if (type.equals("flv")) {
      return 0;
    }
    // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
    // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
    else if (type.equals("wmv9")) {
        return 1;
      } else if (type.equals("rm")) {
        return 1;
      } else if (type.equals("rmvb")) {
        return 1;
      }
    return 9;
  }

  /**
   * ffmepg: 能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
   * ffmpeg -i 002.mp4 -acodec aac -ac 2 -strict experimental -ab 160k -s 1024x768 -vcodec libx264
   * -preset slow -profile:v baseline
   * -level 30 -maxrate 10000000 -bufsize 10000000 -b 1200k -f mp4 -threads 0 OUTPUT.mp4
   * @param inputFile
   * @param outputFile
   * @return
   */
  private static boolean convertMp4ForFfmpeg(String ffmpeg_home, String inputFile, String outputFile, int bitrate,
                                             String resolution) {
    if (!checkfile(inputFile)) {
      System.out.println(inputFile + " is not file");
      return false;
    }

    //验证resolution的格式
    String regEx = "^[1-9][0-9]{2,3}x[1-9][0-9]{2,3}$";
    Pattern p1 = Pattern.compile(regEx);
    Matcher m1 = p1.matcher(resolution);
    if (!m1.matches()) {
      System.out.println(resolution + " 格式不对");
      return false;
    }

    File file = new File(outputFile);
    if (file.exists()) {
      System.out.println("视频文件已经存在！无需转换");
      return true;
    } else {
      System.out.println("正在转换成MP4文件……");
      String cmd = ffmpeg_home + " -i " + "\"" + inputFile + "\" -acodec aac -ac 2 -strict experimental -ab 160k -s " +
                   resolution + " -vcodec libx264 -preset slow -profile:v baseline -level 30 -maxrate 10000000 " +
                   "-bufsize 10000000 -b " + bitrate +"k -f mp4 -threads 0 -qscale 4 " + "\"" + outputFile + "\"" ;

      System.out.println(cmd);


        //                ProcessBuilder builder = new ProcessBuilder();
        //                builder.command(commend);
        //                builder.start();
        if (exec(cmd)) {
          return true;
        } else {
          return false;
        }



    }

  }

  /**
   * 生成视频截图 默认 startTime 0
   * @param ffmpeg_home ffmpeg 路径
   * @param imageSavePath 截图文件保存全路径
   * @param screenSize 截图大小 如640x480
   */
  public static void makeScreenCut( String ffmpeg_home, String inputFile,String imageSavePath , String screenSize){

    makeScreenCut(ffmpeg_home,inputFile,imageSavePath,screenSize,"0");

  }
  /**
   * 生成视频截图
   * @param ffmpeg_home ffmpeg 路径
   * @param imageSavePath 截图文件保存全路径
   * @param screenSize 截图大小 如640x480
   * @param startTime 开始时间 为数字 或者 00:00:00格式
   */
  public static boolean makeScreenCut( String ffmpeg_home, String inputFile,String imageSavePath , String screenSize,
                                    String startTime ){
    //验证screenSize的格式
    String regEx = "^[1-9][0-9]{2,3}x[1-9][0-9]{2,3}$";
    Pattern p1 = Pattern.compile(regEx);
    Matcher m1 = p1.matcher(screenSize);
    if (!m1.matches()) {
      System.out.println(screenSize + " 格式不对");
      return false;
    }

    String cmdv = ffmpeg_home + " -i " + "\"" + inputFile + "\" -y -f image2 -ss "+
                  startTime + " -vframes 1 -s " + screenSize + " \"" + imageSavePath +"\"";

    System.out.println(cmdv);

    exec(cmdv);
    return true;
  }







  public String convertPath(String path){
    path = path.replace("//","\\").replace("/","\\");;
    return path;
  }

  /**
   * 执行指令
   * @param cmd 执行指令
   *
   */
  static public boolean exec(String cmd){
    try {
      Process pro = null;
      if (isWindowsSystem()) {
        pro= Runtime.getRuntime().exec(cmd);
      } else {
        String[] cmds = { "/bin/sh", "-c", cmd };
        pro= Runtime.getRuntime().exec(cmds);
      }


      new DoOutput(pro.getInputStream()).start();
      new DoOutput(pro.getErrorStream()).start();
      pro.waitFor();
      return  true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 判断是否是windows操作系统
   * @author iori
   * @return
   */
  private static boolean isWindowsSystem() {
    String p = System.getProperty("os.name");
    return p.toLowerCase().indexOf("windows") >= 0 ? true : false;
  }

  /**
   * 多线程内部类
   * 读取转换时cmd进程的标准输出流和错误输出流，这样做是因为如果不读取流，进程将死锁
   * @author iori
   */
  private static class DoOutput extends Thread {
    public InputStream is;

    //构造方法
    public DoOutput(InputStream is) {
      this.is = is;
    }

    public void run() {
      BufferedReader br = new BufferedReader(new InputStreamReader(this.is));
      String str = null;
      try {
        //这里并没有对流的内容进行处理，只是读了一遍
        while ((str = br.readLine()) != null) {
          System.out.println(str);
        };
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (br != null) {
          try {
            br.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }


}
