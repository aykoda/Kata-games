package MarsRover;

import java.io.IOException;

public class main {

static void calcualte(int height){
   try {
       if (height <= 145) {
            throw new ArithmeticException("sorry ArithmeticException occur.");
       }
   }catch (ArithmeticException arthmEx){
       System.out.println(arthmEx.getMessage());
   }
}
    public static void main(String[] args) throws Exception {

        calcualte(140);
/*

        //--------COMMANDS
        String command1 = "MMRMMRMMMLMMMLMMMMM";

        //--------OBSTACLES
        Obstacle obstacle = new Obstacle();//new Coordinates(4,4, Direction.CENTER));
        //obstacle.isSetObstaclesListByValue(new Coordinates(1,2, Direction.CENTER));

        //obstacle.setRandomObstacle(2,2,2);
        System.out.printf("\nOBSTACLES %s\n\n", obstacle.getObstaclesList());

        //--------COORDINATES
        Coordinates coordinatesSquare = new Coordinates(1, 2, 5, 5, Direction.NORTH);
        Coordinates coordinatesCircle = new Coordinates(1, 3, Direction.NORTH);

        //--------ROVER 1
        System.out.printf("--------1. ROVER (%s) COMMANDS: %s\n", coordinatesSquare, command1);

        Rover rover1 = new Rover(coordinatesSquare, command1, obstacle);
        //rover1.
        System.out.printf("ALL OBSTACLES %s\n", obstacle.getObstaclesList());

        rover1.commandsMove();
        System.out.printf("ROVERS AFTER MOVE %s\n", obstacle.getObstaclesList());

        rover1.setCommands("LMMM");
        System.out.printf("-------- ROVER (%s) COMMANDS: %s\n", rover1.getCoordinates(), rover1.getCommands());
        System.out.printf("ALL OBSTACLES %s\n", obstacle.getObstaclesList());

        rover1.commandsMove();
        System.out.printf("ROVERS AFTER MOVE %s\n", obstacle.getObstaclesList());

        //--------ROVER 2
        /*
        System.out.printf("\n--------2. ROVER  (%s) COMMANDS: %s\n",coordinatesCircle,command1);
        //try {
        Rover rover2 = new Rover(coordinatesCircle, command1, obstacle);
        System.out.printf("ALL OBSTACLES %s\n",obstacle.getObstaclesList());

        rover2.commandsMove();
        System.out.printf("ROVERS AFTER MOVE %s\n",obstacle.getObstaclesList());
        //}catch(Exception ex){}
         */
    }

}