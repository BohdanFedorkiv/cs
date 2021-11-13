package com.football.codeSeek.services;

import com.football.codeSeek.models.Team;
import com.football.codeSeek.repositories.TeamRepo;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TeamService {
    private final TeamRepo teamRepo;

    public TeamService(TeamRepo teamRepo) {
        this.teamRepo = teamRepo;
    }

    public void save(Team team){
        teamRepo.save(team);
    }

    public Team getById(Long id){
        return teamRepo.getById(id);
    }

    public List<Team> getAllTeam(){
        return teamRepo.getAll();
    }

    public void updateTeam(Team team, Long id){
        Team updated = teamRepo.getById(id);
        updated.setId(team.getId());
        updated.setName(team.getName());
        updated.setCountry(team.getCountry());
        updated.setAccount(team.getAccount());
        updated.setCommission(team.getCommission());
        updated.setPlayers(team.getPlayers());
        teamRepo.save(updated);
    }

    public void deleteTeam(Long id){
        teamRepo.deleteById(id);
    }

    public List<Team> getAllPotentialTeam(Long id){
        return teamRepo.getTeamsWithoutCurrentTeam(id);
    }
}
