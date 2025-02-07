package hu.bme.mit.train.interfaces;

public interface TrainSensor {

	int getSpeedLimit();

	void overrideSpeedLimit(int speedLimit);

	void setAlarmState(boolean state);

	boolean getAlarmState();

	boolean alarmNeeded(int speed);

}
