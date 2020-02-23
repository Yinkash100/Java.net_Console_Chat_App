package chat;

import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class TestArea implements Runnable{
    private final int sleepTime; // random sleep time for thread
    private final String taskName; // name of task
    private final static Random generator = new Random();
    // constructor
    public TestArea( String name )
    {
        taskName = name; // set task name
// pick random sleep time between 0 and 5 seconds
        sleepTime = generator.nextInt( 5000 ); // milliseconds
    } // end PrintTask constructor


    // method run contains the code that a thread will execute
    public void run()
    {
        try // put thread to sleep for sleepTime amount of time
        {
            System.out.printf( "%s going to sleep for %d milliseconds.\n",
                    taskName, sleepTime );
            Thread.sleep( sleepTime ); // put thread to sleep
        } // end try
        catch ( InterruptedException exception )
        {
            System.out.printf( "%s %s\n", taskName,
                    "terminated prematurely due to interruption" );
        } // end catch
// print task name
        System.out.printf( "%s done sleeping\n", taskName );
    } // end method run

}

