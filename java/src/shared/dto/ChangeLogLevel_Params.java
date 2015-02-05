package shared.dto;

public class ChangeLogLevel_Params {
	
	private enum LogLevels {
		ALL, SEVERE, WARNING, INFO, FINE, FINER, FINEST, OFF;	
	}
	
	private LogLevels logLevel;

	public ChangeLogLevel_Params() {}

	public ChangeLogLevel_Params(String logLevel) {
		super();
		setLogLevel(logLevel);
	}

	public String getLogLevel() {
		return logLevel.toString();
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = LogLevels.valueOf(logLevel);
	}

}
