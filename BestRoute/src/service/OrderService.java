package service;

import model.Location;
import model.Order;
import utils.HarvesianFormula;

import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private final List<Order> pendingOrderList;
    private List<Location>  optimizedSequence;

    private final List<Location> priorityLocation;
    private final Location currentLocation;
    public OrderService(Location driverLocation){
        pendingOrderList = new ArrayList<>();
        optimizedSequence = new ArrayList<>();
        priorityLocation = new ArrayList<>();
        currentLocation = driverLocation;
    }
    public void addOrder(Order order){
        pendingOrderList.add(order);
    }
    public  void getNextLocation(){

        while(optimizedSequence.size()<10 && optimizedSequence.size()%2==0) {
            double minTime = Double.MAX_VALUE;
            Order nextOrder = null;
            for (Order order : pendingOrderList) {
                double timeTakenToReachRestaurant = ( HarvesianFormula.getDistance(order.getRestaurant(), currentLocation))/(20.0);
                double timeTaken = Math.max(order.getPreparationTime(),timeTakenToReachRestaurant);
                if (minTime > timeTaken) {
                    minTime = timeTaken;
                    nextOrder = order;
                }
            }
            if (nextOrder != null) {
                pendingOrderList.remove(nextOrder);
                optimizedSequence.add(nextOrder.getRestaurant());
                optimizedSequence.add(nextOrder.getCustomer());
            }
            else{
                break;
            }
        }
        if(optimizedSequence.isEmpty()) {
            System.out.println("No new orders as of now");
            return;
        }

        optimize();
        Location nextLocation = optimizedSequence.get(0);
        if(nextLocation.customerLocation != null){
            //since it's not null, it's a restaurant
            priorityLocation.add(nextLocation.customerLocation);
        }
        optimizedSequence.remove(nextLocation);
        //print next restaurant or customer
        System.out.println("Next destination is  " + nextLocation.name);
        System.out.println("Next destination coordinates are  " + nextLocation.latitude + " " + nextLocation.longitude);

    }
    public  void optimize() {

        List<List<Location>> permutations;

        int priorityCustomers = priorityLocation.size();
        if(priorityCustomers < 2){
            permutations = makePermutation(optimizedSequence);
        }
        else{
            optimizedSequence.remove(priorityLocation.get(0));
            optimizedSequence.remove(priorityLocation.get(1));
            permutations = makePermutation(optimizedSequence);
            for (List<Location> permutation : permutations) {
                permutation.add(0, priorityLocation.get(1));
                permutation.add(0, priorityLocation.get(0));

            }
            priorityLocation.remove(0);
            priorityLocation.remove(0);
        }
        double minTime = Double.MAX_VALUE;
        optimizedSequence = null;
        for(List<Location> permutation: permutations) {
            if (!checkSequence(permutation)) {
                continue;
            }
            double time = getTime(permutation);
            if (time < minTime) {
                optimizedSequence = permutation;
                minTime = time;
            }
        }
    }

    private boolean checkSequence(List<Location> sequence){
        int n=sequence.size();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(sequence.get(j).customerLocation == sequence.get(i))
                    return false;
            }
        }
        return true;
    }

    private double getTime(List<Location> validSequences){

       Location loc = currentLocation;

       double time =0;
       for(Location curr: validSequences){
           time+= HarvesianFormula.getDistance(loc,curr);
           loc = curr;
       }

       return time;
    }
    private List<List<Location>> makePermutation(List<Location> sequences){
            if (sequences.size() == 1) {
                List<List<Location>> list = new ArrayList<>();
                list.add(sequences);
                return list;
            } else {
                List<List<Location>> list = new ArrayList<>();
                for (Location loc : sequences) {
                    List<Location> subList = new ArrayList<>(sequences);
                    subList.remove(loc);
                    List<List<Location>> subListNew = makePermutation(subList);
                    for (List<Location> _list: subListNew) {
                        List<Location> local = new ArrayList<>();
                        local.add(loc);
                        local.addAll(_list);
                        list.add(local);
                    }
                }
                return list;
            }
        }
    }







