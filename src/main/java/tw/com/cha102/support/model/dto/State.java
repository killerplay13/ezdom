package tw.com.cha102.support.model.dto;

import java.util.Set;

public class State {
	private String type;
	// the user changing the state
	private String memeberId;
	// total users
	private Set<String> memeberIds;

	public State(String type, String memeberId, Set<String> memeberIds) {
		super();
		this.type = type;
		this.memeberId = memeberId;
		this.memeberIds = memeberIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemeberId() {
		return memeberId;
	}

	public void setMemeberId(String memeberId) {
		this.memeberId = memeberId;
	}

	public Set<String> getMemeberIds() {
		return memeberIds;
	}

	public void setMemeberIds(Set<String> memeberIds) {
		this.memeberIds = memeberIds;
	}
}
