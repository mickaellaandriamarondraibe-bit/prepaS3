package mg.avion.config;

public final class ConfigurationSimulation {
    public static final double VITESSE_X_INITIALE = 900.0;
    public static final double VITESSE_Y_INITIALE = 0.0;
    public static final double ALTITUDE_INITIALE = 10000.0;
    public static final double DISTANCE_INITIALE = -7000.0;
    public static final double VITESSE_DECROCHAGE = 200.0;

    public static final double PAS_FREINAGE_X = -10.0;
    public static final double PAS_FREINAGE_Y = -2.0;
    public static final double FREINAGE_X_MIN = -40.0;
    public static final double FREINAGE_Y_MIN = -6.0;

    public static final double PAS_TEMPS_SECONDES = 0.1;

    private ConfigurationSimulation() {
    }
}
