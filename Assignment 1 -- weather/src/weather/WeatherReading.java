package weather;

public class WeatherReading {
    //member variables
    private int temperature;
    private int dew_point;
    private int wind_speed;
    private int total_rain;

    //constructor: init all the variables defined in the class
    public WeatherReading(int temperature, int dew_point, int wind_speed, int total_rain) {
        this.temperature = temperature;
        this.dew_point = dew_point;
        this.wind_speed = wind_speed;
        this.total_rain = total_rain;

        if (dew_point > temperature) {
            throw new IllegalArgumentException("Dew point temperature in Celsius which cannot be greater than the air temperature");
        }
        if (wind_speed < 0) {
            throw new IllegalArgumentException("Wind speed should be non-negative");
        }
        if (total_rain < 0) {
            throw new IllegalArgumentException("Total rain received should be non-negative");
        }
    }

    //getter for air temperature
    public int getTemperature() {
        return temperature;
    }

    //getter for dew point temperature
    public int getDewPoint() {
        return dew_point;
    }

    //getter for wind speed
    public int getWindSpeed() {
        return wind_speed;
    }

    //getter for total rain received
    public int getTotalRain() {
        return total_rain;
    }

    //getter for relative humidity
    public int getRelativeHumidity() {
        return 100 - 5 * (temperature - dew_point);
    }

    //getter for heat index
    public double getHeatIndex() {
        double T = temperature;
        double R = getRelativeHumidity();
        double c1 = -8.78469475556;
        double c2 = 1.61139411;
        double c3 = 2.33854883889;
        double c4 = -0.14611605;
        double c5 = -0.012308094;
        double c6 = -0.0164248277778;
        double c7 = 0.002211732;
        double c8 = 0.00072546;
        double c9 = -0.000003582;
        return c1 + c2 * T + c3 * R + c4 * T * R + c5 * T * T
                +c6 * R * R + c7 * T * T * R + c8 * T * R * R
                + c9 * T * T * R * R;
    }

    //getter for wind chill
    public double getWindChill() {
        double temperature = this.temperature;
        double T = temperature * 1.8 + 32;
        double v = wind_speed;
        double wind_chill = 35.74 + 0.6215 * T - 35.75 * Math.pow(v, 0.16)
                + 0.4275 * T * Math.pow(v, 0.16);
        return wind_chill;
    }

    //overriding the one inherited from Object
    @Override
    public String toString() {
        String message = "Reading: T = " + temperature + ", D = " + dew_point + ", v = " + wind_speed + ", rain = " + total_rain;
        return message;
    }
}
