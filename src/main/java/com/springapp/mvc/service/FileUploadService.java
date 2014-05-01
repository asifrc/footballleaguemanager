package com.springapp.mvc.service;

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
    public List<Player> createPlayerList(MultipartFile file) {
        List<Player> playerList = new ArrayList<Player>();
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = fileReader.readLine()) != null) {
                playerList.add(buildPlayerFrom(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerList;
    }

    private Player buildPlayerFrom(String line) {
        String[] playerFields = line.split(",");
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
}
