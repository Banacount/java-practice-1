import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
	public static void main (String[] args) {
		int arg_count = 0;
		for (String arg: args) {
			arg_count++;
		}

		if (arg_count >= 2) {
			System.out.printf("Name of the file: %s\n", args[0]);
			System.out.printf("Initial write: %s\n\n", args[1]);

			try {
				Path path = Paths.get(args[0]);
				Files.write(path, List.of(args[1]));				
				System.out.println("The file has been written.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		System.out.println(arg_count);
	}
} 
