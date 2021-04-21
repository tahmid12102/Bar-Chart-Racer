import java.util.*;
import java.awt.*;

public class BarChart {

    private final String title;                                 //Chart title.
    private final String xAxisLabel;                            //Chart x-axis label.
    private final String dataSource;                            //Chart data source.
    private String caption;                                     //Chart caption.

    private ArrayList<String> names;                            //List of bar names.
    private ArrayList<Integer> values;                          //List of bar values.
    private HashMap<String,Color> colorOf;                      //Map category to color.
    private ArrayList<Color> colors;                            //List of bar colors.
    private HashSet<Color> usedColors = new HashSet<Color>();   //Set of colors already assigned.

    //Generates random Color not assigned previously.
    private Color randomColorGenerator(){
        //Loop to ensure duplicate color is not created
        Color color;
        do {
            Random rand = new Random(System.currentTimeMillis());
            int r = (int)Math.round(rand.nextDouble() * 254);
            int g = (int)Math.round(rand.nextDouble() * 254);
            int b = (int)Math.round(rand.nextDouble() * 254);
            color = new Color(r,g,b);
        } while(usedColors.contains(color));
        //Add to set to keep track which colors have been used.
        usedColors.add(color);
        return color;
    }


    //Constructor
    public BarChart(String title, String xAxisLabel, String dataSource){
        //Corner Cases
        if (title == null || xAxisLabel == null || dataSource == null) throw new IllegalArgumentException("Arguments cannot be null.");
        this.title = title;
        this.xAxisLabel = xAxisLabel;
        this.dataSource = dataSource;
        colorOf = new HashMap<String,Color>();
        //Resets names, values, colors, and caption
        reset();
    }


    //Sets caption of this BarChart.
    public void setCaption(String caption){
        if (caption == null) throw new IllegalArgumentException("Argument cannot be null.");
        this.caption = caption;
    }

    //Sets name, value, and category of this bar.
    public void add(String name, int value, String category){
        if (name == null) throw new IllegalArgumentException("Name cannot be null.");
        if (value <= 0) throw new IllegalArgumentException("Value must be greater than 0.");
        if (category == null) throw new IllegalArgumentException("Category cannot be null;");

        //Determines if given category has been asssigned a color. If not, assign a random color.
        if (!colorOf.containsKey(category)) colorOf.put(category, randomColorGenerator());

        //Add bar attributes to corresponding ArrayLists.
        names.add(name);
        values.add(value);
        colors.add(colorOf.get(category));

    }

    //Create new space in memory for names,values,colors,and caption for new set of records.
    public void reset(){
        names = new ArrayList<String>();
        values = new ArrayList<Integer>();
        colors = new ArrayList<Color>();
        this.caption = "";
    }

    //Returns how x-axis scale is divided. 
    private static int getScale(double xMax){
        int scale = 1;
        while (Math.floor(xMax/scale) >= 8){
            if (scale % 9 == 2) scale = scale * 5 /2;
            else                scale = scale * 2;
        }
        return scale;
    }


    //Draws bar chart to standard draw 
    public void draw(){
        //Corner case if there is nothing to draw
        if(names.isEmpty()) return;

        //Number of bars is equal to number of names or 8, whichever is larger.
        int numberOfBars = Math.max(8,names.size());

        //Need to set x-coordinate scale according to maximum value
        double xMax = Double.NEGATIVE_INFINITY;
        for (int value : values){
            if (value > xMax) xMax = value;
        }
        
        //Set x and y scale
        StdDraw.setXscale(-0.01*xMax,1.2*xMax);
        StdDraw.setYscale(0, numberOfBars*1.25);

        //Draw title
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setFont(new Font("SansSerif",Font.BOLD, 16));
        StdDraw.text(xMax*0.6,numberOfBars*1.2,title);

        //Draw X-Axis label
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.textLeft(0,numberOfBars*1.10,xAxisLabel);

        //Draw Axes
        int scale = getScale(xMax);
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        for (int i = 0; i < xMax; i+=scale){
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.text(i,numberOfBars*1.02,String.format("%,d",i));
            StdDraw.setPenColor(new Color(230,230,230));
            StdDraw.line(i,0.01,i,numberOfBars);
        }

        //Draw caption
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.setFont(new Font("SansSerif",Font.BOLD,60));
        StdDraw.textRight(xMax*1.15,numberOfBars*0.2,caption);

        //Draw source acknowledgement
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 14));
        StdDraw.textRight(1.14*xMax,0.1*numberOfBars,dataSource);

        //Draw bars
        for (int i = 0; i < names.size(); i++){
            StdDraw.setPenColor(colors.get(i));
            StdDraw.filledRectangle(0.5*values.get(i), i-0.5, 0.5*values.get(i), 0.4);
            StdDraw.setPenColor(StdDraw.BLACK);
            int fontSize = (int) Math.ceil(14*10.0/numberOfBars);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, fontSize));
            StdDraw.textRight(values.get(i) - 0.01 * xMax, i - 0.5, names.get(i));
            StdDraw.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
            StdDraw.setPenColor(StdDraw.DARK_GRAY);
            StdDraw.textLeft(values.get(i) + 0.01 * xMax, i - 0.5, String.format("%,d", values.get(i)));
        }
    }

    //Sample client 
        public static void main(String[] args) {
            // create the bar chart
            String title = "The 10 most populous cities";
            String xAxis = "Population (thousands)";
            String source = "Source: United Nations";
            BarChart chart = new BarChart(title, xAxis, source);
            chart.setCaption("2018");
    
            // add the bars to the bar chart
            chart.add("Tokyo",       38194, "East Asia");
            chart.add("Delhi",       27890, "South Asia");
            chart.add("Shanghai",    25779, "East Asia");
            chart.add("Beijing",     22674, "East Asia");
            chart.add("Mumbai",      22120, "South Asia");
            chart.add("São Paulo",   21698, "Latin America");
            chart.add("Mexico City", 21520, "Latin America");
            chart.add("Osaka",       20409, "East Asia");
            chart.add("Cairo",       19850, "Middle East");
            chart.add("Dhaka",       19633, "South Asia");
    
            // draw the bar chart
            StdDraw.setCanvasSize(1000, 700);
            StdDraw.enableDoubleBuffering();
            chart.draw();
            StdDraw.show();
        }
}