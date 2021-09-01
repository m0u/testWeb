package com.research;

import java.io.IOException;

import algorithm.CellBiClust;

public class TEST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CellBiClust ob;
		try {
			ob = new CellBiClust("D:\\SOFTWRE\\new_saltmarsh.csv",",",2,2);
			ob.runAlgorithm("D:/SOFTWRE/","output","true",0.7, 0.85);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
