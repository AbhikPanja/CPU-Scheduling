// File: RoundRobin.java
import java.util.*;

public class RoundRobin {
    public static void runRoundRobin(List<Process> processes, int timeQuantum) {
        Queue<Process> queue = new LinkedList<>();
        List<Process> remainingProcesses = new ArrayList<>(processes);
        int currentTime = 0;
        int totalWaitingTime = 0;
        int totalTurnaroundTime = 0;

        System.out.println("\nExecuting Round Robin Scheduling...");

        // Sort processes by Arrival Time initially
        remainingProcesses.sort(Comparator.comparingInt(p -> p.arrivalTime));

        while (!remainingProcesses.isEmpty() || !queue.isEmpty()) {
            // Add processes that have arrived to the queue
            Iterator<Process> iterator = remainingProcesses.iterator();
            while (iterator.hasNext()) {
                Process process = iterator.next();
                if (process.arrivalTime <= currentTime) {
                    queue.add(process);
                    iterator.remove();
                }
            }

            if (!queue.isEmpty()) {
                Process currentProcess = queue.poll();

                // Execute process for the time quantum or remaining burst time
                int executionTime = Math.min(currentProcess.burstTime, timeQuantum);
                currentProcess.burstTime -= executionTime;
                currentTime += executionTime;

                // If process is not yet completed, add it back to the queue
                if (currentProcess.burstTime > 0) {
                    queue.add(currentProcess);
                } else {
                    // Calculate Turnaround Time and Waiting Time
                    currentProcess.turnaroundTime = currentTime - currentProcess.arrivalTime;
                    currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;

                    // Accumulate totals
                    totalWaitingTime += currentProcess.waitingTime;
                    totalTurnaroundTime += currentProcess.turnaroundTime;
                }
            } else {
                // If no process is ready, advance time++
                currentTime++;
            }
        }

        // Print results
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
