

import java.io.*;
import java.util.List;

public class ConvertSingleVideo {
  private static String mencoder_home = "E:\\ffmpeg\\mencoder.exe";//mencoder.exe所放的路径
  //private static String ffmpeg_home = "E:\\ffmpeg\\ffmpeg.exe";//ffmpeg.exe所放的路径
  private static String ffmpeg_home = "ffmpeg";//ffmpeg.exe所放的路径

  public static String inputFile_home = "E:\\";//需转换的文件的位置
  public static String outputFile_home = "E:\\";//转换后的flv文件所放的文件夹位置
  private String tempFile_home;//存放rm,rmvb等无法使用ffmpeg直接转换为flv文件先转成的avi文件



  /**
   * 功能函数
   *
   * @param inputFile  待处理视频，需带路径
   * @param outputFile 处理后视频，需带路径
   * @return
   */
  public boolean convert(String inputFile, String outputFile) {
    if (!checkfile(inputFile)) {
      System.out.println(inputFile + " is not file");
      return false;
    }
    if (process(inputFile, outputFile)) {
      System.out.println("ok");
      return true;
    }
    return false;
  }

  //检查文件是否存在
  private boolean checkfile(String path) {
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
  private boolean process(String inputFile, String outputFile) {
    int type = checkContentType(inputFile);
    boolean status = false;
    if (type == 0) {
      status = convertMp4ForFfmpeg(inputFile,outputFile);// 直接将文件转为flv文件
    } else if (type == 1) {
      String avifilepath = ConvertMp4ForMencoder(type,inputFile,outputFile);
    }
    return status;
  }

  /**
   * 检查视频类型
   *
   * @param inputFile
   * @return ffmpeg 能解析返回0，不能解析返回1
   */
  public int checkContentType(String inputFile) {
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
  private boolean convertMp4ForFfmpeg(String inputFile, String outputFile) {
    if (!checkfile(inputFile)) {
      System.out.println(inputFile + " is not file");
      return false;
    }
    File file = new File(outputFile);
    if (file.exists()) {
      System.out.println("视频文件已经存在！无需转换");
      return true;
    } else {
      System.out.println("正在转换成MP4文件……");
      String cmd = ffmpeg_home + " -i " + "\"" + inputFile + "\" -acodec aac -ac 2 -strict experimental -ab 160k -s " +
                   "1024x768 -vcodec libx264 -preset slow -profile:v baseline -level 30 -maxrate 10000000 -bufsize " +
                   "10000000 -b 1200k -f mp4 -threads 0 -qscale 4 " + "\"" + outputFile + "\"" ;
     // List<String> commend = new java.util.ArrayList<String>();
      //低精度
      //            ffmpeg -i 002.mp4 -acodec aac -ac 2 -strict experimental -ab 160k -s 1024x768 -vcodec libx264
      //                    * -preset slow -profile:v baseline
      //            * -level 30 -maxrate 10000000 -bufsize 10000000 -b 1200k -f mp4 -threads 0 OUTPUT.mp4
      /*commend.add(ffmpeg_home);
      commend.add("-i");
      commend.add(inputFile);
      commend.add("-acodec");
      commend.add("aac");
      commend.add("-ac");
      commend.add("2");
      commend.add("-strict");
      commend.add("experimental");
      commend.add("-ab");
      commend.add("64k");
      commend.add("-s");
      commend.add("1024x768");
      commend.add("-vcodec");
      commend.add("libx264");
      commend.add("-preset");
      commend.add("faster");
      commend.add("-profile:v");
      commend.add("baseline");
      commend.add("-level");
      commend.add("30");
      commend.add("-maxrate");
      commend.add("50000000");
      commend.add("-bufsize");
      commend.add("10000000");
      commend.add("-b");
      commend.add("700k");
      commend.add("-f");
      commend.add("mp4");
      commend.add("-threads");
      commend.add("0");*/
      // 清晰度 -qscale 4 为最好但文件大, -qscale 6就可以了
     /* commend.add("-qscale");
      commend.add("4");
      commend.add(outputFile);
      StringBuffer test = new StringBuffer();
      for (int i = 0; i < commend.size(); i++)
        test.append(commend.get(i) + " ");*/
      System.out.println(cmd);

      try {
        //                ProcessBuilder builder = new ProcessBuilder();
        //                builder.command(commend);
        //                builder.start();
        CmdExecuter.exec(cmd);
        return true;
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }

    }

  }

  /**
   * Mencoder:
   * 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
   *
   * @param type
   * @param inputFile
   * @return
   */
  //    mencoder 001.rmvb -o new.mp4  -vf dsize=800:600:2,scale=-8:-8,harddup  -oac faac -faacopts mpeg=4:object=2:raw:br=128
  //            -of lavf -lavfopts format=mp4 -ovc x264 -sws 9
  //            -x264encopts nocabac:level_idc=30:bframes=0:bitrate=512:threads=auto:turbo=1:global_header:threads=auto:subq=5:frameref=6:partitions=all:trellis=1:chroma_me:me=umh

  private String ConvertMp4ForMencoder(int type, String inputFile,String outoutFile) {

    List<String> commend = new java.util.ArrayList<String>();
    commend.add(mencoder_home);
    commend.add(inputFile);
    commend.add("-o");
    commend.add(outoutFile);
    commend.add("-vf");
    commend.add("dsize=800:600:2,scale=-8:-8,harddup");
    commend.add("-oac");
    commend.add("faac");
    commend.add("-faacopts");
    commend.add("mpeg=4:object=2:raw:br=128");
    commend.add("-of");
    commend.add("lavf");
    commend.add("-lavfopts");
    commend.add("format=mp4");
    commend.add("-ovc");
    commend.add("x264");
    commend.add("-sws");
    commend.add("9");
    commend.add("-x264encopts");
    commend.add("nocabac:level_idc=30:bframes=0:bitrate=512:threads=auto:turbo=1:global_header:threads=auto:subq=5:frameref=6:partitions=all:trellis=1:chroma_me:me=umh");

    StringBuffer test = new StringBuffer();
    for (int i = 0; i < commend.size(); i++)
      test.append(commend.get(i) + " ");
    System.out.println(test);
    try {
      ProcessBuilder builder = new ProcessBuilder();
      builder.command(commend);
      Process p = builder.start();
      /**
       * 清空Mencoder进程 的输出流和错误流
       * 因为有些本机平台仅针对标准输入和输出流提供有限的缓冲区大小，
       * 如果读写子进程的输出流或输入流迅速出现失败，则可能导致子进程阻塞，甚至产生死锁。
       */
      final InputStream is1 = p.getInputStream();
      final InputStream is2 = p.getErrorStream();
      new Thread() {
        public void run() {
          BufferedReader br = new BufferedReader(new InputStreamReader(is1));
          try {
            String lineB = null;
            while ((lineB = br.readLine()) != null) {
              if (lineB != null) System.out.println(lineB);
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }.start();
      new Thread() {
        public void run() {
          BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
          try {
            String lineC = null;
            while ((lineC = br2.readLine()) != null) {
              if (lineC != null) System.out.println(lineC);
            }
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }.start();

      //等Mencoder进程转换结束，再调用ffmpeg进程
      p.waitFor();
      System.out.println("who cares");
      return tempFile_home;
    } catch (Exception e) {
      System.err.println(e);
      return null;
    }
  }
  /**
   * 生成视频截图
   * @param imageSavePath 截图文件保存全路径
   * @param screenSize 截图大小 如640x480
   */
  public void makeScreenCut( String inputFile,String imageSavePath , String screenSize ){
    String cmdv = ffmpeg_home + " -i " + "\"" + inputFile + "\" -y -f image2 -ss 0 -vframes 1 -s " + screenSize +
                     " \"" + imageSavePath +"\"";
    /*List<String> cmd = new java.util.ArrayList<String>();
    cmd.clear();
    cmd.add(ffmpeg_home);
    cmd.add("-i");
    cmd.add(inputFile);
    cmd.add("-y");
    cmd.add("-f");
    cmd.add("image2");
    cmd.add("-ss");
    cmd.add("8");
    cmd.add("-t");
    cmd.add("0.001");
    cmd.add("-s");
    cmd.add(screenSize);
    cmd.add(imageSavePath);
    CmdExecuter.exec(cmd);*/
    System.out.println(cmdv);

    CmdExecuter.exec(cmdv);

  }


  public String convertPath(String path){
    path = path.replace("//","\\").replace("/","\\");;
    return path;
  }



}