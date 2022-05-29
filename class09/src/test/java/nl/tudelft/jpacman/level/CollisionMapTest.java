package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.npc.Ghost;
import nl.tudelft.jpacman.points.PointCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.junit.MockitoRule;

import java.awt.*;
import java.util.Collection;
import java.util.Map;

/**
 * @class: CollisionMapTest
 * @Decs: 进行GollisionMap测试
 */
public abstract class CollisionMapTest {
    private PointCalculator pointCalculator;
    private Player player;
    private Pellet pellet;
    private Ghost ghost;
    private CollisionMap Collisions;
    public void setPointCalculator(PointCalculator pointCalculator){
        this.pointCalculator=pointCalculator;
    }
    public void setPlayer(Player player){
        this.player=player;
    }
    public void setPellet(Pellet pellet){
        this.pellet=pellet;
    }

    public void setGhost(Ghost ghost) {
        this.ghost = ghost;
    }

    public void setPlayerColllisions(CollisionMap Colllisions) {
        this.Collisions = Colllisions;
    }

    public PointCalculator getPointCalculator() {
        return pointCalculator;
    }
    @BeforeEach
    abstract void init();
    @Test
    void testPlayerPellet(){
        Collisions.collide(player,pellet);
        Mockito.verify(pointCalculator,Mockito.times(1)).consumedAPellet(
                Mockito.eq(player),
                Mockito.eq(pellet)
        );
        Mockito.verify(pellet,Mockito.times(1)).leaveSquare();
        Mockito.verifyNoMoreInteractions(player,pellet);
    }

    /**
     * @ method：testPelletPlayer
     * @ 对象A：Pellet
     * @ 对象B：Player
     * @ desc：对象Pellet和Player进行碰撞测试
     */
    @Test
    void testPelletPlayer(){
        Collisions.collide(player,pellet);
        Mockito.verify(pointCalculator,Mockito.times(1)).consumedAPellet(
            Mockito.eq(player),
            Mockito.eq(pellet)
        );
        Mockito.verify(pellet,Mockito.times(1)).leaveSquare();
        Mockito.verifyNoMoreInteractions(pellet,player);
    }
    /**
     * @ method：testPlayerGhost
     * @ 对象A：Pellet
     * @ 对象B：Player
     * @ desc：对象Pellet和Player进行碰撞测试
     */
    @Test
    void testPlayerGhost(){
        Collisions.collide(player,ghost);
        Mockito.verify(pointCalculator,Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost)
        );
        Mockito.verify(player,Mockito.times(1)).setAlive(false);
        Mockito.verify(player,Mockito.times(1)).setKiller(Mockito.eq(ghost));
        Mockito.verifyNoMoreInteractions(player,ghost);
    }
    /**
     * @ method：testGhostPlayer
     * @ 对象A：Ghost
     * @ 对象B：Player
     * @ desc：对象Pellet和Player进行碰撞测试
     */
    @Test
    void testGhostPlayer(){
        Collisions.collide(ghost,player);
        Mockito.verify(pointCalculator,Mockito.times(1)).collidedWithAGhost(
            Mockito.eq(player),
            Mockito.eq(ghost)
        );
        Mockito.verify(player,Mockito.times(1)).setAlive(false);
        Mockito.verify(player,Mockito.times(1)).setKiller(Mockito.eq(ghost));
        Mockito.verifyNoMoreInteractions(player,ghost);
    }
    /**
     * @ method：testPlayerGhost
     * @ 对象A：Playe
     * @ 对象B：Ghost
     * @ desc：对象Playe和Ghost进行碰撞测试
     */
    @Test
    void testGhostPellet(){
        Collisions.collide(ghost,pellet);
        Mockito.verifyZeroInteractions(ghost,pellet);
    }
    /**
     * @ method：testGhostGhost
     * @ 对象A：Ghost
     * @ 对象B：Ghost
     * @ desc：两个Ghost测试
     */
    @Test
    void testGhostGhost(){
        Ghost ghost1=Mockito.mock(Ghost.class);
        Collisions.collide(ghost,ghost1);
        Mockito.verifyZeroInteractions(ghost,ghost1);
    }
    /**
     * @ method：testPelletPellet
     * @ 对象A：Pellet
     * @ 对象B：Pellet
     * @ desc：两个Pellet测试
     */
    @Test
    void testPelletPellet(){
        Pellet pellet1=Mockito.mock(Pellet.class);
        Collisions.collide(pellet,pellet1);
        Mockito.verifyZeroInteractions(pellet,pellet1);

    }
    /**
     * @ method：testTwoPlayer
     * @ 对象A：Pellet
     * @ 对象B：Player
     * @ desc：两个玩家碰撞
     */
    @Test
    void testTwoPlayer(){
        Player player1=Mockito.mock(Player.class);
        Collisions.collide(player,player1);
        Mockito.verifyZeroInteractions(player,player1);
    }
}
