Requirement clarification
1. How many dice? 1 but should be scalable
2. How many snakes and ladders? can be anything
3. What should be the game over condition? if any 1 player wins


Jump = Snake/Ladder
For Snake, start > end
For Ladder, start < end


Board has Cell[][]
Cell has Jump

We just need to store the Jump object at `getCell[start]` (for both snake and ladder)
Nothing needs to be stored at `getCell[end]`

Why Dequeue for playerlist? cuz we'll give turns to all players in round robin fashion