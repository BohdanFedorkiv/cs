package com.football.codeSeek.services;

import com.football.codeSeek.models.Player;
import com.football.codeSeek.models.Team;
import com.football.codeSeek.repositories.PlayerRepo;
import com.football.codeSeek.repositories.TeamRepo;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final TeamRepo teamRepo;

    public PlayerService(PlayerRepo playerRepo, TeamRepo teamRepo) {
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    public void save(Player player){
        playerRepo.save(player);
    }

    public Player getById(Long id){
        return playerRepo.getById(id);
    }

    public void updatePlayer(Player player, Long id){
        Player updated = playerRepo.getById(id);
        updated.setId(player.getId());
        updated.setFirstName(player.getFirstName());
        updated.setLastName(player.getLastName());
        updated.setAge(player.getAge());
        updated.setExperience(player.getExperience());
        playerRepo.save(updated);
    }

    public void deletePlayer(Long id){
        playerRepo.deleteById(id);
    }

    public void transfer(Long playerId, Long exTeamId, Long newTeamId){
        Player player = playerRepo.getById(playerId);
        Team exTeam = teamRepo.getById(exTeamId);
        Team newTeam = teamRepo.getById(newTeamId);
        double trans = ((double)player.getExperience() * 12 * 100000) / (double)player.getAge();
        double comm = (trans * exTeam.getCommission()) / 100;
        double fullPrice = trans + comm;
        exTeam.setAccount(exTeam.getAccount() + fullPrice);
        newTeam.setAccount(newTeam.getAccount() - fullPrice);
        player.setTeam(newTeam);
        teamRepo.save(exTeam);
        teamRepo.save(newTeam);
        playerRepo.save(player);
    }
}
