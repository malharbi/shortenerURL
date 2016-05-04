package command;

public class GetUrlcommand {
	SearchUrl search =new SearchUrl();
	String originalUrl = new String();
	
	public String execute(String id){
		try {
			if (search.searchByID(id)) {
				String oldUrl = search.originalUrl;	
				return oldUrl;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	}
