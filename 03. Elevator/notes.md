TODO
1. Shrayansh Jain
------------------------------------------------------------------------------
2. Keerti Purswani with gkcs (After req clarification it was all downhill)
   
Req clarification 
1) basements? negative floor?
2) what if on same floor someone wants to go up and someone wants to go down?
3) multiple elevators? can multiple elevators reach the same floor if hey are available (this would be user friendly cuz people prefer less crowded lifts)?
4) helper in lift?
5) elevators an be added/removed? floors can be added?
6) left and right elevator? or only up and down?

elevator state - idle, working, faulty/not working

algo used hashmaps...are there solutions with different data structures?
hashmaps is not a good choice cuz if we introduce some objects in the classes we might end up wit nested maps

separate class for scheduler/dispatcher


we can have either or both
1. any person presses on which floor he wants to go then lifts arrives, or
2. any person just presses the up or down button, lift arrives, and from inside the lift he presses to which floor he wants to go
the request for lifts be it through 1 or 2 will differ and both have to be handled

push request to event bus? what do other youtubers say?

------------------------------------------------------------------------------
3. Ashish Pratap Singh

requirements
1) capacity limit for each elevator
2) min waiting time before elevator is dispatched
3) no starvation (this problem is like job scheduling)
4) concurrency in case of multiple elevators


Request Object (also in Keerti Purswani)

------------------------------------------------------------------------------
1. Think Software

req clarification?
1) do we want to minimise wait time of system or wait time of individual passengers?
2) do we want to have restricted operational zones for each elevator car? (Why do we even want to think about zoning? it's when the number of floors is large and we want to minimise wait time)
3) VIP elevators?
4) emergency alarms/breaks etc?

Objects and Actors
1) dispatcher
2) Elevator System (Singleton)
3) Doors

Usecases
1) calling elevator
2) open/close doors
3) move up/down
4) indicartors showing current floor

LLD
1) Button Interface (press, isPressed)
2) ElevatorButtonImpl
3) HallButtonImpl

elevator system algos
1) FCFS --> not efficient (although someone might be in the way of the elevator but still might be ignored cuz algo is following a queue)
2) incase of multiple elevator cars dispatcher can decide to send the nearest elevator
3) elevator state 3,4 will be ignored by the dispatcher (27:58)
   1) idle
   2) moving same direction towards the passenger and direction passenger wants to go
   3) moving same direction towards the passenger but opposite to direction passenger wants to go
   4) elevator car going away from the passenger
4) algo 2: SSTF (Shortest Seek Time First) i) using PQ/minHeap (distance between passenger and elevator in PQ but that'll keep on changing....also can lead to starvation)
5) algo3 : scan/ elevator algo
   1) 2 boolean arrays - 1 for up, 1 for down
   2) didn't understand use of PQ
   3) has drawaback - always goes to the topmost/bottommost floor only then changes direction
6) algo4: look/ look ahead scan
   1) elevators will stop as soon as there is no more request in front of them and then they'll change direction
7) in reality look + heuristics is used

different algos through strategy DP
