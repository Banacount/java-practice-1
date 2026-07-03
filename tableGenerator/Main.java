import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Status: Finished,
 *
 * */

class printTable {
	public static void wholeTable (ArrayList<Integer> longLenPerColumn, ArrayList<String[]> tableList)
	{
		//firstPart(longLenPerColumn);
		eachItem(longLenPerColumn, tableList);
	}

	public static void firstPart (ArrayList<Integer> longLenPerColumn) 
	{
		System.out.print("|");
		int col_i = 1;
		for (int long_col : longLenPerColumn) 
		{
			for (int i = 0; i < long_col; ++i) 
				System.out.print("-");

			int end_index = longLenPerColumn.size();

			if (col_i < end_index) 
				System.out.print("-");

			col_i++;
		}
		System.out.println("|");
	}

	public static void headerPart (ArrayList<Integer> longLenPerColumn)
	{
		System.out.printf("|");
		for (int long_col : longLenPerColumn) 
		{
			for (int i = 0; i < long_col+2; ++i) 
				System.out.printf("-");

			System.out.printf("|");
		}
		System.out.println("");
	}

	// 
	public static void eachItem (ArrayList<Integer> longLenPerColumn, ArrayList<String[]> tableList)
	{
		int row_index = 0;
		Boolean isFirst = true;

		for (String[] column_items : tableList)
		{
			int column_index = 0;

			for (String col_item : column_items) {
				int longInCol = longLenPerColumn.get(column_index);

				String format_str = "| %-" + String.valueOf(longInCol) + "s ";
				System.out.printf(format_str, col_item);
				column_index++;
			}

			System.out.println("|");
			row_index++;

			if (!isFirst) continue;
			headerPart(longLenPerColumn);
			isFirst = false;
		}
	}
}

class Main {
	public static void main (String[] args) 
	{ 
		File file_test = new File("data.txt"); 
		int row_count = 0, column_count = 0;

		ArrayList<Integer> longLenPerColumn = new ArrayList<>();
		ArrayList<String[]> tableList = new ArrayList<>();

		// Loop through the data and store in tableList
		try {
			Scanner reader = new Scanner(file_test);

			while (reader.hasNextLine()) 
			{
				String line = reader.nextLine();
				String[] parsed = line.split(",");

				int largestLenInCol = 0;
				int column_index = 0;

				if (parsed.length > column_count)
					column_count = parsed.length;

				// Prepare the longLenPerColumn array
				if (row_count <= 0) {
					for (int i = 0; i < parsed.length; ++i) 
						longLenPerColumn.add(0);
				}

				tableList.add(parsed);
				row_count++;

				// Per column process
				for (String col_str : parsed) {
					int col_str_len = col_str.length();

					if (col_str_len > largestLenInCol)
						largestLenInCol = col_str_len;

					int long_similar_col = longLenPerColumn.get(column_index);

					if (long_similar_col < col_str_len)
						longLenPerColumn.set(column_index, col_str_len);

					column_index++;
				}
			}

			//int col_num = 0;
			//for (int longest : longLenPerColumn) 
			//{
			//	System.out.printf("In column number %d, the longest in length is %d.\n", col_num, longest);
			//	col_num++;
			//}

			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error happened.");
			e.printStackTrace();
		}

		// Test formatting
		printTable.wholeTable(longLenPerColumn, tableList);
		
		//System.out.println(String.valueOf(row_count));
		//System.out.println(String.valueOf(column_count));
	}
}
/*
System.out.println("|-----|----------------------------|---------|----------|");
System.out.println("---------------------------------------------------------");
System.out.println("|-------------------------------------------------------|");
System.out.printf("| %-3s | %-26s | %-7s | %-8s |\n", 
				parsed[0], parsed[1], parsed[2], parsed[3]);
*/

/*
 * Let's start cleaning but wait
 *
	// Loop per row
	for (String[] row_strings : tableList) 
	{
		// Loop per column
		for (String column_string: row_strings) 
		{
			System.out.printf("%s, ", column_string);
		}
		System.out.println("");
	}
*/
