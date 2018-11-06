package com.sge.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sge.hibernate.demo.entity.Course;
import com.sge.hibernate.demo.entity.Instructor;
import com.sge.hibernate.demo.entity.InstructorDetail;
import com.sge.hibernate.demo.entity.Review;

public class CreateCourseAndReviewDemo {

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
			
			// start a transaction
			session.beginTransaction();
			
			// create a course
			Course tempCourse = new Course("Pacman - como hacer 10000000 puntos");
			
			// add some reviews
			tempCourse.addReview(new Review("Genial!"));
			tempCourse.addReview(new Review("Muy bueno"));
			tempCourse.addReview(new Review("KK de la vaca!"));
						
			// save the course ... como cascade=all se guarda también la review.
			System.out.println("Guardando course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getReviews());
			
			session.save(tempCourse);
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





