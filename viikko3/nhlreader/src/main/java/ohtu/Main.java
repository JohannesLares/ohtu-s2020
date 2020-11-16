/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import java.util.*;

/**
 *
 * @author mluukkai
 */
public class Main {
    public static void main(String[] args) throws IOException {
      String url = "https://nhlstatisticsforohtu.herokuapp.com/players";

      String body = Request.Get(url).execute().returnContent().asString();

      Gson mapper = new Gson();
      Player[] players = mapper.fromJson(body, Player[].class);
      ArrayList<Player> plrs = new ArrayList<>();

      for (Player p : players) {
        if(p.getNationality().equals("FIN")) {
          plrs.add(p);
        }
      }

      Collections.sort(plrs, Collections.reverseOrder());

      for (Player p : plrs) {
        System.out.println(p);
      }
    }
}
