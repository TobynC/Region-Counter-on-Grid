package collinsworth_project3_2015;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Grid class
 *
 * @author Tobyn Collinsworth
 */
public class Grid
{

    private int[][] grid;
    private int count = 0;
    private int regionNumber = 1;
    private StringBuilder regionInfo = new StringBuilder();

    /**
     * loads the specified file and extracts the information to instantiate the
     * array as well as load the information into the grid
     *
     * @param inputFileName - the passed in file name
     */
    public void load(String inputFileName)
    {
        try
        {
            Scanner inputFile = new Scanner(new File(inputFileName));

            //sets the grid size from the file data
            grid = new int[inputFile.nextInt()][inputFile.nextInt()];

            //fills the grid
            for (int i = 0; i < grid.length; i++)
            {
                for (int j = 0; j < grid[i].length; j++)
                {
                    grid[i][j] = inputFile.nextInt();
                }
            }

            //prints the grid that was loaded
            System.out.println(toString());

            //sets to -1 to make parsing easier
            for (int i = 0; i < grid.length; i++)
            {
                for (int j = 0; j < grid[i].length; j++)
                {
                    if (grid[i][j] == 1)
                    {
                        grid[i][j] = -1;
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * a recursive method that finds the number of consecutive values touching
     * it at a given position.
     *
     * @param grid - the 2D array of integers that holds the values
     * @param row - the current row
     * @param column - the current column
     * @return the total consecutive items found
     */
    public int regions(int[][] grid, int row, int column)
    {
        //if in a valid position and it is not -1, return 0
        if (row < 0 || column < 0 || row > grid.length - 1 || column > grid[0].length - 1 || grid[row][column] != -1)
        {
            return 0;
        }
        else
        {
            //sets the current position to the region number
            grid[row][column] = regionNumber;
            //increments count
            count++;

            //checks all positions in regards to the current position
            int down = regions(grid, row + 1, column);
            int up = regions(grid, row - 1, column);
            int right = regions(grid, row, column + 1);
            int left = regions(grid, row, column - 1);
            int upRight = regions(grid, row - 1, column + 1);
            int upLeft = regions(grid, row - 1, column - 1);
            int downRight = regions(grid, row + 1, column + 1);
            int downLeft = regions(grid, row + 1, column - 1);

            //returns the total number of consecutive items found
            return up + down + left + right + upRight + upLeft + downRight + downLeft + 1;
        }
    }

    /**
     * calls the recursive method at each location -1 is found and prints out
     * the information to the console
     */
    public void findRegion()
    {
        //tests each position in the grid
        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j] == -1)
                {
                    //calls recursive method at the index and adds to regionInfo the regions
                    regions(grid, i, j);
                    this.regionInfo.append("Region: ").append(regionNumber).append("    Contains: ").append(count).append("\n");

                    //increment regionNumber for each found
                    regionNumber++;
                    //count is reset when each region is found
                    count = 0;
                }
            }
        }
        //if regions has not changed from it's initial value then there were no regions
        if (regionNumber == 1)
        {
            this.regionInfo.append("No regions found.");
        }
        //print the information
        System.out.println(regionInfo.toString());
        System.out.println("\n" + this.toString());
    }

    /**
     * saves the modified grid to the user specified file, if the file does not
     * exist the file will be created, otherwise the file will be overwritten
     *
     * @param outputFileName - the passed in file name
     */
    public void save(String outputFileName)
    {
        try
        {
            try (PrintWriter writer = new PrintWriter(outputFileName))
            {
                writer.println(regionInfo.toString());
                writer.print(toString());
            }
        } catch (FileNotFoundException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }

    /**
     * prints the 2D array using a StringBuilder
     *
     * @return string builder object
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                sb.append(grid[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
