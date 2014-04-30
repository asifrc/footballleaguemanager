package com.springapp.mvc.service;

import com.springapp.mvc.model.Player;
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
                Player player = new Player(line, "0");
                playerList.add(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return playerList;
    }
}
