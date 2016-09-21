package MAV_Sort_P;
import java.util.*;



public class MAV_Test_Sort_Class {
	static final int nMax = 10000000;
	static Integer [] aSort_Et = new Integer[nMax];
	static Integer [] aSort_Q = new Integer[nMax];
	static Integer [] aSort_ST = new Integer[nMax];

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MAV_QSort cQSort = new MAV_QSort();
		
		// »нициализируем массивы
		initarray();
		// —тандартна€ сортировка массивов
		System.out.println("√ачало сортировок на " + nMax + " элементах массива целых чисел");
		Arrays.sort(aSort_ST);
		System.out.println("—тандартна€ сортировка закончена: ");
		System.out.println("");
		cQSort.make_QSort(aSort_Q);
		System.out.println("Ѕыстра€ параллельна€ сортировка закончена");
		System.out.println("–авенство массивов стандарной сортировки и быстрой паральной сортировки:"+Arrays.equals(aSort_ST, aSort_Q));

	}
	static void initarray() {
		// »нициализаци€ датчика случайных чисел дл€ заполнени€ массива случайными значени€ми
		Random rand = new Random(48);
		for (int i = 0; i< aSort_Et.length; i++) {
			// заполн€ем первоначальный массив дл€ сортировки
			aSort_Et[i] = rand.nextInt(10000);
			aSort_Q[i] = aSort_Et[i];
			aSort_ST[i] = aSort_Et[i];

		};
	
	}

}
