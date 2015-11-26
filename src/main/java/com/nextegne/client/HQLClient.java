/**
 * 
 */
package com.nextegne.client;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.nextgen.entities.Student;

/**
 * Hibernate Query Language CRUD Operations
 *
 */
public class HQLClient {

	public static void main(String[] args) {
		// insertStudents();
		HQLClient hql = new HQLClient();

	
		// hql.insert();
		// hql.update();
		// hql.delete();
		// hql.selectUniqueRecord();
		hql.selectList();

	}


	private void selectList() {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();
		Query query = s.createQuery("from Student ");
		List<Student> list = query.list();

		List<Student> stList = list;
		System.out.println("All rows in the Table : ");
		for (Student student : list) {
			System.out.print(" Id=" + student.getId());
			System.out.print(" Name=" + student.getName());
			System.out.println(" Grade=" + student.getGrade());
		}

		s.getTransaction().commit();
		s.clear();// s.evict() on all student objects in session
		sf.close();

	}

	private void selectUniqueRecord() {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		Query query = s.createQuery("from Student where id=1");
		Student student = (Student) query.uniqueResult();
		System.out.print("selectUniqueRecord: -----");
		System.out.print("Id=" + student.getId());
		System.out.print("Name=" + student.getName());
		System.out.println("Grrade=" + student.getGrade());

		s.getTransaction().commit();
		s.clear();// s.evict() on all student objects in session
		sf.close();

	}

	/**
	 * Deleting records using HQL
	 */
	private void delete() {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		Query query = s.createQuery("delete from OldStudent where name='Michael'");
		int count = query.executeUpdate();
		System.out.println("Number of Rows Deleted in the oldstudent table=" + count);

		s.getTransaction().commit();
		s.clear();// s.evict() on all student objects in session
		sf.close();

	}

	/**
	 * Updating the primary key column. We can update single column using HQL.
	 * We cannot do this using session update/merge/save/update methods where
	 * all columns are updated.
	 */
	private void update() {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		Query query = s.createQuery("update OldStudent os set id=9 where os.name = 'Omkar'");
		int count = query.executeUpdate();
		System.out.println("Number of Rows Updated in the oldstudent table=" + count);

		s.getTransaction().commit();
		s.clear();// s.evict() on all student objects in session
		sf.close();

	}

	/**
	 * Will dump data from one table to another table
	 */
	private void insert() {
		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		Query query = s.createQuery("insert into OldStudent(id,name,grade) select s.id,s.name,s.grade from Student s");
		int count = query.executeUpdate();
		System.out.println("Number of Rows inserted in the oldstudent table=" + count);

		s.getTransaction().commit();
		s.clear();// s.evict() on all student objects in session
		sf.close();
	}

	private static void insertStudents() {

		Configuration conf = new Configuration();
		conf.configure("hibernate.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		s.beginTransaction();

		Student st = new Student();
		st.setName("Omkar");
		st.setGrade("A");

		Student st1 = new Student();
		st1.setName("John");
		st1.setGrade("B");

		Student st2 = new Student();
		st2.setName("BruceLee");
		st2.setGrade("F");

		Student st3 = new Student();
		st3.setName("Michael");
		st3.setGrade("A");

		s.save(st);
		s.save(st1);
		s.save(st2);
		s.save(st3);

		s.getTransaction().commit();
		s.clear();// s.evict() on all student objects in session
		sf.close();

	}

}
