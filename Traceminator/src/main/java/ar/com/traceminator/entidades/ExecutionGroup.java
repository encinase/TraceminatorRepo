package ar.com.traceminator.entidades;

public class ExecutionGroup {
	
	public String name;
	public String uuid;
	public String state;
	public int debugPort;
	public String listenerPort;
	public String userLevel;
	public String userTraceLevel;
	
	public ExecutionGroup(String name, String uuid, String state,int debugPort, String listenerPort, String userLevel, String userTraceLevel){
		super();
		this.name = name;
		this.uuid = uuid;
		this.state = state;
		this.debugPort = debugPort;
		this.listenerPort = listenerPort;
		this.userLevel = userLevel;
		this.userTraceLevel = userTraceLevel;
	}
	
	public ExecutionGroup(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getDebugPort() {
		return debugPort;
	}

	public void setDebugPort(int debugPort) {
		this.debugPort = debugPort;
	}

	public String getListenerPort() {
		return listenerPort;
	}
	
	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getUserTraceLevel() {
		return userTraceLevel;
	}

	public void setUserTraceLevel(String userTraceLevel) {
		this.userTraceLevel = userTraceLevel;
	}

	public void setListenerPort(String listenerPort) {
		this.listenerPort = listenerPort;
	}
	
}
