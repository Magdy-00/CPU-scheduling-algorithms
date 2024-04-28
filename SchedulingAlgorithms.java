import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math.*;
import java.lang.reflect.Array;

public class SchedulingAlgorithms {

    static int sum;
    static int k = 0;
    static Double averageWaitingTime1 = 0.0;
    static Double averageTurnAroundTime1 = 0.0;

    public static void FCFS(int size) {
        Scanner in = new Scanner(System.in);
        int brustTime[] = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Enter the Brust Time for the process: ");

            if (brustTime[i] < 0) {
                System.out.println("please enter a psotive brust Time");
                break;
            }

            brustTime[i] = in.nextInt();
        }
        for (int i = 0; i < size; i++) {
            // so out of index -1 error wont occur
            if (i == 0) {
                System.out.print(k + "<---->" + brustTime[i] + " ");
            }
            // to add the brustTime[i] to the brustTime[i+1]
            else {
                // to make the next Brust Time the sym of the previous elements
                for (int j = 0; j <= i; j++) {
                    sum += brustTime[j];
                }
                System.out.print(k + "<---->" + sum + " ");
                // return the sum back to zero cause there was an error the for loop kept adding
                // the array more than it should
                sum = 0;

            }
            averageWaitingTime1 += k;
            k += brustTime[i];
            averageTurnAroundTime1 = averageTurnAroundTime1 + k;
        }

        System.out.println();
        averageTurnAroundTime1 /= size;
        averageWaitingTime1 /= size;
        System.out.println("The average waiting time is " + averageWaitingTime1);
        System.out.println("The average trun around time is " + averageTurnAroundTime1);
        in.close();

        // make every thing equals zero to be used agian while the program is runnung
        sum = 0;
        k = 0;
        averageTurnAroundTime1 = 0.0;
        averageWaitingTime1 = 0.0;
    }

    public static void SJF(int size) {
        Scanner in = new Scanner(System.in);
        int brustTime[] = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Enter the Brust Time for the process: ");
            if (brustTime[i] < 0) {
                System.out.println("please enter a postive brustTime");
                break;
            }
            brustTime[i] = in.nextInt();
        }

        Arrays.sort(brustTime);
        for (int i = 0; i < size; i++) {
            // so out of index -1 error wont occur
            if (i == 0) {
                System.out.print(k + "<---->" + brustTime[i] + " ");
            }
            // to add the brustTime[i] to the brustTime[i+1]
            else {
                // to make the next Brust Time the sym of the previous elements
                for (int j = 0; j <= i; j++) {
                    sum += brustTime[j];
                }
                System.out.print(k + "<---->" + sum + " ");
                // return the sum back to zero cause there was an error the for loop kept adding
                // the array more than it should
                sum = 0;

            }
            averageWaitingTime1 += k;
            k += brustTime[i];
            averageTurnAroundTime1 = averageTurnAroundTime1 + k;
        }
        System.out.println();
        averageTurnAroundTime1 /= size;
        averageWaitingTime1 /= size;
        System.out.println("The average waiting time is " + averageWaitingTime1);
        System.out.println("The average trun around time is " + averageTurnAroundTime1);

        // make every thing equals zero to be used agian while the program is runnung
        sum = 0;
        k = 0;
        averageTurnAroundTime1 = 0.0;
        averageWaitingTime1 = 0.0;

        in.close();
    }

    public static void prioritySch(int size) {
        Scanner in = new Scanner(System.in);
        int[] burstTime = new int[size];
        int[] priority = new int[size];
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        // Input burst times and priorities
        for (int i = 0; i < size; i++) {
            System.out.println("Enter the burst time of process : ");
            burstTime[i] = in.nextInt();
            if (burstTime[i] < 0) {
                System.out.println("Please enter a positive number ");
                break;
            }
            System.out.println("Enter its priority: ");
            priority[i] = in.nextInt();
            if (priority[i] < 0) {
                System.out.println("Please enter a positive number ");
                break;
            }
        }

        // Sort processes based on priority (higher value means higher priority)
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (priority[j] > priority[j + 1]) {
                    int temp = priority[j];
                    priority[j] = priority[j + 1];
                    priority[j + 1] = temp;

                    temp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp;
                }
            }
        }

        // Calculate waiting time and turnaround time
        double[] waitingTime = new double[size];
        double[] turnaroundTime = new double[size];

        turnaroundTime[0] = burstTime[0]; // Turnaround time of first process is its burst time
        waitingTime[0] = 0; // Waiting time of first process is 0

        for (int i = 1; i < size; i++) {
            waitingTime[i] = turnaroundTime[i - 1]; // Waiting time is the turnaround time of previous process
            totalWaitingTime += waitingTime[i]; // Accumulate total waiting time
            turnaroundTime[i] = waitingTime[i] + burstTime[i]; // Calculate turnaround time
            totalTurnaroundTime += turnaroundTime[i]; // Accumulate total turnaround time
        }

        // Calculate averages
        double averageWaitingTime = totalWaitingTime / size;
        double averageTurnaroundTime = totalTurnaroundTime / size;

        // Print results
        System.out.print(0 + "<--->");
        for (int i = 0; i < size; i++) {
            System.out.print(turnaroundTime[i] + "<--->");
        }
        System.out.println();
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);

        in.close();
    }

    public static void RR(int size, int q) {
        Scanner in = new Scanner(System.in);
        int[] burstTime = new int[size];
        for (int i = 0; i < size; i++) {
            System.out.println("Enter the brust time of the process: ");
            burstTime[i] = in.nextInt();
        }

        for (int i = 0; i < size; i++) {
            sum += burstTime[i];
        }

        int remainingTime[] = new int[size];
        for (int i = 0; i < size; i++) {
            remainingTime[i] = burstTime[i];
        }

        // Keep track of the current time
        int currentTime = 0;
        while (true) {
            boolean Flag = true;
            for (int i = 0; i < size; i++) {
                if (remainingTime[i] > 0) { // لحد ما البرست يوصل صفر
                    Flag = false;
                    int processEndTime = currentTime + Math.min(q, remainingTime[i]);// بطرح منها الاصغر مابين الوقت او
                                                                                     // البرست تايم لةاقل من الوقت كيو

                    System.out.print(currentTime + "<---> " + processEndTime + " ");

                    remainingTime[i] -= Math.min(q, remainingTime[i]);// نفس الكلام هنا

                    currentTime = processEndTime;
                }
            }

            if (Flag) {
                break;
            }
        }

        in.close();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println(
                "R for Round Robin(RR), P for Priority Scheduling, S for Shortest-Job-First(SJF), and F for First-Come-First-Serve(FCFS): ");
        System.out.print("Enter which Scheduling Algorithm you want to use: ");

        char op = in.nextLine().charAt(0);
        switch (Character.toUpperCase(op)) {
            case 'R':

                System.out.print("Enter the number of process and the time quantum in that order: ");
                RR(in.nextInt(), in.nextInt());
                break;

            case 'P':

                System.out.print("Enter the number of process: ");
                prioritySch(in.nextInt());
                break;

            case 'S':

                System.out.print("Enter the number of process: ");
                SJF(in.nextInt());
                break;

            case 'F':

                System.out.print("Enter the number of process: ");
                FCFS(in.nextInt());
                break;

            default:

                System.out.println("Choose from the given operators ");
                break;

        }

        in.close();
    }

}
