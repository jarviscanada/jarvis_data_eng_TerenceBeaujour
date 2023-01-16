package ca.jrvs.practice.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class VolumeUtilTest {

    @Mock
    AudioManager audioManagerMock;

    @Test
    public void testNormalRingerIsMaximized() {
        // 1.) Ensure AudioManager gets mocked
        // 2.) configure Audiomanager to return RINGER_MODE_NORMAL if getRinderMode is called
        when(audioManagerMock.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_NORMAL);
        // 3.) configure Audiomanager to return 100 if getStreamMaxVolume() is called
        when(audioManagerMock.getStreamMaxVolume()).thenReturn(100);
        // 4.) call VolumeUtil.maximizeVolume with Audiomanager -> code under test
        VolumeUtil.maximizeVolume(audioManagerMock);
        // 5.) verify that setStreamVolume(100) was called on audioManager
        verify(audioManagerMock).setStreamVolume(100);
    }

    @Test
    public void testSilentRingerIsNotDisturbed() {
        // 1.) Ensure AudioManager gets mocked
        // 2.) configure audiomanager to return "RINGER_MODE_SILENT" if getRingerMode is called
        when(audioManagerMock.getRingerMode()).thenReturn(RINGER_MODE.RINGER_MODE_SILENT);
        // 3.) call VolumeUtil.maximizeVolume with audio manager
        VolumeUtil.maximizeVolume(audioManagerMock);
        // 4.) verify that getRingerMode() is called on the mock
        verify(audioManagerMock, atLeastOnce()).getRingerMode();
        // 5.) Ensure that nothing more was called
        verifyNoMoreInteractions(audioManagerMock);
    }
}