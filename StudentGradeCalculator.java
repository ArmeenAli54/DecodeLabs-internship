import java.util.Scanner;

public class StudentGradeCalculator {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            // Student Info
            System.out.print("Enter Student Name  : ");
            String name = sc.nextLine();

            System.out.print("Enter Roll Number   : ");
            String rollNo = sc.nextLine();

            // Number of Subjects
            System.out.print("Enter the number of subjects: ");
            int subjects = sc.nextInt();

            while (subjects <= 0) {
                System.out.print("Invalid! Enter a positive number of subjects: ");
                subjects = sc.nextInt();
            }

            sc.nextLine(); // consume leftover newline

            String[] subjectNames = new String[subjects];
            int[] subjectMarks = new int[subjects];
            int totalMarks = 0;

            // First enter ALL subject names
            System.out.println("\nEnter names for all " + subjects + " subjects:");
            System.out.println("----------------------------------------");
            for (int i = 1; i <= subjects; i++) {
                System.out.print("Subject " + i + " Name : ");
                subjectNames[i - 1] = sc.nextLine();
            }

            // Then enter marks for each subject
            System.out.println("\nEnter marks for all " + subjects + " subjects:");
            System.out.println("----------------------------------------");
            for (int i = 0; i < subjects; i++) {
                System.out.print("Enter marks for " + subjectNames[i] + " (out of 100): ");
                int marks = sc.nextInt();

                while (marks < 0 || marks > 100) {
                    System.out.print("Invalid marks! Enter marks between 0 and 100: ");
                    marks = sc.nextInt();
                }

                subjectMarks[i] = marks;
                totalMarks += marks;
            }

            double percentage = (double) totalMarks / subjects;

            String grade;

            if (percentage >= 90) {
                grade = "A+";
            } else if (percentage >= 80) {
                grade = "A";
            } else if (percentage >= 70) {
                grade = "B";
            } else if (percentage >= 60) {
                grade = "C";
            } else if (percentage >= 50) {
                grade = "D";
            } else {
                grade = "F";
            }

            // Result Output
            System.out.println("\n========================================");
            System.out.println("           STUDENT RESULT CARD          ");
            System.out.println("========================================");
            System.out.println("Student Name  : " + name);
            System.out.println("Roll Number   : " + rollNo);
            System.out.println("----------------------------------------");
            System.out.println("Subject wise Marks:");
            System.out.println("----------------------------------------");

            for (int i = 0; i < subjects; i++) {
                System.out.printf("%-20s : %d/100%n", subjectNames[i], subjectMarks[i]);
            }

            System.out.println("----------------------------------------");
            System.out.println("Total Marks   : " + totalMarks + "/" + (subjects * 100));
            System.out.printf("Percentage    : %.2f%%%n", percentage);
            System.out.println("Grade         : " + grade);

            if (percentage >= 50) {
                System.out.println("Status        : PASS ");
            } else {
                System.out.println("Status        : FAIL ");
            }

            System.out.println("========================================");
        }
    }
}