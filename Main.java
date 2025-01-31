import java.util.*;

class Student
{
    private String name;
    private String group;
    private int course;
    private List<Integer> grades;

    public Student(String name, String group, int course, List<Integer> grades)
    {
        this.name = name;
        this.group = group;
        this.course = course;
        this.grades = grades;
    }

    public String getName()
    {
        return name;
    }

    public int getCourse()
    {
        return course;
    }

    public double getAverageGrade()
    {
        return grades.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    public void nextCourse()
    {
        this.course++;
    }

    @Override
    public String toString()
    {
        return name + " (Курс: " + course + ", Средний балл: " + getAverageGrade() + ")";
    }
}

class StudentManagement
{
    public static void removeLowGradeStudents(List<Student> students)
    {
        students.removeIf(student ->
        {
            if (student.getAverageGrade() < 3)
            {
                return true;
            }
            else
            {
                student.nextCourse();
                return false;
            }
        });
    }

    public static void printStudents(List<Student> students, int course)
    {
        students.stream()
                .filter(student -> student.getCourse() == course)
                .map(Student::getName)
                .forEach(System.out::println);
    }

    public static void main(String[] args)
    {
        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Иван Иванов", "Группа A", 1, Arrays.asList(4, 5, 3, 2)),
                new Student("Мария Смирнова", "Группа B", 2, Arrays.asList(5, 5, 4, 5)),
                new Student("Петр Петров", "Группа A", 1, Arrays.asList(2, 2, 3, 1)),
                new Student("Анна Козлова", "Группа C", 3, Arrays.asList(3, 4, 3, 4))
        ));

        System.out.println("Студенты до обработки:");
        students.forEach(System.out::println);

        removeLowGradeStudents(students);

        System.out.println("\nСтуденты после обработки:");
        students.forEach(System.out::println);

        System.out.println("\nСтуденты на 2 курсе:");
        printStudents(students, 2);
    }
}
