
class Test2 {
	private void what () {
		System.out.println("What.");
	}

	public void main () {
		System.out.println("Test thingy");
		this.what();
	}
}

public class Test {
	public static void main (String[] args) {
		System.out.println("Why was you so shocked!");
		Test2 what = new Test2();
		what.main();
	} 
}
