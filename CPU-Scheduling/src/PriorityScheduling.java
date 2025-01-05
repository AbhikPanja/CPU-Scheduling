// PriorityScheduling.java
import java.util.*;

public class PriorityScheduling {
    public static void runPriorityScheduling(List<Process> processes) {
        // Sort processes by Arrival Time initially
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int completed = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\nExecuting Priority Scheduling...");

        while (completed < processes.size()) {
            // Find the process with the highest priority among processes that have arrived
            Process highestPriority = null;
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.turnaroundTime == 0) {
                    if (highestPriority == null || process.priority < highestPriority.priority) {
                        highestPriority = process;
                    }
                }
            }

            if (highestPriority != null) {
                // Calculate Waiting Time and Turnaround Time for the selected process
                highestPriority.waitingTime = currentTime - highestPriority.arrivalTime;
                highestPriority.turnaroundTime = highestPriority.waitingTime + highestPriority.burstTime;

                // Advance current time by the burst time of the selected process
                currentTime += highestPriority.burstTime;
                completed++;

                // Accumulate totals
                totalWaitingTime += highestPriority.waitingTime;
                totalTurnaroundTime += highestPriority.turnaroundTime;
            } else {
                // If no process is available, advance time+
                currentTime++;
            }
        }

        // Print results
        printResults(processes, totalWaitingTime, totalTurnaroundTime);
    }

    private static void printResults(List<Process> processes, int totalWaitingTime, int totalTurnaroundTime) {
        System.out.println("\nProcess Execution Results:");
        System.out.println("ID\tArrival\tBurst\tPriority\tWaiting\tTurnaround");

        for (Process process : processes) {
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t%d\t\t%d\n", process.processId, process.arrivalTime, process.burstTime, process.priority, process.waitingTime, process.turnaroundTime);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", (double) totalWaitingTime / processes.size());
        System.out.printf("Average Turnaround Time: %.2f\n", (double) totalTurnaroundTime / processes.size());
    }
}
