import model.Customer;
import model.Location;
import model.Order;
import model.Restaurant;
import service.OrderService;



public class BestRoute {

    public static void main(String[] args){
        Location driverLocation = new Location(20.39, 30.39, "Aman");
        OrderService orderService = new OrderService(driverLocation);

        Restaurant r1 = new Restaurant("Burger King", 21.41, 34.43);
        Restaurant r2 = new Restaurant("Dominos", 21.42, 34.44);
        Restaurant r3 = new Restaurant( "McDonald's", 21.43, 34.45);
        Customer c1 = new Customer("Rohan",21.44, 34.46);
        Customer c2 = new Customer("Sohan",21.45, 34.47);
        Customer c3 = new Customer("Shaam",21.46, 34.48);

        Order order1 = new Order(1,30.0,r1,c1);
        orderService.addOrder(order1);
        Order order2 = new Order(2,10.0,r2,c2);
        orderService.addOrder(order2);
        Order order3 = new Order(3,15.0,r3,c3);
        orderService.addOrder(order3);

        orderService.getNextLocation();
        orderService.getNextLocation();
        orderService.getNextLocation();
        orderService.getNextLocation();
        orderService.getNextLocation();
        orderService.getNextLocation();
        orderService.getNextLocation();
    }
}
