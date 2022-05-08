import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ServerTasksTest {

    @DataProvider
    public Object[][] parsedTasks() {
        return new Object[][]{
                {180, new int[]{45, 30, 55, 20, 80, 20}, 4},
                {60, new int[]{20, 7, 10, 8, 10, 27, 2, 3, 10, 5}, 5}
        };
    }

    @DataProvider
    public Object[][] stringArrays() {
        return new Object[][]{
                {new String[]{"45", "30", "55", "20", "80", "20"}, new int[]{45, 30, 55, 20, 80, 20}},
                {new String[]{"45", "2", "-8", "0"}, new int[]{45, 2, -8, 0}}
        };
    }

    @DataProvider
    public Object[][] rawTaskInput() {
        return new Object[][]{
                {"6 180", "45 30 55 20 80 20", 4},
                {"10 60", "20 7 10 8 10 27 2 3 10 5", 5}
        };
    }

    @Test(groups = {"Main"}, dataProvider = "rawTaskInput", dependsOnGroups = {"Helper"})
    public void problemSolver(String rawServerInfo, String rawTasks, int expected){
        var actual = ServerTasks.solveProblem(rawServerInfo, rawTasks);
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = {"Helper"}, dataProvider = "stringArrays")
    public void stringsAsInts(String[] strings, int[] expected) {
        var actual = ServerTasks.stringsAsInts(strings);
        Assert.assertEquals(actual, expected);
    }

    @Test(groups = {"Helper"}, dataProvider = "parsedTasks")
    public void getNumberOfTasksDone(int serverTime, int[] tasks, int expected) {
        var actual = ServerTasks.getNumberOfTasksDone(serverTime, tasks);
        Assert.assertEquals(expected, actual);
    }
}
