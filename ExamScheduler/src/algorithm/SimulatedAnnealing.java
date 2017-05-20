package algorithm;

import java.util.ArrayList;
import java.util.Random;

import logic.Problem;

public class SimulatedAnnealing {
	
	public enum TypeOfDecrease {MULTIPLICATIVE, SUBTRACTIVE};
	
	private Problem problem;
	private final int MAXITERATIONS;
	private final int NUMREPETITIONS;
	private final double TEMPERATURE;
	private final double MINTEMPERATURE;
	private final double COOLINGRATE;
	private final TypeOfDecrease TYPEOFDECREASE;
	private ArrayList<Integer> solution;
	private Random random;
	
	

	public SimulatedAnnealing(Problem problem, int maxIterations, int numRepetitions, double temperature, double minTemperature, 
			double coolingRate, TypeOfDecrease typeOfDecrease){
		
		this.problem = problem;
		this.MAXITERATIONS = maxIterations;
		this.NUMREPETITIONS = numRepetitions;
		this.TEMPERATURE = temperature;
		this.MINTEMPERATURE = minTemperature;
		this.COOLINGRATE = coolingRate;
		this.TYPEOFDECREASE = typeOfDecrease;
		
		generateInitialState();
		solve();		
	}

	public void generateInitialState(){
		random = new Random();
		solution = new ArrayList<>();		
		
		for (int i = 0; i < problem.getExams().size(); i++){
			int examDay = random.nextInt(problem.getNumberOfDays())+1;
			solution.add(examDay);
		}		
	}

	public void solve(){
		Evaluator evaluator = Evaluator.getInstance();
		double currentTemperature = TEMPERATURE;
		
		/* Calculates the value of the initial solution */
		double currentValue = evaluator.calculateFitness(solution, problem);

		while(currentTemperature > MINTEMPERATURE){
			int examToChange = random.nextInt(solution.size());
			int oldExamDay = solution.get(examToChange);
			//int newExamDay = random.nextInt(endDay-startingDay)+startingDay;
			int newExamDay = random.nextInt(problem.getNumberOfDays())+1;
			solution.set(examToChange, newExamDay);

			double newValue = evaluator.calculateFitness(solution, problem);

			System.out.println(currentValue);

			double change = newValue - currentValue;
			if ((change > 0) || (random.nextDouble() < Math.pow(Math.E, change / currentTemperature)))
				currentValue = newValue;
			else
				solution.set(examToChange, oldExamDay);			

			currentTemperature *= COOLINGRATE;			
		}	
	}	

	public double getTEMPERATURE() {
		return TEMPERATURE;
	}

	public double getCOOLINGRATE() {
		return COOLINGRATE;
	}

	public double getMINTEMPERATURE() {
		return MINTEMPERATURE;
	}

	public ArrayList<Integer> getSolution() {
		return solution;
	}

	public void setSolution(ArrayList<Integer> solution) {
		this.solution = solution;
	}
}
