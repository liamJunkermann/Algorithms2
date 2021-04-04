import static org.junit.Assert.*;

import org.junit.Test;

public class CompetitionTests {
    String valid = "tinyEWD.txt";
    String invalid = "empty.txt";

    @Test
    public void testDijkstraConstructor() {
        int result;

        result = new CompetitionDijkstra("", 50, 50, 50).timeRequiredforCompetition();
        assertEquals("Invalid, no filename", result, -1);

        result = new CompetitionDijkstra(valid, 20, 60, 70).timeRequiredforCompetition();
        assertEquals("Invalid, a speed too slow", -1, result);

        result = new CompetitionDijkstra(valid, 60, 20, 70).timeRequiredforCompetition();
        assertEquals("Invalid, b speed too slow", -1, result);

        result = new CompetitionDijkstra(valid, 60, 70, 20).timeRequiredforCompetition();
        assertEquals("Invalid, c speed too slow", -1, result);

        result = new CompetitionDijkstra(valid, 120, 60, 70).timeRequiredforCompetition();
        assertEquals("Invalid, a speed too fast", -1, result);

        result = new CompetitionDijkstra(valid, 60, 120, 70).timeRequiredforCompetition();
        assertEquals("Invalid, b speed too fast", -1, result);

        result = new CompetitionDijkstra(valid, 60, 70, 120).timeRequiredforCompetition();
        assertEquals("Invalid, c speed too fast", -1, result);

        result = new CompetitionDijkstra(invalid, 60, 60, 50).timeRequiredforCompetition();
        assertEquals("Invalid, no intersections or streets", -1, result);

        result = new CompetitionDijkstra(valid, 50, 60, 70).timeRequiredforCompetition();
        assertEquals("Valid, a as slowest", 38, result);

        result = new CompetitionDijkstra(valid, 60, 50, 70).timeRequiredforCompetition();
        assertEquals("Valid, b as slowest", 38, result);

        result = new CompetitionDijkstra(valid, 60, 70, 50).timeRequiredforCompetition();
        assertEquals("valid, c as slowest", 38, result);
    }

    @Test
    public void testFWConstructor() {
        int result;

        result = new CompetitionFloydWarshall("", 50, 50, 50).timeRequiredforCompetition();
        assertEquals("Invalid, no filename", result, -1);

        result = new CompetitionFloydWarshall(valid, 20, 60, 70).timeRequiredforCompetition();
        assertEquals("Invalid, a speed too slow", -1, result);

        result = new CompetitionFloydWarshall(valid, 60, 20, 70).timeRequiredforCompetition();
        assertEquals("Invalid, b speed too slow", -1, result);

        result = new CompetitionFloydWarshall(valid, 60, 70, 20).timeRequiredforCompetition();
        assertEquals("Invalid, c speed too slow", -1, result);

        result = new CompetitionFloydWarshall(valid, 120, 60, 70).timeRequiredforCompetition();
        assertEquals("Invalid, a speed too fast", -1, result);

        result = new CompetitionFloydWarshall(valid, 60, 120, 70).timeRequiredforCompetition();
        assertEquals("Invalid, b speed too fast", -1, result);

        result = new CompetitionFloydWarshall(valid, 60, 70, 120).timeRequiredforCompetition();
        assertEquals("Invalid, c speed too fast", -1, result);

        result = new CompetitionFloydWarshall(invalid, 60, 60, 50).timeRequiredforCompetition();
        assertEquals("Invalid, no intersections or streets", -1, result);

        result = new CompetitionFloydWarshall(valid, 50, 60, 70).timeRequiredforCompetition();
        assertEquals("Valid, a as slowest", 38, result);

        result = new CompetitionFloydWarshall(valid, 60, 50, 70).timeRequiredforCompetition();
        assertEquals("Valid, b as slowest", 38, result);

        result = new CompetitionFloydWarshall(valid, 60, 70, 50).timeRequiredforCompetition();
        assertEquals("valid, c as slowest", 38, result);
    }
}
