package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class PlayerCollisionsTest extends CollisionMapTest{
    @BeforeEach
    void init(){
        this.setPointCalculator(Mockito.mock(PointCalculator.class));
        this.setPlayer(Mockito.mock(Player.class));
        this.setPellet(Mockito.mock(Pellet.class));
        this.setGhost(Mockito.mock(Ghost.class));
        this.setPlayerColllisions(new PlayerCollisions(this.getPointCalculator()));
    }

}
