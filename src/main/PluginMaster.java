package main;

import java.io.File;

public class PluginMaster {

	public int nr_of_plugins;
	
	public int getNr_of_plugins() {
		return nr_of_plugins;
	}	

	public PluginContainer[] plugin;

	public PluginMaster(boolean verbose) throws Exception {
		NCBUtils.talk("Loading plugins...");
		File directory = new File("plugins");
		File[] ft = directory.listFiles();
		int n = 0;
		this.nr_of_plugins=1;
		File[] f = new File[ft.length];
		for (int i = 0; i <= ft.length-1; i++) {
			if (ft[i].getName().contains(".ncbp")) {

				this.nr_of_plugins++;
				f[n] = ft[i];
				n++;
			}
		}
		plugin =new PluginContainer[this.nr_of_plugins];
		plugin[0]=new LinkPlugin(true);
		for(int i=1;i<this.nr_of_plugins ;i++){
			if (verbose) {
				//sNCBUtils.talk(i+"");
				NCBUtils.talk(f[i-1].getName());
			}			
			plugin[i]=new PluginContainer(f[i-1], false);
		}
		
		
				
		
	}

}
