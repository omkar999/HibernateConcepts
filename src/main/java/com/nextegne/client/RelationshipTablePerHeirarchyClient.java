/**
 * 
 */
package com.nextegne.client;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.relationships.entities.ContractEmployee;
import com.relationships.entities.Employee;
import com.relationships.entities.RegularEmployee;

/**
 * @author sree
 *
 */
public class RelationshipTablePerHeirarchyClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration conf = new Configuration();
		conf.configure("relationships.cfg.xml");
		SessionFactory sf = conf.buildSessionFactory();
		Session s = sf.openSession();
		/*s.beginTransaction();

		Employee e1 = new Employee();
		e1.setName("John");

		RegularEmployee e2 = new RegularEmployee();
		e2.setName("Arnold");
		e2.setSalary(50000);
		e2.setBonus(5);

		ContractEmployee e3 = new ContractEmployee();
		e3.setName("Arjun Kumar");
		e3.setPay_per_hour(1000);
		e3.setContract_period("15 hours");

		s.persist(e1);
		s.persist(e2);
		s.persist(e3);

		s.getTransaction().commit();*/
		s.clear();// s.evict() on all student objects in session
		sf.close();

	}

}
