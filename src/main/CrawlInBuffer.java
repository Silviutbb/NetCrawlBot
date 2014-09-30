package main;

public class CrawlInBuffer {
	String[] array;
	private int dimension;
	private int index;
	int quant;
	
	public CrawlInBuffer(int size){
		this.dimension=size;
		this.index=0;
		this.quant=0;
		this.array= new String[this.dimension];		
	}
	
	private void insert(String[] data) throws Exception{
		for(int i=1; i<=data.length;  i++){
			this.array[((this.index+i)%this.dimension)]=data[i-1];
			this.quant=this.quant+data.length;
			if(array[this.index]==null)
				this.index=(this.index+1)%this.dimension;
		}
	}
	public String pull(){
		String returned=new String();
		try {
			returned=this.array[this.index];
		} catch (Exception e) {
		}
		index=(index+1)%this.dimension;
		this.quant--;
		if(this.quant<=0)
			fill(Interfata.settings.ncb_buffer * 80 / 1000);
		return returned;
	}
	
	public boolean fill(int amount){
		try {
			insert(Interfata.database.getCrawlLinksFromDB(amount));
			
		} catch (Exception e) {
			NCBUtils.talk("Error getting links #1");
			return false;
		}
		
			return true;
	}

}
