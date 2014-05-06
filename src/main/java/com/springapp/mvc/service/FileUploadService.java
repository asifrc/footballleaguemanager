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
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUploadService {
    private BufferedReader fileReader;

    public List<Player> createPlayerList(MultipartFile file) {
        List<Player> playerList = new ArrayList<Player>();

        try {
            fileReader = new BufferedReader((new InputStreamReader(file.getInputStream())));
            String line;
            while((line = fileReader.readLine()) != null) {
                playerList.add(buildPlayerFrom(line));
            }
        } catch (IOException e) {
            System.out.println("ERROR: Error reading file");
            e.printStackTrace();
        }

        return playerList;
    }

    public List<Coach> createCoachList(MultipartFile file) {
        List<Coach> coachList = new ArrayList<Coach>();

        try {
            fileReader = new BufferedReader((new InputStreamReader(file.getInputStream())));
            String line;
            while((line = fileReader.readLine()) != null) {
                coachList.add(buildCoachFrom(line));
            }
        } catch (IOException e) {
            System.out.println("ERROR: Error reading file");
            e.printStackTrace();
        }

        return coachList;
    }

    private Player buildPlayerFrom(String line) {
        String[] playerFields = line.split(",");
        String name =   playerFields[0];
        String team =   playerFields[1];
        String number = playerFields[2];
        int age =       Integer.parseInt(playerFields[3]);

        return new PlayerBuilder().withName(name)
                                  .withTeam(team)
                                  .withNumber(number)
                                  .withAge(age)
                                  .build();
    }

    private Coach buildCoachFrom(String line) {
        String[] playerFields = line.split(",");
        String name =   playerFields[0];
        String team =   playerFields[1];
        String position = playerFields[2];

        return new CoachBuilder().withName(name)
                .withTeam(team)
                .withPosition(position)
                .build();
    }
}
