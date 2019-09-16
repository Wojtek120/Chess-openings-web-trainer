package pl.wojtek120.chessopeningswebtrainer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move.UserOpeningMoveCreationDto;
import pl.wojtek120.chessopeningswebtrainer.model.entities.UserOpeningMove;
import pl.wojtek120.chessopeningswebtrainer.model.repositories.UserOpeningMoveRepository;

@Controller
public class TestController {

    private UserOpeningMoveRepository userOpeningMoveRepository;

    public TestController(UserOpeningMoveRepository userOpeningMoveRepository) {
        this.userOpeningMoveRepository = userOpeningMoveRepository;
    }

//    @GetMapping("/")
//    public String testPrepare(Model model){
//        model.addAttribute("userOpeningMove", new UserOpeningMove() );
//
//        return "test";
//    }
//
//    @PostMapping("/")
//    public String test(UserOpeningMove userOpeningMove){
//        userOpeningMoveRepository.save(userOpeningMove);
//
//        return "redirect:/";
//    }

//    @GetMapping("/")
//    public String testPrepare(Model model){
//        model.addAttribute("userOpeningMoveCreationDto", new UserOpeningMoveCreationDto() );
//
//        return "test";
//    }
//
//    @PostMapping("/")
//    public String test(@DTO(UserOpeningMoveCreationDto.class) UserOpeningMove userOpeningMove){
//        userOpeningMoveRepository.save(userOpeningMove);
//
//        return "redirect:/";
//    }
}
