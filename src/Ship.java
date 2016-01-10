/**
 * Created by Tymek on 13.10.15.
 */

/**
 * Ship class
 */
abstract class Ship extends Vehicle {
    /**
     * Shows right buttons and labels in Vehicle Inspector for Ship
     */
    @Override
    void inspect() {
        super.inspect();
        WorldController.getVehicleInspector().getEmergencyLandingButton().setVisible(false);
    }
}
