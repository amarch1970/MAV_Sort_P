

package MAV_Sort_P;

/* Класс быстрой сортивровки в параллельном режиме*/
public class MAV_QSort {
	int nlen;
	Integer[] qSortArr;
	static int nCount = 0;
	private int semaphor = 0; // Показывает текущее количество паралельных процессов. Должен меняться только синхронезированными методами.
	
	synchronized void incSem (){
		// запускается новый процесс, увеличиваем счетчик
		semaphor++;
//		System.out.println(" semaphor++"+semaphor);
	}
	synchronized void decSem (){
		// Процесс завершается - уменьшяем счетчик
		semaphor--;
//		System.out.println(" semaphor--"+semaphor);
	}

	// Определяем внутренний Runnable  class
	public class RunSort implements Runnable{
		int y1;
		int y2;
		public RunSort(int x1, int x2){
			// конструктор паралельного процесса, должны увеличить счетчик
			y1 = x1;
			y2 = x2;
			incSem();
		}
		public void run(){
//			System.out.println(" y1="+y1+"; y2="+y2);
			QSort(y1, y2);
			//врвльный процесс завершается, уменьшаем счетчик
			decSem();
		}
	}
	
	
	public void make_QSort(Integer[] SortArr){
		nlen = SortArr.length;
		if (nlen > 1) {
			// Сортируем только если в массиве более одного элемента
			qSortArr = SortArr;
		
			// Запускаем потоки
			new Thread(new RunSort(0, nlen-1)).start();
			//QSort(0, nlen-1);
			// Не выходим из циклв, пока запущен хоть один паралельный процесс
			while(semaphor > 0) {
				Thread.yield();
			}
		}
			
	}
	
	private void QSort(int x1, int x2){
		//  определяем рекурсивную процедуру для сортировки. Входные параметры - нижний и верхние индексф для массива
		int nPos = (x1+x2)/2;
		int nProm;
		/* Алгоритм. 
		 * Идем сдева направо и справа надева до тех пор, пока счетчики не встретяться
		 * Идем слева, как только находим элемент больший заданного - останавливаемся
		 * Идем справа, как только находиим элемент меньше заданного - останавливаемся
		 * Меняем элементы местами
		 * 
		 * Как только счетчики встретились отправляем  то что слева на рекрсию и то что справа - на рекурсию
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
				// Меняем местами
				nProm = qSortArr[i];
				qSortArr[i] = qSortArr[j];
				qSortArr[j] = nProm; 
				//Если один из индексов дошел до опорного элемента, то индекс опорного элемента меняем на другой инекс (который не дошел)
				if (i == nPos) nPos = j; else if (j == nPos) nPos = i;
			}
		}
		if (x1 < (nPos-1)){
			if (((nPos-1) - x1) > 5000)
				// При маленьких значениях сортируемого массива нет смысла применять праллелизм, так как велики накладные расходы
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
