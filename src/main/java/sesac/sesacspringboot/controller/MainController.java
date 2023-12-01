package sesac.sesacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sesac.sesacspringboot.dto.UserDTO;
import sesac.sesacspringboot.vo.UserVO;

import java.util.Arrays;

@Controller
// @RestController: Controller 면서 모든 메소드가 @ResponseBody 를 갖도록 하는 어노테이션
public class MainController {
  @GetMapping("/api")
  public String getApi() {
    return "request";
  }

  // 1) get: ?key=value
  // 검색 / 해시태그
  // /get/response1?name=abc&age=14
  @GetMapping("/get/response1")
  public String getResponse1(@RequestParam(value = "name") String name, @RequestParam(value = "age", required = false) int age, Model model) {
    // @RequestParam: 요청의 파라미터를 매개변수로 받을 때 적는 어노테이션
    // value = ? 뒤에 넘어온 key
    // required 값을 설정할 수 있다. = true / false -> 기본값은 true
    // required가 true로 되어있으면 ? 뒤에 해당되는 key가 없을 경우 메소드를 찾지 못한다
    model.addAttribute("name", name);
    model.addAttribute("age", age);
    return "response";
  }

  @GetMapping("/get/response2")
  public String getResponse2(@RequestParam(value = "name", required = false) String name, Model model) {
    model.addAttribute("name", name);
    return "response";
  }

  // /get/response3/이름/나이
  // uri에 변수가 들어올 때 그 값을 가져오는 방법
  @GetMapping("/get/response3/{name}/{age}")
  public String getResponse3(@PathVariable(value = "name") String n, @PathVariable String age, Model model) {
    model.addAttribute("name", n);
    model.addAttribute("age", age);
    return "response";
  }

  @GetMapping({"/get/response4/{name}", "/get/response4/{name}/{age}"})
  public String getResponse4(@PathVariable(value = "name") String n, @PathVariable(required = false) String age, Model model) {
    // @Pathvariable에 required 설정이 가능하나, 기본값은 true
    // @Pathvariable에 required 설정할 때는 GetMapping에 url도 같이 설정해줘야 한다.
    // required 값이 false 인 자원이 뒤로 가야함
    model.addAttribute("name", n);
    model.addAttribute("age", age);
    return "response";
  }

  @GetMapping("/introduce/{name}")
  public String getIntroduce(@PathVariable(value = "name") String name, Model model) {
    model.addAttribute("name", name);
    return "response";
  }

  @GetMapping("/introduce2")
  public String getIntroduce2(@RequestParam(value = "name") String name, @RequestParam(value = "age") int age, Model model) {
    model.addAttribute("name", name);
    model.addAttribute("age", age);
    return "response";
  }

  //////////////////////////////
  // post 값을 전달할 때 그 값을 controller에서 받는 방법은 @RequestParam
  @PostMapping("/post/response1")
  public String postResponse1(@RequestParam(value = "name") String name, Model model) {
    model.addAttribute("name", name);
    return "response";
  }

  @PostMapping("/post/response2")
  public String postResponse2(@RequestParam(value = "name", required = false) String name, Model model) {
    model.addAttribute("name", name);
    return "response";
  }

  @PostMapping("/post/response3")
  @ResponseBody // res.send
  // return 하는 문자열의 template 파일을 불러오는게 아니라
  // return 하는 문자열 그대로 값을 전달하는 것
  public String postResponse3(@RequestParam(value = "name") String name, Model model) {
    model.addAttribute("name", name);
    return "response 는 이겁니다!";
  }

  @PostMapping("/postPrac")
  @ResponseBody
  public String postPrac(@RequestParam(value = "name") String name, @RequestParam(value = "gender") String gender, @RequestParam(value = "year") int year, @RequestParam(value = "month") int month, @RequestParam(value = "day") int day, @RequestParam(value = "hobby") String hobby, Model model) {
    model.addAttribute("name", name);
    model.addAttribute("gender", gender);
    model.addAttribute("year", year);
    model.addAttribute("month", month);
    model.addAttribute("day", day);
    model.addAttribute("hobby", hobby);
    return "이름 : " + name + "성별 : " + gender + "생년월일 : " + year + "-" + month + "-" +
        day + "관심사 : " + hobby;
  }

  @GetMapping("/postPrac")
  public String prac() {
    return "postPrac";
  }

