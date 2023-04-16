package hu.bme.mit.train.user;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import java.util.Timer;
import java.util.TimerTask;

public class TrainUserImpl implements TrainUser {

	private TrainController controller;
	private int joystickPosition;
	private boolean alarmState;

	public TrainUserImpl(TrainController controller) {
		this.controller = controller;
	}

	@Override
	public boolean getAlarmFlag() {
		return false;
	}

	@Override
	public int getJoystickPosition() {
		return joystickPosition;
	}

	@Override
	public void overrideJoystickPosition(int joystickPosition) {
		this.joystickPosition = joystickPosition;


		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				controller.setJoystickPosition(joystickPosition);
			}
		}, 5000);


		//controller.setJoystickPosition(joystickPosition);
	}

	
	@Override 
	public boolean getAlarmState() {
		return alarmState;
	}


	@Override
	public void setAlarmState(boolean alarmState){
		this.alarmState = alarmState;
	}
}
