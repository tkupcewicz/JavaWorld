/**
 * Created by Tymek on 13.10.15.
 */
public abstract class Ship extends Vehicle{
    public Ship() {
        super();
    }
    public void inspect(){
        super.inspect();
        WorldController.getVehicleInspector().getEmergencyLandingButton().setVisible(false);
    }
}
