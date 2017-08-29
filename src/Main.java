import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

      String[] strings = new String[]{"a","b","c","d"};
      String[] arrs = new String[strings.length];
      for(int i = strings.length-1,y = 0; i >= 0; i--,y++) {
        arrs[y] = strings[i];
      }
      System.out.println(arrs);
    /* String[] ssa = new String[2];
      ssa[0] = "qq";
      ssa[1] = "ss";*/



     /* String sss = "20170607_11.34.00.mp4";
      String[] dd = sss.split("_");
      if (dd.length != 3) {
        System.out.println(dd.length);
      }*/


     /* System.out.println( StringUtils.join(ssa, "/"));*/

     /*int c =10 & 20 ;

     if ( (10 & 20) > 0 ) {
       System.out.println(1);
     }
      if ( (10 & 20) < 0) {
        System.out.println(2);
      };*/
     /* Student s1 = new Student("111", "221");
      Student s2 = new Student("112", "222");*/
    //  List<Student> ssll = new ArrayList<Student>();
     /* List<Student> ss22 = new ArrayList<Student>();
      List<Student> ss33 = new ArrayList<Student>();
      List<Student> pppp = new ArrayList<Student>();
      Set<Student> ss = new HashSet<Student>();*/
  /*    ssll.add(s1);
      ssll.add(s2);*/

     /* ss.addAll(ss22);
      ss.addAll(ss33);
      pppp.addAll(ss);
*/




     // sgy(ssll);

     /* for(Student st : ssll) {
        if(st.getStuId().equals("112")) {
          Student s3 = new Student("114", "225");
          pppp.add(s3);
        }

      }
      ssll.addAll(pppp);
      for(Student st : ssll) {
        System.out.println(st.getStuName());

      }
*/

     // int aa = 544;
    /*  String ss = "01";*/
     // System.out.println(aa/365);
     /* Random xx = new Random();
      JSONObject ob = new JSONObject();
      ob.put("random", xx.nextInt(100));
      ob.put("random", xx.nextInt(100));
*/
     //int dd = (int) (Math.random() * 1000);

     /* Student s1 = new Student("111", "221");
      Student s2 = new Student("111", "222");
      if (!s1.getStuName().equals(s2.getStuName())) {
        System.out.println("kajlglfdajhlkdahjl");
      }
     Student s11 = new Student("113", "231");
      Student s22 = new Student("113", "232");
      List<Student> ssll = new ArrayList<Student>();
      List<Student> pppp = new ArrayList<Student>();
      List<Student> sskk = new ArrayList<Student>();


      Set<Student> ss = new HashSet<Student>();
      ssll.add(s1);
      ssll.add(s11);
      sskk.add(s1);
      sskk.add(s22);
      ss.addAll(ssll);
      ss.addAll(sskk);
      pppp.addAll(ss);
      for(Student s : pppp) {

      }*/
      /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
        Date d = sdf.parse("2017-06-19 11:12:31");
        System.out.println(d.getTime()); ;
      } catch (ParseException e) {
        e.printStackTrace();
      }*/




     /* Date date = new Date(System.currentTimeMillis()- 5*24*60*60*1000);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String dateString = sdf.format(date);
      System.out.print(dateString);*/
     /* String  ss = "03.02.04.mp4";

      System.out.println(ss.substring(0,ss.lastIndexOf(".")));*/

     /* long a = 2 ;
      a++;
      String  ss = "sys" + 0 +a;
      System.out.println(ss);*/

      /*boolean ss = MP4VIDEOCONVERTUTILL.convert("E:/ffmg/ffmpeg.exe","E:/VID_20170301_13.13.00.mp4",
                                                "E:/FFF_666666.mp4");
     *//* MP4VIDEOCONVERTUTILL.makeScreenCut("E:/ffmpeg/ffmpeg.exe","E:/VID_20170301_13.13.00.mp4", "E:/test5555.jpg",
                                         "800x600");*//*
     if(!ss) {
       System.out.println("convert successi萨克东方大厦考虑到福建省当");
     } else {
       System.out.println("ahdoag");
     }*/
        //String path="E://pdl-images/ueditor/video/001.mp4";
        //path = path.replace("//","\\").replace("/","\\");
       // System.out.println(path);


    /*  String regEx = "^[1-9][0-9]{2,3}x[1-9][0-9]{2,3}$";
      Pattern p1 = Pattern.compile(regEx);
      Matcher m1 = p1.matcher("779x143");
      if (!m1.matches()) {
        System.out.println(" 格式不对");
      } else {
        System.out.println(" 正确");
      }*/
    }

 /* public static List<Student> sgy(List<Student> students) {
    for (Student st : students) {
      st.stuName = "++++++";
    }
    return students;
  }*/


}
