/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2014 Ashutosh Kumar Singh [me@aksingh.net]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
*/

package weatherLib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Parses daily forecast data (from the JSON data) and provides methods to
 * get/access the information about daily forecasted weather. This class
 * provides <code>has</code> and <code>get</code> methods to access the
 * information.
 * <p>
 * <p>
 * <code>has</code> methods can be used to check if the data exists, i.e., if
 * the data was available (successfully downloaded) and was parsed correctly.
 * <p>
 * <p>
 * <code>get</code> methods can be used to access the data, if the data exists,
 * otherwise <code>get</code> methods will give value as per following
 * basis:<br>
 * Boolean: <code>false</code><br>
 * Integral: Minimum value (MIN_VALUE)<br>
 * Floating point: Not a number (NaN)<br>
 * Objects: Data initialized with default/non-parameterized constructor<br>
 * Others: <code>null</code><br>
 * <p>
 * @author Ashutosh Kumar Singh
 * @version 2013/08/07
 * @since 2.5.0.1
 */
public class DailyForecastData{

    /**
     * Key for JSON object - City
     */
    private final String JSON_CITY = "city";
    /**
     * Key for JSON object - List of forecasts
     */
    private final String JSON_FORECAST_LIST = "list";

    /*
     ************************
     * Declaring sub-classes
     ************************
     */
    /**
     * Parses data about city (from the JSON data) and provides methods to
     * get/access the information. For example, city name, coordinates, country
     * name, population, etc. This class provides <code>has</code> and
     * <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class City {

        /**
         * Key for JSON object - Coordinates
         */
        private final String JSON_CITY_COORD = "coord";

        /**
         * Parses data about geographic coordinates (from the JSON data) and
         * provides methods to get/access the information. This class provides
         * <code>has</code> and <code>get</code> methods to access the
         * information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
         * <p>
         * <p>
         * <code>get</code> methods can be used to access the data, if the data
         * exists, otherwise <code>get</code> methods will give value as per
         * following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         * <p>
         * @author Ashutosh Kumar Singh
         * @version 2013/08/07
         * @since 2.5.0.1
         */
        public static class Coord extends AbstractWeatherData.Coord {

            /**
             * Non-parameterized constructor
             * <p>
             * <p>
             * Initializes variables as per following basis:<br>
             * Boolean: <code>false</code><br>
             * Integral: Minimum value (MIN_VALUE)<br>
             * Floating point: Not a number (NaN)<br>
             * Others: <code>null</code><br>
             */
            public Coord() {
                super();
            }

            /**
             * Parameterized constructor
             * <p>
             * Initializes variables from values from the given JSON object.
             * <p>
             * @param jsonObj JSON object containing data about clouds
             */
            public Coord(JSONObject jsonObj) {
                super(jsonObj);
            }
        }

        /**
         * Key for JSON variable <code>City code (ID)</code>
         */
        private final String JSON_CITY_ID = "id";
        /**
         * Key for JSON variable <code>City name</code>
         */
        private final String JSON_CITY_NAME = "name";
        /**
         * Key for JSON variable <code>Country code of city</code>
         */
        private final String JSON_CITY_COUNTRY_CODE = "country";
        /**
         * Key for JSON variable <code>Population of city</code>
         */
        private final String JSON_CITY_POPULATION = "population";

        /**
         * City code (ID)
         */
        private final long cityID;
        /**
         * City name
         */
        private final String cityName;
        /**
         * Country code of city
         */
        private final String countryCode;
        /**
         * Population of city
         */
        private final long population;

