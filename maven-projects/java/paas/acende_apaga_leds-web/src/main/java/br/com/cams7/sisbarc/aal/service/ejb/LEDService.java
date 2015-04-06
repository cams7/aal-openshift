/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.ejb;

import java.util.List;
import java.util.concurrent.Future;

import br.com.cams7.arduino.ArduinoException;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.entity.LEDEntity.EstadoLED;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;
import br.com.cams7.util.AppException;

/**
 * @author cams7
 *
 */
public interface LEDService extends AALService<LEDEntity, PinPK> {

	public Future<LEDEntity> alteraLEDEstado(LEDEntity led)
			throws ArduinoException;

	public Future<List<LEDEntity>> getLEDsAtivadoPorBotao()
			throws ArduinoException;

	public EstadoLED getEstadoLEDAtivadoPorBotao(byte pin) throws AppException;

}
