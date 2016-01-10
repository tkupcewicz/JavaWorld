/**
 * Created by Tymek on 15.10.15.
 */
abstract class Airport extends Building {

    Airport() {
        int aircraftCapacity = MapConfig.randInt(MapConfig.getMinAirportCapacity(), MapConfig.getMaxAirportCapacity());
    }

    @Override
    public void inspect() {
        super.inspect();
        WorldController.getBuildingInspector().setVehicleTypeLabel("Planes: ");
    }
}
