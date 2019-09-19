package pl.wojtek120.chessopeningswebtrainer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.UserOpeningCreationDto;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.UserOpeningDto;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserOpeningService;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserService;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("user/opening")
public class UserOpeningController {

    private final UserOpeningService userOpeningService;
    private final UserService userService;

    public UserOpeningController(UserOpeningService userOpeningService, UserService userService) {
        this.userOpeningService = userOpeningService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String listOfRepositories(Model model, Principal principal) {
        model.addAttribute("openings", userOpeningService.getAllByUserUsername(principal.getName()));
        return "userOpeningList";
    }

    @GetMapping("/add")
    public String addNewOpeningRepository(Model model) {
        model.addAttribute("userOpeningDto", new UserOpeningCreationDto());
        return "userOpeningAdd";
    }

    @PostMapping("/add")
    public String processAddBookPage(@Valid UserOpeningCreationDto userOpeningDto, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "/books/add";
        }

        userOpeningDto.setUserId(userService.getLoggedUserIdByUsername(principal.getName()));

        return "redirect:/user/opening/list?addedOpeningId=" + userOpeningService.save(userOpeningDto);
    }
}
