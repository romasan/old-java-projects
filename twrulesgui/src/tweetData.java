//import java.util.ArrayList;


public class tweetData {
	private String boobs;
    private double completed_in;
    private String max_id;
    private String max_id_str;
    private String next_page;
    private int page;
    private String query;
    private String refresh_url;
    private String since_id;
    private int results_per_page;
    private String since_id_str;
    private String previous_page;//Exception in thread "main" com.bigfatgun.fixjures.FixtureException: Could not find type of method for previous_page
    //private ArrayList<tweetDetails> results = new ArrayList<tweetDetails>();
    private String results;
	public String getRefresh_url() {
		return refresh_url;
	}
	public void setRefresh_url(String refresh_url) {
		this.refresh_url = refresh_url;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getNext_page() {
		return next_page;
	}
	public void setNext_page(String next_page) {
		this.next_page = next_page;
	}
	public double getCompleted_in() {
		return completed_in;
	}
	public void setCompleted_in(double completed_in) {
		this.completed_in = completed_in;
	}
	public String getSince_id() {
		return since_id;
	}
	public void setSince_id(String since_id) {
		this.since_id = since_id;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public String getMax_id_str() {
		return max_id_str;
	}
	public void setMax_id_str(String max_id_str) {
		this.max_id_str = max_id_str;
	}
	public String getMax_id() {
		return max_id;
	}
	public void setMax_id(String max_id) {
		this.max_id = max_id;
	}
	public int getResults_per_page() {
		return results_per_page;
	}
	public void setResults_per_page(int results_per_page) {
		this.results_per_page = results_per_page;
	}
	public String getBoobs() {
		return boobs;
	}
	public void setBoobs(String boobs) {
		this.boobs = boobs;
	}
	public String getSince_id_str() {
		return since_id_str;
	}
	public void setSince_id_str(String since_id_str) {
		this.since_id_str = since_id_str;
	}
	public String getPrevious_page() {
		return previous_page;
	}
	public void setPrevious_page(String previous_page) {
		this.previous_page = previous_page;
	}

}
