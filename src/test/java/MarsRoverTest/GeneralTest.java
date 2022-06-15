package MarsRoverTest;

import MarsRover.Coordinates;
import MarsRover.Direction;
import MarsRover.Obstacle;
import MarsRover.Rover;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class GeneralTest {
    //#region VariablesDefinition
    String resultPosition = "";
    private String command;
    private Coordinates surfaceSquare;
    private Coordinates surfaceCircle;

    Obstacle roverObstacle = new Obstacle();
    private Obstacle roverSet = new Obstacle();
    private static Obstacle obstacleKeep2, obstacleKeep3;//= new Obstacle();
    static Obstacle obstacleKeep = new Obstacle();

    private Rover rover;// = new Rover();
    private static Rover roverKeep;

    private static Obstacle obstacleSet;
    private List<Coordinates> obstacleList = new ArrayList<Coordinates>();
    private List<Rover> roverList;
    private List<String> commandList;
    //#endregion
    @Before
    public void beforeTest() {
        //#region ObstacleList
        obstacleList = Arrays.asList(
                new Coordinates(3, 4, Direction.CENTER),
                new Coordinates(1, 1, Direction.CENTER),
                new Coordinates(1, 2, Direction.CENTER),
                new Coordinates(1, 3, Direction.CENTER));
        obstacleSet = new Obstacle(new Coordinates(4, 4, Direction.CENTER));
        obstacleSet.setObstaclesList(obstacleList);
        obstacleSet.isSetObstaclesListByValue(new Coordinates(2, 2, Direction.CENTER));
        //#endregion
        //#region RoverList
        command = "MMRMMRMMMLMMMLMMMMM";
        commandList = Arrays.asList(command, new String("MMRMMR"), new String("MLMMMLMMMMM"));
        surfaceSquare = new Coordinates(3, 3, 5, 5, Direction.NORTH);
        surfaceCircle = new Coordinates(2, 2, Direction.NORTH);
        rover = new Rover(surfaceSquare, command, roverSet);
        roverList = Arrays.asList(rover, new Rover(surfaceCircle, commandList.get(1), roverSet));
        //#endregion
        obstacleKeep2 = new Obstacle();
        roverKeep = new Rover(new Coordinates(3, 3, 5, 5, Direction.NORTH), "", obstacleKeep2);
    }
    //#region InitTestSection
    @Test
    public void A_should_SetInit_ObstacleList() {
        assertThat(obstacleSet.getObstaclesList().size()).isEqualTo(6);
    }
    @Test
    public void B_should_NotAddToList_ExistObstacle() {
        boolean result = obstacleSet.isSetObstaclesListByValue(new Coordinates(2, 2, Direction.CENTER));
        Assert.assertEquals(false, result);
    }
    @Test
    public void C_should_AddToList_RandomObstacles() {
        obstacleSet.setRandomObstacle(5, 5, 2);
        assertThat(obstacleSet.getObstaclesList().size()).isNotEqualTo(5);
    }
    @Test
    public void D_should_SetInit_RoverObstacleList() {

        assertThat(roverSet.getObstaclesList().size()).isEqualTo(2);
    }
    @Test
    public void E_should_NotAddToObstacleList_RoverExistCoordinates() {
        boolean result = roverSet.isSetObstaclesListByValue(surfaceSquare);
        Assert.assertEquals(false, result);
    }
    @Test
    public void F_should_Merge_AD_ObstacleAndRoverList() {
        obstacleSet.setObstaclesList(roverSet.getObstaclesList());
        assertThat(obstacleSet.getObstaclesList().size()).isEqualTo(7);
    }
    //#endregion
    //#region RoversSection
    private static Stream<Arguments> parametersGenerator() {
        return Stream.of(
                Arguments.of(new Coordinates(1, 1, 5, 5, Direction.WEST),
                        "MMRMMRMMMLMMMLMMMMM", "1 5 W", true),
                Arguments.of(new Coordinates(2, 2, 10, 10, Direction.NORTH),
                        "MMRMMLMMML", "4 7 W", true),
                Arguments.of(new Coordinates(1, 2, 5, 10, Direction.SOUTH),
                        "MLLMMMMMMM", "1 8 N", true),
                Arguments.of(new Coordinates(3, 3, 10, 5, Direction.EAST),
                        "MMRLLMMRRLMRLRM", "6 4 S", true),
                Arguments.of(new Coordinates(2, 2, Direction.NORTH),
                        "MMLRMMLRMMMR", "2 9 E", true)
        );
    }
    //@Ignore
    @ParameterizedTest
    @MethodSource("parametersGenerator")
    public void should_Move_SingleRover_Cases(Coordinates prmCoordinates, String prmCommand, String prmExpected, Boolean prmIsTrue) {
        rover = new Rover(prmCoordinates, prmCommand, roverObstacle);
        resultPosition = rover.commandsMove();
        Assert.assertEquals(resultPosition.contentEquals(prmExpected), prmIsTrue);
    }
    //@Ignore
    @ParameterizedTest
    @MethodSource("parametersGenerator")
    public void should_Move_AllRoversSequentially_StopedOneSetObstacle(Coordinates prmCoordinates, String prmCommand, String prmExpected, Boolean prmIsTrue) {
        rover = new Rover(prmCoordinates, prmCommand, obstacleKeep);
        resultPosition = rover.commandsMove();
        Assert.assertEquals(resultPosition.contentEquals(prmExpected), prmIsTrue);
    }
    //#endregion
    //#region CommandsSection
    private static Stream<Arguments> parametersGenerator2() {
        return Stream.of(
                Arguments.of("MMRMMRMMMLMMMLMMMMM","5 5 N", true),
                Arguments.of("MMLMMR", "3 5 N", true),
                Arguments.of("MMMMMMMLMMMMM", "1 5 W", true),
                Arguments.of("MLLMMR", "3 5 S", true),
                Arguments.of("MMLRMMRMM", "1 1 W", true)
        );
    }
    // @Ignore
    @ParameterizedTest
    @MethodSource("parametersGenerator2")
    public void should_ContinuedMove_NextCommands(String prmCommand, String prmExpected, Boolean prmIsTrue) {
        roverKeep.setCommands(prmCommand);
        String resultPosition = roverKeep.commandsMove();
        Assert.assertEquals(resultPosition.contentEquals(prmExpected), prmIsTrue);
    }
    //#endregion
}

