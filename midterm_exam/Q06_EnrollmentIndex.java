package midterm_exam;

public class Q06_EnrollmentIndex {
    private final java.util.Map<String, java.util.Set<String>> enrollmentMapR26 = new java.util.HashMap<>();

    public boolean enroll(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        enrollmentMapR26.putIfAbsent(courseCode, new java.util.HashSet<>());
        return enrollmentMapR26.get(courseCode).add(studentId);
    }

    public boolean drop(String courseCode, String studentId) {
        if (courseCode == null || courseCode.trim().isEmpty() || studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        java.util.Set<String> students = enrollmentMapR26.get(courseCode);
        if (students != null) {
            boolean removed = students.remove(studentId);
            if (students.isEmpty()) {
                enrollmentMapR26.remove(courseCode);
            }
            return removed;
        }
        return false;
    }

    public int courseSize(String courseCode) {
        java.util.Set<String> students = enrollmentMapR26.get(courseCode);
        return students != null ? students.size() : 0;
    }

    public java.util.List<String> studentsOf(String courseCode) {
        java.util.Set<String> students = enrollmentMapR26.get(courseCode);
        if (students == null) return new java.util.ArrayList<>();
        java.util.List<String> list = new java.util.ArrayList<>(students);
        java.util.Collections.sort(list);
        return list;
    }

    public java.util.List<String> coursesOf(String studentId) {
        java.util.List<String> courses = new java.util.ArrayList<>();
        for (java.util.Map.Entry<String, java.util.Set<String>> entry : enrollmentMapR26.entrySet()) {
            if (entry.getValue().contains(studentId)) {
                courses.add(entry.getKey());
            }
        }
        java.util.Collections.sort(courses);
        return courses;
    }

    public java.util.Map<String, Integer> summary() {
        java.util.Map<String, Integer> map = new java.util.TreeMap<>();
        for (java.util.Map.Entry<String, java.util.Set<String>> entry : enrollmentMapR26.entrySet()) {
            map.put(entry.getKey(), entry.getValue().size());
        }
        return map;
    }
}