        private final Coord coord;

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public City() {
            this.cityID = Long.MIN_VALUE;
            this.cityName = null;
            this.countryCode = null;
            this.population = Long.MIN_VALUE;

            this.coord = new Coord();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about city
         */
        public City(JSONObject jsonObj) {
            this.cityID = (jsonObj != null) ? jsonObj.optLong(this.JSON_CITY_ID, Long.MIN_VALUE) : Long.MIN_VALUE;
            this.cityName = (jsonObj != null) ? jsonObj.optString(this.JSON_CITY_NAME, null) : null;
            this.countryCode = (jsonObj != null) ? jsonObj.optString(this.JSON_CITY_COUNTRY_CODE, null) : null;
            this.population = (jsonObj != null) ? jsonObj.optLong(this.JSON_CITY_POPULATION, Long.MIN_VALUE) : Long.MIN_VALUE;

            JSONObject jsonObjCoord = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_CITY_COORD) : null;
            this.coord = (jsonObjCoord != null) ? new Coord(jsonObjCoord) : new Coord();
        }

        public boolean hasCityCode() {
            return (this.cityID != Long.MIN_VALUE);
        }

        public boolean hasCityName() {
            return (this.cityName != null);
        }

        public boolean hasCountryCode() {
            return (this.countryCode != null);
        }

        public boolean hasCityPopulation() {
            return (this.population != Long.MIN_VALUE);
        }

        public long getCityCode() {
            return this.cityID;
        }

        public String getCityName() {
            return this.cityName;
        }

        public String getCountryCode() {
            return this.countryCode;
        }

        public long getCityPopulation() {
            return this.population;
        }

