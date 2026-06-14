import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Task {
	public String detail;
	public int id;
	public byte status;

	Task (String detail, int id, byte status) {
		this.detail = detail;
		this.id = id;
		this.status = status;
	}
}

class Tasks {
	public String session_name;
	private int id_tracker = 0;
	private ArrayList<Task> tasks = new ArrayList<>();

	Tasks (String session_name) {
		this.session_name = session_name;
	}

	public void addTask (String task_detail) {
		Task someTask = new Task(task_detail, id_tracker, (byte) 0);
		tasks.add(someTask);
		id_tracker++;
	}

	public int deleteTask (int key_id) {
		int index_key = -1;
		int count = 0;

		for (Task task : tasks) {
			if (task.id == key_id) {
				index_key = count;
				break;
			}
			count++;
		}

		if (index_key >= 0) {
			tasks.remove(index_key);
			System.out.printf("Deleted task with id '%d'.\n", key_id);
		} else
			System.out.printf("Can't find any task with id '%d'.\n", key_id);

		return index_key;
	}

	public void viewTasks () {
		System.out.printf("<- %s --->\n\n", this.session_name);

		for (Task task : tasks) {
			String status = (task.status == 1 ? "/" : " ");

			System.out.printf("%d* [%s] - %s. \n", 
								task.id, 
								status, 
								task.detail);
		}
	}

	public void markDone (int task_id) {
		int target_index = -1;
		int count = 0;

		for (Task task : tasks) {
			if (task.id == task_id) target_index = count;
			count++;
		}

		if (target_index >= 0) {
			Task new_task = this.tasks.get(target_index);
			new_task.status = 1;
			this.tasks.set(target_index, new_task);
			System.out.printf("Marked the task id '%d' as completed.\n", new_task.id);
		} else {
			System.out.println("The task doesn't seem to exist.");
		}
	}

	public void writeToFile() {
		try {
			FileWriter writer = new FileWriter(this.session_name);
			String packed_data = "";

			for (Task task : tasks) {
				packed_data = packed_data + String.format(
						"%s|%s|%s\n", 
						task.detail, 
						String.valueOf(task.id), 
						String.valueOf(task.status));
			}

			writer.write(packed_data);
			writer.close();
		} catch (IOException e) {
			System.out.println("IOException error..");;
		}
	}

	public void loadFile() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.session_name));

			String line;
			while ((line = reader.readLine()) != null) {
				String[] string_task = line.split("\\|");

				Task got = new Task(
						string_task[0], 
						id_tracker, 
						Byte.valueOf(string_task[2]));

				this.tasks.add(got);
				id_tracker++;
			}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist.");;
		} catch (IOException e) {
			System.out.println("IOException error.");;
		}
	} 

	public ArrayList<Task> getTasks () {
		return tasks;
	}
}

class TaskTracker {
	public static void main (String args[]) 
	{
		if (args.length >= 2) 
		{
			// Add task
			if (args[0].equals("add-task")) {
				Tasks session = new Tasks(args[1]);
				Scanner input = new Scanner(System.in);
				session.loadFile();

				System.out.print("Task: ");
				String task_detail = "";
				while (task_detail.strip().equals("")) {
					task_detail = input.nextLine();
				}

				session.addTask(task_detail);
				session.writeToFile();
				input.close();
				System.out.println("Task has been added.");
			}

			// View tasks
			else if (args[0].equals("view-tasks") || args[0].equals("ls")) {
				Tasks session = new Tasks(args[1]);
				session.loadFile();
				session.viewTasks();
			}
		}

		if (args.length >= 3) 
		{
			// Mark tasks
			if (args[0].equals("mark-task")) {
				Tasks session = new Tasks(args[1]);
				session.loadFile();
				int id_target;

				try {
					id_target = Integer.valueOf(args[2]);
					session.markDone(id_target);
				} catch (NumberFormatException e) {
					System.out.println("Invalid id argument.");
				}

				session.writeToFile();
			}

			// Delete tasks
			if (args[0].equals("delete-task")) {
				Tasks session = new Tasks(args[1]);
				session.loadFile();
				int id_target;

				try {
					id_target = Integer.valueOf(args[2]);
					session.deleteTask(id_target);
				} catch (NumberFormatException e) {
					System.out.println("Invalid id argument.");
				}

				session.writeToFile();
			}
		}

		//for (String arg : args) {
		//	System.out.println(arg);
		//}
		
		/*
		 *
		 * Test case:
		Tasks session = new Tasks("test");
		session.addTask("Your mum");
		session.addTask("Bruh");
		session.addTask("Brushings");
		session.addTask("Nggas");

		session.viewTasks();
		session.loadFile();
		System.out.println("");
		session.markDone(2);
		session.viewTasks();
		session.writeToFile();
		*/

		//System.out.println(session.viewTasks().get(0).detail);
		//for (Task task : session.getTasks()) {
		//	System.out.println(task.detail);
		//}
	}
}
