## Abstract

The folllowing program produces an animated bar chart using a text file dataset from a command line argument and an integer k determining how many bars to display on the canvas. The *Bar* data type aggregates related information for use in a bar chart. The *BarChart* data type supports drawing static bar charts to standard draw. *BarChartRacer* uses *BarChart* and *Bar* to produce an animated bar chart. The x-axis of the chart represents the value of each bar and the y-axis represents each bar in ascending order of value from bottom to top. The colors of the bars are assigned according to the category that each bar belongs to. Using *cities.txt* as an example, Beijing and Guangzhou are assigned the same color, because they both belong to the East Asia category, while Cairo is a different color because it belongs to the Middle East category. 

## Text File Organization

The text files begin with the first three lines representing the title of the chart, the x-axis label, and the data source, respectively. Followed by that is a line with the number of records, *n*, for the next set of bars to be displayed followed by each *n* record. Each record is formatted by the caption, name of the bar, value, and category, each seperated by a comma. 

## Bar.java API

**private final String name** - Attribute for the name of the bar.

**private final int value** - Attribute for the value of the bar.

**private final String category** - Attribute for the category of the bar.

**public class Bar (String name, int value, String category)** - Constructor that creates a new bar.

**public String getName()** - Accessor that returns the name of this bar.

**public int getValue()** - Accessor that returns the value of this bar.

**public String getCategory()** - Accessor that returns the category of this bar.

**public int compareTo** - Compares two bars by value.

**public static void main (String [] args)** - Sample test client.

### Comparable Interface

In order to sort the bars in ascending order, the Comparable interface is implemented to compare Bar objects by value.

### Argument Exceptions - Corner Cases

An IllegalArgumentException is thrown in the constructor if *name* is null, *value* is negative, or *category* is null.

An IllegalArgumentException is thrown if the argument to **compareTo** is null.

## BarChart.java API 

**private final String title** - Attribute for the title of the chart.

**private final String xAxisLabel** - Attribute for the x-axis label of the chart.

**private final String dataSource** - Attribute for the data source of the chart.

**private String caption** - Attribute for the caption of the chart.

**private ArrayList<String> names** - Attribute for the names of all the bars of the chart stored in an ArrayList.

**private ArrayList<Integer> values** - Attribute for the values of all the bars of the chart stored in an ArrayList.

**private HashMap<String,Color> colorOf** - Attribute to map each category of the dataset to a color using a HashMap.

**private ArrayList<Color> colors** - Attribute for the colors of all the bars of the chart stored in an ArrayList.

**private HashSet<Color> usedColors** - Attribute for the set of colors already assigned to a category.

**private static Color randomColorGeneration()** - Returns a Color object with random (r,g,b) values.

**public BarChart(String title, String xAxisLabel, String dataSource)** - Constructor that creates a bar chart with the given title, x-axis label, and data source.

**public void setCaption(String caption)** - Mutator that sets the caption of this bar chart.

**public void add(String name, int value, String category)** - Mutator that adds a bar (name, value, category) to this bar chart by appending to respective ArrayLists.

**public void reset()** - Mutators that removes (names, values, colors) all the bars from this bar chart by setting each respective ArrayList to an empty set.

**private static int getScale(double xMax)** - Returns the increment value of each x-axis value. Results in a minimum of 4 divisions and a maximum of 8 divisions.

**public void draw()** - Draws this bar chart to standard draw.

**public static void main (String[] args)** - Sample test client.

### Argument Exceptions - Corner Cases 

An IllegalArgumentException is thrown in the constructor if *title*, *xAxisLabel*, or *dataSource* is null.

An IllegalArgumentException is thrown in **setCaption(String Caption)** if *caption* is null.

An IllegalArgumentException is thrown in **add(String name, int value, String category)** if *name* or * category* is null or if *value* is less than or equal to 0.

## BarChartRacer.java API 

**public static void main (String [] args)** - Main function that takes a String as a command line argument for the dataset text file and an integer k determining how many bars to display on the canvas. Uses BarChart and Bar to create an animated bar chart. Audio (SpongeBob music) is looped in the background for entertainment purposes.


