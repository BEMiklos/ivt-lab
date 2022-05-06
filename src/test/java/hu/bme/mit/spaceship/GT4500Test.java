package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primTS, secTS;

  @BeforeEach
  public void init(){
    this.primTS = mock(TorpedoStore.class);
    this.secTS = mock(TorpedoStore.class);
    this.ship = new GT4500(primTS,secTS);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    when(primTS.isEmpty()).thenReturn(false);
    when(primTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primTS,times(1)).isEmpty();
    verify(primTS,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(primTS.isEmpty()).thenReturn(false);
    when(primTS.fire(1)).thenReturn(true);
    when(secTS.isEmpty()).thenReturn(false);
    when(secTS.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(primTS,times(0)).isEmpty();
    verify(primTS,times(1)).fire(1);
    verify(secTS,times(0)).isEmpty();
    verify(secTS,times(1)).fire(1);
  }

}
