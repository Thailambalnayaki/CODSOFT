import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    String name;
    int rollNumber;
    String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
}

class StudentManagementSystem {
    List<Student> students = new ArrayList<>();

    void addStudent(Student student) {
        students.add(student);
    }

    void removeStudent(int rollNumber) {
        students.removeIf(student -> student.rollNumber == rollNumber);
    }

    Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.rollNumber == rollNumber) {
                return student;
            }
        }
        return null;
    }

    void displayAllStudents() {
        for (Student student : students) {
            System.out.println("Name: " + student.name + ", Roll Number: " + student.rollNumber + ", Grade: " + student.grade);
        }
    }

    void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.name + "," + student.rollNumber + "," + student.grade);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    students.add(new Student(parts[0], Integer.parseInt(parts[1]), parts[2]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

public class StudentManagementSystemApp {

    public static void main(String[] args) {
        StudentManagementSystem studentManagementSystem = new StudentManagementSystem();

        // Load existing data from file
        studentManagementSystem.loadFromFile("student_data.txt");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save and exit");

            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    String name = getStringInput("Enter the student's name: ");
                    int rollNumber = getIntegerInput("Enter the student's roll number: ");
                    String grade = getStringInput("Enter the student's grade: ");
                    Student newStudent = new Student(name, rollNumber, grade);
                    studentManagementSystem.addStudent(newStudent);
                    System.out.println("Student added successfully.");
                    break;
                case 2:
                    int removeRollNumber = getIntegerInput("Enter the roll number of the student to remove: ");
                    studentManagementSystem.removeStudent(removeRollNumber);
                    System.out.println("Student removed successfully.");
                    break;
                case 3:
                    int searchRollNumber = getIntegerInput("Enter the roll number of the student to search: ");
                    Student foundStudent = studentManagementSystem.searchStudent(searchRollNumber);
                    if (foundStudent != null) {
                        System.out.println("Student found - Name: " + foundStudent.name + ", Roll Number: "
                                + foundStudent.rollNumber + ", Grade: " + foundStudent.grade);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    studentManagementSystem.displayAllStudents();
                    break;
                case 5:
                    // Save data to file before exiting
                    studentManagementSystem.saveToFile("student_data.txt");
                    System.out.println("Data saved. Exiting the program.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static int getIntegerInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
        return input;
    }

    private static String getStringInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
