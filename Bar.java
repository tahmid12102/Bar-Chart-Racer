public class Bar implements Comparable<Bar>{

    private final String name;
    private final int value;
    private final String category;

    //Constructors
    public Bar(String name, int value, String category){
        //Argument exceptions for corner cases.
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        if (value < 0) throw new IllegalArgumentException("Value cannot be negative.");
        if (category == null) throw new IllegalArgumentException("Category cannot be null");
        //Constructor assignments.
        this.name = name;
        this.value = value;
        this.category = category;
    }

    //Accessors
    //Return the name of this bar.
    public String getName(){return this.name;}
    
    //Returns the value of this bar.
    public int getValue(){return this.value;}
    
    //Returns the category of this bar.
    public String getCategory(){return this.category;}


    //Compare two bars by value
    public int compareTo(Bar that){
        //Argument exception for corner case.
        if(that == null) throw new IllegalArgumentException("Argument cannot be null");
        return Integer.compare(this.value, that.getValue());    
    }

    //Test client
    public static void main(String[] args){

    }


}