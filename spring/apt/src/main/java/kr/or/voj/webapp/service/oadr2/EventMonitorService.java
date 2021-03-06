package kr.or.voj.webapp.service.oadr2;

import kr.or.voj.quartz.job.DrProperties;
import kr.or.voj.webapp.processor.ProcessorServiceFactory;
import kr.or.voj.webapp.utils.*;
import net.ion.oadr2.service.impl.Lookup;
import net.ion.open.oadr2.model.v20b.*;
import net.ion.open.oadr2.model.v20b.ei.*;
import net.ion.open.oadr2.model.v20b.strm.StreamPayloadBase;
import net.ion.open.oadr2.model.v20b.xcal.Properties;
import net.ion.open.oadr2.model.v20b.xcal.Uid;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import java.io.File;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class EventMonitorService {
	private static final Logger logger = Logger.getLogger(EventMonitorService.class);
	private static DrProperties drProperties = (DrProperties)ProcessorServiceFactory.getBean(DrProperties.class);

	public static void run() throws Exception {
		File file = null;
		// logger.warn("OpenAdr Event ReceiveJob Start!! : AES - "+secretKey);

		try {
			if (drProperties == null) {
				logger.error("DrProperties 정보를 가져올 수 없습니다.");
				return;
			}

			file = File.createTempFile("event_", ".xml");
			OadrPayload payload = new OadrPayload();
			OadrSignedObject signedObj = new OadrSignedObject();
			OadrPoll oadrPoll = new OadrPoll();
			oadrPoll.setVenID(JCEUtils.encrypt(drProperties.VEN_ID));
			signedObj.setOadrPoll(oadrPoll);
			payload.setOadrSignedObject(signedObj);

			StringWriter sw = new StringWriter();
			Marshaller marshaller = Lookup.getMarshaller(payload);
			marshaller.marshal(payload, sw);
			String reqXml = XmlUtil.format(sw.toString());
			logger.debug(reqXml);

			String target_addr = drProperties.VTN_TRANSPORT_ADDRESS_POOL;
			OadrHttpUtils.pull(target_addr, reqXml, file);
			String resXml = FileUtils.readFileToString(file, "UTF-8");

			// 응답 파일 저장
//			String fileId = null;
//			if(!StringUtils.isEmpty(resXml))
//				fileId = RepositoryUtils.save("EVENT", file);


			dispatch(target_addr, reqXml, resXml, file);

			// String src = "D:/oadr/2015/03/09_182000_abd55cbb-789d-44ca-af37-1a10e6260855.xml";
//			String xpath = "ns7:oadrPayload/ns7:oadrSignedObject/ns7:oadrDistributeEvent/ns7:oadrEvent/ns2:eiEvent";
//			String[][] paths = {
//					{"event_id", 	"ns2:eventDescriptor/ns2:eventID/text()"},
//					{"created_date","ns2:eventDescriptor/ns2:createdDateTime/text()"},
//					{"event_status","ns2:eventDescriptor/ns2:eventStatus/text()"},
//					{"test_event", 	"ns2:eventDescriptor/ns2:testEvent/text()"},
//					{"start_date", 	"ns2:eiActivePeriod/ns5:properties/ns5:dtstart/ns5:date-time/text()"},
//					{"tot_duration","ns2:eiActivePeriod/ns5:properties/ns5:duration/ns5:duration/text()"},
//
//					{"signal_name", "ns2:eiEventSignals/ns2:eiEventSignal/ns2:signalName/text()"},
//					{"signal_type", "ns2:eiEventSignals/ns2:eiEventSignal/ns2:signalType/text()"},
//					{"signal_id", 	"ns2:eiEventSignals/ns2:eiEventSignal/ns2:signalID/text()"}
//			};
//
//			String[][] loopPaths = {
//					{"duration",   	"ns2:eiEventSignals/ns2:eiEventSignal/ns6:intervals/ns2:interval/ns5:duration/ns5:duration/text()"},
//					{"duration_seq","ns2:eiEventSignals/ns2:eiEventSignal/ns6:intervals/ns2:interval/ns5:uid/ns5:text/text()"},
//					{"reduction", 	"ns2:eiEventSignals/ns2:eiEventSignal/ns6:intervals/ns2:interval/ns2:signalPayload/ns2:payloadFloat/ns2:value/text()"},
//			};
//
//			NodeList nodes = XpathUtils.readNodes(file, xpath, null);
//
//			String fileId = null;
//			if(nodes.getLength() > 0){
//				//저장소에 저장
//				fileId = RepositoryUtils.save("EVENT", file);
//				logger.debug("OpenAdr Event 수신 파일 : " + fileId);
//			}else{
//				logger.debug("OpenAdr Event 수신 파일 : 데이타 없음");
//			}
//
//			for (int i = 0; i < nodes.getLength(); i++) {
//				try {
//					Node node = nodes.item(i);
//
//					List<Map<String, Object>> list = XpathUtils.read(file, paths, node);
//					List<Map<String, Object>> subList = XpathUtils.read(file, loopPaths, node);
//
//					double reduction = 0;
//
//					for(Map<String, Object> row : subList){
//						String reductionStr = (String)row.get("reduction");
//						reduction += Double.parseDouble(reductionStr);
//					}
//
//					//size==0
//                    logger.debug(list);
//					for(Map<String, Object> param : list){
//						CaseInsensitiveMap params = new CaseInsensitiveMap();
//						params.putAll(param);
//						params.put("file_id", fileId);
//						params.put("tot_reduction", reduction);
//
//						String test_event = (String)params.get("test_event");
//						params.put("test_event", "true".equalsIgnoreCase(test_event) ? "Y" : "N");
//						params.put("tot_duration", TimeUtils.getMinute((String)params.get("tot_duration")));
//						params.put("created_date", TimeUtils.getDateGMT((String)params.get("created_date")));
//						params.put("start_date", TimeUtils.getDateGMT((String)params.get("start_date")));
//
//
//                        int x = TimeUtils.getMinute((String) params.get("tot_duration"));
//                        logger.debug("tot_reduction : "+reduction+",tot_duration : "+x);
//
//						ProcessorServiceFactory.executeQuery("drApi", "event", params);
//					}
//
//				} catch (Exception e) {
//					logger.error("OpenAdr Event DB 처리 오류", e);
//					//isError = true;
//					//ex = e;
//				}
//			}


			ProcessorServiceFactory.executeQuery("drApi", "evenCancel", new CaseInsensitiveMap());

		}finally{
			FileUtils.deleteQuietly(file);
		}
		logger.debug("OpenAdr Event Receive Complete");
	}

	private static void dispatch(String target_addr, String reqXml, String resXml, File file) throws Exception {
		logger.debug(resXml);
		Object payload = OadrUtils.unmarshal(resXml);
		if(payload == null)
			throw new Exception("Payload must not be null");

		payload = OadrUtils.unwrap(payload);

//		DatatypeFactory xmlDataTypeFac = DatatypeFactory.newInstance();
//		javax.xml.datatype.Duration duration = xmlDataTypeFac.newDuration( true, 0, 0, 0, 0, 5, 0 );
//
//        DurationPropType dpt = new DurationPropType(new DurationValue(duration.toString()));
//		logger.debug(duration.toString());
//        logger.debug(dpt.getDuration().getValue());
//
//		duration = xmlDataTypeFac.newDuration("PT10M");
//		logger.debug(duration.getMinutes());
//		logger.debug(duration.getSeconds());


		if(payload instanceof OadrDistributeEvent) {
			List<OadrDistributeEvent.OadrEvent> oadrEvents = ((OadrDistributeEvent)payload).getOadrEvents();
			String fileId = null;
			if(oadrEvents.size()>0) {
				fileId = RepositoryUtils.save("EVENT", file);
			}
			for (OadrDistributeEvent.OadrEvent oadrEvent : oadrEvents) {
				String eventType = "adr_" + oadrEvent.getEiEvent().getEventDescriptor().getEventStatus().value();
				if(eventType.equals("adr_completed")) {
					logger.debug(eventType);
					break;
				}
				parseEiEventReport(oadrEvent, reqXml, resXml, fileId);
			}
			return;
		}

		if(payload instanceof OadrResponse) {
			logger.debug("RECEIVED OadrResponse");
			return;
		}

		throw new Exception("Unknown Payload Type for 2.0b profile : "+payload.getClass());
	}

	private static void parseEiEventReport(OadrDistributeEvent.OadrEvent oadrEvent, String reqXml, String resXml, String fileId) throws Exception {
		EiActivePeriod eiActivePeriod = oadrEvent.getEiEvent().getEiActivePeriod();
		Properties properties = eiActivePeriod.getProperties();
        String test_event = oadrEvent.getEiEvent().getEventDescriptor().getTestEvent();

        DatatypeFactory xmlDataTypeFac = DatatypeFactory.newInstance();
		EventDescriptor eventDescriptor = oadrEvent.getEiEvent().getEventDescriptor();
		String eventID = eventDescriptor.getEventID();
		String testEvent = eventDescriptor.getTestEvent();
		long modifyNum = eventDescriptor.getModificationNumber();
        Date credate = eventDescriptor.getCreatedDateTime().getValue().toGregorianCalendar().getTime();
		EventStatusEnumeratedType event_status = eventDescriptor.getEventStatus();
		Date stdate = properties.getDtstart().getDateTime().getValue().toGregorianCalendar().getTime();
		List<EiEventSignal> eventSignals = oadrEvent.getEiEvent().getEiEventSignals().getEiEventSignals();
		Float reduction = (float) 0;
        long tot_duration = 0;
        String signal_name = null;
        String signal_type = null;
        String signal_id = null;

		for (EiEventSignal eiEventSignal : eventSignals) {
            signal_name = eiEventSignal.getSignalName();
            signal_type = eiEventSignal.getSignalType().value();
            signal_id = new String(eiEventSignal.getSignalID().getBytes("utf-8"));
			List<Interval> intervals = eiEventSignal.getIntervals().getIntervals();
			for (Interval interval : intervals) {
                String dur = interval.getDuration().getDuration().getValue();
                tot_duration += dur2min(stdate, dur);
                Uid uid = interval.getUid();
                if (interval.getStreamPayloadBases() != null){
					List<JAXBElement<? extends StreamPayloadBase>> payloadBases = interval.getStreamPayloadBases();
					for (JAXBElement<? extends StreamPayloadBase> payloadBase : payloadBases) {
						SignalPayload signalPayload = (SignalPayload)payloadBase.getValue();
						JAXBElement<? extends PayloadBaseType> payload = signalPayload.getPayloadBase();
						PayloadBaseType payloadValue = payload.getValue();
						PayloadFloatType payloadFloat = (PayloadFloatType) payloadValue;
						reduction += payloadFloat.getValue();
					}
				}
			}
		}
		// apt_api.xml : (event_id, created_date,start_date,tot_duration,tot_reduction,event_status,test_event,signal_name,signal_type,signal_id,file_id)

        CaseInsensitiveMap params = new CaseInsensitiveMap();
		params.put("file_id", fileId);
        params.put("event_id",eventID);
		params.put("test_event", testEvent);
		params.put("event_status",event_status);
        params.put("modify_num",modifyNum);
        params.put("created_date", credate);
		params.put("start_date",stdate);
        params.put("signal_name",signal_name);
        params.put("signal_type",signal_type);
        params.put("signal_id",signal_id);
//		params.put("reqXml",reqXml);
//      params.put("resXml",resXml);
		params.put("tot_reduction", reduction);
        params.put("test_event", "true".equalsIgnoreCase(test_event) ? "Y" : "N");
        params.put("tot_duration", tot_duration);
        save(params);
	}

    private static long dur2min(Date stdate, String dur) throws Exception {
        DatatypeFactory df = DatatypeFactory.newInstance();
        javax.xml.datatype.Duration duration = df.newDuration(dur);
        long ms = duration.getTimeInMillis(stdate);
        return TimeUnit.MILLISECONDS.toMinutes(ms);
    }

    private static void save(CaseInsensitiveMap params) throws Exception {
        for (Object k : params.keySet()) {
            Object v = params.get(k);
            logger.debug(k.toString()+":"+v.toString());
        }
        ProcessorServiceFactory.executeQuery("drApi", "event", params);
    }

}
