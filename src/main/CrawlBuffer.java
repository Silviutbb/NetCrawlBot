package main;

public class CrawlBuffer {

	String[] array;
	String[] table;
	String[] adress;
	private int dimension;
	private int index;
	int quant;

	public CrawlBuffer(int size) {
		this.dimension = size;
		this.index = 0;
		this.quant = 0;
		this.array = new String[this.dimension];
		this.table = new String[this.dimension];
		this.adress = new String[this.dimension];
	}

	public void insert(String data, String tableTo, String from) {
		if(tableTo=="links")
			NCBUtils.patternReplace(data, "//", "/");
		this.array[(this.index + this.quant) % this.dimension] = data;
		this.table[(this.index + this.quant) % this.dimension] = tableTo;
		this.adress[(this.index + this.quant) % this.dimension] = NCBUtils
				.getHash(from); // hashed here so shut up
		this.quant++;
		if (this.quant >= this.dimension)
			flushBuffer();
	}

	private String[][] pull() {
		String[][] pulled = new String[3][this.quant];
		
		for (int i = 0; i < this.quant; i++) {
			pulled[0][i] = this.array[(this.index + i) % this.dimension];
			pulled[1][i] = this.table[(this.index + i) % this.dimension];
			pulled[2][i] = this.adress[(this.index + i) % this.dimension];
		}
		this.index = (index + quant) % dimension;
		this.quant = 0;
		return pulled;
	}

	public boolean flushBuffer() {
		String[][] data = this.pull();
		Interfata.database.sendToDatabase(data[0], data[2], data[1]);
		return true;
	}
}
