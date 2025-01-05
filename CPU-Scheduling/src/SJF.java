// File: SJF.java
import java.util.*;

public class SJF {
    public static void runSJF(List<Process> processes) {
        // Sort processes by Arrival Time initially
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int completed = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\nExecuting SJF Scheduling...");

        while (completed < processes.size()) {
            // Find the process with the shortest burst time among processes that have arrived
            Process shortest = null;
            for (Process process : processes) {
                if (process.arrivalTime <= currentTime && process.turnaroundTime == 0) {
                    if (shortest == null || process.burstTime < shortest.burstTime) {
                        shortest = process;
                    }
                }
            }

            if (shortest != null) {
                // Calculate Waiting Time and Turnaround Time for the selected process
                shortest.waitingTime = currentTime - shortest.arrivalTime;
                shortest.turnaroundTime = shortest.waitingTime + shortest.burstTime;

                // Advance current time by the burst time of the selected process
                currentTime += shortest.burstTime;
                completed++;

                // Accumulate totals
                totalWaitingTime += shortest.waitingTime;
                totalTurnaroundTime += shortest.turnaroundTime;
            } else {
                // Without Process expand the time
                currentTime++;
            }
        }

        // Print
        printResults(processes, totalWaitingTime, totalTurnaroundTime);
    }

    private static void printResults(List<Process> processes, int totalWaitingTime, int totalTurnaroundTime) {
        System.out.println("\nProcess Execution Results:");
        System.out.println("ID\tArrival\tBurst\tWaiting\tTurnaround");

        for (Process process : processes) {
            System.out.printf("%d\t%d\t\t%d\t\t%d\t\t%d\n", process.processId, process.arrivalTime, process.burstTime, process.waitingTime, process.turnaroundTime);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", (double) totalWaitingTime / processes.size());
        System.out.printf("Average Turnaround Time: %.2f\n", (double) totalTurnaroundTime / processes.size());
    }
}
