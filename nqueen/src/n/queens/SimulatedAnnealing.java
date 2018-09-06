package n.queens;

/**
 *
 * @author Rumal
 */
public class SimulatedAnnealing extends NQueen {

    double tempreture;

    public SimulatedAnnealing(int boardSize, int tollerence, double tempreture) {
        super(boardSize, tollerence);
        this.tempreture = tempreture;
        currentState = new SimulatedAnnealingState(boardSize);
    }

    @Override
    public void solve() {
        while (!isSolvedPossition(currentState)) {
            double temperature;
            double delta;
            double probability;
            double rand;


            for (temperature = this.tempreture; (temperature > 0) && (currentState.getCost() != 0); temperature--) {
                nextState = currentState.getNextState();
                int currentCost = currentState.getCost();
                int nextCost = nextState.getCost();
                delta = currentCost - nextCost;
//                System.out.println("temperature="+temperature+" currentCost"+currentCost+"  下一步cost"+nextCost);
                probability = Math.exp(delta / temperature);
                rand = Math.random();
                System.out.println(probability);
                if (delta > 0) {
                    currentState = nextState;
                } else if (rand <= probability) {
                    currentState = nextState;
                }
            }
        }
    }
}
