//Main Entry
import java.util.*;

public class CPUScheduler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the CPU Scheduling Simulator!");
        System.out.println("Choose Input Mode:");
        System.out.println("1. Predefined Input");
        System.out.println("2. Custom Input");

        int inputMode = scanner.nextInt();
        List<Process> processes;

        if (inputMode == 1) {
            // Predefined Input
            processes = Arrays.asList(
                new Process(1, 0, 5, 3),
                new Process(2, 1, 3, 1),
                new Process(3, 2, 8, 2),
                new Process(4, 3, 6, 4)
            );
            System.out.println("Using predefined input:");
            System.out.println("ID\tArrival\tBurst\tPriority");
            for (Process p : processes) {
                System.out.printf("%d\t%d\t%d\t%d\n", p.processId, p.arrivalTime, p.burstTime, p.priority);
            }
        } else {
            // Custom Input
            processes = new ArrayList<>();
            System.out.println("Enter the number of processes:");
            int n = scanner.nextInt();

            for (int i = 0; i < n; i++) {
                System.out.printf("Enter details for Process %d (ID, Arrival Time, Burst Time, Priority):\n", i + 1);
                int id = scanner.nextInt();
                int arrivalTime = scanner.nextInt();
                int burstTime = scanner.nextInt();
                int priority = scanner.nextInt();
                processes.add(new Process(id, arrivalTime, burstTime, priority));
            }
        }

        System.out.println("\nChoose Scheduling Algorithm:");
        System.out.println("1. First Come First Serve (FCFS)");
        System.out.println("2. Shortest Job First (SJF)");
        System.out.println("3. Priority Scheduling");
        System.out.println("4. Round Robin");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                FCFS.runFCFS(processes);
                break;
            case 2:
                SJF.runSJF(processes);
                break;
            case 3:
                PriorityScheduling.runPriorityScheduling(processes);
                break;
            case 4:
                System.out.println("Enter Time Quantum for Round Robin:");
                int timeQuantum = scanner.nextInt();
                RoundRobin.runRoundRobin(processes, timeQuantum);
                break;
            default:
                System.out.println("Invalid choice. Exiting...");
        }

        scanner.close();
    }
}




//Sample Input ... Anything you can put but it is a sample for reminder :)
//4 Process (Abhik)
// Process 1: Arrival=0, Burst=5, Priority=3
// Process 2: Arrival=1, Burst=3, Priority=1 
// Process 3: Arrival=2, Burst=8, Priority=2
// Process 4: Arrival=3, Burst=6, Priority=4