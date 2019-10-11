package pl.wojtek120.chessopeningswebtrainer.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.UserOpeningDto;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.branch.UserOpeningBranchDto;
import pl.wojtek120.chessopeningswebtrainer.model.dto.user.opening.move.UserOpeningMoveCreationDto;
import pl.wojtek120.chessopeningswebtrainer.model.services.OpeningService;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserOpeningBranchService;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserOpeningMoveService;
import pl.wojtek120.chessopeningswebtrainer.model.services.UserOpeningService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/chessboard")
public class ChessboardController {

    private final UserOpeningMoveService userOpeningMoveService;
    private final UserOpeningBranchService userOpeningBranchService;
    private final UserOpeningService userOpeningService;
    private final OpeningService openingService;

    private String openingName;

    public ChessboardController(UserOpeningMoveService userOpeningMoveService, UserOpeningBranchService userOpeningBranchService, UserOpeningService userOpeningService, OpeningService openingService) {
        this.userOpeningMoveService = userOpeningMoveService;
        this.userOpeningBranchService = userOpeningBranchService;
        this.userOpeningService = userOpeningService;
        this.openingService = openingService;
    }

    @RequestMapping
    public String chessboard(@CookieValue(value = "opening", defaultValue = "-1") String currentOpeningIdStr) {

        Long currentOpeningId = Long.parseLong(currentOpeningIdStr);

        if(currentOpeningId == -1){
            return "redirect:/user/opening/list";
        }


        return "chessboard";
    }


    @GetMapping("/branch/load")
    @ResponseBody
    private String loadOpeningBranchesInfo(@CookieValue(value = "opening", defaultValue = "-1") String currentOpeningIdStr) {

        Long currentOpeningId = Long.parseLong(currentOpeningIdStr);

        if(currentOpeningId == -1){
            return "redirect:/user/opening/list";
        }

        return userOpeningBranchService.loadOpeningBranchesInfo(currentOpeningId);
    }


    @PostMapping("/save/repository")
    public String saveBranchStringToDatabase(@CookieValue(value = "opening", defaultValue = "-1") String currentOpeningIdStr, @RequestBody String jsonBranchStr, HttpServletResponse response) {

        Long currentOpeningId = Long.parseLong(currentOpeningIdStr);

        if(currentOpeningId == -1){
            return "redirect:/user/opening/list";
        }

        UserOpeningDto userOpeningDto = userOpeningService.getOne(currentOpeningId);

        userOpeningBranchService.deleteAllByOpeningIdWithAllMoves(currentOpeningId);

        ObjectMapper objectMapper = new ObjectMapper();
        Long branchId = null;
        try {
            Map<String, String> allMovesInRepositoryMap = objectMapper.readValue(jsonBranchStr, new TypeReference<LinkedHashMap<String, String>>() {
            });

            for (Map.Entry<String, String> entry : allMovesInRepositoryMap.entrySet()) {
                String pgnString = entry.getValue();
                int[] numbersOfActualBranchParentAndMoveNumber = getNoOfActualBranchParentAndMoveFromKey(entry.getKey());

                if (numbersOfActualBranchParentAndMoveNumber != null) {
                    int currentBranchNumber = numbersOfActualBranchParentAndMoveNumber[0];
                    int parentBranchNumber = numbersOfActualBranchParentAndMoveNumber[1];
                    int moveNumber = numbersOfActualBranchParentAndMoveNumber[2];

                    if (moveNumber == 0) {
                        UserOpeningBranchDto userOpeningBranchDto = new UserOpeningBranchDto(currentBranchNumber, parentBranchNumber, currentOpeningId);
                        branchId = userOpeningBranchService.save(userOpeningBranchDto);


                        userOpeningDto.addUserOpeningBranch(branchId);
                        userOpeningService.save(userOpeningDto);

                    }

                    setAndAddMoveToDatabase(moveNumber, branchId, pgnString);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

//        loadOpeningBranchesInfo(currentOpeningId); //TODO usun

        return "redirect:/chessboard";
    }

    @PostMapping("/set/opening")
    public void setOpeningName(@RequestBody String pgnStr){

        pgnStr = pgnStr.replaceAll("\\+", " ").replaceAll("=", "");

//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(pgnStr); //TODO NIE DZIALA GDY SZACH

        openingName = openingService.getOpeningNameByPgn(pgnStr);

    }

    @GetMapping("/get/opening")
    @ResponseBody
    public String getOpeningName(){
        return openingName;
    }



    private void setAndAddMoveToDatabase(int moveNumber, Long userOpeningBranchId, String pgn) {
        UserOpeningMoveCreationDto userOpeningMoveDto = new UserOpeningMoveCreationDto();
        userOpeningMoveDto.setUserOpeningBranchId(userOpeningBranchId);
        userOpeningMoveDto.setMoveNumber(moveNumber);
        userOpeningMoveDto.setPgn(pgn);

        System.out.println("Move number = " + moveNumber);
        System.out.println("Branch number = " + userOpeningBranchId);
        System.out.println("Pgn = " + pgn);
        System.out.println(userOpeningMoveDto);


        userOpeningMoveService.save(userOpeningMoveDto);
    }

    private int[] getNoOfActualBranchParentAndMoveFromKey(String key) {

        Pattern pattern = Pattern.compile("branch.(\\d+).(-?\\d+).(\\d+)");
        Matcher matcher = pattern.matcher(key);
        if (matcher.find()) {
            int currentBranchNumber = Integer.parseInt(matcher.group(1));
            int parentBranchNumber = Integer.parseInt(matcher.group(2));
            int moveNumber = Integer.parseInt(matcher.group(3));

            return new int[]{currentBranchNumber, parentBranchNumber, moveNumber};
        }

        return null;
    }

}
