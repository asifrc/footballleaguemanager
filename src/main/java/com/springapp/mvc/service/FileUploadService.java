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
            players.add(PlayerBuilder.buildPlayerFrom(line));
        }

        return players;
    }

    public Set<Coach> createCoachesFrom(MultipartFile file) {
        Set<Coach> coaches = new HashSet<Coach>();
        Set<String> fileLines = getFileLines(file);

        for (String line : fileLines) {
            coaches.add(CoachBuilder.buildCoachFrom(line));
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

}
