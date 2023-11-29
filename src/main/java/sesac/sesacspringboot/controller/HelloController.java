package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HelloController {
  @GetMapping("hi")
  public String getHi(Model model) {
    //Model model: 컨트롤러 클래스의 메소드가 파라미터로 받을 수 있는 객체
//    model.addAttribute("msg", "hi");
//    return "hi";

    // 타임리프 표현식과 문법
    Hello hello = new Hello(99);
    List<String> names = Arrays.asList("Kim", "Lee", "Hong", "Park");


    model.addAttribute("hello", "Spring World");
    model.addAttribute("uText", "<strong>Hello</strong>");
    model.addAttribute("value", "이름을 입력하세요!");
    model.addAttribute("withValue", "hello");
    model.addAttribute("link", "hi");
    model.addAttribute("imgSrc", "/test222.jpg");
    model.addAttribute("userRole", "admin");
    model.addAttribute("isAdmin", "false");
    model.addAttribute("names", names);
    model.addAttribute("classHello", hello);

    return "hi";
  }

  class Hello {
    private int age;

    public Hello(int age) {
      this.age = age;
    }

    public int getAge() {
      return age;
    }
  }
}

