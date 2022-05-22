package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.PlayerFactory;
import nl.tudelft.jpacman.points.DefaultPointCalculator;
import nl.tudelft.jpacman.points.PointCalculator;
import nl.tudelft.jpacman.sprite.PacManSprites;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.PrintWriter;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.in;
import static org.junit.jupiter.api.Assertions.*;

public class InkyTest {
        private PacManSprites pacManSprites;
        private LevelFactory levelFactory;
        private GhostFactory ghostFactory;
        private PlayerFactory playerFactory;
        private BoardFactory boardFactory;
        private GhostMapParser ghostMapParser;
        private PointCalculator pointCalculator;
        @BeforeEach
        void Init(){
            pacManSprites=new PacManSprites();
            boardFactory=new BoardFactory(pacManSprites);
            ghostFactory=new GhostFactory(pacManSprites);
            pointCalculator=new DefaultPointCalculator();
            levelFactory=new LevelFactory(pacManSprites,ghostFactory,pointCalculator);
            playerFactory=new PlayerFactory(pacManSprites);
            ghostMapParser=new GhostMapParser(levelFactory,boardFactory,ghostFactory);
        }
        /*
        测试没有Blinky时Inky行动
         */
        @Test
        void testNoBlinky(){
            Level level = ghostMapParser.parseMap(
                Lists.newArrayList("###PI ", "######")
            );
            Player player = playerFactory.createPacMan();
            player.setDirection(Direction.WEST);
            level.registerPlayer(player);

            Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());

            assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());

        }
        @Test
        /*
            测试当player，inky，blinky之间没有有效路径时情况
         */
        void testNoPath(){
            Level level=ghostMapParser.parseMap(
                Lists.newArrayList(
                    "############", "  #P#  I  B#", "############"
                )
            );
            Player player=playerFactory.createPacMan();
            player.setDirection(Direction.WEST);
            level.registerPlayer(player);
            Inky inky=Navigation.findUnitInBoard(Inky.class,level.getBoard());
            assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
        }
        @Test
        /*
        测试没有Player时的情况
         */
        void testNoPlayer(){
            Level level=ghostMapParser.parseMap(
                Lists.newArrayList(
                    "####", "B  I", "####"
                )
            );
            Inky inky=Navigation.findUnitInBoard(Inky.class,level.getBoard());
            assertThat(inky.nextAiMove()).isEqualTo(Optional.empty());
        }
        @Test
        /**
         * 当player、inky、blinky都在地图中，且存在有效路径
         * Inky跟在Blinky之后，追随Player
         */
        void testGoTowardPlayer(){
            Level level=ghostMapParser.parseMap(
                Lists.newArrayList(
                    "#################","#    P    B   I #","#################"
                )
            );
            Player player=playerFactory.createPacMan();
            player.setDirection(Direction.EAST);
            level.registerPlayer(player);
            Inky inky=Navigation.findUnitInBoard(Inky.class,level.getBoard());
            assertThat(inky.nextAiMove()).isEqualTo(Optional.of(Direction.WEST));
        }
        @Test
        /**
         * 当player、inky、blinky都在地图中，且存在有效路径
         * Inky在Player之前，且Blinky在Inky之后，则Inky将远离Player
         */
        void testInkyMoveAway(){
            Level level=ghostMapParser.parseMap(
                Lists.newArrayList(
                    "##############","#  B P  I    #","##############"

                )
            );
            Player player = playerFactory.createPacMan();
            player.setDirection(Direction.EAST);
            level.registerPlayer(player);
            Inky inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());

            assertThat(inky.nextAiMove()).isEqualTo(Optional.of(Direction.EAST));
        }
    @Test
    /**
     * 当player、inky、blinky都在地图中，且存在有效路径
     * Inky在Player与Binky之间，则Inky将靠近Player
     */
    void testInkyMove(){
        Level level = ghostMapParser.parseMap(
            Lists.newArrayList(
                "#########","# P  I B#","#########"
            )
        );
        Player player = playerFactory.createPacMan();
        player.setDirection(Direction.WEST);
        level.registerPlayer(player);
        Inky inky = Navigation.findUnitInBoard(Inky.class,level.getBoard());

        assertThat(inky.nextAiMove()).isEqualTo(Optional.ofNullable(Direction.WEST));
    }
}
