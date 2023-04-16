package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 5;
	private boolean alarmState;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		if(alarmNeeded(speedLimit)){
			this.setAlarmState(true);
			user.setAlarmState(true);
		}else{
			this.setAlarmState(false);
			user.setAlarmState(false);
		}
		controller.setSpeedLimit(speedLimit);
	}

	@Override 
	public boolean getAlarmState() {
		return alarmState;
	}


	@Override
	public void setAlarmState(boolean alarmState){
		this.alarmState = alarmState;
	}

	@Override 
	public boolean alarmNeeded(int speed){
		
		if(speed < 0 || speed > 500){
			return true;
		}
		if (speed < controller.getReferenceSpeed()*0.5){
			return true;
		}
		return false;
	}

}
