public class Menu {

    protected String name;
    protected double price;
    protected String ID;
    protected String type;

    public Menu(String name){
        this.name = name;

    }

    public Menu(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getType()
    {
        return type;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }
    
    public double getPrice(){
        return price;
    }
}
