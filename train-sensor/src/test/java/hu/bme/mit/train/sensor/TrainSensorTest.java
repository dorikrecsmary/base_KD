package hu.bme.mit.train.sensor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainUser;

import static org.mockito.Mockito.*;

public class TrainSensorTest {

    private TrainSensorImpl sensor;
    private TrainController controllerMock;
    private TrainUser userMock;

    @Before 
    public void init(){
        controllerMock = mock(TrainController.class);
        userMock = mock(TrainUser.class);
        sensor = new TrainSensorImpl(controllerMock, userMock);
    }

    @Test
    public void SpeedLimitIsBelowZero(){
        int newSpeedLimit = -1;
        boolean expectedAlarmState = true;

        sensor.overrideSpeedLimit(newSpeedLimit);

        assertTrue(expectedAlarmState== sensor.getAlarmState());
    }

    @Test
    public void SpeedLimitIsAbove500(){
        int newSpeedLimit = 500;
        boolean expectedAlarmState = true;

        sensor.overrideSpeedLimit(newSpeedLimit);

        assertTrue(expectedAlarmState==sensor.getAlarmState());
    }

    @Test
    public void SpeedLimitIsOverRealtiveLimit(){    
        int speedLimit = 5;
        int referenceSpeed = 50;
        boolean expectedAlarmState = true;

        controllerMock.setReferenceSpeed(referenceSpeed);
        sensor.overrideSpeedLimit(speedLimit);

        assertTrue(expectedAlarmState==sensor.getAlarmState());
    }

    @Test
    public void SpeedLimitIsOK(){
        int speedLimit = 5;
        boolean expectedAlarmState = false;

        sensor.overrideSpeedLimit(speedLimit);

        assertTrue(expectedAlarmState == sensor.getAlarmState());
    }

}
