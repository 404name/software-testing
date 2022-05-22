package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.PacmanConfigurationException;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.board.Square;
import nl.tudelft.jpacman.npc.Ghost;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions.*;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MapParserTest {
        private MapParser mapParser;
        private final LevelFactory levelFactory=mock(LevelFactory.class);
        private final BoardFactory boardFactory=mock(BoardFactory.class);
        @BeforeEach
        void setup(){
            mapParser =new MapParser(levelFactory,boardFactory);
            when(boardFactory.createGround()).thenReturn(mock(Square.class));
            when(boardFactory.createWall()).thenReturn(mock(Square.class));
            when(levelFactory.createGhost()).thenReturn(mock(Ghost.class));
            when(levelFactory.createPellet()).thenReturn(mock(Pellet.class));
    }
    @Test
    @Order(1)
    @DisplayName("文件名为null")
    void nullFile(){
            assertThatThrownBy(()->{
                mapParser.parseMap((String) null);
            }).isInstanceOf(NullPointerException.class);
    }
    @Test
    @Order(2)
    @DisplayName("读取不存在的文件")
    void notExistFile(){
            String file="/notexistmap.txt";
            assertThatThrownBy(()->{
                mapParser.parseMap(file);
            }).isInstanceOf(PacmanConfigurationException.class).hasMessage("Could not get resource for: "+file);
    }
    @Test
    @Order(3)
    @DisplayName("读取存在的文件")
    void existsFile()throws IOException {
        String file = "/simplemap.txt";
        mapParser.parseMap(file);
        verify(boardFactory, times(4)).createGround();
        verify(boardFactory, times(2)).createWall();
        verify(levelFactory, times(1)).createGhost();
    }
    @Test
    @Order(4)
    @DisplayName("读取无法识别的地图文件")
    void unrecoginzedMap(){
            String file="/unrecognizedMap.txt";
            assertThatThrownBy(()->{
                mapParser.parseMap(file);
        }).isInstanceOf(PacmanConfigurationException.class).hasMessage("Could not get resource for: /unrecognizedMap.txt");
    }

}
