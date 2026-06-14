import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class WriteMail {
	public static void main (String[] args) {
		int arg_count = 0;
		for (String arg: args) {
			arg_count++;
		}

		if (arg_count >= 5) {
			System.out.printf("\nFile-name: %s\n", args[0]);
			System.out.printf("From: %s\n", args[1]);
			System.out.printf("To: %s\n", args[2]);
			System.out.printf("Subject: %s\n\n", args[3]);
			System.out.printf("%s\n--- End ---\n\n", args[4]);

			String mail_txt = String.format("From %s,\nTo: %s\n\nSubject > %s\n\n%s", args[1], args[2], args[3], args[4]);

			try {
				Path path = Paths.get(args[0]);
				Files.write(path, List.of(mail_txt));				
				System.out.println("The file has been written.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Arguments must be: [file_name] [from] [to] [subject] [main_text]");
		}
	}
}
