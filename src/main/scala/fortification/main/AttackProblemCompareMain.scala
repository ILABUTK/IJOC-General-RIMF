package fortification.main

import java.io.File
import java.io.PrintWriter

import fortification.data.AttackerProblemInstance
import fortification.data.DefenderProblemInstance
import fortification.data.InstanceReader
import fortification.data.LocationProblemInstance
import fortification.data.PMedianProblem
import fortification.data.SolverInstructor
import fortification.solver.AttackerProblemCuttingPlaneSolver
import fortification.solver.AttackerProblemEnumerateSolver
import fortification.data.output.ResultOutput
import fortification.solver.PMedianSolver

object AttackProblemCompareMain {
  def main(args: Array[String]): Unit = {
    
    
    val (resultOut, logOut) = ResultOutput.createOutputPrinter("AttackProblemSolverCompare")
    
    val outCapture = logOut

    val nodes = List(50, 75, 100, 150)
    val ps = List(15, 20, 30)
    val units = List(3, 6, 9)

    Console.withOut(outCapture) {

      for (node <- nodes; p <- ps; unit <- units) {
        val (demands, dcs) = InstanceReader.readInstanceFrom(s"input//UCFLData${node}.txt")
        val alpha = 0.2
        val theta = 400
        val w = 0.4
        val defenceUnits = 0
        val attackUnits = unit
        
        resultOut.print(s"${node}\t${p}\t${attackUnits}\t")

        val locationInstance = new LocationProblemInstance(demands, dcs)
        val pMedianInstance = new PMedianProblem(locationInstance, p)
        
        val openLocations = new PMedianSolver(pMedianInstance).solve()
         
        println(openLocations)
        
        val defenderInstance = new DefenderProblemInstance(locationInstance, defenceUnits, attackUnits, alpha, theta, w)
        val attackInstance = new AttackerProblemInstance(defenderInstance, openLocations, Set.empty[Int], attackUnits)

        val instructor = SolverInstructor(3600, 0.001)

        val solverCuttingPlane = new AttackerProblemCuttingPlaneSolver(attackInstance, instructor)

        val solverEnumerate = new AttackerProblemEnumerateSolver(attackInstance, instructor)

        solverCuttingPlane.solve() match {
          case Some(sol) => {
            println(sol)
            resultOut.print(s"${sol.objectiveValue}\t${sol.time}\t${sol.numEvaluation}")
          }
          case _         => println("Error")
        }
        
        resultOut.print("\t")

        solverEnumerate.solve() match {
          case Some(sol) => {
            println(sol)
            resultOut.print(s"${sol.objectiveValue}\t${sol.time}\t${sol.numEvaluation}")
          }
          case _         => println("Error")
        }
        
        resultOut.println()
        resultOut.flush()
        
      }
      
      resultOut.close()

    }

  }
}
