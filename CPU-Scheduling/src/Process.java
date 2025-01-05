public class Process {
    int processId;
    int arrivalTime;
    int burstTime;
    int priority;

    // Additional attributes for scheduling algorithms
    int waitingTime = 0;
    int turnaroundTime = 0;
    int remainingTime = 0; // For algorithms like Round Robin(inititally)

    // Constructor
    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime; // Initialize remaining time to burst time
    }
}