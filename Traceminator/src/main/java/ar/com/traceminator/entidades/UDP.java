package ar.com.traceminator.entidades;

public class UDP {
	
	public String flowName;
	public String udpName;
	public String udpValue;
	
	public UDP(String flowName, String udpName, String udpValue){
		super();
		this.flowName = flowName;
		this.udpName = udpName;
		this.udpValue = udpValue;
	}
	
	public UDP(){
		
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getUdpName() {
		return udpName;
	}

	public void setUdpName(String udpName) {
		this.udpName = udpName;
	}

	public String getUdpValue() {
		return udpValue;
	}

	public void setUdpValue(String udpValue) {
		this.udpValue = udpValue;
	}

}
