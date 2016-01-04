import java.util.LinkedList;

import static java.lang.Math.atan2;

/**
 * Created by Tymek on 13.10.15.
 */

public abstract class Vehicle extends PhysicalObject implements Runnable {
    private int uniqueId;
    private int speed;
    private Building nextDestination;
    private Building currentBuilding;
    abstract void moveTo();
    abstract void randomizeRoute();
    private boolean moving;

    double calculateXVel(int posX, int posY, int tarX, int tarY){
        double angle = atan2(tarY-posY, tarX-posX);
        double xVel = speed * Math.cos(angle);
        return xVel;
    }
    double calculateYVel(int posX, int posY, int tarX, int tarY){
        double angle = atan2(tarY-posY, tarX-posX);
        double yVel = speed * Math.sin(angle);
        return yVel;
    }

    public void run() {
        this.randomizeRoute();
        double xVel = 0;
        double yVel = 0;
        while(true) {
            if(!this.isMoving()){
                System.out.println("Isn't moving");


//                Path temp = Path.calculatePath(this.getCurrentBuilding().getPosition(),
//                        this.getNextDestination().getPosition());
//
//
//                WorldController.getMainMap().addObjectToDraw(temp);
//                xVel = calculateXVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());
//                yVel = calculateYVel(temp.getDestination().getX(), temp.getDestination().getY(), temp.getOrigin().getX(), temp.getOrigin().getY());

                xVel = calculateXVel(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY(), this.getNextDestination().getPosition().getX(), this.getNextDestination().getPosition().getY());
                yVel = calculateYVel(this.getCurrentBuilding().getPosition().getX(), this.getCurrentBuilding().getPosition().getY(), this.getNextDestination().getPosition().getX(), this.getNextDestination().getPosition().getY());
                this.setMoving(true);
            }
            else{
                System.out.println("Is moving" + this.getPosition().toString());
                int tmpx = this.getPosition().getX();
                int tmpy = this.getPosition().getY();
                tmpx = tmpx +  (int) Math.round(xVel);
                tmpy = tmpy +  (int) Math.round(yVel);
                System.out.println(xVel + " " + yVel);
                this.setPosition(tmpx, tmpy);
                if(this.getPosition().equals(this.nextDestination.getPosition())){
                    this.setMoving(false);
                }
            }



            try {
                Thread.sleep(256);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Building getNextDestination() {
        return nextDestination;
    }

    public void setNextDestination(Building nextDestination) {
        this.nextDestination = nextDestination;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Building getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(Building currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