  @GetMapping("/dto/response1")
  @ResponseBody
  public String dtoResponse1(@ModelAttribute UserDTO userDTO) {
    // 변수로 값을 하나씩 가져오는 게 아니라 객체에 값을 담아서 가져오고 싶을 때 사용하는 방법
    // @ModelAttribute: html 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑해주는 역할
    // 매핑 = setter 함수 실행
    // -> ?name=&age= -> setName() setAge()
    String msg = "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    return msg;
  }

  @PostMapping("/dto/response2")
  @ResponseBody
  public String dtoResponse2(UserDTO userDTO) {
    // 아무것도 없는 상태 = @ModelAttribute 상태
    String msg = "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    return msg;
  }

  @PostMapping("/dto/response3")
  @ResponseBody
  public String dtoResponse3(@RequestBody UserDTO userDTO) {
    // @RequestBody: 요청의 본문에 있는 데이터(body)를 받아와서 객체에 매핑(필드값에 값을 주입 = 내가 만든 setter가 아닌 필드 자체에 값을 넣는 방식)
    // 전달받은 요청의 형식이 json 또는 xml 일 때에만 실행 가능
    // 일반폼전송 = www-x-form-urlencoded
    String msg = "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    return msg;
  }

  // get 방식 - vo = null (modelattribute = setter 함수를 실행)
  // post - vo = null
  // post 방식 - vo - requestbody = 오류
  @GetMapping("/vo/response1")
  @ResponseBody
  public String voResponse1(UserVO userVO) {
    String msg = "이름 : " + userVO.getName() + ", 나이: " + userVO.getAge();
    return msg;
  }

  @PostMapping("/vo/response2")
  @ResponseBody
  public String voResponse2(UserVO userVO) {
    String msg = "이름 : " + userVO.getName() + ", 나이: " + userVO.getAge();
    return msg;
  }

  @PostMapping("/vo/response3")
  @ResponseBody
  public String voResponse3(@RequestBody UserVO userVO) {
    String msg = "이름 : " + userVO.getName() + ", 나이: " + userVO.getAge();
    return msg;
  }

  @GetMapping("/axios/response1")
  @ResponseBody
  public String axiosAPI1(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age) {
    String msg = "이름 : " + name + ", 나이: " + age;
    return msg;
  }

  @GetMapping("/axios/response2")
  @ResponseBody
  public String axiosAPI2(UserDTO userDTO) {
    String msg = "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    return msg;
  }

  @PostMapping("/axios/response3")
  @ResponseBody
  public String axiosAPI3(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) String age) {
    // @RequestParam = @ModelAttribute = query string 으로 넘어온 데이터를 읽을 수 있다.
    // json으로 데이터가 넘어와서 데이터 자체는 넘어왔지만 json을 읽을 수 없어 안 넘어온것처럼 인식
    // 데이터가 없는 상황에서 @RequestParam에 required가 true => 에러
    String msg = "이름 : " + name + ", 나이: " + age;
    return msg;
  }

  @PostMapping("/axios/response4")
  @ResponseBody
  public String axiosAPI4(UserDTO userDTO) {
    // ModelAttribute 는 json으로 넘어온 데이터를 읽지 못한다! (setter 함수는 실행하지만, json 을 인식하지 못함) -> null
    String msg = "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    return msg;
  }

  @PostMapping("/axios/response5")
  @ResponseBody
  public String axiosAPI5(@RequestBody UserDTO userDTO) {
    String msg = "이름 : " + userDTO.getName() + ", 나이: " + userDTO.getAge();
    return msg;
  }

  // axios get - (RequestParam) - ? > o
  // axios get - dto > o
  // axios post - (RequestParam) -> required가 false일 때는 null, true일 때는 x
  // axios post - requestbody x dto -> null
  // axios post - requestbody o dto -> o

//  Get 일반 : 가능
//  GET VO : null = @modelAttribute (setter 함수를 실행할 수 없어서)
//  Post 일반 : 실패
//  Post VO - RequestBody X: null
//  Post VO - RequestBody O: 가능 = setter 함수 실행할 수 없어도 매핑 가능

  // 실습
  @GetMapping("/dynamicForm")
  public String dynamicForm() {
    return "dynamicForm";
  }

  @PostMapping("/axios/dynamicForm")
  @ResponseBody
  public String dynamicForm(@RequestBody UserVO userVO) {
//    String msg = "이름 : " + userVO.getName() + ", 성별: " + userVO.getGender() + ", 생년월일: " + userVO.getBirth() + ", 관심사: " + userVO.getHobby();
    System.out.println(userVO.getName() + userVO.getGender() + userVO.getBirth() + Arrays.toString(userVO.getHobby()));
    return userVO.getName();
  }
}
