package shared.dto;

public class AddAI_Params {

	String AIType;
	
	public AddAI_Params() {}

	public AddAI_Params(String aIType) {
		super();
		setAIType(aIType);
	}

	public String getAIType() {
		return AIType;
	}

	public void setAIType(String aIType) {
		AIType = aIType;
	}
}
