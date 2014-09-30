package main;



public class LinkPlugin extends PluginContainer implements PluginInterface  {
	
	
	
	public LinkPlugin(boolean verbose){
		super.name="LinkPlugin";
		super.has_date=false;
		super.plugin_is_standard=true;
		super.sql_table="links";
		super.syntax_clean_number=15;
		super.syntax_test_hii_number=2;
		super.syntax_test_has_in_it_nr = new String[super.syntax_test_hii_number];
		super.syntax_is_on_page_number=0;
		
		super.syntax_test_has_in_it_nr[0]="www\\.";
		super.syntax_test_has_in_it_nr[1]="href=";
		
		super.syntax_clean_nr=new String[super.syntax_clean_number];
		
		super.syntax_clean_nr[0]="href=";
		super.syntax_clean_nr[1]="http://";
		super.syntax_clean_nr[2]="action=";
		super.syntax_clean_nr[3]="\"";
		super.syntax_clean_nr[4]="\".*";
		super.syntax_clean_nr[5]="\'";
		super.syntax_clean_nr[6]="#";
		super.syntax_clean_nr[7]="<.*";
		super.syntax_clean_nr[8]="\\n";
		super.syntax_clean_nr[9]="/style.css";
		super.syntax_clean_nr[10]=">.*";
		super.syntax_clean_nr[11]="javascript.*";
		super.syntax_clean_nr[12]="/n$";
		super.syntax_clean_nr[13]="^src=";
		super.syntax_clean_nr[14]="^class=";
		
		if(verbose)
		NCBUtils.talk("Link plugin started");
	}
	
	

}
