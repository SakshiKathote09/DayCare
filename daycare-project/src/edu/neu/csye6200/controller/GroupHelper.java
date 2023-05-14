package edu.neu.csye6200.controller;

/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import edu.neu.csye6200.dao.ClassGroupDaoImpl;
import edu.neu.csye6200.factory.ClassroomFactory;
import edu.neu.csye6200.factory.GroupFactory;
import edu.neu.csye6200.model.Classroom;
import edu.neu.csye6200.model.DayCare;
import edu.neu.csye6200.model.Group;
import edu.neu.csye6200.model.Student;
import edu.neu.csye6200.model.Teacher;
import java.time.LocalDate;

/**
 * @author sid
 *
 */
public class GroupHelper {

	static int currentTeacherIndexFlag = 0;
	static String studentfile = "students.txt";
	static String teachersfile = "teachers.txt";
	private static List<Student> students = new ArrayList<>();
	private static List<Teacher> teachers = new ArrayList<>();

	public static List<Classroom> groupMe() throws Exception {

		StudentService studentService = new StudentService();
		TeacherService teacherService = new TeacherService();

		//List<String> tempStudents = FileUtil.readTextFile(studentfile);
		// tempStudents.forEach(student -> students.add(new Student(student)));
		try {
			students = studentService.getAllStudents();
			teachers = teacherService.getAllTeachers();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		tempStudents.forEach(student -> students.add(StudentFactory.getInstance().getObject(student)));
//
//		students.forEach(student -> {
//			try {
//				studentService.registerStudent(student);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//
//		List<String> tempTeachers = FileUtil.readTextFile(teachersfile);
//		// tempTeachers.forEach(teacher -> teachers.add(new Teacher(teacher)));
//
//		tempTeachers.forEach(teacher -> teachers.add(TeacherFactory.getInstance().getObject(teacher)));
//		teachers.forEach(teacher -> {
//			try {
//				int teacherId = teacherService.registerTeacher(teacher);
//				teacher.setEmployeeId(teacherId);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});

		List<Student> sixToTwelve = students.stream().filter(student -> student.getAge() >= 6 && student.getAge() <= 12)
				.collect(Collectors.toList());

		List<Student> thirteenToTwentyfour = students.stream()
				.filter(student -> student.getAge() >= 13 && student.getAge() <= 24).collect(Collectors.toList());

		List<Student> twentyfiveToThirtyFive = students.stream()
				.filter(student -> student.getAge() >= 25 && student.getAge() <= 35).collect(Collectors.toList());

		List<Student> thirtySixToFortySeven = students.stream()
				.filter(student -> student.getAge() >= 36 && student.getAge() <= 47).collect(Collectors.toList());

		List<Student> fortyEightToFiftyNine = students.stream()
				.filter(student -> student.getAge() >= 48 && student.getAge() <= 59).collect(Collectors.toList());
		List<Student> sixtyAndUp = students.stream().filter(student -> student.getAge() >= 60)
				.collect(Collectors.toList());

                
//                Student s1 = new Student(1, "Uday", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s2 = new Student(2, "Nio", "Pinto", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s3 = new Student(3, "Uday3", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s4 = new Student(4, "Uday4", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s5 = new Student(5, "Uday5", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s6 = new Student(6, "Uday6", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s7 = new Student(7, "Uday7", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s8 = new Student(8, "Uday8", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s9 = new Student(9, "Uday9", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                Student s10 = new Student(10, "Uday10", "Sid", LocalDate.MIN, LocalDate.MIN, 12, "Boston", 0);
//                
//                Student s11 = new Student(11, "Uday11", "Sid", LocalDate.MIN, LocalDate.MIN, 18, "Boston", 0);
//                Student s12 = new Student(12, "Uday12", "Sid", LocalDate.MIN, LocalDate.MIN, 18, "Boston", 0);
//                Student s13 = new Student(13, "Uday13", "Sid", LocalDate.MIN, LocalDate.MIN, 18, "Boston", 0);
//                Student s14 = new Student(14, "Uday14", "Sid", LocalDate.MIN, LocalDate.MIN, 18, "Boston", 0);
//                Student s15 = new Student(15, "Uday15", "Sid", LocalDate.MIN, LocalDate.MIN, 18, "Boston", 0);
//                Student s16 = new Student(16, "Uday16", "Sid", LocalDate.MIN, LocalDate.MIN, 18, "Boston", 0);
//                Student s17 = new Student(17, "Uday17", "Sid", LocalDate.MIN, LocalDate.MIN, 27, "Boston", 0);
//                Student s18 = new Student(18, "Uday18", "Sid", LocalDate.MIN, LocalDate.MIN, 27, "Boston", 0);
//                Student s19 = new Student(19, "Uday19", "Sid", LocalDate.MIN, LocalDate.MIN, 27, "Boston", 0);
//                Student s20 = new Student(20, "Uday20", "Sid", LocalDate.MIN, LocalDate.MIN, 27, "Boston", 0);
//                
//                
//                sixToTwelve.add(s1);
//                sixToTwelve.add(s2);
//                sixToTwelve.add(s3);
//                sixToTwelve.add(s4);
//                sixToTwelve.add(s5);
//                sixToTwelve.add(s6);
//                sixToTwelve.add(s7);
//                sixToTwelve.add(s8);
//                sixToTwelve.add(s9);
//                sixToTwelve.add(s10);
//                thirteenToTwentyfour.add(s11);
//                thirteenToTwentyfour.add(s12);
//                thirteenToTwentyfour.add(s13);
//                thirteenToTwentyfour.add(s14);
//                thirteenToTwentyfour.add(s15);
//                thirteenToTwentyfour.add(s16);
//                twentyfiveToThirtyFive.add(s17);
//                twentyfiveToThirtyFive.add(s18);
//                twentyfiveToThirtyFive.add(s19);
//                twentyfiveToThirtyFive.add(s20);
//           
//                Teacher e = new Teacher(1, "Panda", "Panda", "u@g.com", LocalDate.MIN);
//                 Teacher e1 = new Teacher(2, "Uday", "Sid", "u@g.com", LocalDate.MIN);
//                  Teacher e2 = new Teacher(3, "Uday2", "Sid1", "u@g.com", LocalDate.MIN);
//                   Teacher e3 = new Teacher(4, "Uday3", "Sid1", "u@g.com", LocalDate.MIN);
//                    Teacher e4 = new Teacher(5, "Uday4", "Sid1", "u@g.com", LocalDate.MIN);
//                     Teacher e5 = new Teacher(6, "Uday5", "Sid1", "u@g.com", LocalDate.MIN);
//                
//                teachers.add(e);
//                teachers.add(e1);
//                     teachers.add(e2);
//                     teachers.add(e3);
//                     teachers.add(e4);
//                     teachers.add(e5);
                     
                     
                 List<List<Student>> segmentsSixToTwelve = new ArrayList<>();
                if(sixToTwelve.size()>0){
                    int segmentSize = 4;
                    for (int i = 0; i < sixToTwelve.size(); i += segmentSize) {
                   int endIndex = Math.min(i + segmentSize, sixToTwelve.size());
                    List<Student> segment = sixToTwelve.subList(i, endIndex);
                 segmentsSixToTwelve.add(segment);
                 }
                }
                
                 List<List<Student>> segmentsThirteenToTwentyfour = new ArrayList<>();
                if(thirteenToTwentyfour.size()>0){
                    int segmentSize = 5;
                    for (int i = 0; i < thirteenToTwentyfour.size(); i += segmentSize) {
                   int endIndex = Math.min(i + segmentSize, thirteenToTwentyfour.size());
                    List<Student> segment = thirteenToTwentyfour.subList(i, endIndex);
                 segmentsThirteenToTwentyfour.add(segment);
                 }
                }
                
                List<List<Student>> segmentsTwentyfiveToThirtyFive = new ArrayList<>();
                if(twentyfiveToThirtyFive.size()>0){
                    int segmentSize = 6;
                    for (int i = 0; i < twentyfiveToThirtyFive.size(); i += segmentSize) {
                   int endIndex = Math.min(i + segmentSize, twentyfiveToThirtyFive.size());
                    List<Student> segment = twentyfiveToThirtyFive.subList(i, endIndex);
                 segmentsTwentyfiveToThirtyFive.add(segment);
                 }
                }
                
                List<List<Student>> segmentsThirtySixToFortySeven = new ArrayList<>();
                if(thirtySixToFortySeven.size()>0){
                    int segmentSize = 8;
                    for (int i = 0; i < thirtySixToFortySeven.size(); i += segmentSize) {
                   int endIndex = Math.min(i + segmentSize, thirtySixToFortySeven.size());
                    List<Student> segment = thirtySixToFortySeven.subList(i, endIndex);
                 segmentsThirtySixToFortySeven.add(segment);
                 }
                }
                
                List<List<Student>> segmentsFortyEightToFiftyNine = new ArrayList<>();
                if(fortyEightToFiftyNine.size()>0){
                    int segmentSize = 12;
                    for (int i = 0; i < fortyEightToFiftyNine.size(); i += segmentSize) {
                   int endIndex = Math.min(i + segmentSize, fortyEightToFiftyNine.size());
                    List<Student> segment = fortyEightToFiftyNine.subList(i, endIndex);
                 segmentsFortyEightToFiftyNine.add(segment);
                 }
                }
                
                List<List<Student>> segmentsSixtyAndUp = new ArrayList<>();
                if(sixtyAndUp.size()>0){
                    int segmentSize = 15;
                    for (int i = 0; i < sixtyAndUp.size(); i += segmentSize) {
                   int endIndex = Math.min(i + segmentSize, sixtyAndUp.size());
                    List<Student> segment = sixtyAndUp.subList(i, endIndex);
                 segmentsSixtyAndUp.add(segment);
                 }
                }
                
                
                
                int classroomCounter = 0, groupCounter = 1,teacherCounter=0;
                List<Classroom> classrooms = new ArrayList<>();
                int indgroupCounter=1;
                if(segmentsSixToTwelve.size()>=1){
                   
                    for(int i=0;i<segmentsSixToTwelve.size();i++){
                         Group g = new Group();
                        if(segmentsSixToTwelve.get(i)!=null){
                            g.setGroupId(groupCounter++);
                            g.setStudents(segmentsSixToTwelve.get(i));
                            g.setTeacher(teachers.get(teacherCounter++));
                            indgroupCounter++;
                        //segmentsSixToTwelve.remove(i);
                        }
                    
                    if(classrooms.size()!=0 && classroomCounter<classrooms.size()){
                        if(classrooms.get(classroomCounter).getGroups().size()!=0){
                        classrooms.get(classroomCounter).getGroups().add(g);
                      }
                    }else{
                        Classroom classroom = new Classroom();
                        List<Group> groups = new ArrayList<>();
                        groups.add(g);
                        classroom.setGroups(g);
                        classroom.setClassId(classroomCounter+1); 
                        classrooms.add(classroom);
                    }
                    if(indgroupCounter>3){
                         classroomCounter++;
                    }
                   
                    }
                }
                
                classroomCounter = 0;
                indgroupCounter=0;
                if(segmentsThirteenToTwentyfour.size()>=1){
                   
                    for(int i=0;i<segmentsThirteenToTwentyfour.size();i++){
                         Group g = new Group();
                        if(segmentsThirteenToTwentyfour.get(i)!=null){
                            g.setGroupId(groupCounter++);
                            g.setStudents(segmentsThirteenToTwentyfour.get(i));
                            g.setTeacher(teachers.get(teacherCounter++));
                            indgroupCounter++;
                        //segmentsSixToTwelve.remove(i);
                        }
                    
                    if(classrooms.size()!=0 && classroomCounter<classrooms.size()){
                        if(classrooms.get(classroomCounter).getGroups().size()!=0){
                        classrooms.get(classroomCounter).getGroups().add(g);
                      }
                    }else{
                        Classroom classroom = new Classroom();
                        List<Group> groups = new ArrayList<>();
                        groups.add(g);
                        classroom.setGroups(g);
                        classroom.setClassId(classroomCounter+1); 
                        classrooms.add(classroom);
                    }
                    if(indgroupCounter>3){
                         classroomCounter++;
                    }
                   
                    }
                }
                
                classroomCounter = 0;indgroupCounter=1;
                if(segmentsTwentyfiveToThirtyFive.size()>=1){
                   
                    for(int i=0;i<segmentsTwentyfiveToThirtyFive.size();i++){
                         Group g = new Group();
                        if(segmentsTwentyfiveToThirtyFive.get(i)!=null){
                            g.setGroupId(groupCounter++);
                            g.setStudents(segmentsTwentyfiveToThirtyFive.get(i));
                            g.setTeacher(teachers.get(teacherCounter++));
                            indgroupCounter++;
                        //segmentsSixToTwelve.remove(i);
                        }
                    
                    if(classrooms.size()!=0 && classroomCounter<classrooms.size()){
                        if(classrooms.get(classroomCounter).getGroups().size()!=0){
                        classrooms.get(classroomCounter).getGroups().add(g);
                      }
                    }else{
                        Classroom classroom = new Classroom();
                        List<Group> groups = new ArrayList<>();
                        groups.add(g);
                        classroom.setGroups(g);
                        classroom.setClassId(classroomCounter+1); 
                        classrooms.add(classroom);
                    }
                    if(indgroupCounter>3){
                         classroomCounter++;
                    }
                   
                    }
                }
                
                classroomCounter = 0;indgroupCounter=0;
                if(segmentsThirtySixToFortySeven.size()>=1){
                    
                    for(int i=0;i<segmentsThirtySixToFortySeven.size();i++){
                         Group g = new Group();
                        if(segmentsThirtySixToFortySeven.get(i)!=null){
                            g.setGroupId(groupCounter++);
                            g.setStudents(segmentsThirtySixToFortySeven.get(i));
                            g.setTeacher(teachers.get(teacherCounter++));
                            indgroupCounter++;
                        //segmentsSixToTwelve.remove(i);
                        }
                    
                    if(classrooms.size()!=0 && classroomCounter<classrooms.size()){
                        if(classrooms.get(classroomCounter).getGroups().size()!=0){
                        classrooms.get(classroomCounter).getGroups().add(g);
                      }
                    }else{
                        Classroom classroom = new Classroom();
                        List<Group> groups = new ArrayList<>();
                        groups.add(g);
                        classroom.setGroups(g);
                        classroom.setClassId(classroomCounter+1); 
                        classrooms.add(classroom);
                    }
                    if(indgroupCounter>3){
                         classroomCounter++;
                    }
                   
                    }
                }
                
                classroomCounter = 0;indgroupCounter=0;
                if(segmentsFortyEightToFiftyNine.size()>=1){
                    
                    for(int i=0;i<segmentsFortyEightToFiftyNine.size();i++){
                        Group g = new Group();
                        if(segmentsFortyEightToFiftyNine.get(i)!=null){
                            g.setGroupId(groupCounter++);
                            g.setStudents(segmentsFortyEightToFiftyNine.get(i));
                            g.setTeacher(teachers.get(teacherCounter++));
                            indgroupCounter++;
                        //segmentsSixToTwelve.remove(i);
                        }
                    
                    if(classrooms.size()!=0 && classroomCounter<classrooms.size()){
                        if(classrooms.get(classroomCounter).getGroups().size()!=0){
                        classrooms.get(classroomCounter).getGroups().add(g);
                      }
                    }else{
                        Classroom classroom = new Classroom();
                        List<Group> groups = new ArrayList<>();
                        groups.add(g);
                        classroom.setGroups(g);
                        classroom.setClassId(classroomCounter+1); 
                        classrooms.add(classroom);
                    }
                    if(indgroupCounter>2){
                         classroomCounter++;
                    }
                   
                    }
                }
                
                classroomCounter = 0;indgroupCounter=0;
                if(segmentsSixtyAndUp.size()>=1){
                   
                    for(int i=0;i<segmentsSixtyAndUp.size();i++){
                         Group g = new Group();
                        if(segmentsSixtyAndUp.get(i)!=null){
                            g.setGroupId(groupCounter++);
                            g.setStudents(segmentsSixtyAndUp.get(i));
                            g.setTeacher(teachers.get(teacherCounter++));
                            indgroupCounter++;
                        //segmentsSixToTwelve.remove(i);
                        }
                    
                    if(classrooms.size()!=0 && classroomCounter<classrooms.size()){
                        if(classrooms.get(classroomCounter).getGroups().size()!=0){
                        classrooms.get(classroomCounter).getGroups().add(g);
                      }
                    }else{
                        Classroom classroom = new Classroom();
                        List<Group> groups = new ArrayList<>();
                        groups.add(g);
                        classroom.setGroups(g);
                        classroom.setClassId(classroomCounter+1); 
                        classrooms.add(classroom);
                    }
                    if(indgroupCounter>2){
                         classroomCounter++;
                    }
                   
                    }
                }
                
                return classrooms ;
//		List<List<Student>> studentAgeGroups = new ArrayList<>();
//
//		studentAgeGroups.add(sixToTwelve);
//		studentAgeGroups.add(thirteenToTwentyfour);
//		studentAgeGroups.add(twentyfiveToThirtyFive);
//		studentAgeGroups.add(thirtySixToFortySeven);
//		studentAgeGroups.add(fortyEightToFiftyNine);
//		studentAgeGroups.add(sixtyAndUp);
//
//		ListIterator<List<Student>> li = studentAgeGroups.listIterator();
//		while (li.hasNext()) {
//			List<Student> s = li.next();
//			if (s.size() == 0) {
//				li.remove();
//			}
//		}
//
//		for (List<Student> s : studentAgeGroups) {
//			System.out.println(s);
//                        
//		}
//
//		int flag = 0;
//		while (flag < studentAgeGroups.size()) {
//			// Hardcoding size and class size parameters from rules-ratio.
//			if (flag == 0) {
//				System.out.println("calling 6-12");
//				parseAndAdd(studentAgeGroups.get(flag), 4, 3);
//			} else if (flag == 1) {
//				System.out.println("calling 13-24");
//				parseAndAdd(studentAgeGroups.get(flag), 5, 3);
//			} else if (flag == 2) {
//				System.out.println("calling 25-35");
//				studentAgeGroups.get(flag);
//				parseAndAdd(studentAgeGroups.get(flag), 6, 3);
//			} else if (flag == 3) {
//				System.out.println("calling 36-47");
//				parseAndAdd(studentAgeGroups.get(flag), 8, 3);
//			} else if (flag == 4) {
//				System.out.println("calling 48-59");
//				parseAndAdd(studentAgeGroups.get(flag), 12, 2);
//			} else if (flag == 5) {
//				System.out.println("calling 60+");
//				parseAndAdd(studentAgeGroups.get(flag), 15, 2);
//			}
//			flag = flag + 1;
//		}
//                
//		//parseAddTeacher(teachers, DayCare.getClassroom());
//		//assignGroups(DayCare.getClassroom());
//	}
//
//	public static void parseAndAdd(List<Student> studs, int size, int classSize) throws Exception {
//		System.out.println("i am  in parseaddstud");
//		System.out.println(studs);
//		System.out.println("Class Size: " + size);
//		int numGroups = 0;
//		if (studs.size() % size == 0) {
//			numGroups = studs.size() / size;
//
//		} else {
//			numGroups = studs.size() / size + 1;
//		}
//		List<Group> groups = new ArrayList<>();
//		int temp = 0;
//		for (int i = 0; i < numGroups; i++) {
//			groups.add(GroupFactory.getInstance().getObject());
//			for (int j = 0; j < size; j++) {
//				if ((temp + j) < studs.size()) {
//					groups.get(i).addStudents(studs.get(temp + j));
//				}
//				groups.get(i).setStudentsEnrolled(groups.get(i).getStudents().size());
//			}
//			temp = temp + size;
//		}
//
//		List<Classroom> classes = new ArrayList<>();
//		int tempC = 0;
//		int numClassrooms = 0;
//		if (groups.size() % classSize == 0) {
//			numClassrooms = groups.size() / classSize;
//		} else {
//			numClassrooms = groups.size() / classSize + 1;
//		}
//
//		System.out.println("Number of Classrooms: " + numClassrooms);
//		System.out.println("Group SIze: " + groups.size());
//		for (int i = 0; i < numClassrooms; i++) {
//			classes.add(ClassroomFactory.getInstance().getObject());
//			for (int j = 0; j < classSize; j++) {
//				if ((tempC + j) < groups.size()) {
//					classes.get(i).setGroups(groups.get(tempC + j));
//				}
//			}
//			classes.get(i).setGroupsEnrolled(groups.size());
//			tempC = tempC + classSize;
//		}
//
//		classes.forEach(c -> DayCare.addClassroom(c));
//	}
//
//	public static void parseAddTeacher(List<Teacher> t, List<Classroom> c) throws Exception {
//		int currTF = 0;
//		for (Classroom cl : c) {
//			for (Group g : cl.getGroups()) {
//				g.assignTeacher(t.get(currTF));
//				System.out.println("In parseAddTeacher : Assigning teacher to a group");
//				// System.out.println(g.toString());
//				System.out.println(t.get(currTF));
//				currTF = currTF + 1;
//				if (currTF == t.size()) {
//					currTF = 0;
//				}
//			}
//		}
//
//	}
//
//	public static void assignGroups(List<Classroom> classrooms) throws Exception {
//		ClassGroupDaoImpl dao = new ClassGroupDaoImpl();
//		for (Classroom classroom : classrooms) {
//			int classId = dao.createClassroom(classroom);
//			List<Group> groups = classroom.getGroups();
//			for (Group group : groups) {
//				int groupId = dao.createGroup(group);
//				List<Student> students = group.getStudents();
//				for (Student student : students)
//					dao.assignClassroom(student.getStudentId(), group.getTeacher().getEmployeeId(), classId, groupId);
//			}
//		}
//	}
//	
//	public List<Group> getClassRoomGroupInfo() throws Exception {
//		ClassGroupDaoImpl dao = new ClassGroupDaoImpl();
//		return dao.getGroupInfo();
//	}
        }
}
