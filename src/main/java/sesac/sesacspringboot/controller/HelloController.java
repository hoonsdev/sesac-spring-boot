package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
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
    List<Person> list = new ArrayList<>();
    Person person1 = new Person("kim", 10);
    Person person2 = new Person("lee", 20);
    Person person3 = new Person("hong", 30);
    Person person4 = new Person("park", 40);
    Person person5 = new Person("shin", 50);
    list.add(person1);
    list.add(person2);
    list.add(person3);
    list.add(person4);
    list.add(person5);


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
    model.addAttribute("age", 18);
    model.addAttribute("list", list);

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

  class Person {
    private String name;
    private  int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }
  }
}

