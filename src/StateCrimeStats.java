import java.io.Serializable;

/**
 * Created by Marcus on 6/30/2016.
 */
public class StateCrimeStats implements Serializable
{
    private String stateName;
    private int population;
    private int violentCrimes;
    private int theftCrimes;
    private String highestPropertyCrimeCity;
    private double highestPropertyCrimeRate;

    public StateCrimeStats(String sta, int pop, int vio, int thef, String propCity, double propRate)
    {
        stateName = sta;
        population = pop;
        violentCrimes = vio;
        theftCrimes = thef;
        highestPropertyCrimeCity = propCity;
        highestPropertyCrimeRate = propRate;
    }

    @Override
    public String toString()
    {
        return String.format("State: %s\n" +
                             "Population: %d\n" +
                             "Violent Crimes: %d\n" +
                             "Theft Crimes: %d\n" +
                             "Highest Property Crime Rate: %s - %f\n", stateName, population, violentCrimes, theftCrimes, highestPropertyCrimeCity, highestPropertyCrimeRate);
    }
}
