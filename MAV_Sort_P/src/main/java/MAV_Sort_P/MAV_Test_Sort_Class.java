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
		
		// �������������� �������
		initarray();
		// ����������� ���������� ��������
		System.out.println("������ ���������� �� " + nMax + " ��������� ������� ����� �����");
		Arrays.sort(aSort_ST);
		System.out.println("����������� ���������� ���������: ");
		System.out.println("");
		cQSort.make_QSort(aSort_Q);
		System.out.println("������� ������������ ���������� ���������");
		System.out.println("��������� �������� ���������� ���������� � ������� ��������� ����������:"+Arrays.equals(aSort_ST, aSort_Q));

	}
	static void initarray() {
		// ������������� ������� ��������� ����� ��� ���������� ������� ���������� ����������
		Random rand = new Random(48);
		for (int i = 0; i< aSort_Et.length; i++) {
			// ��������� �������������� ������ ��� ����������
			aSort_Et[i] = rand.nextInt(10000);
			aSort_Q[i] = aSort_Et[i];
			aSort_ST[i] = aSort_Et[i];

		};
	
	}

}
