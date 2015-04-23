The application not only validates a Sudoku file but it is capable of generating
a SudokuMatrix both complete or partial (ready to be played).
In order to use it:

```
mvn clean install
echo "*************************"
echo "Launcing Java Application"
echo "*************************"
java -jar target/sudoku-puzzle-1.0-SNAPSHOT.jar $1 

*************************
Launcing Java Application
*************************
Welcome to Sudoku App
----------------------------------
 6 | 4 | 8   5 | 3 | 9   7 | 1 | 2  
 5 | 3 | 9   7 | 1 | 2   6 | 4 | 8  
 7 | 1 | 2   6 | 4 | 8   5 | 3 | 9  

 4 | 8 | 5   3 | 9 | 7   1 | 2 | 6  
 3 | 9 | 7   1 | 2 | 6   4 | 8 | 5  
 1 | 2 | 6   4 | 8 | 5   3 | 9 | 7  

 8 | 5 | 3   9 | 7 | 1   2 | 6 | 4  
 9 | 7 | 1   2 | 6 | 4   8 | 5 | 3  
 2 | 6 | 4   8 | 5 | 3   9 | 7 | 1  
----------------------------------
Please press the following options:
[a] Generate a Sudoku matrix 
[b] Generate a Sudoku playble matrix
[c] Validate a Sudoku file
```
