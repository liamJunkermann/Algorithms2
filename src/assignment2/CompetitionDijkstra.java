import java.io.File;
import java.util.*;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

 /**
  * @Author Liam Junkermann
  */
public class CompetitionDijkstra {

    String filename;
    int sA;
    int sB;
    int sC;

    int intersections = 0;
    int streets = 0;
    boolean valid = true;

    ArrayList<String> graphString;
    double[][] graph;

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA,       sB, sC: speeds for 3 contestants
     */
    CompetitionDijkstra(String filename, int sA, int sB, int sC) {
        this.filename = filename;
        this.sA = sA;
        this.sB = sB;
        this.sC = sC;
        this.graphString = new ArrayList<String>();
        parseFile(fileScanner(filename));
    }

    private Scanner fileScanner(String filename) {
        try {
            return new Scanner(new File(filename));
        } catch (Exception e) {
            valid = false;
            return null;
        }
    }

    private void parseFile(Scanner file) {
        if (valid) {
            try {
                if (file.hasNextInt()) {
                    this.intersections = file.nextInt();
                }
                if (file.hasNextInt()) {
                    this.streets = file.nextInt();
                    file.nextLine();
                }
                while (file.hasNextLine()) {
                    graphString.add(file.nextLine());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private double[][] buildGraph() {
        double[][] graph = new double[this.intersections][this.streets];
        for (int i = 0; i < this.intersections; i++) {
            for (int j = 0; j < this.intersections; j++) {
                graph[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < this.intersections; i++) {
            graph[i][i] = 0;
        }

        for (int i = 0; (i < this.graphString.size()); i++) {
            Scanner lineScanner = new Scanner(this.graphString.get(i));
            int street = lineScanner.nextInt();
            int connectingStreet = lineScanner.nextInt();
            double distance = lineScanner.nextDouble();

            graph[street][connectingStreet] = distance;
            lineScanner.close();
        }

        return graph;
    }

    private double dijkstra() {
        double currentMaxDist = 0;
        for (int i = 0; i < this.intersections; i++) {
            int queueSize = 1;
            double[] distance = new double[this.intersections];
            boolean[] marked = new boolean[this.intersections];
            boolean[] reached = new boolean[this.intersections];

            for (int j = 0; j < this.intersections; j++) {
                distance[j] = Double.POSITIVE_INFINITY;
                marked[j] = false;
                reached[j] = false;
            }

            distance[i] = 0;
            reached[i] = true;

            while (queueSize > 0) {
                int indexOfShortest = getShortestPath(distance, marked);
                for (int j = 0; j < intersections; j++) {
                    if (((graph[indexOfShortest][j] + distance[indexOfShortest]) < distance[j]) && (!marked[j])) {
                        distance[j] = (graph[indexOfShortest][j] + distance[indexOfShortest]);
                        queueSize++;
                        reached[j] = true;
                    }
                }

                marked[indexOfShortest] = true;
                queueSize--;
            }

            double max = getHighestValue(distance);
            if (max == Double.POSITIVE_INFINITY) {
                return -1;
            }
            if (max > currentMaxDist) {
                currentMaxDist = max;
            }
        }
        return currentMaxDist;
    }

    private double getHighestValue(double[] distance) {
        double highest = 0;
        for (int i = 0; i < distance.length; i++)
            highest = (distance[i] > highest) ? distance[i] : highest;
        return highest;
    }

    private int getShortestPath(double[] distance, boolean[] marked) {
        int shortest = 0;
        for (int i = 1; i < distance.length; i++)
            if ((distance[i] < distance[shortest] && !marked[i]) || marked[shortest]) {
                shortest = i;
            }
        return shortest;
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can
     *         meet
     */
    public int timeRequiredforCompetition() {
        if (!valid || this.intersections == 0
                || ((sA > 100 || sA < 50) || (sB > 100 || sB < 50) || (sC > 100 || sC < 50)))
            return -1;
        int slowestSpeed = 0;
        if (this.sA < this.sB && this.sA < this.sC)
            slowestSpeed = sA;
        else if (this.sB < this.sA && this.sB < this.sC)
            slowestSpeed = this.sB;
        else
            slowestSpeed = this.sC;

        double[][] dist = new double[this.intersections][this.intersections];

        for (int i = 0; i < this.intersections; i++) {
            for (int j = 0; j < this.intersections; j++) {
                dist[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        graph = buildGraph();
        double maxDistance = dijkstra();

        if (maxDistance == -1)
            return -1;
        else
            return (int) ((Math.ceil((maxDistance * 1000) / slowestSpeed)));
    }

}
