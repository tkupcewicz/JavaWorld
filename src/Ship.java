/**
 * Created by Tymek on 13.10.15.
 */
abstract class Ship extends Vehicle {
    Ship() {
    }

    @Override
    void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getEmergencyLandingButton().setVisible(false);
    }
}
