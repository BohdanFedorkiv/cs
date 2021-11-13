package com.football.codeSeek.controllers;

import com.football.codeSeek.models.Team;
import com.football.codeSeek.services.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/team")
public class TeamController {
    private TeamService teamService;

    @GetMapping("/create")
    public String newTeam(@ModelAttribute("team") Team team) {
        return "createTeam";
    }

    @PostMapping("/create")
    public String createTeam(@ModelAttribute("team") Team team){
        teamService.save(team);
        return "redirect:/home";
    }

    @GetMapping("/getTeam/{id}")
    public String getTeam(Model model, @PathVariable("id") Long id){
        model.addAttribute("team", teamService.getById(id));
        return "teamInfo";
    }

    @GetMapping("/updateTeam/{id}")
    public String editTeam(Model model, @PathVariable("id") Long id){
        model.addAttribute("team", teamService.getById(id));
        return "updateTeam";
    }

    @PatchMapping("/updateTeam/{id}")
    public String updateTeam(@ModelAttribute("team") Team team,
                             @PathVariable("id") Long id){
        teamService.updateTeam(team, id);
        return String.format("redirect:/team/getTeam/%s", id);
    }

    @GetMapping("/getAllTeam")
    public String getAllTeam(Model model){
        model.addAttribute("teams", teamService.getAllTeam());
        return "getAllTeam";
    }

    @DeleteMapping("/deleteTeam/{id}")
    public String deleteTeam(@PathVariable("id") Long id){
        teamService.deleteTeam(id);
        return "redirect:/home";
    }
}
