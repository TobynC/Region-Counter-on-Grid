/**
 * Project 3 for CS 1181
 * finds connected regions in a text files and changes the value based on a 
 * counter and saves the modified version to a user specified file
 */

package collinsworth_project3_2015;

import java.util.Scanner;

/**
 * @author Tobyn Collinsworth
 * CS1181
 * Instructor: R. Volkers
 * TA: R. Brant
 */

public class TestDrive 
{
    /**
     * test driver
     * @param args - command line arguments 
     */
    public static void main(String[] args) 
    {
        Scanner in = new Scanner(System.in);
        Grid grid = new Grid();
        
        System.out.println("Enter the file name.");
        grid.load(in.next());
        grid.findRegion();
        System.out.println("Enter the output file name.");
        grid.save(in.next());
    }
}
