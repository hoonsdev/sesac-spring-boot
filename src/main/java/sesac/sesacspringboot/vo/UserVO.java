package sesac.sesacspringboot.vo;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class UserVO {
  public String name, age;
  public String gender, birth;
  public String[] hobby;
}
