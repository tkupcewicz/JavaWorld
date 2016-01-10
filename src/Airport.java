/**
 * Created by Tymek on 15.10.15.
 */
abstract class Airport extends Building {

    Airport() {

    }

    /**
     * Sets Vehicles Label in Inspector to Planes:
     */
    @Override
    public void inspect() {
        super.inspect();
        WorldController.getBuildingInspector().setVehicleTypeLabel("Planes: ");
    }
}
