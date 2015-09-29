package com.jkkp.utils;

public class MathUtils {

	public static int[] bubbleSort(int[] array){
		if(array != null && array.length > 0){
			for (int i = 0; i < array.length; i++)
			{
				for (int j = i; j < array.length; j++)
				{
					if (array[i] > array[j])
					{
						int temp = array[i];
						array[i] = array[j];
						array[j] = temp;
					}
				}
			}
			return array;
		}
		return null;
	}
	
}
