package com.springapp.mvc.service;

import com.springapp.mvc.model.Coach;
import com.springapp.mvc.model.CoachBuilder;
import com.springapp.mvc.model.Player;
import com.springapp.mvc.model.PlayerBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileUploadService {
    private BufferedReader fileReader;

    public Set<Player> createPlayersFrom(MultipartFile file) {
        Set<Player> players = new HashSet<Player>();

        Set<String> fileLines = getFileLines(file);
        for (String line : fileLines) {
            players.add(buildPlayerFrom(line));
        }

        return players;
    }

    public Set<Coach> createCoachesFrom(MultipartFile file) {
        Set<Coach> coaches = new HashSet<Coach>();

        Set<String> fileLines = getFileLines(file);
        for (String line : fileLines) {
            coaches.add(buildCoachFrom(line));
        }

        return coaches;
    }

    private Set<String> getFileLines(MultipartFile file) {
        Set<String> fileLines = new HashSet<String>();

        try {
            fileReader = new BufferedReader((new InputStreamReader(file.getInputStream())));
            String line;
            while ((line = fileReader.readLine()) != null) {
                fileLines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return fileLines;
    }

    private Player buildPlayerFrom(String line) {
        String[] playerFields = line.split(",");
        if (playerFields.length > 4) {
            throw new RuntimeException("Too many fields");
        }
        String name = playerFields[0];
        String team = playerFields[1];
        String number = playerFields[2];
        int age = Integer.parseInt(playerFields[3]);

        return new PlayerBuilder().withName(name)
                .withTeam(team)
                .withNumber(number)
                .withAge(age)
                .build();
    }

    private Coach buildCoachFrom(String line) {
        String[] coachFields = line.split(",");
        if (coachFields.length > 3) {
            throw new RuntimeException("Too many fields");
        }

        String name = coachFields[0];
        String team = coachFields[1];
        String position = coachFields[2];

        return new CoachBuilder().withName(name)
                .withTeam(team)
                .withPosition(position)
                .build();
    }
}
