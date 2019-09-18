package pl.wojtek120.chessopeningswebtrainer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChessboardController {

    @RequestMapping("/")
    public String chessboard(){
        return "chessboard";
    }

}
