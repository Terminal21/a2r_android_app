package terminal21.a2r.index;

import java.util.Vector ;

public class Session {
	private CharSequence title ;
	private Vector<Sensor> sensors ;
	
	public Session(CharSequence title) {
		this.title = title ;
		this.sensors = new Vector<Sensor>() ;
	}
	
	public CharSequence getTitle() {
		return this.title ;
	}
	
	public void addSensor(Sensor sensor) {
		this.sensors.add(sensor) ;
	}
	
	public Vector<Sensor> getSensors() {
		return this.sensors ;
	}
	
	public boolean hasSensors() {
		return !sensors.isEmpty() ;
	}
}
