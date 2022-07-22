package src.main.models;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Cart {
    ArrayList<Item> items;

    public Cart() {
        this.items = new ArrayList<Item>();
    }

    public Item getItem(int index) {
        return new Item(this.items.get(index));
    }

    public void setItem(int index, Item item) {
        this.items.set(index, new Item(item));
    }

    public boolean addItem(Item item){
        if(this.items.contains(item)){
            return false;
        }
        this.items.add(new Item(item));
        return true;
    }

    public boolean contains(Item item){
        return items.contains(item);
    }

    public void removeItem(String name){
        if(this.items.isEmpty()) {
            throw new IllegalStateException("U BROKED MY TOY");
        }
        this.items.removeIf((item) -> item.getName().equals(name));
    }

    public double getSubtotal(){
      return items.stream()
                  .mapToDouble((item) -> item.getPrice())
                  .sum();
    }

    public double getTax(double subtotal){
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.parseDouble(format.format(subtotal * .13));
    }

    public String checkout(){
        if (items.isEmpty()){
            throw new IllegalStateException("Lol empty");
        }
        return "\tRECEIPT\n\n" +
        "\tSubtotal: $" + getSubtotal() + "\n" +
        "\tTax: $" + getTax(getSubtotal()) + "\n" +
        "\tTotal: $" + getTotal(getSubtotal(), getTax(getSubtotal())) + "\n";
    }

    public double getTotal(double subtotal, double tax){
        return subtotal + tax;
    }

    public void clear(){
        this.items.clear();
    }
    
    public String toString() {
        String temp = "";
        for (int i = 0; i < this.items.size(); i++) {
            temp += this.items.get(i).toString();
            temp += "\n";
        }
        return temp;
    }

}
