package pl.wojtek120.chessopeningswebtrainer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.UserOpeningCreationDto;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserOpeningBranchService;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserOpeningService;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("user/opening")
public class UserOpeningController {

    private final UserOpeningService userOpeningService;
    private final UserService userService;
    private final UserOpeningBranchService userOpeningBranchService;

    public UserOpeningController(UserOpeningService userOpeningService, UserService userService, UserOpeningBranchService userOpeningBranchService) {
        this.userOpeningService = userOpeningService;
        this.userService = userService;
        this.userOpeningBranchService = userOpeningBranchService;
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


    @PostMapping("/delete/{id}")
    public String deleteRepositoryWithAllBranchesAndMoves(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response){

        Cookie cookie = WebUtils.getCookie(request, "opening");
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }


        userOpeningBranchService.deleteAllByOpeningIdWithAllMoves(id);
        userOpeningService.deleteById(id);

        return "redirect:/user/opening/list";
    }



    @GetMapping("/train/{id}")
    @ResponseBody
    public ModelAndView trainSelectedBranch(@PathVariable String id, HttpServletResponse response) {

        Cookie cookie = new Cookie("opening", id);
        cookie.setPath("/");
        response.addCookie(cookie);


        return new ModelAndView("redirect:/chessboard");
    }
}
