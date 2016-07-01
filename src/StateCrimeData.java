import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;


/**
 * Created by Marcus on 6/30/2016.
 */
public class StateCrimeData
{
    private String stateName;
    private ArrayList<CityCrimeData> crimeByCity;

    public StateCrimeData()
    {
        crimeByCity = new ArrayList<CityCrimeData>();
    }

    public void readCrimeData(String crimeFile) throws FileNotFoundException
    {
        boolean done = false;
        StringTokenizer stringTokenizer;
        Scanner fileInput = null;
        String cityData;
        String cityName;
        int population;
        int violentCrimeCount;
        int murderCount;
        int robberyCount;
        int assaultCount;
        int propertyCount;
        int burglaryCount;
        int larcenyCount;
        int vehicleTheftCount;
        int arsonCount;

        //Create Scanner
        fileInput = new Scanner(new File(crimeFile));
        //Read from file
        try
        {
            stateName = fileInput.nextLine();
            while(!done)
            {
                cityData = fileInput.nextLine();
                stringTokenizer = new StringTokenizer(cityData, ",");
                cityName = stringTokenizer.nextToken();
                population = Integer.parseInt(stringTokenizer.nextToken());
                violentCrimeCount = Integer.parseInt(stringTokenizer.nextToken());
                murderCount = Integer.parseInt(stringTokenizer.nextToken());
                robberyCount = Integer.parseInt(stringTokenizer.nextToken());
                assaultCount = Integer.parseInt(stringTokenizer.nextToken());
                propertyCount = Integer.parseInt(stringTokenizer.nextToken());
                burglaryCount = Integer.parseInt(stringTokenizer.nextToken());
                larcenyCount = Integer.parseInt(stringTokenizer.nextToken());
                vehicleTheftCount = Integer.parseInt(stringTokenizer.nextToken());
                arsonCount = Integer.parseInt(stringTokenizer.nextToken());
                crimeByCity.add(new CityCrimeData(cityName, population, violentCrimeCount, murderCount,
                                                  robberyCount, assaultCount, propertyCount, burglaryCount,
                                                  larcenyCount, vehicleTheftCount, arsonCount));
            }
        }
        catch(NoSuchElementException NSEE)
        {
            done = true;
        }
        catch(NumberFormatException NFE)
        {
            System.out.println("Improperly Formatted");
        }
        catch(IllegalStateException ISE)
        {
            System.out.println("Error reading from file");
            return;
        }

        //Close Scanner
        if(fileInput != null)
        {
            fileInput.close();
        }
    }

    public StateCrimeStats processCrimeData()
    {
        int statePopulation = 0;
        int stateViolentCrimes = 0;
        int stateTheftCrimes = 0;
        double cityHighCrimeRate = 0;
        String highCrimeRateCity = "";
        CityCrimeData cityCrimeData;
        double localCrimeRate;

        for(int index = 0; index < crimeByCity.size(); index++)
        {
            cityCrimeData = crimeByCity.get(index);
            statePopulation += cityCrimeData.population;
            stateViolentCrimes += cityCrimeData.violentCrimeCount;
            stateTheftCrimes += cityCrimeData.burglaryCount + cityCrimeData.robberyCount + cityCrimeData.larcenyCount + cityCrimeData.vehicleTheftCount;
            localCrimeRate = calculateCrimeRate(cityCrimeData.propertyCount, cityCrimeData.population);
            if(localCrimeRate > cityHighCrimeRate)
            {
                cityHighCrimeRate = localCrimeRate;
                highCrimeRateCity = cityCrimeData.cityName;
            }
        }
        return new StateCrimeStats(stateName, statePopulation, stateViolentCrimes, stateTheftCrimes, highCrimeRateCity, cityHighCrimeRate);
    }

    private double calculateCrimeRate(int propertyCount, int population)
    {
        return ((double) propertyCount / population) * 100000;
    }
}
