package ar.com.traceminator.entidades;

public class Mensaje {
	
	
	private int numero;
	private String fecha;
	private String qmgr;
	private String cola;
	private String msgid;
	private String correlid;
	private String msg;
	private String msgFormateado;
	
	public Mensaje(int numero, String fecha, String qmgr, String cola, String msgid, String correlid, String msg, String msgFormateado){
		super();
		this.numero = numero;
		this.fecha = fecha;
		this.qmgr = qmgr;
		this.cola = cola;
		this.msgid = msgid;
		this.correlid = correlid;
		this.msg = msg;
		this.msgFormateado = msgFormateado;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getQmgr() {
		return qmgr;
	}

	public void setQmgr(String qmgr) {
		this.qmgr = qmgr;
	}

	public String getCola() {
		return cola;
	}

	public void setCola(String cola) {
		this.cola = cola;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getCorrelid() {
		return correlid;
	}

	public void setCorrelid(String correlid) {
		this.correlid = correlid;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String getMsgFormateado() {
		return msgFormateado;
	}

	public void setMsgFormateado(String msgFormateado) {
		this.msgFormateado = msgFormateado;
	}

	public Mensaje(){
		
	}

	

}
