import org.junit.Before;
import org.junit.Test;
import weather.WeatherReading;

import static org.junit.Assert.*;

public class WeatherReadingTest {
    //variable
    private WeatherReading weather;

    //create a WeatherReading object
    @Before
    public void setup() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
    }

    //invalid dew point temperature
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionDewPoint() {
        WeatherReading myWeather = new WeatherReading(23, 100, 3, 12);
    }

    //invalid wind speed
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionWindSpeed() {
        WeatherReading myWeather = new WeatherReading(23, 12, -3, 12);
    }

    //invalid total rain received
    @Test(expected = IllegalArgumentException.class)
    public void testExceptionRain() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, -12);
    }

    //test getter for air temperature
    @Test
    public void testGetTemperature() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals(23, myWeather.getTemperature());
    }

    //test getter for dew point temperature
    @Test
    public void testGetDewPoint() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals(12, myWeather.getDewPoint());
    }

    //test getter for wind speed
    @Test
    public void testGetWindSpeed() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals(3, myWeather.getWindSpeed());
    }

    //test getter for total rain received
    @Test
    public void testGetTotalRain() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals(12, myWeather.getTotalRain());
    }

    //test getter for relative humidity
    @Test
    public void testGetRelativeHumidity() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals(45, myWeather.getRelativeHumidity());
    }

    //test getter for heat index
    @Test
    public void testGetHeatIndex() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals(25.112, myWeather.getHeatIndex(), 0.001);
    }

    //test getter for wind chill
    @Test
    public void testGetWindChill() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals(76.1465, myWeather.getWindChill(), 0.001);
    }

    //test toString method
    @Test
    public void testToString() {
        WeatherReading myWeather = new WeatherReading(23, 12, 3, 12);
        assertEquals("Reading: T = 23, D = 12, v = 3, rain = 12", myWeather.toString());
    }
}