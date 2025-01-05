// File: FCFS.java
import java.util.*;

public class FCFS {
    public static void runFCFS(List<Process> processes) {
        // Sort processes by Arrival Time
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));

        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\nExecuting FCFS Scheduling...");

        for (Process process : processes) {
            // If CPU is idle, advance time to the process's arrival time
            if (currentTime < process.arrivalTime) {
                currentTime = process.arrivalTime;
            }

            // Calculate Waiting Time and Turnaround Time
            process.waitingTime = currentTime - process.arrivalTime;
            process.turnaroundTime = process.waitingTime + process.burstTime;

            // Advance the current time by the process's burst time
            currentTime += process.burstTime;

            // Accumulate totals
            totalWaitingTime += process.waitingTime;
            totalTurnaroundTime += process.turnaroundTime;
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
