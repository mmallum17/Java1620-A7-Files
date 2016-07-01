import java.io.*;
import java.util.Scanner;

/**
 * Created by Marcus on 6/30/2016.
 */
public class CrimeDataDriver
{

    public static void main(String[] args)
    {
        ObjectInputStream serializableInput;
        ObjectOutputStream serializableOutput;
        StateCrimeStats stateCrimeStats;
        String stateName;
        Scanner input  = new Scanner(System.in);
        System.out.print("Enter state name: ");
        stateName = input.nextLine();


        //Try to read from file
        try
        {
            serializableInput = new ObjectInputStream(new FileInputStream(stateName + ".ser"));
            stateCrimeStats = (StateCrimeStats)serializableInput.readObject();
            serializableInput.close();
            System.out.println("Loading existing data...");
            System.out.print(stateCrimeStats);
        }
        catch(FileNotFoundException FNFE)
        {
            System.out.println("Processing new data...");
            StateCrimeData stateCrimeData = new StateCrimeData();
            try
            {
                stateCrimeData.readCrimeData(stateName + ".csv");
                stateCrimeStats = stateCrimeData.processCrimeData();
                serializableOutput = new ObjectOutputStream(new FileOutputStream(stateName + ".ser"));
                serializableOutput.writeObject(stateCrimeStats);
                serializableOutput.close();
                System.out.print(stateCrimeStats);
                System.out.println(stateName + "'s stats saved.");
            }
            catch(FileNotFoundException FNF)
            {
                System.err.println("Error reading crime data");
            }
            catch(IOException IOE)
            {
                System.err.println("Error writing to serializable file");
            }
        }
        catch (EOFException EOFE)
        {
            System.err.println("End of of Serializable File Reached");
        }
        catch(IOException IOE)
        {
            System.err.println("Error reading from serializable file");
        }
        catch(ClassNotFoundException CNFE)
        {
            System.err.println("Error, class not found");
        }
    }
}
