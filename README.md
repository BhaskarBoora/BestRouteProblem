# BestRouteProblem

			Best Route Problem

Assumptions:
Each restaurant serves to a unique user.
No of restaurants are equal to number of users.
As soon as the order is received, the restaurant starts preparing.
Have considered the speed of delivery guy to be 20km/hr.

Calculation
If we assume n orders to be delivered.Its basically same as calculating permutations possible for arranging Ri,Ci where i is from 1 to N.
Since only restaurant can precede a customer corresponding , so each case is then divided by 2.So,we can say that there are total ((2n)!/2^n) cases possible for each delivery.

The Approach
So we can understand the brute way of doing the problem will be going through each path possible and then deciding which ones the shortest.
The approach that I have taken is that at any point of time , whenever we pick  orders from two restaurants ,we give it a bit higher priority as compared to other orders and hence focus towards delivering them first.Only when such orders are delivered to the customers we pick up another order from our list and assign it to the delivery guy.

All the orders are initially in pending list, we only move orders from pending list to optimized list when optimized list has less than five orders.
Why was this number kept exactly at 5 ? Since (2n)!/(pow(2,n)) for n=5 => 113400  and for an average computer this would take one sec to find the optimal path.

How to decide whether which order moves from pendling list to optimized list?
For this I have  calculated the minimum time to reach the restaurant will be the one that we will cater to. The minimum time would be simply max(time taken to reach that restaurant, avg prep. time). So, the order with minimum time moves to optimized list.

Once each order is in optimized list, we basically calculate all the permutations possible with those orders, weed out sequences where destination of customers precede their restaurants.

We use priority list to cater to the orders who have been already picked by the delivery guy , priority list size can be maximum two, hence we are having threshold such that the delivery guy cannot have more than two orders at a time with him/her.




Running the code:
The BestRoute java file creates instances of restaurants,customers and orders and is the trigger point for the code.
At any point of time orderService.getNextLocation();gives the delivery guy information about where to go next and to what coordinates.  