        // Objects
        public Coord getCoordinates_Object() {
            return this.coord;
        }
    }

    /**
     * Parses data about forecasts (from the JSON data) and provides methods to
     * get/access the information. This class provides <code>has</code> and
     * <code>get</code> methods to access the information.
     * <p>
     * <p>
     * <code>has</code> methods can be used to check if the data exists, i.e.,
     * if the data was available (successfully downloaded) and was parsed
     * correctly.
     * <p>
     * <p>
     * <code>get</code> methods can be used to access the data, if the data
     * exists, otherwise <code>get</code> methods will give value as per
     * following basis:<br>
     * Boolean: <code>false</code><br>
     * Integral: Minimum value (MIN_VALUE)<br>
     * Floating point: Not a number (NaN)<br>
     * Others: <code>null</code><br>
     * <p>
     * @author Ashutosh Kumar Singh
     * @version 2013/08/07
     * @since 2.5.0.1
     */
    public static class Forecast extends AbstractWeatherData {

        /**
         * Key for JSON object - Temperature
         */
        public final String JSON_TEMP = "temp";

        /*
         ***************************
         * Declaring sub-sub-classes
         ***************************
         */
        /**
         * Parses data about weather (from the JSON data) and provides methods
         * to get/access the information. For example, weather id, name, etc.
         * This class provides <code>has</code> and <code>get</code> methods to
         * access the information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
         * <p>
         * <p>
         * <code>get</code> methods can be used to access the data, if the data
         * exists, otherwise <code>get</code> methods will give value as per
         * following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         * <p>
         * @author Ashutosh Kumar Singh
         * @version 2013/08/07
         * @since 2.5.0.1
         */
        public static class Weather extends AbstractWeatherData.Weather {

            /**
             * Non-parameterized constructor
             * <p>
             * <p>
             * Initializes variables as per following basis:<br>
             * Boolean: <code>false</code><br>
             * Integral: Minimum value (MIN_VALUE)<br>
             * Floating point: Not a number (NaN)<br>
             * Others: <code>null</code><br>
             */
            public Weather() {
                super();
            }

            /**
             * Parameterized constructor
             * <p>
             * Initializes variables from values from the given JSON object.
             * <p>
             * @param jsonObj JSON object containing data about weather
             */
            public Weather(JSONObject jsonObj) {
                super(jsonObj);
            }
        }

        /**
         * Parses data about temperature (from the JSON data) and provides
         * methods to get/access the information. For example, weather id, name,
         * etc. This class provides <code>has</code> and <code>get</code>
         * methods to access the information.
         * <p>
         * <p>
         * <code>has</code> methods can be used to check if the data exists,
         * i.e., if the data was available (successfully downloaded) and was
         * parsed correctly.
         * <p>
         * <p>
         * <code>get</code> methods can be used to access the data, if the data
         * exists, otherwise <code>get</code> methods will give value as per
         * following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         * <p>
         * @author Ashutosh Kumar Singh
         * @version 2013/08/07
         * @since 2.5.0.1
         */
        public static class Temperature {

            /**
             * Key for JSON variable <code>Temp -> Day</code>
             */
            public final String JSON_TEMP_DAY = "day";
            /**
             * Key for JSON variable <code>Temp -> Minimum</code>
             */
            public final String JSON_TEMP_MIN = "min";
            /**
             * Key for JSON variable <code>Temp -> Maximum</code>
             */
            public final String JSON_TEMP_MAX = "max";
            /**
             * Key for JSON variable <code>Temp -> Night</code>
             */
            public final String JSON_TEMP_NIGHT = "night";
            /**
             * Key for JSON variable <code>Temp -> Evening</code>
             */
            public final String JSON_TEMP_EVENING = "eve";
            /**
             * Key for JSON variable <code>Temp -> Morning</code>
             */
            public final String JSON_TEMP_MORNING = "morn";

            /**
             * Day temperature
             */
            private final float dayTemp;
            /**
             * Minimum temperature
             */
            private final float minTemp;
            /**
             * Maximum temperature
             */
            private final float maxTemp;
            /**
             * Night temperature
             */
            private final float nightTemp;
            /**
             * Evening temperature
             */
            private final float eveTemp;
            /**
             * Morning temperature
             */
            private final float mornTemp;

            /**
             * Non-parameterized constructor
             * <p>
             * <p>
             * Initializes variables as per following basis:<br>
             * Boolean: <code>false</code><br>
             * Integral: Minimum value (MIN_VALUE)<br>
             * Floating point: Not a number (NaN)<br>
             * Others: <code>null</code><br>
             */
            public Temperature() {
                this.dayTemp = Float.NaN;
                this.minTemp = Float.NaN;
                this.maxTemp = Float.NaN;
                this.nightTemp = Float.NaN;
                this.eveTemp = Float.NaN;
                this.mornTemp = Float.NaN;
            }

            /**
             * Parameterized constructor
             * <p>
             * Initializes variables from values from the given JSON object.
             * <p>
             * @param jsonObj JSON object containing data about temperature
             */
            public Temperature(JSONObject jsonObj) {
                this.dayTemp = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_TEMP_DAY, Double.NaN) : Float.NaN;
                this.minTemp = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_TEMP_MIN, Double.NaN) : Float.NaN;
                this.maxTemp = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_TEMP_MAX, Double.NaN) : Float.NaN;
                this.nightTemp = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_TEMP_NIGHT, Double.NaN) : Float.NaN;
                this.eveTemp = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_TEMP_EVENING, Double.NaN) : Float.NaN;
                this.mornTemp = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_TEMP_MORNING, Double.NaN) : Float.NaN;
            }

            public boolean hasDayTemperature() {
                return (this.dayTemp != Float.NaN);
            }

            public boolean hasMinimumTemperature() {
                return (this.minTemp != Float.NaN);
            }

            public boolean hasMaximumTemperature() {
                return (this.maxTemp != Float.NaN);
            }

            public boolean hasNightTemperature() {
                return (this.nightTemp != Float.NaN);
            }

            public boolean hasEveningTemperature() {
                return (this.eveTemp != Float.NaN);
            }

            public boolean hasMorningTemperature() {
                return (this.mornTemp != Float.NaN);
            }

            public float getDayTemperature() {
                return this.dayTemp;
            }

            public float getMinimumTemperature() {
                return this.minTemp;
            }

            public float getMaximumTemperature() {
                return this.maxTemp;
            }

            public float getNightTemperature() {
                return this.nightTemp;
            }

            public float getEveningTemperature() {
                return this.eveTemp;
            }

            public float getMorningTemperature() {
                return this.mornTemp;
            }
        }

        /*
         ************************
         * Declaring this sub-class
         ************************
         */
        /**
         * Key for JSON variable <code>Pressure</code>
         */
        private final String JSON_FORECAST_PRESSURE = "pressure";
        /**
         * Key for JSON variable <code>Humidity</code>
         */
        private final String JSON_FORECAST_HUMIDITY = "humidity";
        /**
         * Key for JSON variable <code>Wind speed</code>
         */
        private final String JSON_FORECAST_WIND_SPEED = "speed";
        /**
         * Key for JSON variable <code>Wind degree</code>
         */
        private final String JSON_FORECAST_WIND_DEGREE = "deg";
        /**
         * Key for JSON variable <code>Percentage of clouds</code>
         */
        private final String JSON_FORECAST_CLOUDS = "clouds";

        /**
         * Pressure
         */
        private final float pressure;
        /**
         * Humidity
         */
        private final float humidity;
        /**
         * Wind speed
         */
        private final float windSpeed;
        /**
         * Wind degree
         */
        private final float windDegree;
        /**
         * Percentage of clouds
         */
        private final float cloudsPercent;

        private final Temperature temp;

        /**
         * List of weather information (code, name, etc.)
         */
        private final List<Weather> weatherList;
        /**
         * Count (number) of elements in list of weather information
         */
        private final int weatherListCount;

        /**
         * Non-parameterized constructor
         * <p>
         * <p>
         * Initializes variables as per following basis:<br>
         * Boolean: <code>false</code><br>
         * Integral: Minimum value (MIN_VALUE)<br>
         * Floating point: Not a number (NaN)<br>
         * Others: <code>null</code><br>
         */
        public Forecast() {
            super();

            this.pressure = Float.NaN;
            this.humidity = Float.NaN;
            this.windSpeed = Float.NaN;
            this.windDegree = Float.NaN;
            this.cloudsPercent = Float.NaN;

            this.temp = new Temperature();

            this.weatherList = Collections.EMPTY_LIST;
            this.weatherListCount = this.weatherList.size();
        }

        /**
         * Parameterized constructor
         * <p>
         * Initializes variables from values from the given JSON object.
         * <p>
         * @param jsonObj JSON object containing data about forecasts
         */
        public Forecast(JSONObject jsonObj) {
            super(jsonObj);

            JSONObject jsonObjTemp = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_TEMP) : null;
            this.temp = (jsonObjTemp != null) ? new Temperature(jsonObjTemp) : new Temperature();

            this.humidity = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_FORECAST_HUMIDITY, Double.NaN) : Float.NaN;
            this.pressure = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_FORECAST_PRESSURE, Double.NaN) : Float.NaN;
            this.windSpeed = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_FORECAST_WIND_SPEED, Double.NaN) : Float.NaN;
            this.windDegree = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_FORECAST_WIND_DEGREE, Double.NaN) : Float.NaN;
            this.cloudsPercent = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_FORECAST_CLOUDS, Double.NaN) : Float.NaN;

            JSONArray jsonArrWeather = (jsonObj != null) ? jsonObj.optJSONArray(this.JSON_WEATHER) : null;
            this.weatherList = (jsonArrWeather != null) ? new ArrayList<Weather>(jsonArrWeather.length()) : Collections.EMPTY_LIST;
            if (this.weatherList != Collections.EMPTY_LIST) {
                for (int i = 0; i < jsonArrWeather.length(); i++) {
                    JSONObject jsonObjWeather = jsonArrWeather.optJSONObject(i);
                    if (jsonObjWeather != null) {
                        this.weatherList.add(new Weather(jsonObjWeather));
                    }
                }
            }
            this.weatherListCount = this.weatherList.size();
        }

        public boolean hasHumidity() {
            return (this.humidity != Float.NaN);
        }

        public boolean hasPressure() {
            return (this.pressure != Float.NaN);
        }

        public boolean hasWindSpeed() {
            return (this.windSpeed != Float.NaN);
        }

        public boolean hasWindDegree() {
            return (this.windDegree != Float.NaN);
        }

        public boolean hasPercentageOfClouds() {
            return (this.cloudsPercent != Float.NaN);
        }

        public float getHumidity() {
            return this.humidity;
        }

        public float getPressure() {
            return this.pressure;
        }

        public float getWindSpeed() {
            return this.windSpeed;
        }

        public float getWindDegree() {
            return this.windDegree;
        }

        public float getPercentageOfClouds() {
            return this.cloudsPercent;
        }

        // Objects
        public Temperature getTemperature_Object() {
            return this.temp;
        }

        // Lists
        public boolean hasWeather_List() {
            return (this.weatherListCount != 0);
        }

        public int getWeather_List_Count() {
            return this.weatherListCount;
        }

        public List<Weather> getWeather_List() {
            return this.weatherList;
        }
    }

    /*
     ***********************
     * Declaring this class
     ***********************
     */
    /**
     * Key for JSON variable <code>Response code</code>
     */
    private final String JSON_RESPONSE_CODE = "cod";
    /**
     * Key for JSON variable <code>Response time</code>
     */
    private final String JSON_RESPONSE_TIME = "message";
    /**
     * Key for JSON variable <code>Forecast count</code>
     */
    private final String JSON_RESPONSE_FORECAST_COUNT = "cnt";

    /**
     * Response code
     */
    private final String responseCode;
    /**
     * Response time
     */
    private final float responseTime;
    /**
     * Forecast count
     */
    private final int responseForecastCount;

    private final City city;

    /**
     * List of forecast information
     */
    private final List<Forecast> forecastList;
    /**
     * Count (number) of elements in list of forecast information
     */
    private final int forecastListCount;

    /**
     * Parameterized constructor
     * <p>
     * Initializes variables from values from the given JSON object.
     * <p>
     * @param jsonObj JSON object containing data about daily forecasts
     */
    public DailyForecastData(JSONObject jsonObj) {
        this.responseCode = (jsonObj != null) ? jsonObj.optString(this.JSON_RESPONSE_CODE, null) : null;
        this.responseTime = (jsonObj != null) ? (float) jsonObj.optDouble(this.JSON_RESPONSE_TIME, Double.NaN) : Float.NaN;
        this.responseForecastCount = (jsonObj != null) ? jsonObj.optInt(this.JSON_RESPONSE_FORECAST_COUNT, Integer.MIN_VALUE) : Integer.MIN_VALUE;

        JSONObject jsonObjCity = (jsonObj != null) ? jsonObj.optJSONObject(this.JSON_CITY) : null;
        this.city = (jsonObjCity != null) ? new City(jsonObjCity) : new City();

        JSONArray jsonArrForecast = (jsonObj != null) ? jsonObj.optJSONArray(this.JSON_FORECAST_LIST) : null;
        this.forecastList = (jsonArrForecast != null) ? new ArrayList<Forecast>(jsonArrForecast.length()) : Collections.EMPTY_LIST;
        if (this.forecastList != Collections.EMPTY_LIST) {
            for (int i = 0; i < jsonArrForecast.length(); i++) {
                JSONObject jsonObjWeather = jsonArrForecast.optJSONObject(i);
                if (jsonObjWeather != null) {
                    this.forecastList.add(new Forecast(jsonObjWeather));
                }
            }
        }
        this.forecastListCount = this.forecastList.size();
    }

    public boolean hasResponseCode() {
        return (this.responseCode != null);
    }

    public boolean hasResponseTime() {
        return (this.responseTime != Float.NaN);
    }

    public boolean hasResponseForecastCount() {
        return (this.responseForecastCount != Integer.MIN_VALUE);
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public float getResponseTime() {
        return this.responseTime;
    }

    public int getResponseForecastCount() {
        return this.responseForecastCount;
    }

    // Objects
    public City getCity_Object() {
        return this.city;
    }

    // Lists
    public boolean hasForecast_List() {
        return (this.forecastListCount != 0);
    }

    public int getForecast_List_Count() {
        return this.forecastListCount;
    }

    public List<Forecast> getForecast_List() {
        return this.forecastList;
    }
}
