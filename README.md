# Dataset and source code for `Efficient Solution Methods for a General $r$-interdiction Median Problem with Fortification`


Kaike Zhang, Xueping Li and Mingzhou Jin (2021), "Efficient Solution Methods for a General $r$-interdiction Median Problem with Fortification."  INFORMS Journal on Computing. Vol. ###, Pages: ###-###. 

![Bi-level Problem](/bi-level_problem.png)


## Dataset 
Data used in this paper can be found in the `input` folder.
Each file corresponds to an instance with specified number of nodes or cities.
The first line is the number of cities `n`.
Next `n` lines, each line specifies a city in the order of:
 - index (int)
 - demand (float)
 - emergency cost (float)
 - fixed facility cost (float)
 - latitude (float)
 - longitude (float)

## Source Code 
Code is written in `Scala` and can be run with Scala IDEs, no addition package needed, but you may need to configure the path so `CPLEX` can be recognized. Result tables are gathered by running corresponding files.

- Table 3
   - `AttackProblemCompareMain.scala`
   - `AttackProblemCompareMainLinear.scala`

- Table 4, Table 5 
   - `AttackProblemVaryMain.scala`

- Table 6, Table 7
   - `CompareRIMPSolverZhu.scala`
   - `RIMPSolverCuttingPlane.scala`
   - `RIMPSolverTreeSearch.scala`
  
- Table 8, Table 9
   - `CompareRIMPSolversP0.scala`
