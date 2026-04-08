package tpicap;

// Price-Time Priority algorithm
/*  Given I am a user running the application, when I submit an “order book” (containing orders) to be
matched using the price-time-priority algorithm, then I want to see the state of the order book.
Under a basic price-time-priority algorithm, the earliest active buy order at the highest price takes
priority over any subsequent order at that price, which in turn takes priority over any active buy order at
a lower price

Order Book
CompanyId   OrderId    Direction  Volume       Price    OrderDateTime
    A           A1      Buy         100         4.99    09:27:43
    B           B1      Buy         200         5.00    10:21:46
    C           C1      Buy         150         5.00    10:26:18
    D           D1      Sell        150         5.00    10:32:41
    E           E1      Sell        100         5.00    10:33:07
Acceptance Criteria:
● A1 - NoMatch - the higher priced orders took priority
● B1 - FullMatch
 ○ D1 - 150
 ○ E1 - 50
● C1 - PartialMatch
 ○ E1 - 50
● D1 - FullMatch
 ○ B1 - 150
● E1 - FullMatch
 ○ B1 - 50
 ○ C1 - 50
    * */
public class OrderExecution {
    // Matching algorithm
    /* Step 0 -- Decide the data structure based on the execution algorithm (priority deque might be best can use a heap based solution too)
    * Step 1.1-- Pair the orders either as buy or sell in 2 different structures-- (use parallel executors to process
    * ------------buy and sell orders concurrently)
    * Step 1.2 -- While pairing as buy or sell, use a custom comparator to order the incoming ids in
    * -- priority of descending price & descending volume ( can use a deque for comparing and adding these and individual Nodes)
    * -- Node can have 2 elements price & volume as float)
    *  Step 3 -- For each Sell order, starting from the highest element in deque (tail),
    *        3.1) init a new thread and match with the highest element in Buy by reducing the number of sell with buy (Vs- Vb)
    *        3.2) Loop continues while Vs > 0 or no more buyers left to fulfill the order
    *        3.3) Keep reducing the volume in buy and sell orders and update order book against the individual order
    * id as order id which fulfilled the order and if remaining volume -partial fulfilled l else if remaining volume = 0 , the completely fulfilled
    * Step 4 -- FOr each entry in the order book format and print orders in the order om which they appear
    *
    *
    * */
}
