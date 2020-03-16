package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import entities.Employee;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		Scanner sc = new Scanner(System.in);
		 //lendo  o caminho do  arquivo
	    System.out.print("Enter full file path");
	    String path = sc.nextLine();
	    
	    System.out.print("Enter Salary: ");
	    double  salaryToday = sc.nextDouble();
	    
	    try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
	    	List<Employee> list = new ArrayList<>();
	    	/*criamos uma lista da classe employee(empregado)
	    	
	    	variavel para ler as linhas*/
	    	String line = br.readLine();
	    	//while para percorrer as linhas
	    	while (line != null) {
	    		//fazer um fields para armazenar um vetor, que recorta
	    		//String passando como referencia nesse caso  a virgula
	    		String[] fields = line.split(",");
	    		
	    		list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2]) ));
	    		line = br.readLine();//mandando  ler a proxima linha
	    	}
	    	
	    	Comparator<String> compEmail = (E1, E2) -> E1.toUpperCase().compareTo(E2.toUpperCase());
	    	
	    	List<String> namesSalary = list.stream()
	    			.filter(p -> p.getSalary() > salaryToday)
	    			.map(p -> p.getEmail()).sorted(compEmail)
	    			.collect(Collectors.toList());
	    	
	    	namesSalary.forEach(System.out::println);
	    	
	    	double sum = list.stream()
	    			.filter(p -> p.getName().charAt(0) == 'M')
	    			.map(p -> p.getSalary())
	    			.reduce(0.0,(x,y) -> x+y);
	    	
	    	System.out.println("Sum of salary of people name starts with 'M': " + String.format("%.2f", sum));
	    	
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
