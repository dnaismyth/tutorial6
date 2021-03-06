package springsetup;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tutorial.springsetup.App;
import tutorial.springsetup.entity.Game;
import tutorial.springsetup.repository.GameRepository;
import tutorial.springsetup.service.GameService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)	// App.class is the name of my main application class, if yours is different you might have to change this.
public class GameServiceTests {
	
	@Autowired
	private GameRepository gameRepo;	// By autowiring in our game repository, we will have access to all of it's public methods
	
	@Autowired
	private GameService gameService;

	@Test
	public void testFindGameByTitle(){
		List<Game> results = gameRepo.findGamesByTitle("%mario%");	// We want to find all games with a title like "mario"
		Assert.assertEquals(2, results.size());
	}
	
	@Test
	public void testSearchGamesByTitle(){
		List<Game> results = gameService.searchGamesByTitle("mario");
		Assert.assertEquals(2,  results.size());
	}
	
	@Test
	public void testInsertGame() throws Exception{
		Game game = new Game();
		game.setTitle("The Legend of Zelda: A Link to the Past");
		game.setDescription("Action-adventure video game developed and published by Nintendo.");
		Game created = gameService.insertGame(game);
		Assert.assertNotNull(created.getId());
	}
}
