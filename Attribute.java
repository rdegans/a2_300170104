/**
 * This class is used for representing an attribute of a dataset, be the dataset
 * an actual one (with a data matrix) or a virtual one (without a data matrix).
 * 
 * @author Mehrdad Sabetzadeh, University of Ottawa
 * @author Guy-Vincent Jourdan, University of Ottawa
 *
 */
public class Attribute {
	/**
	 * Name of the attribute
	 */
	private String name;
	/**
	 * Column number of the attribute
	 */
	private int absoluteIndex;
	/**
	 * Type of the attribute
	 */
	private AttributeType type;
	/**
	 * Value set of the attribute
	 */
	private String[] values;

	/**
	 * Constructor for Attribute
	 * 
	 * @param name          is the name label of the attribute
	 * @param absoluteIndex is the column number where the attribute is located in a
	 *                      data matrix. Notice that, for virtual datasets, the
	 *                      position of an attribute in the "attributes" array
	 *                      (defined in the DataSet class) has no relationship to
	 *                      the actual column that the attribute represents. We
	 *                      therefore cannot rely on the indices in the "attributes"
	 *                      array to give us the data-matrix column number that the
	 *                      attribute is associated with.
	 * @param type          is the type of the attribute
	 * @param values        is the set of unique values that the attribute can
	 *                      assume. Make sure you create a COPY of the "values"
	 *                      array rather than merely stating this.values = values.
	 */
	public Attribute(String name, int absoluteIndex, AttributeType type, String[] values) {
		
		this.name = name;
		this.absoluteIndex = absoluteIndex;
		this.type = type;

		String[] copyValues = new String[values.length];

		for (int i = 0; i < copyValues.length; i++) {
			values[i] = copyValues[i];
		}

		this.values = copyValues;

	}

	/**
	 * @return the name of the attribute
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * @return the absolute index (column number) of the attribute
	 */
	public int getAbsoluteIndex() {
		
		return absoluteIndex;
	}

	/**
	 * @return the type of the attribute
	 */
	public AttributeType getType() {
		
		return type;
	}

	/**
	 * @return the value set of the attribute. Make sure you return a COPY of the
	 *         values array (as opposed to the values array itself). This is so that
	 *         no other object can change the value set of this attribute.
	 */
	public String[] getValues() {
		
		String[] copyValues = new String[values.length];

		for (int i = 0; i < copyValues.length; i++) {
			values[i] = copyValues[i];
		}

		return copyValues;
	}

	/**
	 * Replaces the value set of the attribute with the supplied parameter
	 * (newValues). We do not want any other object to have a direct reference to
	 * the (updated) values array in the attribute. We therefore cannot merely
	 * state: this.values = newValues. Instead, we need to create a copy of the
	 * newValues array first.
	 * 
	 * @param newValues is an array containing the value set that should replace the
	 *                  attribute's current value set
	 */
	public void replaceValues(String[] newValues) {
		
		String[] copyValues = new String[newValues.length];

		for (int i = 0; i < copyValues.length; i++) {
			newValues[i] = copyValues[i];
		}

		this.values = copyValues;
	}

	/**
	 * Creates a deep copy of the attribute. Notice that, for the Attribute class,
	 * deep copy extends only to the values array. Deep copy does not apply to
	 * primitive attributes and is unnecessary for immutable objects such as
	 * strings.
	 * 
	 * @return a (deep) copy the attribute
	 */
	public Attribute clone() {
		
		String clonename = this.name;
		int cloneabsoluteIndex = this.absoluteIndex;
		AttributeType clonetype = this.type;

		String[] clonevalues = new String[this.values.length];
		for (int i = 0; i < clonevalues.length; i++) {
			values[i] = clonevalues[i];
		}

		Attribute clone = new Attribute(clonename, cloneabsoluteIndex, clonetype, clonevalues);
		return clone;
	}

	/**
	 * @return a string representation of the attribute
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("   [absolute index: " + absoluteIndex + "] ");
		buffer.append(name);
		if (type == AttributeType.NUMERIC)
			buffer.append(" (numeric): ");
		else
			buffer.append(" (nominal): ");

		buffer.append("{");

		for (int i = 0; i < values.length; i++) {
			if (type == AttributeType.NUMERIC)
				buffer.append(values[i]);
			else
				buffer.append('\'').append(values[i]).append('\'');

			if (i < values.length - 1)
				buffer.append(", ");
		}

		buffer.append('}');

		return buffer.toString();
	}
}
