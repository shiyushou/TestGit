/**
 * Created by shiyushou on 2017/5/22.
 */
public class Student {
  public String stuId;
  public String stuName;

  public Student() {}
  public Student(String stuId, String stuName) {
    this.stuId =  stuId;
    this.stuName = stuName;
  }



  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Student){
      Student stu = (Student) obj;
      if (stu.stuId == this.stuId)
        return true;
    }
return false;
  }

  @Override
  public int hashCode() {
    return this.stuId.hashCode();
  }

  public String getStuId() {
    return stuId;
  }

  public void setStuId(String stuId) {
    this.stuId = stuId;
  }

  public String getStuName() {
    return stuName;
  }

  public void setStuName(String stuName) {
    this.stuName = stuName;
  }
}
