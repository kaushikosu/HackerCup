This project was created for the qualification round of Facebook's Hacker Cup hackathon in January 2017.

It solves the following problems:

Progress Pie:

Some progress bars fill you with anticipation. Some are finished before you know it and make you wonder why there was a progress bar at all.

Some progress bars progress at a pleasant, steady rate. Some are chaotic, lurching forward and then pausing for long periods. Some seem to slow down as they go, never quite reaching 100%.

Some progress bars are in fact not bars at all, but circles.

On your screen is a progress pie, a sort of progress bar that shows its progress as a sector of a circle. Envision your screen as a square on the plane with its bottom-left corner at (0, 0), and its upper-right corner at (100, 100). Every point on the screen is either white or black. Initially, the progress is 0%, and all points on the screen are white. When the progress percentage, P, is greater than 0%, a sector of angle (P% * 360) degrees is colored black, anchored by the line segment from the center of the square to the center of the top side, and proceeding clockwise.


While you wait for the progress pie to fill in, you find yourself thinking about whether certain points would be white or black at different amounts of progress.

Input
Input begins with an integer T, the number of points you're curious about. For each point, there is a line containing three space-separated integers, P, the amount of progress as a percentage, and X and Y, the coordinates of the point.

Output
For the ith point, print a line containing "Case #i: " followed by the color of the point, either "black" or "white".

Constraints
1 ≤ T ≤ 1,000 
0 ≤ P, X, Y ≤ 100 
Whenever a point (X, Y) is queried, it's guaranteed that all points within a distance of 10-6 of (X, Y) are the same color as (X, Y).

Explanation of Sample
In the first case all of the points are white, so the point at (55, 55) is of course white.

In the second case, (55, 55) is close to the filled-in sector of the circle, but it's still white.

In the third case, the filled-in sector of the circle now covers (55, 55), coloring it black.

sample Input:

5
0 55 55
12 55 55
13 55 55
99 99 99
87 20 40


sample output:

Case #1: white
Case #2: white
Case #3: black
Case #4: white
Case #5: black


Lazy Loading problem:

Wilson works for a moving company. His primary duty is to load household items into a moving truck. Wilson has a bag that he uses to move these items. He puts a bunch of items in the bag, moves them to the truck, and then drops the items off.

Wilson has a bit of a reputation as a lazy worker. Julie is Wilson's supervisor, and she's keen to make sure that he doesn't slack off. She wants Wilson to carry at least 50 pounds of items in his bag every time he goes to the truck.

Luckily for Wilson, his bag is opaque. When he carries a bagful of items, Julie can tell how many items are in the bag (based on the height of the stack in the bag), and she can tell the weight of the top item. She can't, however, tell how much the other items in the bag weigh. She assumes that every item in the bag weighs at least as much as this top item, because surely Wilson, as lazy as he is, would at least not be so dense as to put heavier items on top of lighter ones. Alas, Julie is woefully ignorant of the extent of Wilson's lack of dedication to his duty, and this assumption is frequently incorrect.

Today there are N items to be moved, and Wilson, paid by the hour as he is, wants to maximize the number of trips he makes to move all of them to the truck. What is the maximum number of trips Wilson can make without getting berated by Julie?

Note that Julie is not aware of what items are to be moved today, and she is not keeping track of what Wilson has already moved when she examines each bag of items. She simply assumes that each bagful contains a total weight of at least k * w where k is the number of items in the bag, and w is the weight of the top item.

Input
Input begins with an integer T, the number of days Wilson "works" at his job. For each day, there is first a line containing the integer N. Then there are N lines, the ith of which contains a single integer, the weight of the ith item, Wi.

Output
For the ith day, print a line containing "Case #i: " followed by the maximum number of trips Wilson can take that day.

Constraints
1 ≤ T ≤ 500 
1 ≤ N ≤ 100 
1 ≤ Wi ≤ 100 
On every day, it is guaranteed that the total weight of all of the items is at least 50 pounds.

Explanation of Sample
In the first case, Wilson can make two trips by stacking a 30-pound item on top of a 1-pound item, making the bag appear to contain 60 pounds.

In the second case, Wilson needs to put all the items in the bag at once and can only make one trip.

In the third case, one possible solution is to put the items with odd weight in the bag for the first trip, and then the items with even weight in the bag for the second trip, making sure to put the heaviest item on top.

Sample Input:

5
4
30
30
1
1
3
20
20
20
11
1
2
3
4
5
6
7
8
9
10
11
6
9
19
29
39
49
59
10
32
56
76
8
44
60
47
85
71
91

Sample output:

Case #1: 2
Case #2: 1
Case #3: 2
Case #4: 3
Case #5: 8
