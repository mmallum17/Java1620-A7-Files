/**
 * Created by Marcus on 6/30/2016.
 */
public class CityCrimeData
{
    public String cityName;
    public int population;
    public int violentCrimeCount;
    public int murderCount;
    public int robberyCount;
    public int assaultCount;
    public int propertyCount;
    public int burglaryCount;
    public int larcenyCount;
    public int vehicleTheftCount;
    public int arsonCount;

    public CityCrimeData(String name, int pop, int vio, int mur, int rob, int ass, int prop, int bur, int lar, int veh, int ars)
    {
        cityName = name;
        population = pop;
        violentCrimeCount = vio;
        murderCount = mur;
        robberyCount = rob;
        assaultCount = ass;
        propertyCount = prop;
        burglaryCount = bur;
        larcenyCount = lar;
        vehicleTheftCount = veh;
        arsonCount = ars;
    }
}
