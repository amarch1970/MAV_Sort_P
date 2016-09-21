package MAV_Sort_P;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import MAV_Sort_P.MAV_QSort;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import ru.yandex.qatools.allure.annotations.*;
import java.util.*;


@Title("������������ ����������� � ������� ������������ ����������")
@Description("��� ����� �����")
@RunWith(Parameterized.class)
public class SortJUnitTest { 
	static final int nMax_f = 10000000; // ����������� ���������� ��������� � �������
	static int [] aSort_Et = new int[nMax_f];
	MAV_QSort cQSort = new MAV_QSort();
	public Integer[] aSort_ST;
	public Integer[] aSort_Q;
	public int nArrLen;
	


	public SortJUnitTest(ArrayList<Integer[]> arr_s){
		aSort_ST = arr_s.get(0);
		aSort_Q = arr_s.get(1);
		nArrLen = aSort_Q.length; 
				
	
}
	
	@BeforeClass
	public static void initarray() {
	}


// ��������� ��� ������� ������ - ��������� ������� ��� ���������� 10, 100, 1000 � �.� ���������. ������������ ���������� ������ � nMax_f 
	
	@Parameterized.Parameters
	public static List<List<Integer[]>> testArray(){
		// ��������� ������� ����������. ������ ���� ����� ��� ������� �� ����� �� ������������ ����� 1 - ����������� ����������, 2- ����������� 3 - �������
		List<List<Integer[]>> aCount = new ArrayList<List<Integer[]>>();
		Integer[] aProm_st;
		Integer[] aProm_qt;
		List<Integer[]> aProm_lt;

		// ������������� ������� ��������� ����� ��� ���������� ������� ���������� ����������
		Random rand = new Random(48);
		for (int i = 0; i< aSort_Et.length; i++) {
			// ��������� �������������� ������ ��� ����������
			aSort_Et[i] = rand.nextInt(nMax_f);
		};
		
		//System.out.println("aSort_Et[1]="+ aSort_Et[1]);
		
		for ( int nMax= 10; nMax <= nMax_f; nMax *= 10){
			aProm_st=new Integer[nMax];
			aProm_qt=new Integer[nMax];
			// ��������� �������
			for(int i = 0; i < nMax; i++){
				aProm_st[i] = aSort_Et[i];
				aProm_qt[i] = aSort_Et[i];
			}
			aProm_lt = new ArrayList<Integer[]>();
			aProm_lt.add(aProm_st);
			aProm_lt.add(aProm_qt);
			aCount.add(aProm_lt);
//			System.out.println("aProm_lt.length="+ aProm_lt.size());
		}

//		System.out.println("aCount[0]="+ aCount.get(0).get(0)[1]);
		return aCount;
	}
	

	@Title("����� ���� �� �����������")
	@Description("����� ���� �� �����������")
	@Test
	public void test() {
	
//		System.out.println("������ �����  : ");
		// �������������� �������
		initarray();

		// ��������� ������ �� ����������� ����������
		test_ST(nArrLen);
	
		// ��������� ������ �� ������� ����������
		test_Q(nArrLen);
	

		// ��������� ��������������� ������� �� ����������
//		test_good_Q(aSort_Q, aSort_ST);

	}

	
	@Step ("������� ��������� ���������� �� {0} ��������� �������")
	public void test_Q(int nL){
		// ��������� ������ �� ������� ����������
		cQSort.make_QSort(aSort_Q);
	}

	@Step ("����������� ���������� �� {0} ��������� �������")
	public void test_ST(int nL){
		// ��������� ������ �� ����������� ����������
		Arrays.sort(aSort_ST);
	}

	@Step ("�������� ������������ ������� � ����������� ����������")
	public void test_good_Q(Integer[] a_Q, Integer[] a_ST){
		// ���������� �������
		assertTrue(Arrays.equals(a_ST, a_Q));
	}


}
