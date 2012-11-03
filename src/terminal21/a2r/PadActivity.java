package terminal21.a2r;

import terminal21.a2r.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.View ;
import android.view.View.OnTouchListener ;
import android.view.MotionEvent ;
import android.widget.LinearLayout ;
import android.widget.TextView ;

import terminal21.a2r.index.Index ;
import terminal21.a2r.index.Session ;
import terminal21.a2r.index.Sensor ;
import terminal21.a2r.transmitter.Entity;
import terminal21.a2r.transmitter.Transmitter ;

public class PadActivity extends Activity implements OnTouchListener {
	
	private TextView coordinates ;

	private Transmitter transmitter ;
	
	private Session session ;
	private Sensor sensor ;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState) ;
		setContentView(R.layout.pad);
		
		this.session = Index.getInstance().getCurrentSession() ;
		this.sensor = Index.getInstance().getCurrentSensor() ;
		
		this.transmitter = Transmitter.getInstance() ;
		this.transmitter.connect(this.session.getProxy(), this.sensor.getTargetPort()) ;
		
		this.coordinates = (TextView)findViewById(R.id.coordinates) ;
		LinearLayout layout = (LinearLayout)findViewById(R.id.padLayout) ;
		layout.setOnTouchListener(this) ;
	}
	
	public boolean onTouch(View view, MotionEvent event) {
		this.coordinates.setText(Float.toString(event.getX()) + ":" + Float.toString(event.getY())) ;
		
		transmitter.addEntity(new Entity(Entity.A, (short)(event.getX() * 65536 / view.getWidth()))) ;
		transmitter.addEntity(new Entity(Entity.B, (short)(event.getY() * 65536 / view.getHeight()))) ;
		
		this.transmitter.emit() ;
		
		return true ;
	}
}