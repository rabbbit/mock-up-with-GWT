package me.smape.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.i18n.client.DateTimeFormat;
import me.smape.client.Employee;

public class TestData {

	public static List<Employee> getEmployees()
	{
		List<Employee> employees = new ArrayList<Employee>();
		DateTimeFormat f = DateTimeFormat.getFormat("yyyy-mm-dd");
		employees.add(new Employee("Hollie Voss","Hometown","Old nigga",154,f.parse("2006-05-01")));
		employees.add(new Employee("Emerson Milton","Eurecom","Java mastah",454,f.parse("2007-03-01")));
		employees.add(new Employee("Christina Blake","Eurecom","Project Manager",345,f.parse("2008-08-01")));
		employees.add(new Employee("Heriberto Rush","Eurecom","Old friend",5534,f.parse("2009-02-07")));
		employees.add(new Employee("Candice Carson","Eurecom","Jedi Knight",435,f.parse("2007-11-01")));
		employees.add(new Employee("Chad Andrews","Eurecom","nvm",45,f.parse("2008-02-01")));
		employees.add(new Employee("Dirk Newman","Eurecom","Never talk again",454,f.parse("2009-03-01")));
		employees.add(new Employee("Bell Snedden","Eurecom","Never talk again",344,f.parse("2007-07-07")));
		employees.add(new Employee("Benito Meeks","Italy","Obi wan Kenoby",234,f.parse("2008-02-01")));
		employees.add(new Employee("Gail Horton","Italy","Kentacky ketchup",234,f.parse("2009-05-01")));
		employees.add(new Employee("Claudio Engle","Paris","Mike!",224,f.parse("2008-09-03")));
		employees.add(new Employee("Buster misjenou","Paris","Don't give him beer",434,f.parse("2008-02-07")));

		return employees;
	}
}
