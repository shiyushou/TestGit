/**
 * Created by shiyushou on 2017/3/3.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;


/**
 * CmdExecuter
 * <p>Title: 命令执行器</p>
 * <p>Description: 封装对操作系统命令行发送指令相关操作</p>
 * <p>Date: 2010-7-14</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: novel-supertv.com</p>
 * @author chenggong
 * @version 1.0
 */
public class CmdExecuter {

  /**
   * 执行指令
   * @param cmd 执行指令
   *
   */
  static public void exec(String cmd){
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

    } catch (Exception e) {
      e.printStackTrace();
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
