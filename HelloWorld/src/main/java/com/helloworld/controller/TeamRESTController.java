package com.helloworld.controller;

import com.helloworld.model.Team;
import com.helloworld.respository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/teams")
public class TeamRESTController {
    private TeamRepository teamRepository;

    @Autowired
    public TeamRESTController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping
    public List<Team> findAllTeams() {
        return teamRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Team findTeam(@PathVariable("id") long id) {
        return teamRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        teamRepository.save(team);
        return new ResponseEntity<Team>(team, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Team> deleteAll() {
        teamRepository.deleteAll();
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable("id") long id) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            System.out.println("Team not found");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        teamRepository.deleteById(id);
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Team> updateAll(@RequestBody List<Team> teams) {
        teamRepository.deleteAll();
        teamRepository.saveAll(teams);
        return new ResponseEntity<Team>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Team> updatePartOfTeam(@RequestBody Map<String, Object> updates, @PathVariable("id") long id) {
        Team team = teamRepository.findById(id);
        if (team == null) {
            System.out.println("Team not found.");
            return new ResponseEntity<Team>(HttpStatus.NOT_FOUND);
        }
        partialUpdate(team, updates);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void partialUpdate(Team team, Map<String, Object> updates) {
        if (updates.containsKey("teamName")) {
            team.setTeamName((String) updates.get("teamName"));
        }

        teamRepository.save(team);
    }

}
