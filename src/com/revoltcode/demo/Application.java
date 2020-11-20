package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;

public class Application {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try {
			System.out.println("Creating instructor");
			
			Instructor instructor = new Instructor("David", "Richard", "davidrichard@yahoo.com"); 
			InstructorDetail instructorDetail = new InstructorDetail("http://www.somegi.com", "@comot");
			
			instructor.setInstructorDetailId(instructorDetail);
			
			session.beginTransaction();
			
			System.out.println("Saving instructor");
			session.save(instructor);
			
			/*Deleting User
			 * 
			 * Instructor instructor = session.get(Instructor.class, 1);
			 * 
			 * if(instructor != null){
			 * 		session.delete(instructor);
			 * }
			 */
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			factory.close();
		}
	}
}
