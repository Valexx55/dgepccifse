package edu.meyfp.alumnos.dto;


/*
 * {
    "categories": [],
    "created_at": "2020-01-05 13:42:28.420821",
    "icon_url": "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
    "id": "19dSBhtrQnqn7azg_eF1fw",
    "updated_at": "2020-01-05 13:42:28.420821",
    "url": "https://api.chucknorris.io/jokes/19dSBhtrQnqn7azg_eF1fw", //HAL/HATEOAS
    "value": "Solid Snake has a beard because Chuck Norris bit his chin an it became a fist"
}
 */
public class FraseChuckNorris {
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public FraseChuckNorris(String value) {
		super();
		this.value = value;
	}
	
	public FraseChuckNorris() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "FraseChuckNorris [value=" + value + "]";
	}
	
	

}
