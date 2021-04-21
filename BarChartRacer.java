import java.util.Arrays;

public class BarChartRacer{
    public static void main(String [] args){
        //Play audio in a continuous loop till the programs finishes execution.
        StdAudio.loop("aloha.wav");

        //Take file name from command line argument take as standard input.
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);

        //Set Canvas Size
        StdDraw.setCanvasSize(1000, 700);
        StdDraw.enableDoubleBuffering();

        //Read in title, x-axis label, and data source from first 3 lines of text file
        String title = in.readLine();
        String xAxisLabel = in.readLine();
        String dataSource = in.readLine();

        //Create BarChart
        BarChart chart = new BarChart(title, xAxisLabel, dataSource);

        //Loop iterated till end of text file document.
        while(in.hasNextLine()){
            //Skip empty line.
            in.readLine();

            //Read in number of records for next bar chart
            int numberOfRecords = Integer.parseInt(in.readLine());

            //Create an array of bars for corresponding number of records.
            Bar[] bars = new Bar[numberOfRecords];

             //Read records line by line
            for (int i = 0; i < numberOfRecords; i++){
                //Read in record as a string and store in array.
                String record = in.readLine();
                String [] recordarr = record.split(",");

                //Index array of strings and set equal to name, caption, and category. 
                String caption = recordarr[0];
                chart.setCaption(caption);
                String name = recordarr[1];
                int value = Integer.parseInt(recordarr[3]);
                String category = recordarr[4];

                //Store Bar object in array with corresponding name,value, and category of record
                bars[i] = new Bar(name,value,category);
            }

            //Sort the bars in the array by value
            Arrays.sort(bars);

            //Add each bar to the chart.
            for (int i = numberOfRecords-k-1 ; i < numberOfRecords; i++){
                chart.add(bars[i].getName(), bars[i].getValue(), bars[i].getCategory());
            }

            //Clear. draw, then show chart
            StdDraw.clear();
            chart.draw();
            StdDraw.show();
            //Pause 1/10 of a second and reset() chart before displaying next set of records
            StdDraw.pause(100);
            chart.reset();
        }
        chart.reset();
    }
}