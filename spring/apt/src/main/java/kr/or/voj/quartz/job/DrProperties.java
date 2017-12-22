package kr.or.voj.quartz.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

public class DrProperties  {
	private String template;
	
	public String getTemplateRoot() {
		return template;
	}

	public void setTemplate(Resource template) {
		try {
			this.template = template.getFile()  + "/";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Value("#{dr['oadr.venId']}")
	public String VEN_ID;
	@Value("#{dr['oadr.meterid']}")
	public String  METERID;

	@Value("#{dr['oadr.template.event.poll']}")
	public String TEMPLA_EVENT_POLLT;


	@Value("#{dr['oadr.vtn.adderss.event.poll']}")
	public String VTN_TRANSPORT_ADDRESS_POOL;
	
	@Value("#{dr['oadr.vtn.adderss.ismamt_power']}")
	public String VTN_ADDERSS_ISMAMT_POWER;

	@Value("#{dr['oadr.vtn.adderss.cbl']}")
	public String VTN_ADDERSS_CBL;
	
	@Value("#{dr['oadr.template.smartmeter.send']}")
	public String TEMPLA_SMARTMETER_SEND;
	
	@Value("#{dr['oadr.vtn.adderss.smamtmeter_power']}")
	public String VTN_ADDERSS_SMAMTMETER_POWER;

	@Value("#{dr['oadr.vtn.aes.key']}")
	public String SECRET_KEY;

	@Value("#{dr['oadr.vtn.secret.enable']}")
	public String SECRET_ENABLE;
	
	@Value("#{dr['ioem.adderss.control.device']}")
	public String CTL_DEV_URL;
	
	
}
