package model;

public class Order {

    private int orderID;
    private double preparationTime;
    private Restaurant restaurant;
    private Customer customer;

    public Restaurant getRestaurant(){
        return this.restaurant;
    }

    public Customer getCustomer(){
        return this.customer;
    }
    public double getPreparationTime(){
        return this.preparationTime;
    }



    public Order(int orderId, double  preparationTime, Restaurant restaurant, Customer customer)
    {
        this.orderID = orderId;
        this.preparationTime = preparationTime;
        this.restaurant = restaurant;
        this.customer = customer;
        this.restaurant.customerLocation = customer;
    }
}
