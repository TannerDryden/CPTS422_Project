package CategoryBTestFiles;

public class NumLoopStatementsTest2 {
	
	boolean foo = true;
	boolean bar = false;
	
	void func() {
		while (foo) {
		
			for(int i = 0; i < 10; i++) {
				foo = false;
			}
		
		}
		
		do {
			bar = true;
		} while(bar);
	}
}
