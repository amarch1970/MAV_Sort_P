

package MAV_Sort_P;

/* ����� ������� ����������� � ������������ ������*/
public class MAV_QSort {
	int nlen;
	Integer[] qSortArr;
	static int nCount = 0;
	private int semaphor = 0; // ���������� ������� ���������� ����������� ���������. ������ �������� ������ ������������������� ��������.
	
	synchronized void incSem (){
		// ����������� ����� �������, ����������� �������
		semaphor++;
//		System.out.println(" semaphor++"+semaphor);
	}
	synchronized void decSem (){
		// ������� ����������� - ��������� �������
		semaphor--;
//		System.out.println(" semaphor--"+semaphor);
	}

	// ���������� ���������� Runnable  class
	public class RunSort implements Runnable{
		int y1;
		int y2;
		public RunSort(int x1, int x2){
			// ����������� ������������ ��������, ������ ��������� �������
			y1 = x1;
			y2 = x2;
			incSem();
		}
		public void run(){
//			System.out.println(" y1="+y1+"; y2="+y2);
			QSort(y1, y2);
			//�������� ������� �����������, ��������� �������
			decSem();
		}
	}
	
	
	public void make_QSort(Integer[] SortArr){
		nlen = SortArr.length;
		if (nlen > 1) {
			// ��������� ������ ���� � ������� ����� ������ ��������
			qSortArr = SortArr;
		
			// ��������� ������
			new Thread(new RunSort(0, nlen-1)).start();
			//QSort(0, nlen-1);
			// �� ������� �� �����, ���� ������� ���� ���� ����������� �������
			while(semaphor > 0) {
				Thread.yield();
			}
		}
			
	}
	
	private void QSort(int x1, int x2){
		//  ���������� ����������� ��������� ��� ����������. ������� ��������� - ������ � ������� ������� ��� �������
		int nPos = (x1+x2)/2;
		int nProm;
		/* ��������. 
		 * ���� ����� ������� � ������ ������ �� ��� ���, ���� �������� �� �����������
		 * ���� �����, ��� ������ ������� ������� ������� ��������� - ���������������
		 * ���� ������, ��� ������ �������� ������� ������ ��������� - ���������������
		 * ������ �������� �������
		 * 
		 * ��� ������ �������� ����������� ����������  �� ��� ����� �� ������� � �� ��� ������ - �� ��������
		 * 
		 * */		
		int i = x1;
		int j = x2;
//		System.out.println(" x1="+x1+"; x2="+x2);
		if (x1 >=x2) return;
		nCount++;
//		System.out.println(" x1="+x1+ " x2="+x2+" nPos="+nPos+"; nPosZn="+qSortArr[nPos]+ " nCount="+nCount);
		while (j > i) {
			while ((i < nPos) && (qSortArr[i] <= qSortArr[nPos])) i++;
			while ((j > nPos) && (qSortArr[j] >= qSortArr[nPos])) j--;
			if ((j > i) && (qSortArr[i] >= qSortArr[j])) {
				// ������ �������
				nProm = qSortArr[i];
				qSortArr[i] = qSortArr[j];
				qSortArr[j] = nProm; 
				//���� ���� �� �������� ����� �� �������� ��������, �� ������ �������� �������� ������ �� ������ ����� (������� �� �����)
				if (i == nPos) nPos = j; else if (j == nPos) nPos = i;
			}
		}
		if (x1 < (nPos-1)){
			if (((nPos-1) - x1) > 5000)
				// ��� ��������� ��������� ������������ ������� ��� ������ ��������� ����������, ��� ��� ������ ��������� �������
				new Thread(new RunSort(x1, nPos-1)).start();
			else QSort(x1, nPos-1);
		};
		if ((nPos+1) < x2){
			if ((x2 - (nPos+1)) > 5000)
				new Thread(new RunSort(nPos+1, x2)).start();
			else QSort(nPos+1, x2);
		};	

//		QSort(x1, nPos-1);
//		QSort(nPos+1, x2);
	}
}
