package ar.com.traceminator.entidades;

public class Flow {
	
	private String name;
	private String bar;
	private String queues;
	private int instAdicionales;
	private String userTrace;
	private int numero;
	private String estado;
	
	public Flow(String name, String bar, String queues, int instAdicionales, String userTrace, int numero, String estado) {
		super();
		this.name = name;
		this.bar = bar;
		this.queues = queues;
		this.instAdicionales = instAdicionales;
		this.userTrace = userTrace;
		this.numero = numero;
		this.estado = estado;
	}
	
	public Flow(){
		
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	public String getQueues() {
		return queues;
	}

	public void setQueues(String queues) {
		this.queues = queues;
	}

	public int getInstAdicionales() {
		return instAdicionales;
	}

	public void setInstAdicionales(int instAdicionales) {
		this.instAdicionales = instAdicionales;
	}

	public String getUserTrace() {
		return userTrace;
	}

	public void setUserTrace(String userTrace) {
		this.userTrace = userTrace;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
