import java.io.File;
import java.util.Scanner;

/**
 * This class provides an implementation of the DataReader interface for CSV
 * files
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class CSVReader implements DataReader {
	// WRITE YOUR CODE HERE!
	/**
	 * Instance variable for storing the file path from the source
	 */
	private String sourceId;

	/**
	 * The delimiter that separates attribute names and attribute values
	 */
	private static final char DELIMITER = ',';

	/**
	 * Character allowing escape sequences containing the delimiter
	 */
	private static final char QUOTE_MARK = '\'';

	/**
	 * Instance variable for storing the number of attributes (columns)
	 */
	private int numColumns;

	/**
	 * Instance variable for storing the number of datapoints (data rows)
	 */
	private int numRows;

	/**
	 * Instance variable for storing attribute names
	 */
	private String[] attributeNames;

	/**
	 * Instance variable for storing datapoints
	 */
	private String[][] matrix; 
	
	/**
	 * Constructs a dataset by loading a CSV file
	 * 
	 * @param strFilename is the name of the file
	 */
	public CSVReader(String filePath) throws Exception {

		// WRITE YOUR CODE HERE!
		File file = new File(filePath.trim());
		sourceId = file.getAbsolutePath();

		Scanner sc1 = new Scanner(sourceId);
		int col = 1;
		int ro = -1;
		boolean flag = false;

		while (sc1.hasNext()) {
			// Read one full line from file
			String str = sc1.nextLine();

			col = 1;

			if (str.equals("") ) {
				continue;
			}

			for (int i = 0; i < str.length(); i++) { // flag would be true if it is inside a quoted phrase
				if (str.charAt(i) == QUOTE_MARK && flag == false) {
					flag = true;
				} else if (str.charAt(i) == QUOTE_MARK && flag == true){
					flag = false; 
				} 

				if (flag == false) { // to check if it is a new column or just the same column
					if (str.charAt(i) == DELIMITER ) {
						col = col +1;
						continue;
					}
				} 
			}
			ro = ro + 1;
		}


		numColumns = col;
		numRows = ro;

		attributeNames = new String[numColumns];
		matrix = new String[numRows][numColumns];

		Scanner sc2 = new Scanner(sourceId);
		flag = false;
		ro = -1;


		while (sc2.hasNext()) {
			
			String str = sc2.nextLine();
			
			col = 0;
			String x = "";

			if (str.equals("") ) {
				continue;
			}

			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == QUOTE_MARK && flag == false) { // flag would be true if it is inside a quoted phrase
					flag = true;
					continue;
				} else if (str.charAt(i) == QUOTE_MARK && flag == true){
					flag = false; 
					continue;
				}

				if (str.charAt(i) == DELIMITER ) { // to check if it is a new column or just the same column
					if (flag == true) {
						x = x + str.charAt(i);
					} else{
						if (ro < 0) {
							attributeNames[col] = x.trim();
							x = "";
						} else {
							if (x == "") {
								matrix[ro][col] = "MISSING";
							} else{
								matrix[ro][col] = x.trim();
								x = "";
							}
						}
						
						col = col + 1;
					}
				} else{
					x = x + str.charAt(i);
				}

			}

			// the string after the loop is the last value of the row which still need to be added
			if (ro < 0) { // most likely not but just incase.
				attributeNames[col] = x.trim();
				x = "";
			} else {
				if (x == "") {
					matrix[ro][col] = "MISSING";
				} else{
					matrix[ro][col] = x.trim();
					x = "";
				}
			}
			

			ro = ro + 1;

		}

		sc1.close();
		sc2.close();


	}

	/**
	 * Get the array of the attribute names
	 * 
	 * @return the names of the dataset's attributes
	 */
	public String[] getAttributeNames() {
		
		return attributeNames;
	}

	/**
	 * get the 2-D array of the values
	 * 
	 * @return the data matrix of the dataset
	 */
	public String[][] getData() {
		
		return matrix;
	}

	/**
	 * get the source ID
	 * 
	 * @return a string identifier for the data source (for example, the name and
	 *         location of the data source if the source happens to be a file).
	 */
	public String getSourceId() {
		
		return sourceId;
	}

	/**
	 * get the number of columns
	 * 
	 * @return the number of columns in the dataset
	 */
	public int getNumberOfColumns() {
		
		return numColumns;
	}

	/**
	 * get the number of rows
	 * 
	 * @return the number of rows (datapoints) in the dataset
	 */
	public int getNumberOfDataRows() {
		
		return numRows;
	}
}
