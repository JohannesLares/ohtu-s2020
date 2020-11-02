package ohtuesimerkki;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void searchWorks() {
    	Player player = stats.search("Semenko");
    	assertEquals("Semenko", player.getName());
    	assertEquals("EDM", player.getTeam());
    }
    
    @Test
    public void searchReturnsNull() {
    	Player player = stats.search("Jokuhauska");
    	assertEquals(null, player);
    }
    
    @Test
    public void teamWorks() {
    	List<Player> list = stats.team("EDM");
    	assertEquals(3, list.size());
    	assertEquals("Gretzky", list.get(2).getName());
    }
    
    @Test
    public void topScoresWork() {
    	List<Player> list = stats.topScorers(3);
    	assertEquals("Gretzky", list.get(0).getName());
    }
}