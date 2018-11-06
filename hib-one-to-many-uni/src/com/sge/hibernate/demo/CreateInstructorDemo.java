package com.sge.hibernate.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sge.hibernate.demo.entity.Course;
import com.sge.hibernate.demo.entity.Instructor;
import com.sge.hibernate.demo.entity.InstructorDetail;
import com.sge.hibernate.demo.entity.Review;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.addAnnotatedClass(Review.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			
			// create the objects			
			Instructor tempInstructor = 
					new Instructor("Jaime", "aRRRR", "susana.public@gmail.com");
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail(
							"http://www.youtube.com",
							"Yoga");		
			
			
			
			/*List<Course> courses=new ArrayList<Course>();
			courses.add(new Course("Samba"));
			courses.add(new Course("Pumba"));*/
			
			
			// start a transaction
			session.beginTransaction();
			
			Course c=new Course("cursillo macramememe");
			
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			tempInstructor.add(c);
						
			
			//session.save(tempInstructorDetail);
			//session.save(c);
			
			//tempInstructor.setCourses(courses);
			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
			//
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);					
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			
			// add clean up code
			//session.close();
			
			factory.close();
		}
	}

}





