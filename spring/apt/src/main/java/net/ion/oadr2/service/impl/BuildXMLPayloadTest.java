/*
 * Copyright 2014 kt corp. All rights reserved.
 * This is a proprietary software of kt corp, and you may not use this file except in 
 * compliance with license agreement with kt corp. Any redistribution or use of this 
 * software, with or without modification shall be strictly prohibited without prior written 
 * approval of kt corp, and the copyright notice above does not evidence any actual or 
 * intended publication of such software.
 */ 


package net.ion.oadr2.service.impl;

import net.ion.oadr2.service.ADRConstants;
import net.ion.oadr2.utils.ISOUtils;
import net.ion.oadr2.utils.UUID;
import net.ion.open.oadr2.model.v20b.*;
import net.ion.open.oadr2.model.v20b.ei.*;
import net.ion.open.oadr2.model.v20b.emix.MarketContext;
import net.ion.open.oadr2.model.v20b.power.EndDeviceAsset;
import net.ion.open.oadr2.model.v20b.power.PowerAttributes;
import net.ion.open.oadr2.model.v20b.power.PowerRealType;
import net.ion.open.oadr2.model.v20b.xcal.*;
import net.ion.open.oadr2.model.v20b.xcal.Interval;
import net.ion.open.oadr2.xmpp.v20b.JAXBManager;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.joda.time.Duration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class BuildXMLPayloadTest {

    @Mock
    protected static Logger LOGGER = Logger.getLogger(BuildXMLPayloadTest.class);

	static net.ion.open.oadr2.xmpp.JAXBManager jaxbManager20a = null;
	static JAXBManager jaxbManager20b = null;

	static Marshaller marshaller20a = null;
	static Marshaller marshaller20b = null;

	@BeforeClass
	public static void setup() throws JAXBException{
		jaxbManager20b = new JAXBManager();
		jaxbManager20a = new net.ion.open.oadr2.xmpp.JAXBManager();

		marshaller20a = jaxbManager20a.getContext().createMarshaller();
		marshaller20b = jaxbManager20b.getContext().createMarshaller();
	}

	public BuildXMLPayloadTest() throws JAXBException{
		jaxbManager20b = new JAXBManager();
		jaxbManager20a = new net.ion.open.oadr2.xmpp.JAXBManager();

		marshaller20a = jaxbManager20a.getContext().createMarshaller();
		marshaller20b = jaxbManager20b.getContext().createMarshaller();
	}

    @Test
	public static OadrCreateReport reportRequest_R1_3010_TH_VEN() throws JAXBException, DatatypeConfigurationException{

    	List<OadrReportRequest> reports = new ArrayList<OadrReportRequest>();


		XMLGregorianCalendar startDate = ISOUtils.newXMLGregorianCalendar();

		TemperatureType temperatureType = new TemperatureType()
			.withItemDescription("temperature")
			.withItemUnits(TemperatureUnitType.CELSIUS)
			.withSiScaleCode("none"); // the value must be one of them [p, n, micro, m, c, d, k, M, G, T, none]


		SpecifierPayload specifierPayload = new SpecifierPayload()
			.withRID("rID021213_052118_454_0")
			.withReadingType(ReadingTypeEnumeratedType.X_NOT_APPLICABLE.value());
//			.withItemBase(new ObjectFactory().createTemperature(temperatureType));

		List<SpecifierPayload> specifierPayloads = new ArrayList<SpecifierPayload>();
		specifierPayloads.add(specifierPayload);


		ReportSpecifier reportSpecifier = new ReportSpecifier()
		.withReportSpecifierID("ReportSpecifierID021213_052117_954_0")

		.withSpecifierPayloads(specifierPayloads)
		.withGranularity(new DurationPropType(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString())))
		.withReportBackDuration(new DurationPropType(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString())))
		.withReportInterval(new Interval()
			.withProperties(new Properties()
				.withDtstart(new Dtstart().withDateTime(new DateTime(startDate)))
				.withDuration(new DurationPropType(new DurationValue(new Duration(0).toStandardMinutes().toString())))
			)
		);

        verify(LOGGER).setLevel(Level.INFO);
        verify(LOGGER).info("message");
        LOGGER.info("xxxx");
        // verify(appender).doAppend(logCaptor.capture());
        // assertThat("1",is("1"));



		reports.add(new OadrReportRequest()
                        .withReportRequestID(UUID.id())
                        .withReportSpecifier(reportSpecifier)
        );



		OadrCreateReport registerReport = new OadrCreateReport()
		.withOadrReportRequests(reports)
		.withVenID("TH_VEN")
		.withSchemaVersion(ADRConstants.PROFILE_20B)
		.withRequestID(UUID.id());




		StringWriter writer = new StringWriter();
		marshaller20b.marshal(registerReport, writer);

		System.out.println("OadrCreateReport_R1_3010_TH_VEN");
		System.out.println(writer.toString());

		return registerReport;
	}


