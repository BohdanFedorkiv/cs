package com.football.codeSeek.controllers;

import com.football.codeSeek.models.Player;
import com.football.codeSeek.services.PlayerService;
import com.football.codeSeek.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@AllArgsConstructor
@RequestMapping("/player")
public class PlayerController {
    private final PlayerService playerService;
    private final TeamService teamService;

    @GetMapping("/create")
    public String newPlayer(@ModelAttribute("player") Player player, Model model) {
        model.addAttribute("teams", teamService.getAllTeam());
        return "createPlayer";
    }

    @PostMapping("/create")
    public String createPlayer(@ModelAttribute("player") Player player){
        playerService.save(player);
        return "redirect:/home";
    }

    @GetMapping("/getPlayer/{id}")
    public String getPlayer(Model model, @PathVariable("id") Long id){
        model.addAttribute("player", playerService.getById(id));
        model.addAttribute("possibleTeams", teamService.getAllPotentialTeam(id));
        return "playerInfo";
    }

    @GetMapping("/updatePlayer/{id}")
    public String editPlayer(Model model, @PathVariable("id") Long id){
        model.addAttribute("player", playerService.getById(id));
        model.addAttribute("teams", teamService.getAllTeam());
        return "updatePlayer";
    }

    @PatchMapping("/updatePlayer/{id}")
    public String updatePlayer(@ModelAttribute("player") Player player,
                             @PathVariable("id") Long id){
        playerService.updatePlayer(player, id);
        return String.format("redirect:/player/getPlayer/%s", id);
    }

    @DeleteMapping("/deletePlayer/{id}")
    public String deletePlayer(@PathVariable("id") Long id){
        playerService.deletePlayer(id);
        return "redirect:/home";
    }

    @GetMapping("/transfer/{playerId}/{oldTeamId}/{newTeamId}")
    public String transfer(@PathVariable("playerId") Long playerId,
                          @PathVariable("oldTeamId") Long oldTeamId,
                          @PathVariable("newTeamId") Long newTeamId){
        playerService.transfer(playerId, oldTeamId, newTeamId);
        return "redirect:/home";
    }
}
