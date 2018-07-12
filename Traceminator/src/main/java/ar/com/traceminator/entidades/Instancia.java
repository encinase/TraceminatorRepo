package ar.com.traceminator.entidades;

public class Instancia {
	
	public String broker;
	public String eg;
	public String flowName;
	public int value;
	
	public Instancia(String broker, String eg, String flowName, int value){
		super();
		this.broker = broker;
		this.eg = eg;
		this.flowName = flowName;
		this.value = value;
	}
	
	public Instancia(){
		
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getBroker() {
		return broker;
	}

	public void setBroker(String broker) {
		this.broker = broker;
	}

	public String getEg() {
		return eg;
	}

	public void setEg(String eg) {
		this.eg = eg;
	}

}
