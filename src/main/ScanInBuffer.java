package main;

public class ScanInBuffer {

	String[] array;
	private int dimension;
	private int index;
	int quant;
	private String[] tables;
	private int currentTable;
	private int lastId;

	public ScanInBuffer(int size) {
		int i;
		String[] inter = new String[Interfata.plugins.getNr_of_plugins()];
		this.dimension = size;
		this.index = 0;
		this.quant = 0;
		this.array = new String[this.dimension];
		this.currentTable = 0;
		this.lastId = 0;
		int counter = 0;
		for (i = 0; i < Interfata.plugins.getNr_of_plugins(); i++){
			if (!Interfata.plugins.plugin[i].plugin_is_standard)
				inter[i] = Interfata.plugins.plugin[i].sql_table;
			else
				inter[i] = "delete";
			}
		for (i = 0; i < inter.length; i++) {
			if (inter[i] == "delete") {
				counter++;
				//System.out.println("counter="+counter+" when i="+i+" interlen="+inter.length);
				for (int j = i + 1; j < inter.length; j++){
					inter[j - 1] = inter[j];
					i=-1;
				}
			}

		}
		//System.out.println("counter="+counter);
		this.tables = new String[inter.length - counter];
		for (i = 0; i < tables.length; i++)
			this.tables[i] = inter[i];
		//System.out.println("Tabel "+tables[i]+" pe pozitia "+i);

	}

	private void insert(String[] data) throws Exception {
		for (int i = 1; i < data.length; i++) 
			this.array[((this.index + i) % this.dimension)] = data[i - 1];
			this.quant = this.quant + data.length;
		
	}

	public String pull() {
		if(this.quant<=0)
			fill(this.dimension/2);
		String returned = new String();
		returned = this.array[this.index];
		index = (index + 1) % this.dimension;
		this.quant--;
		return returned;
	}

	public boolean fill(int amount) {
		//System.out.println(""+amount+" "+this.lastId+" "+ this.tables[this.currentTable]);
		String[] toBeInserted = Interfata.database.getScanlLinksFromDB(amount,
				this.lastId, this.tables[this.currentTable]);
		this.lastId += toBeInserted.length;
		if (toBeInserted.length < amount)
			this.currentTable++;
		if (this.currentTable >= this.tables.length) {
			this.currentTable = 0;
			this.lastId = 0;
		}

		try {
			insert(toBeInserted);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	

}
