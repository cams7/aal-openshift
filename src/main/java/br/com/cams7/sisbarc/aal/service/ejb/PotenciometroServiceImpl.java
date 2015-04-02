/**
 * 
 */
package br.com.cams7.sisbarc.aal.service.ejb;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.cams7.sisbarc.aal.jpa.domain.entity.PotenciometroEntity;
import br.com.cams7.sisbarc.aal.jpa.domain.pk.PinPK;

/**
 * @author cams7
 *
 */
@Stateless
@Local(PotenciometroService.class)
public class PotenciometroServiceImpl extends
		AALServiceImpl<PotenciometroEntity, PinPK> implements
		PotenciometroService {

	public PotenciometroServiceImpl() {
		super();
	}

}
