package com.company;
import java.io.*;
public class Main
{
    private static String encoding;

    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            System.out.println("Wrong number of input arguments");
            return;
        }

        File testFile = new File(args[0]);
        encoding = args[1];

        String contents = getContents(testFile);
        System.out.print("File contents:\r\n" + contents);
    }

    static public String getContents(File inputFile)
    {
        StringBuilder contents = new StringBuilder();

        try
        {
            if (inputFile == null)
            {
                throw new IllegalArgumentException("File should not be null.");
            }

            if (!inputFile.exists())
            {
                throw new FileNotFoundException();
            }

            if (!inputFile.canRead())
            {
                throw new IllegalArgumentException("File cannot be written: " + inputFile);
            }

            if (!inputFile.isFile())
            {
                throw new IllegalArgumentException("Should not be a directory: " + inputFile);
            }

            FileInputStream fis = new FileInputStream(inputFile);
            InputStreamReader in = new InputStreamReader(fis, encoding);
            BufferedReader input =  new BufferedReader(in);
            try
            {
                String line = null;

                while ((line = input.readLine()) != null)
                {
                    contents.append(line + "\r\n");
                }
            }
            finally
            {
                input.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File does not exist: " + inputFile);
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return contents.toString();
    }

    /*private static void write(String contents, File outputFile)
    {
        String trimmed = contents.trim().replaceAll(" +", " ").replaceAll("\t", " ");
        try
        {
            OutputStream os = new FileOutputStream(outputFile);
            byte[] contentInBytes = trimmed.getBytes();
            os.write(contentInBytes);
            os.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }*/
}
