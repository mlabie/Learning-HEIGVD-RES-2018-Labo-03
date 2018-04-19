package ch.heigvd.res.labs.smtp.util;

import ch.heigvd.res.labs.smtp.data.Victim;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class that implements a static method that read a file containing e-mail address of victims,
 * and return a list of Vtictims.
 *
 * @author David Jaquet & Marc Labie
 */
public class VictimReader {

    private VictimReader(){}


    public static List<Victim> getVictims(String file) throws IOException{

        List<Victim>   victims        = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String         victimEmail;

        while ((victimEmail = bufferedReader.readLine()) != null){
            victims.add(new Victim(victimEmail));
        }

        return victims;
    }
}
