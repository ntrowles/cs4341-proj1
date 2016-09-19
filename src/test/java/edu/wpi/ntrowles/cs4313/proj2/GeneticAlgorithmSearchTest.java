package edu.wpi.ntrowles.cs4313.proj2;

import org.junit.Test;

import edu.wpi.ntrowles.cs4313.proj2.Main;

public class GeneticAlgorithmSearchTest {
	@Test
	public void searchTest1(){
		String[] args = {"src/test/resources/genetic_1"};
		Main.main(args);
	}
	public void searchTest2(){
		String[] args2 = {"src/test/resources/genetic_2"};
		Main.main(args2);
	}
	public void searchTest3(){
		String[] args3 = {"src/test/resources/genetic_3"};
		Main.main(args3);
	}
	public void searchTest4(){
		String[] args4 = {"src/test/resources/genetic_4"};
		Main.main(args4);
	}
	public void searchTest5(){
		String[] args5 = {"src/test/resources/genetic_5"};
		Main.main(args5);
	}
}
