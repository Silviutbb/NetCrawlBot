package main;

public class Interfata {
	
	public static GraphicUserInterface gr;
	public static Settings settings;
	public static PluginMaster plugins;
	public static BufferMain buffers;
	public static PostgreConnection database;
	public static WorkMaster masterWorker;
	public static BackEnd backEnd;
	public static Timer timer;
	
	private static boolean loader(){
		
		try {
			timer = new Timer();
			@SuppressWarnings("unused")
			UpdateManager um = new UpdateManager("http://silviu.condruz.com/ncbversion.txt", 2);
			um = null;
			backEnd=new BackEnd(1000);
			
			gr = new GraphicUserInterface();
			NCBUtils.talk("Starting loader ... ");
			settings=new Settings(true);
			database=new PostgreConnection();
			if(!PostgreConnection.testConnection())
				return false;
			plugins= new PluginMaster(false);
			backEnd.start();
			database.testDatabaseIntegrity();
			buffers= new BufferMain(true);			
			masterWorker = new WorkMaster();
			
		
		
		} catch (Exception e) {
			e.printStackTrace();
			NCBUtils.talk("Loader failed with exception!");
			return false;
		}	
		
		
		NCBUtils.talk("Loader done!");
		return true;
	}
	
	
	public static void main(String[] args) {
		if(loader()){
						
			buffers.start();
			masterWorker.start();
			
			
			
		}
		else{
			NCBUtils.talk("Loader failed!");
			buffers.stop();
			masterWorker.stop();
			
		}
		
		
		
	}
	
}
