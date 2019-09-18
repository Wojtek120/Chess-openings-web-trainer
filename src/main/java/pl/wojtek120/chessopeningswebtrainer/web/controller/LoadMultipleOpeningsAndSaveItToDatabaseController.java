package pl.wojtek120.chessopeningswebtrainer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.OpeningDto;
import pl.wojtek120.chessopeningswebtrainer.model.services.OpeningService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/admin/load")
public class LoadMultipleOpeningsAndSaveItToDatabaseController {

    private final OpeningService openingService;

    public LoadMultipleOpeningsAndSaveItToDatabaseController(OpeningService openingService) {
        this.openingService = openingService;
    }

    @GetMapping
    public String loadOpeningsFromFileWhichIsSelectedInJs(Model model) {
        model.addAttribute("openingString", "");
        return "loadPgnFile";
    }

    @PostMapping
    public String saveOpeningToDatabase(String openingPgnString) {

        String[] splitedOpeningString = openingPgnString.replaceAll("\r", "").split("\n");
        OpeningDto openingDto = new OpeningDto();
        boolean first = true;


        for(String lineString : splitedOpeningString) {

            if(lineString.matches(".*ECO.*")) {

                if(!first) {
                    openingService.save(openingDto);
                }
                first = false;

                openingDto.setEco(getStringBetweenQuotes(lineString));
                openingDto.setVariation("");
            }

            if(lineString.matches(".*Opening.*")) {
                openingDto.setOpeningName(getStringBetweenQuotes(lineString));
            }

            if(lineString.matches(".*Variation.*")) {
                openingDto.setVariation(getStringBetweenQuotes(lineString));
            }

            if(lineString.matches("^1\\..*")) {
                openingDto.setPgn(lineString);
            } else {
                openingDto.setPgn(openingDto.getPgn() + " " + lineString);
            }
        }

        return null;
    }

    private String getStringBetweenQuotes(String str){
        Pattern pattern = Pattern.compile( "\"(.*)\"");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return "redirect:/";
    }
}