//	@Test
	public void report1() throws JAXBException{
		XMLGregorianCalendar startDate = ISOUtils.newXMLGregorianCalendar();

		OadrReport report = new OadrReport()
			.withCreatedDateTime(new DateTime(startDate))
			.withDuration(new DurationPropType(new DurationValue(new Duration(60 * 60 * 1000).toStandardMinutes().toString())))
			.withReportRequestID("0")
			.withReportSpecifierID("MetadataTelemetryStatus_0")
			.withReportName(ReportNameEnumeratedType.METADATA_TELEMETRY_STATUS.value())
			.withOadrReportDescriptions(new OadrReportDescription()
					.withRID("my thermostat 1")
					.withReportSubject(new EiTarget().withEndDeviceAssets(new EndDeviceAsset("Thermostat")))
					.withReportDataSource(new EiTarget().withResourceIDs("resource1"))
					.withReportType(ReportEnumeratedType.X_RESOURCE_STATUS.value())
					.withReadingType(ReadingTypeEnumeratedType.X_NOT_APPLICABLE.value())
					.withMarketContext(new MarketContext("http://MarketContext1"))
					.withOadrSamplingRate(new OadrSamplingRate()
						.withOadrMaxPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrMinPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrOnChange(false))
			);

		StringWriter writer = new StringWriter();
		marshaller20b.marshal(report, writer);

		System.out.println(writer.toString());
	}



	private OadrReport getMetadataTelemetryStatusReport() {
		XMLGregorianCalendar startDate = ISOUtils.newXMLGregorianCalendar();


		OadrReport report = new OadrReport()
			.withCreatedDateTime(new DateTime(startDate))
			.withDuration(new DurationPropType(new DurationValue(new Duration(60 * 60 * 1000).toStandardMinutes().toString())))
			.withReportRequestID("0")
			.withReportSpecifierID("MetadataTelemetryStatus_0")
			.withReportName(ReportNameEnumeratedType.METADATA_TELEMETRY_STATUS.value())
			.withOadrReportDescriptions(new OadrReportDescription()
					.withRID("my thermostat 1")
					.withReportSubject(new EiTarget().withEndDeviceAssets(new EndDeviceAsset("Thermostat")))
					.withReportDataSource(new EiTarget().withResourceIDs("resource1"))
					.withReportType(ReportEnumeratedType.X_RESOURCE_STATUS.value())
					.withReadingType(ReadingTypeEnumeratedType.X_NOT_APPLICABLE.value())
					.withMarketContext(new MarketContext("http://MarketContext1"))
					.withOadrSamplingRate(new OadrSamplingRate()
						.withOadrMaxPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrMinPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrOnChange(false))
			);



		return report;
	}

	private OadrReport getMetadataHistoryUsageReport() {
		XMLGregorianCalendar startDate = ISOUtils.newXMLGregorianCalendar();

		PowerRealType powerRealType = new PowerRealType()
			.withItemDescription("RealPower")
			.withItemUnits("W")
			.withSiScaleCode("none")
			.withPowerAttributes(new PowerAttributes()
					.withAc(true)
					.withHertz(new BigDecimal(0))
					.withVoltage(new BigDecimal(0))
			);

		JAXBElement<PowerRealType> powerReal = new net.ion.open.oadr2.model.v20b.power.ObjectFactory().createPowerReal(powerRealType);
		OadrReport report = new OadrReport()
			.withCreatedDateTime(new DateTime(startDate))
			.withDuration(new DurationPropType(new DurationValue(new Duration(60 * 60 * 1000).toStandardMinutes().toString())))
			.withReportRequestID("0")
			.withReportSpecifierID("MetadataHistoryUsage_0")
			.withReportName(ReportNameEnumeratedType.METADATA_HISTORY_USAGE.value())
			.withOadrReportDescriptions(new OadrReportDescription()
					.withRID("my meter 1")
					.withReportDataSource(new EiTarget().withResourceIDs("resource1"))
					.withReportType(ReportEnumeratedType.USAGE.value())
					.withItemBase(powerReal)
					.withReadingType(ReadingTypeEnumeratedType.X_NOT_APPLICABLE.value())
					.withMarketContext(new MarketContext("http://MarketContext1"))
					.withOadrSamplingRate(new OadrSamplingRate()
						.withOadrMaxPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrMinPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrOnChange(false))
			);



		return report;
	}

	private OadrReport getMetadataTelemetryUsageReport() {
		XMLGregorianCalendar startDate = ISOUtils.newXMLGregorianCalendar();


		PowerRealType powerRealType = new PowerRealType()
			.withItemDescription("RealPower")
			.withItemUnits("W")
			.withSiScaleCode("none")
			.withPowerAttributes(new PowerAttributes()
					.withAc(true)
					.withHertz(new BigDecimal(0))
					.withVoltage(new BigDecimal(0))
			);

		JAXBElement<PowerRealType> powerReal = new net.ion.open.oadr2.model.v20b.power.ObjectFactory().createPowerReal(powerRealType);
		OadrReport report = new OadrReport()
			.withCreatedDateTime(new DateTime(startDate))
			.withDuration(new DurationPropType(new DurationValue(new Duration(60 * 60 * 1000).toStandardMinutes().toString())))
			.withReportRequestID("0")
			.withReportSpecifierID("MetadataTelemetryUsage_0")
			.withReportName(ReportNameEnumeratedType.METADATA_TELEMETRY_USAGE.value())
			.withOadrReportDescriptions(new OadrReportDescription()
					.withRID("my meter 1")
					.withReportDataSource(new EiTarget().withResourceIDs("resource1"))
					.withReportType(ReportEnumeratedType.USAGE.value())
					.withItemBase(powerReal)
					.withReadingType(ReadingTypeEnumeratedType.DIRECT_READ.value())
					.withMarketContext(new MarketContext("http://MarketContext1"))
					.withOadrSamplingRate(new OadrSamplingRate()
						.withOadrMaxPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrMinPeriod(new DurationValue(new Duration(15 * 60 * 1000).toStandardMinutes().toString()))
						.withOadrOnChange(false))
			);



		return report;
	}

}
