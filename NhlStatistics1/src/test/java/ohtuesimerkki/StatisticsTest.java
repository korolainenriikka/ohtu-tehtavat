/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author riikoro
 */
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
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void playerSearchedByNameIsFound() {
        Player player = stats.search("Semenko");
        assertEquals("Semenko", player.getName());
    }
    
    @Test
    public void nameNotInPlayersSearchedReturnsNull() {
        assertEquals(null, stats.search("moi"));
    }
    
    @Test
    public void playersInTeamAreFound() {
        List<Player> playersInEDM = stats.team("EDM");
        assertEquals(3, playersInEDM.size());        
    }
    
    @Test
    public void topScorersAreListedInCorrectOrder() {
        List<Player> top = stats.topScorers(3);
        assertEquals("Gretzky", top.get(0).getName());
        assertEquals("Lemieux", top.get(1).getName());
        assertEquals("Yzerman", top.get(2).getName());
    
    }
}
