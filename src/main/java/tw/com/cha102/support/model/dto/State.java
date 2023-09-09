package tw.com.cha102.support.model.dto;

import java.util.Set;

public class State {
	private String type;
	// the user changing the state
	private String memberId;
	// total users
	private Set<String> memberIds;

	public State(String type, String memberId, Set<String> memberIds) {
		super();
		this.type = type;
		this.memberId = memberId;
		this.memberIds = memberIds;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Set<String> getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(Set<String> memberIds) {
		this.memberIds = memberIds;
	}
}
