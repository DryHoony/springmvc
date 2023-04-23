package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }


    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!");

        return "response/hello"; // view 의 논리적인 이름으로 들어감
        // @ResponseBody 쓰면 HTTP 메세지 바디에 들어가서 곤란,,
    }

    // 권장하진 않지만
    // void 인경우 바디를 처리하는 파라미터가 없으면 논리 뷰 이름 사용가능 - 스프링이 관례적으로 생략하도록 도와줌
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello!");
    }


}
