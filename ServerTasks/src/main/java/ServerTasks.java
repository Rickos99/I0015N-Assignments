import java.util.Arrays;
import java.util.Scanner;

public class ServerTasks {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        var serverInformation = sc.nextLine();
        var tasks = sc.nextLine();

        var solution = solveProblem(serverInformation, tasks);
        System.out.println(solution);
    }

    static int solveProblem(String rawServerInfo, String rawTasks){
        var serverTime = Integer.parseInt(rawServerInfo.split(" ")[1]);
        var tasks = rawTasks.split(" ");
        var tasksTime = stringsAsInts(tasks);

        return getNumberOfTasksDone(serverTime, tasksTime);
    }

    static int[] stringsAsInts(String[] strings) {
        return Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
    }

     static int getNumberOfTasksDone(int serverTime, int[] tasks) {
        var tasksTime = 0;
        var tasksDone = 0;
        for (int task : tasks) {
            if (tasksTime + task > serverTime) break;
            tasksTime += task;
            tasksDone++;
        }
        return tasksDone;
    }
}
